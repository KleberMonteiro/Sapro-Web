package br.com.saproweb.sistema.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;

import br.com.saproweb.sistema.dominio.entidades.Curso;
import br.com.saproweb.sistema.dominio.entidades.Disciplina;
import br.com.saproweb.sistema.dominio.entidades.Grade;
import br.com.saproweb.sistema.dominio.entidades.Periodo;
import br.com.saproweb.sistema.dominio.service.CursoService;
import br.com.saproweb.sistema.dominio.service.DisciplinaService;
import br.com.saproweb.utils.enumeration.DiaEnum;
import br.com.saproweb.utils.enumeration.StatusEnum;

@Named
@Scope("request")
public class CursoController implements Serializable {

	private static final long serialVersionUID = 1L;

	private Logger logger = Logger.getLogger(DisciplinaController.class);

	@Inject
	@Named("cursoService")
	private CursoService cursoService;

	@Inject
	@Named("disciplinaService")
	private DisciplinaService disciplinaService;

	private Curso curso;
	private Grade grade;
	private Set<Grade> grades;

	private List<Disciplina> disciplinas;
	private List<String> dias;
	private List<String> periodosStr;
	private Map<String, String> periodosMap;
	private Map<String, Map<String, String>> gradeMap;

	private DiaEnum diasEnum;

	private static final int QTD_PERIODOS = 10;
	private static final int QTD_DISCIPLINAS_PERIODO = 7;

	@SuppressWarnings("unused")
	@PostConstruct
	private void init() {
		try {

			disciplinas = disciplinaService.buscarTodos();
			dias = new ArrayList<String>();
			for (int dia = 1; dia <= QTD_DISCIPLINAS_PERIODO; dia++) {
				dias.add(String.valueOf(dia));
			}

			gerarPeriodos();

			// Pega o parâmetro id da url
			HttpServletRequest request = (HttpServletRequest) FacesContext
					.getCurrentInstance().getExternalContext().getRequest();
			String idCurso = request.getParameter("id");

			if (idCurso != null) {
				long id = Long.parseLong(idCurso);
				curso = cursoService.buscarPorId(id);

				if (curso != null) {
					grades = curso.getGrades();

					if (grades.size() > 0) {
						for (Iterator<Grade> iterator = grades.iterator(); iterator
								.hasNext();) {
							Grade grade = iterator.next();

							if (grade.isAtual()) {
								this.grade = grade;
								break;
							}
						}

						montarGrade();
					}
				} else {
					novoRegistro();
				}
			} else {
				novoRegistro();
			}

		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	private void gerarPeriodos() {
		try {

			periodosStr = new ArrayList<String>();
			periodosMap = new HashMap<String, String>();

			for (int i = 1; i <= QTD_PERIODOS; i++) {
				String periodoStr = String.valueOf(i);
				periodosStr.add(periodoStr);
				periodosMap.put(periodoStr, periodoStr + "º Período");
			}

		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	private void montarGrade() {
		try {

			gradeMap = new HashMap<String, Map<String, String>>();
			Set<Periodo> periodos = grade.getPeriodos();

			for (String periodoStr1 : periodosStr) {
				boolean periodoAdicionado = false;

				for (Periodo periodo : periodos) {
					String periodoStr2 = periodo.getPeriodoStr();

					if (periodoStr1.equalsIgnoreCase(periodoStr2)) {
						Map<String, String> disciplinasMap = new HashMap<String, String>();
						List<Disciplina> listaDisciplinas = new ArrayList<Disciplina>(
								periodo.getDisciplinas());

						for (int index = 0; index < QTD_DISCIPLINAS_PERIODO; index++) {
							String indexStr = String.valueOf(index + 1);
							
							try {
								Disciplina disciplina = listaDisciplinas
										.get(index);
								String idDisciplina = String.valueOf(disciplina
										.getId());
								disciplinasMap.put(indexStr, idDisciplina);
							} catch (IndexOutOfBoundsException e) {
								disciplinasMap.put(indexStr, "");
							}
						}

						gradeMap.put(periodoStr1, disciplinasMap);
						periodoAdicionado = true;
					}
				}

				if (!periodoAdicionado) {
					gradeMap.put(periodoStr1, gerarDisciplinasMap());
				}
			}

		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public void novoRegistro() {
		logger.debug("Criando novo curso...");
		curso = new Curso();
		grade = new Grade();
		grades = new HashSet<Grade>();
		gerarGrade();
	}

	private void gerarGrade() {
		gradeMap = new HashMap<String, Map<String, String>>();

		for (int periodo = 1; periodo <= QTD_PERIODOS; periodo++) {
			gradeMap.put(String.valueOf(periodo), gerarDisciplinasMap());
		}
	}

	private Map<String, String> gerarDisciplinasMap() {
		Map<String, String> disciplinasMap = new HashMap<String, String>();

		for (int index = 1; index <= QTD_DISCIPLINAS_PERIODO; index++) {
			disciplinasMap.put(String.valueOf(index), "");
		}

		return disciplinasMap;
	}

	private Grade criarGrade() {
		try {

			Grade grade = new Grade();
			Set<Periodo> periodos = new HashSet<Periodo>();
			Set<Disciplina> disciplinas;

			for (Entry<String, Map<String, String>> entry : gradeMap.entrySet()) {
				String periodoStr = entry.getKey();
				Map<String, String> disciplinasMap = entry.getValue();
				Periodo periodo = new Periodo();
				disciplinas = new HashSet<Disciplina>();

				for (Entry<String, String> entry2 : disciplinasMap.entrySet()) {
					String disciplinaIdStr = entry2.getValue();

					if (!disciplinaIdStr.equals("")) {
						long disciplinaId = Long.parseLong(entry2.getValue());

						for (Disciplina disciplina : this.disciplinas) {
							if (disciplinaId == disciplina.getId()) {
								disciplinas.add(disciplina);
							}
						}
					}
				}
				
				if (!disciplinas.isEmpty()) {
					periodo.setPeriodoStr(periodoStr);
					periodo.setDisciplinas(disciplinas);
					periodos.add(periodo);
				}				
			}

			grade.setPeriodos(periodos);
			grade.setAtual(true);
			grade.setStatus(StatusEnum.ATIVO);

			return grade;

		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}
	}

	public void salvar() {
		try {

			if (!grades.isEmpty()) {
				for (Grade grade : grades) {
					if (grade.isAtual()) {
						grade.setAtual(false);
						grade.setStatus(StatusEnum.DELETADO);
					}
				}
			}

			Grade grade = criarGrade();
			grades.add(grade);
			curso.setGrades(grades);
			cursoService.salvar(curso);

		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public Map<String, Map<String, String>> getGradeMap() {
		return gradeMap;
	}

	public void setGradeMap(Map<String, Map<String, String>> gradeMap) {
		this.gradeMap = gradeMap;
	}

	public List<String> getPeriodosStr() {
		return periodosStr;
	}

	public void setPeriodosStr(List<String> periodosStr) {
		this.periodosStr = periodosStr;
	}

	public Map<String, String> getPeriodosMap() {
		return periodosMap;
	}

	public void setPeriodosMap(Map<String, String> periodosMap) {
		this.periodosMap = periodosMap;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public DiaEnum getDiasEnum() {
		return diasEnum;
	}

	public void setDiasEnum(DiaEnum diasEnum) {
		this.diasEnum = diasEnum;
	}

	public List<String> getDias() {
		return dias;
	}

	public void setDias(List<String> dias) {
		this.dias = dias;
	}

}
