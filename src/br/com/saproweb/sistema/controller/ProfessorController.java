package br.com.saproweb.sistema.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import br.com.saproweb.utils.enumeration.TurnoEnum;

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

		disciplinasSelecionadas = new ArrayList<Disciplina>();
		disciplinasSelecionadas.add(disciplinas.get(0));
		disciplinasSelecionadas.add(disciplinas.get(1));
		
		gerarProfessor();		
		
	}	

	public void gerarProfessor() {
		professor = new Professor();
		professor.setDisciplinas(new HashSet<Disciplina>(disciplinasSelecionadas));
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
	
	private Set<Dia> gerarDias(){
		Set<Dia> dias = new HashSet<Dia>();
		
		for (int i = 0; i < DiaEnum.values().length; i++) {
			Dia dia = new Dia();			
			dia.setDia(DiaEnum.values()[i]);
			dia.setTurnos(gerarTurnos());
			dia.setStatus(StatusEnum.ATIVO);
			dias.add(dia);
		}
		
		return dias;		
	}
	
	private Set<Turno> gerarTurnos() {
		Set<Turno> turnos = new HashSet<Turno>();
		
		for (int i = 0; i < TurnoEnum.values().length; i++) {
			Turno turno = new Turno();
			turno.setTurno(TurnoEnum.values()[i]);
			turno.setHorario1(false);
			turno.setHorario2(false);
			turnos.add(turno);
		}
		
		return turnos;
	}
	
	public void salvar(){
		try {
			
			professor.setNome("Kléber");
			professor.setMatricula("0001");
			professorService.salvar(professor);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testar(){
		Professor prof = professores.get(0);
		System.out.println(prof.getNome());
		System.out.println(prof.getMatricula());
		
		for (Dia dia : prof.getQuadroDeHorarios().getSemana().getDias()) {
			System.out.println("----------- " + dia.getDia() + " -----------");
			for(Turno turno : dia.getTurnos()){
				System.out.print(turno.getTurno());
				System.out.println(" ( " + turno.isHorario1() + " , " + turno.isHorario2() + " ) ");
			}
			System.out.println("");
		}		
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
