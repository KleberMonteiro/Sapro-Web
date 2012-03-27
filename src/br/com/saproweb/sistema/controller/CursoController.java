package br.com.saproweb.sistema.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;

import br.com.saproweb.sistema.dominio.entidades.Curso;
import br.com.saproweb.sistema.dominio.entidades.DiaDisciplina;
import br.com.saproweb.sistema.dominio.entidades.Disciplina;
import br.com.saproweb.sistema.dominio.entidades.Grade;
import br.com.saproweb.sistema.dominio.entidades.Periodo;
import br.com.saproweb.sistema.dominio.service.CursoService;
import br.com.saproweb.sistema.dominio.service.DisciplinaService;
import br.com.saproweb.utils.enumeration.DiaEnum;

@Named
@Scope("session")
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
	
	private List<Disciplina> disciplinas;
	
	private List<String> periodos;
	private Map<String, String> periodosMap;	
	private Map<String, Map<String, String>> gradeMap;
	
	private DiaEnum diasEnum;

	@SuppressWarnings("unused")
	@PostConstruct
	private void init() {		
		disciplinas = disciplinaService.buscarTodos();		
		novoCurso();
		gerarPeriodos();
		gerarGrade();		
	}
	
	public void novoCurso() {
		logger.debug("Criando novo curso...");
		curso = new Curso();
		grade = new Grade();
	}
	
	private void gerarPeriodos() {
		periodos = new ArrayList<String>();
		periodos.add("1");
		periodos.add("2");
		periodos.add("3");
		periodos.add("4");
		periodos.add("5");
		periodos.add("6");
		periodos.add("7");
		periodos.add("8");
		periodos.add("9");
		periodos.add("10");
		
		periodosMap = new HashMap<String, String>();
		periodosMap.put("1", "1º Período");
		periodosMap.put("2", "2º Período");
		periodosMap.put("3", "3º Período");
		periodosMap.put("4", "4º Período");
		periodosMap.put("5", "5º Período");
		periodosMap.put("6", "6º Período");
		periodosMap.put("7", "7º Período");
		periodosMap.put("8", "8º Período");
		periodosMap.put("9", "9º Período");
		periodosMap.put("10", "10º Período");
	}
	
	private void gerarGrade() {
		gradeMap = new HashMap<String, Map<String, String>>();
		gradeMap.put("1", gerarSemana());				
		gradeMap.put("2", gerarSemana());				
		gradeMap.put("3", gerarSemana());				
		gradeMap.put("4", gerarSemana());				
		gradeMap.put("5", gerarSemana());				
		gradeMap.put("6", gerarSemana());				
		gradeMap.put("7", gerarSemana());				
		gradeMap.put("8", gerarSemana());				
		gradeMap.put("9", gerarSemana());				
		gradeMap.put("10", gerarSemana());				
	}
	
	private Map<String, String> gerarSemana() {
		Map<String,String> semana = new HashMap<String, String>();		
		semana.put("SEGUNDA", "");
		semana.put("TERCA", "");
		semana.put("QUARTA", "");
		semana.put("QUINTA", "");
		semana.put("SEXTA", "");
		semana.put("SABADO", "");
		semana.put("DOMINGO", "");
		return semana;
	}
	
	private Grade criarGrade() {		
		List<Periodo> listaPeriodos = new ArrayList<Periodo>();		
		
		for (Iterator<String> iterator = periodos.iterator(); iterator.hasNext();) {
			String periodoStr = iterator.next();
			Map<String, String> disciplinaPorDiaMap = gradeMap.get(periodoStr);
			
			String idDisciplinaSegunda = disciplinaPorDiaMap.get("SEGUNDA");
			String idDisciplinaTerca = disciplinaPorDiaMap.get("TERCA");
			String idDisciplinaQuarta = disciplinaPorDiaMap.get("QUARTA");
			String idDisciplinaQuinta = disciplinaPorDiaMap.get("QUINTA");
			String idDisciplinaSexta = disciplinaPorDiaMap.get("SEXTA");
			String idDisciplinaSabado = disciplinaPorDiaMap.get("SABADO");
			String idDisciplinaDomingo = disciplinaPorDiaMap.get("DOMINGO");
			
			List<DiaDisciplina> listaDias = new ArrayList<DiaDisciplina>();
			
			for (Iterator<Disciplina> iterator2 = disciplinas.iterator(); iterator2.hasNext();) {
				Disciplina disciplina = iterator2.next();
				String id = String.valueOf(disciplina.getId());				
				
				DiaDisciplina dia = new DiaDisciplina();				
				
				if(id.equals(idDisciplinaSegunda)){					
					dia.setDisciplina(disciplina);
					dia.setDia(DiaEnum.SEGUNDA);
					listaDias.add(dia);
				}
				else if(id.equals(idDisciplinaTerca)){
					dia.setDisciplina(disciplina);
					dia.setDia(DiaEnum.TERCA);
					listaDias.add(dia);
				}
				else if(id.equals(idDisciplinaQuarta)){
					dia.setDisciplina(disciplina);
					dia.setDia(DiaEnum.QUARTA);
					listaDias.add(dia);
				}
				else if(id.equals(idDisciplinaQuinta)){
					dia.setDisciplina(disciplina);
					dia.setDia(DiaEnum.QUINTA);
					listaDias.add(dia);
				}
				else if(id.equals(idDisciplinaSexta)){
					dia.setDisciplina(disciplina);
					dia.setDia(DiaEnum.SEXTA);
					listaDias.add(dia);
				}
				else if(id.equals(idDisciplinaSabado)){
					dia.setDisciplina(disciplina);
					dia.setDia(DiaEnum.SABADO);
					listaDias.add(dia);
				}
				else if(id.equals(idDisciplinaDomingo)){
					dia.setDisciplina(disciplina);
					dia.setDia(DiaEnum.DOMINGO);
					listaDias.add(dia);
				}				
			}		
			
			if (listaDias.size() > 0) {
				Periodo periodo = new Periodo();
				periodo.setPeriodo(periodoStr);
				periodo.setDias(listaDias);
				listaPeriodos.add(periodo);	
			}			
					
		}
		
		grade.setPeriodos(listaPeriodos);
		
		return grade;
	}
	
	public void salvar() {		
		try {
			
			criarGrade();
			curso.setGrade(grade);			
			cursoService.salvar(curso);
			
//			Curso curso = cursoService.buscarPorId(2L);
//			cursoService.excluir(curso);

		
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

	public List<String> getPeriodos() {
		return periodos;
	}

	public void setPeriodos(List<String> periodos) {
		this.periodos = periodos;
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

}
