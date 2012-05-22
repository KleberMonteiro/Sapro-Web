package br.com.saproweb.sistema.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.primefaces.model.DualListModel;
import org.springframework.context.annotation.Scope;

import br.com.saproweb.sistema.dominio.entidades.Dia;
import br.com.saproweb.sistema.dominio.entidades.Disciplina;
import br.com.saproweb.sistema.dominio.entidades.Professor;
import br.com.saproweb.sistema.dominio.entidades.QuadroDeHorarios;
import br.com.saproweb.sistema.dominio.entidades.Semana;
import br.com.saproweb.sistema.dominio.entidades.Turno;
import br.com.saproweb.sistema.dominio.service.DisciplinaService;
import br.com.saproweb.sistema.dominio.service.ProfessorService;
import br.com.saproweb.utils.datamodel.ProfessoresDataModel;
import br.com.saproweb.utils.enumeration.DiaEnum;
import br.com.saproweb.utils.enumeration.StatusEnum;
import br.com.saproweb.utils.enumeration.TurnoEnum;

@Named
@Scope("session")
public class ProfessorController implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger
			.getLogger(ProfessorController.class);

	@Inject
	@Named("professorService")
	private ProfessorService professorService;

	@Inject
	@Named("disciplinaService")
	private DisciplinaService disciplinaService;

	private Professor professor;
	private Professor[] professoresSelecionados;
	private List<Professor> professores;
	private List<Disciplina> disciplinasSource;
	private List<Disciplina> disciplinasTarget;
	private DualListModel<Disciplina> disciplinas;
	private ProfessoresDataModel professoresDataModel;

	@SuppressWarnings("unused")
	@PostConstruct
	private void init() {
		try {
			
			carregarPagina();

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getClass() + ":" + e.getMessage());
		}
	}

	public void carregarPagina() {
		try {
			
			professor = new Professor();
			carregarProfessores();
			carregarDisciplinas();

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getClass() + ":" + e.getMessage());
		}
	}

	private void carregarProfessores() {
		try {

			logger.debug("Carregando professores...");
			
			professores = professorService.buscarTodos();
			professoresSelecionados = new Professor[professores.size()];
			professoresDataModel = new ProfessoresDataModel(professores);

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getClass() + ":" + e.getMessage());
		}
	}

	private void carregarDisciplinas() {
		try {

			logger.debug("Carregando disciplinas...");
			
			disciplinasSource = disciplinaService.buscarTodos();
			disciplinasTarget = new ArrayList<Disciplina>();

			if (professor != null && professor.getId() != 0) {
				professor = professorService.buscarPorId(professor.getId());

				if (professor != null) {
					List<Disciplina> disciplinasProfessor = new ArrayList<Disciplina>(
							professor.getDisciplinas());

					if (disciplinasProfessor.size() > 0) {
						for (Disciplina disciplinaSource : disciplinasSource) {
							for (Disciplina disciplina : disciplinasProfessor) {
								if (disciplina.getId() == disciplinaSource
										.getId())
									disciplinasTarget.add(disciplinaSource);
							}
						}
					}
				} else {
					gerarProfessor();
				}
			} else {
				gerarProfessor();
			}

			disciplinas = new DualListModel<Disciplina>(disciplinasSource,
					disciplinasTarget);

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getClass() + ":" + e.getMessage());
		}
	}

	public void gerarProfessor() {
		try {

			logger.debug("Criando novo professor...");
			
			professor.setQuadroDeHorarios(gerarQuadroDeHorarios());
			
			logger.debug("Professor criado!");

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getClass() + ":" + e.getMessage());
		}
	}

	private QuadroDeHorarios gerarQuadroDeHorarios() {
		try {

			logger.debug("Gerando novo Quadro de horarios...");
			
			QuadroDeHorarios quadroDeHorarios = new QuadroDeHorarios();
			quadroDeHorarios.setSemana(gerarSemana());

			return quadroDeHorarios;

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getClass() + ":" + e.getMessage());
			return null;
		}
	}

	private Semana gerarSemana() {
		try {
			
			logger.debug("Gerando semana...");
			
			Semana semana = new Semana();
			semana.setDias(gerarDias());

			return semana;

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getClass() + ":" + e.getMessage());
			return null;
		}
	}

	private Set<Dia> gerarDias() {
		try {

			logger.debug("Gerando dias...");
			
			Set<Dia> dias = new HashSet<Dia>();

			for (int i = 0; i < DiaEnum.values().length; i++) {
				Dia dia = new Dia();
				dia.setDia(DiaEnum.values()[i]);
				logger.debug("Gerando turnos de " + DiaEnum.values()[i] + " ...");
				dia.setTurnos(gerarTurnos());
				dia.setStatus(StatusEnum.ATIVO);
				dias.add(dia);
			}

			return dias;

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getClass() + ":" + e.getMessage());
			return null;
		}
	}

	private Set<Turno> gerarTurnos() {
		try {
			
			Set<Turno> turnos = new HashSet<Turno>();

			for (int i = 0; i < TurnoEnum.values().length; i++) {
				Turno turno = new Turno();
				turno.setTurno(TurnoEnum.values()[i]);
				turno.setHorario1(false);
				turno.setHorario2(false);
				turnos.add(turno);
			}

			return turnos;

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getClass() + ":" + e.getMessage());
			return null;
		}
	}

	public void salvar() {
		try {			
			if (!professor.getNome().isEmpty()) {			
				logger.debug("Salvando...");
	
				professor.setDisciplinas(new HashSet<Disciplina>(disciplinas
						.getTarget()));
				professorService.salvar(professor);
				
				carregarPagina();
				
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Professor salvo com sucesso!"));
	
				logger.debug("Registro salvo/atualizado com sucesso...");								
			} else {				
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Favor informar o nome do profesor."));				
			}
		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getClass() + ":" + e.getMessage());
		}
	}

	public void excluir() {
		try {
			
			logger.debug("Excluindo...");

			int qtdeProfesoresSelecionadas = professoresSelecionados.length;
			
			if (qtdeProfesoresSelecionadas > 0) {
				for (int i = 0; i < qtdeProfesoresSelecionadas; i++) {
					Professor professor = professoresSelecionados[i];
					professorService.excluir(professor);
					logger.debug("Professor: '" + professor.getNome() + "' excluído com sucesso!");
				}
				
				if (qtdeProfesoresSelecionadas == 1) {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage("Professor removido com sucesso!"));
				} else if (qtdeProfesoresSelecionadas > 1) {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage("Professores removidos com sucesso!"));
				}
				
				carregarProfessores();
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Por favor selecione ao menos um profesor!"));
			}
			

			

			

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getClass() + ":" + e.getMessage());
		}
	}

	public void editar(ActionEvent actionEvent) {
		try {

			logger.debug("Capturando dados do professor...");
			
			professor = (Professor) actionEvent.getComponent().getAttributes()
					.get("professor");
			disciplinasTarget = new ArrayList<Disciplina>(
					professor.getDisciplinas());
			carregarDisciplinas();

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getClass() + ":" + e.getMessage());
		}
	}

	// public void testar() {
	// Professor prof = professores.get(0);
	// System.out.println(prof.getNome());
	// System.out.println(prof.getMatricula());
	//
	// for (Dia dia : prof.getQuadroDeHorarios().getSemana().getDias()) {
	// System.out.println("----------- " + dia.getDia() + " -----------");
	// for (Turno turno : dia.getTurnos()) {
	// System.out.print(turno.getTurno());
	// System.out.println(" ( " + turno.isHorario1() + " , "
	// + turno.isHorario2() + " ) ");
	// }
	// System.out.println("");
	// }
	// }

	// Gets e Sets
	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Professor[] getProfessoresSelecionados() {
		return professoresSelecionados;
	}

	public void setProfessoresSelecionados(Professor[] professoresSelecionados) {
		this.professoresSelecionados = professoresSelecionados;
	}

	public List<Professor> getProfessores() {
		return professores;
	}

	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}

	public DualListModel<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(DualListModel<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public ProfessoresDataModel getProfessoresDataModel() {
		return professoresDataModel;
	}

	public void setProfessoresDataModel(
			ProfessoresDataModel professoresDataModel) {
		this.professoresDataModel = professoresDataModel;
	}
}
