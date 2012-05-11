package br.com.saproweb.sistema.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;

import br.com.saproweb.sistema.dominio.entidades.Dia;
import br.com.saproweb.sistema.dominio.entidades.Disciplina;
import br.com.saproweb.sistema.dominio.entidades.Professor;
import br.com.saproweb.sistema.dominio.entidades.QuadroDeHorarios;
import br.com.saproweb.sistema.dominio.entidades.Semana;
import br.com.saproweb.sistema.dominio.entidades.Turno;
import br.com.saproweb.sistema.dominio.service.DisciplinaService;
import br.com.saproweb.sistema.dominio.service.ProfessorService;
import br.com.saproweb.utils.enumeration.DiaEnum;
import br.com.saproweb.utils.enumeration.StatusEnum;

@Named
@Scope("request")
public class ProfessorController implements Serializable {

	private static final long serialVersionUID = 1L;

	private Logger logger = Logger.getLogger(ProfessorController.class);

	@Inject
	@Named("professorService")
	private ProfessorService professorService;

	@Inject
	@Named("disciplinaService")
	private DisciplinaService disciplinaService;

	private Professor professor;
	private List<Professor> professores;
	private List<Disciplina> disciplinas;
	private List<Disciplina> disciplinasSelecionadas;

	@SuppressWarnings("unused")
	@PostConstruct
	private void init() {

		logger.debug("Carregando listas de professores e disciplinas");
		professores = professorService.buscarTodos();
		disciplinas = disciplinaService.buscarTodos();

	}

	public void gerarProfessor() {
		professor = new Professor();
		professor.setDisciplinas(disciplinasSelecionadas);
		professor.setQuadroDeHorarios(gerarQuadroDeHorarios());
	}

	private QuadroDeHorarios gerarQuadroDeHorarios() {
		QuadroDeHorarios quadroDeHorarios = new QuadroDeHorarios();
		quadroDeHorarios.setSemana(gerarSemana());
		
		return quadroDeHorarios;
	}

	private Semana gerarSemana() {
		Semana semana = new Semana();
		semana.setDias(gerarDias());
		
		return semana;
	}
	
	private List<Dia> gerarDias(){
		List<Dia> dias = new ArrayList<Dia>();
		
		for (int i = 0; i < DiaEnum.values().length; i++) {
			Dia dia = new Dia();			
			dia.setDia(DiaEnum.values()[i]);
			dia.setTurnos(gerarTurnos());
			dia.setStatus(StatusEnum.ATIVO);
		}
		
		return dias;		
	}
	
	private List<Turno> gerarTurnos() {
		List<Turno> turnos = new ArrayList<Turno>();
		
		return turnos;
	}

	// Gets e Sets
	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public List<Professor> getProfessores() {
		return professores;
	}

	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

}
