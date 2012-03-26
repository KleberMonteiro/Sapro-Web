package br.com.saproweb.sistema.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;

import br.com.saproweb.sistema.dominio.entidades.Disciplina;
import br.com.saproweb.sistema.dominio.service.DisciplinaService;
import br.com.saproweb.utils.datamodel.DisciplinasDataModel;

@Named
@Scope("session")
public class DisciplinaController implements Serializable {

	private static final long serialVersionUID = 1L;

	private Logger logger = Logger.getLogger(DisciplinaController.class);

	@Inject
	@Named("disciplinaService")
	private DisciplinaService disciplinaService;

	private Disciplina disciplina;
	private Disciplina disciplinaSelecionada;
	private List<Disciplina> disciplinas;
	private Disciplina[] disciplinasSelecionadas;
	private DisciplinasDataModel disciplinasDataModel;

	@SuppressWarnings("unused")
	@PostConstruct
	private void init() {
		disciplinaSelecionada = new Disciplina();
		criar();
		carregarListas();
	}

	private void carregarListas() {
		disciplinas = disciplinaService.buscarTodos();
		disciplinasDataModel = new DisciplinasDataModel(disciplinas);
		disciplinasSelecionadas = new Disciplina[disciplinas.size()];
	}

	public void criar() {
		disciplina = new Disciplina();
	}

	public void salvar() {
		try {

			logger.debug("Salvando disciplina...");
			disciplinaService.salvar(disciplina);
			carregarListas();

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Disciplina adicionada com sucesso!"));

		} catch (Exception e) {
			logger.error(e.getClass() + ":" + e.getMessage());
			e.printStackTrace();
		}
	}

	public void atualizar() {
		try {

			logger.debug("Atualizando disciplina...");
			disciplinaService.atualizar(disciplina);

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Disciplina atualizada com sucesso!"));

		} catch (Throwable e) {
			logger.error(e.getClass() + ":" + e.getMessage());
			e.printStackTrace();
		}
	}

	public void excluir() {
		try {

			logger.debug("Excluindo disciplinas...");

			if (disciplinasSelecionadas.length > 0) {
				for (int i = 0; i < disciplinasSelecionadas.length; i++) {
					Disciplina disciplina = disciplinasSelecionadas[i];
					disciplinaService.excluir(disciplina);
				}

				carregarListas();
				criar();

				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(
								"Disciplina(s) removida(s) com sucesso!"));
			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Nenhuma disciplina foi selecionada!", ""));
			}

		} catch (Throwable e) {
			logger.error(e.getClass() + ":" + e.getMessage());
			e.printStackTrace();
		}
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public Disciplina[] getDisciplinasSelecionadas() {
		return disciplinasSelecionadas;
	}

	public void setDisciplinasSelecionadas(Disciplina[] disciplinasSelecionadas) {
		this.disciplinasSelecionadas = disciplinasSelecionadas;
	}

	public DisciplinasDataModel getDisciplinasDataModel() {
		return disciplinasDataModel;
	}

	public Disciplina getDisciplinaSelecionada() {
		return disciplinaSelecionada;
	}

	public void setDisciplinaSelecionada(Disciplina disciplinaSelecionada) {
		this.disciplinaSelecionada = disciplinaSelecionada;
	}

}
