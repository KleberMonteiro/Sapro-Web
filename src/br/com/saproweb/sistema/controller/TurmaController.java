package br.com.saproweb.sistema.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;

import br.com.saproweb.sistema.dominio.entidades.Cadeira;
import br.com.saproweb.sistema.dominio.entidades.Curso;
import br.com.saproweb.sistema.dominio.entidades.Dia;
import br.com.saproweb.sistema.dominio.entidades.Disciplina;
import br.com.saproweb.sistema.dominio.entidades.Grade;
import br.com.saproweb.sistema.dominio.entidades.Periodo;
import br.com.saproweb.sistema.dominio.entidades.Professor;
import br.com.saproweb.sistema.dominio.entidades.Turno;
import br.com.saproweb.sistema.dominio.service.CursoService;
import br.com.saproweb.sistema.dominio.service.ProfessorService;
import br.com.saproweb.utils.comparator.PrioridadeComparator;
import br.com.saproweb.utils.enumeration.DiaEnum;
import br.com.saproweb.utils.enumeration.TurnoEnum;

@Named
@Scope("session")
public class TurmaController implements Serializable {

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(TurmaController.class);

	@Inject
	@Named("cursoService")
	private CursoService cursoService;

	@Inject
	@Named("professorService")
	private ProfessorService professorService;

	private Curso curso;
	private Curso cursoSelecionado;
	private List<Curso> cursos;

	private Periodo periodo;
	private String periodoSelecionado;
	private List<Periodo> periodos;
	private List<String> periodosStr;

	private List<String> turnos;
	private String turnoSelecionado;
	private TurnoEnum turno;

	private List<Disciplina> disciplinas;
	private List<Cadeira> cadeiras;
	private List<Professor> professores;

	Map<Disciplina, List<Professor>> professoresPorDisciplinaMap;

	@SuppressWarnings("unused")
	@PostConstruct
	private void init() {
		try {

			carregarPagina();
			periodosStr = new ArrayList<String>();

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getClass() + ":" + e.getMessage());
		}
	}

	public void novoRegistro() {
		try {

			curso = null;
			periodo = null;
			turnoSelecionado = "";
			carregarPagina();
			carregarPeriodos();
			carregarDisicplinas();

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getClass() + ":" + e.getMessage());
		}
	}

	public void carregarPagina() {
		try {

			carregarCursos();
			carregarTurnos();

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getClass() + ":" + e.getMessage());
		}
	}

	private void carregarCursos() {
		try {

			cursoSelecionado = new Curso();
			cursos = cursoService.buscarTodos();

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getClass() + ":" + e.getMessage());
		}
	}

	private void carregarTurnos() {
		try {

			turnos = new ArrayList<String>();
			turnos.add(TurnoEnum.MANHA.getLabel());
			turnos.add(TurnoEnum.TARDE.getLabel());
			turnos.add(TurnoEnum.NOITE.getLabel());

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getClass() + ":" + e.getMessage());
		}
	}

	private void carregarTurnoSelecionado() {
		try {

			for (TurnoEnum turno : TurnoEnum.values()) {
				if (turno.getLabel().equalsIgnoreCase(turnoSelecionado))
					this.turno = turno;
			}

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getClass() + ":" + e.getMessage());
		}
	}

	public void carregarPeriodos() {
		try {

			carregarCursoSelecionado();

			if (curso != null) {
				Grade grade = null;
				Set<Grade> grades = curso.getGrades();

				for (Grade gradeAtual : grades) {
					if (gradeAtual.isAtual())
						grade = gradeAtual;
				}

				periodos = new ArrayList<Periodo>(grade.getPeriodos());
				carregarPeriodosStr(periodos);
			} else {
				periodos = new ArrayList<Periodo>();
				carregarPeriodosStr(periodos);
			}

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getClass() + ":" + e.getMessage());
		}
	}

	private void carregarCursoSelecionado() {
		try {

			boolean selecionado = false;

			for (Curso curso : cursos) {
				if (curso.getId() == cursoSelecionado.getId()) {
					selecionado = true;
					this.curso = curso;
				}
			}
			if (!selecionado)
				this.curso = null;

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getClass() + ":" + e.getMessage());
		}
	}

	public void carregarPeriodosStr(List<Periodo> periodos) {
		try {

			periodosStr.clear();

			if (periodos.size() > 0) {
				for (int i = 1; i <= periodos.size(); i++) {
					String periodoStr = String.valueOf(i);
					periodosStr.add(periodoStr);
				}
			}

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getClass() + ":" + e.getMessage());
		}
	}

	public void alocarProfessores() {
		try {

			if (curso != null) {
				if (!periodoSelecionado.isEmpty()) {
					if (!turnoSelecionado.isEmpty()) {

						carregarTurnoSelecionado();
						carregarDisicplinas();
						carregarProfessores();
						agruparProfessoresPorDisciplinas();
						carregarCadeiras();

					} else {
						FacesContext.getCurrentInstance().addMessage(
								null,
								new FacesMessage(FacesMessage.SEVERITY_WARN,
										"Favor selecionar o turno!", ""));
					}
				} else {
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_WARN,
									"Favor selecionar o periodo!", ""));
				}
			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Favor selecionar o curso!", ""));
			}

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getClass() + ":" + e.getMessage());
		}
	}

	private void carregarDisicplinas() {
		try {

			carregarPeriodoSelecionado();

			if (periodo != null)
				disciplinas = new ArrayList<Disciplina>(
						periodo.getDisciplinas());
			else
				disciplinas = new ArrayList<Disciplina>();

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getClass() + ":" + e.getMessage());
		}
	}

	private void carregarPeriodoSelecionado() {
		try {

			for (Periodo periodoSelecionado : periodos) {
				if (periodoSelecionado.getPeriodoStr().equals(
						this.periodoSelecionado))
					periodo = periodoSelecionado;
			}

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getClass() + ":" + e.getMessage());
		}
	}

	private void carregarProfessores() {
		try {

			professores = professorService.buscarTodos();

			List<Professor> professoresTemp = new ArrayList<Professor>();

			for (Professor professor : professores) {
				for (Disciplina disciplina : disciplinas) {
					for (Disciplina disciplinaProfessor : professor
							.getDisciplinas()) {
						if (disciplina.getId() == disciplinaProfessor.getId()
								&& !professoresTemp.contains(professor))
							professoresTemp.add(professor);
					}
				}
			}

			professores = professoresTemp;

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getClass() + ":" + e.getMessage());
		}
	}

	private void agruparProfessoresPorDisciplinas() {
		try {

			professoresPorDisciplinaMap = new HashMap<Disciplina, List<Professor>>();

			for (Disciplina disciplina : disciplinas) {
				List<Professor> professores = new ArrayList<Professor>();

				for (Professor professor : this.professores) {
					for (Disciplina disciplinaProfessor : professor
							.getDisciplinas()) {
						if (disciplina.getId() == disciplinaProfessor.getId())
							professores.add(professor);
					}
				}

				professoresPorDisciplinaMap.put(disciplina, professores);
			}

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getClass() + ":" + e.getMessage());
		}
	}

	private void carregarCadeiras() {
		try {

			Cadeira cadeira = null;
			cadeiras = new ArrayList<Cadeira>();
			List<Professor> professoresAlocados = new ArrayList<Professor>();

			for (Entry<Disciplina, List<Professor>> entry : professoresPorDisciplinaMap
					.entrySet()) {
				Disciplina disciplina = entry.getKey();
				List<Professor> professores = entry.getValue();

				if (professores.size() == 0) {
					cadeira = new Cadeira();
					cadeira.setDisciplina(disciplina);
					cadeiras.add(cadeira);
				} else if (professores.size() == 1) {
					cadeira = new Cadeira();
					cadeira.setDisciplina(disciplina);
					cadeira.setProfessor(professores.get(0));
					cadeiras.add(cadeira);

					if (!professoresAlocados.contains(professores.get(0)))
						professoresAlocados.add(professores.get(0));

				} else {
					for (Professor professor : professores) {
						List<Dia> dias = professor.getQuadroDeHorarios()
								.getSemana().getListaDias();
						int prioridade = 7;

						for (Dia dia : dias) {
							for (Turno turno : dia.getTurnos()) {
								if (turno.getTurno() == this.turno) {
									if (turno.isDisponivel())
										prioridade--;
								}
							}
						}

						professor.setPrioridade(prioridade);
					}

					Collections.sort(professores, new PrioridadeComparator());

					cadeira = new Cadeira();
					cadeira.setDisciplina(disciplina);

					for (Professor professor : professores) {
						if (!professoresAlocados.contains(professor)) {
							cadeira.setProfessor(professor);
							professoresAlocados.add(professor);
							break;
						}
					}

					cadeiras.add(cadeira);
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getClass() + ":" + e.getMessage());
		}
	}

	private void atribuirDias() {
		try {

			List<DiaEnum> diasEnum = new ArrayList<DiaEnum>();
			for (DiaEnum diaEnum : DiaEnum.values()) {
				diasEnum.add(diaEnum);
			}

			for (Cadeira cadeira : cadeiras) {
				List<Dia> dias = cadeira.getProfessor().getQuadroDeHorarios()
						.getSemana().getListaDias();

				for (DiaEnum diaEnum : diasEnum) {
					for (Dia dia : dias) {
						if (diaEnum == dia.getDia()) {
							for(Turno turno : dia.getListaTurnos()){
								if(turno.isDisponivel()){
									cadeira.setDia(diaEnum);
								}
							}
						}
					}
				}
			}

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getClass() + ":" + e.getMessage());
		}
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Curso getCursoSelecionado() {
		return cursoSelecionado;
	}

	public void setCursoSelecionado(Curso cursoSelecionado) {
		this.cursoSelecionado = cursoSelecionado;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public List<String> getPeriodosStr() {
		return periodosStr;
	}

	public void setPeriodosStr(List<String> periodosStr) {
		this.periodosStr = periodosStr;
	}

	public String getPeriodoSelecionado() {
		return periodoSelecionado;
	}

	public void setPeriodoSelecionado(String periodoSelecionado) {
		this.periodoSelecionado = periodoSelecionado;
	}

	public List<String> getTurnos() {
		return turnos;
	}

	public void setTurnos(List<String> turnos) {
		this.turnos = turnos;
	}

	public String getTurnoSelecionado() {
		return turnoSelecionado;
	}

	public void setTurnoSelecionado(String turnoSelecionado) {
		this.turnoSelecionado = turnoSelecionado;
	}

	public List<Cadeira> getCadeiras() {
		return cadeiras;
	}

	public void setCadeiras(List<Cadeira> cadeiras) {
		this.cadeiras = cadeiras;
	}

	public List<Professor> getProfessores() {
		return professores;
	}

	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}

	public Map<Disciplina, List<Professor>> getProfessoresPorDisciplinaMap() {
		return professoresPorDisciplinaMap;
	}

	public void setProfessoresPorDisciplinaMap(
			Map<Disciplina, List<Professor>> professoresPorDisciplinaMap) {
		this.professoresPorDisciplinaMap = professoresPorDisciplinaMap;
	}

}
