package br.com.saproweb.sistema.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;

import br.com.saproweb.sistema.dominio.entidades.Disciplina;
import br.com.saproweb.sistema.dominio.entidades.Professor;
import br.com.saproweb.sistema.dominio.service.DisciplinaService;
import br.com.saproweb.sistema.dominio.service.ProfessorService;
import br.com.saproweb.utils.GeradorProfessor;

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

	@SuppressWarnings("unused")
	@PostConstruct
	private void init() {

		logger.debug("Carregando listas de professores e disciplinas");
		professores = professorService.buscarTodos();
		disciplinas = disciplinaService.buscarTodos();

	}
	
	public void criarProfessor(){
		professor = GeradorProfessor.gerar();
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
