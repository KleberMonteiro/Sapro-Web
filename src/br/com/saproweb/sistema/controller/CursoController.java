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
import br.com.saproweb.sistema.dominio.entidades.Disciplina;
import br.com.saproweb.sistema.dominio.entidades.Grade;
import br.com.saproweb.sistema.dominio.entidades.Periodo;
import br.com.saproweb.sistema.dominio.service.CursoService;
import br.com.saproweb.sistema.dominio.service.DisciplinaService;

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
	Grade grade = new Grade();
	
	private List<Disciplina> disciplinas;
	
	private List<String> periodos;
	private Map<String, String> periodosMap;	
	private Map<String, Map<String, String>> gradeMap;	

	@SuppressWarnings("unused")
	@PostConstruct
	private void init() {
		curso = new Curso();
		disciplinas = disciplinaService.buscarTodos();		
		gerarPeriodos();
		gerarGrade();		
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
		gradeMap = new HashMap<String, Map<String,String>>();
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
	
	private Map<String,String> gerarSemana() {
		Map<String,String> semana = new HashMap<String, String>();		
		semana.put("segunda", "");
		semana.put("terca", "");
		semana.put("quarta", "");
		semana.put("quinta", "");
		semana.put("sexta", "");
		semana.put("sabado", "");
		semana.put("domingo", "");
		return semana;
	}
	
	private Grade criarGrade() {		
		List<Periodo> listaPeriodos = new ArrayList<Periodo>();
		
		for (Iterator<String> iterator = periodos.iterator(); iterator.hasNext();) {
			String periodoStr = iterator.next();
			Map<String, String> disciplinaPorDiaMap = gradeMap.get(periodoStr);
			String disciplinaSegunda = disciplinaPorDiaMap.get("segunda");
			String disciplinaTerca = disciplinaPorDiaMap.get("terca");
			String disciplinaQuarta = disciplinaPorDiaMap.get("quarta");
			String disciplinaQuinta = disciplinaPorDiaMap.get("quinta");
			String disciplinaSexta = disciplinaPorDiaMap.get("sexta");
			String disciplinaSabado = disciplinaPorDiaMap.get("sabado");
			String disciplinaDomingo = disciplinaPorDiaMap.get("domingo");
			Periodo periodo = new Periodo();
			
			for (Iterator<Disciplina> iterator2 = disciplinas.iterator(); iterator2.hasNext();) {
				Disciplina disciplina = iterator2.next();
				String id = String.valueOf(disciplina.getId());
				
				if(id.equals(disciplinaSegunda)){
					periodo.setDisciplinaSegunda(disciplina);
				}else if(id.equals(disciplinaTerca)){
					periodo.setDisciplinaTerca(disciplina);
				}else if(id.equals(disciplinaQuarta)){
					periodo.setDisciplinaQuarta(disciplina);
				}else if(id.equals(disciplinaQuinta)){
					periodo.setDisciplinaQuinta(disciplina);
				}else if(id.equals(disciplinaSexta)){
					periodo.setDisciplinaSexta(disciplina);
				}else if(id.equals(disciplinaSabado)){
					periodo.setDisciplinaDomingo(disciplina);
				}else if(id.equals(disciplinaDomingo)){
					periodo.setDisciplinaDomingo(disciplina);
				}				
			}
			
			listaPeriodos.add(periodo);			
		}
		
		grade.setPeriodos(listaPeriodos);
		
		return grade;
	}
	
	public void salvar() {
		criarGrade();
		System.out.println(grade.getPeriodos().get(0).getDisciplinaSegunda().getNome());
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

}
