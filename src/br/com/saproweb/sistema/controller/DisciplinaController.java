package br.com.saproweb.sistema.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.context.annotation.Scope;

import br.com.saproweb.sistema.dominio.entidades.Disciplina;
import br.com.saproweb.sistema.dominio.service.DisciplinaService;
import br.com.saproweb.utils.datamodel.DisciplinaDataModel;

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
	private DisciplinaDataModel disciplinaDataModel;	

	@SuppressWarnings("unused")
	@PostConstruct
	private void init() {
		criar();
		carregarListas();
	}

	private void carregarListas() {
		disciplinas = disciplinaService.buscarTodos();
		disciplinaDataModel = new DisciplinaDataModel(disciplinas);
	}

	public void criar() {
		disciplina = new Disciplina();
	}

	public void salvar() {
		try {

			logger.debug("Salvando disciplina...");
			disciplinaService.salvar(disciplina);
			carregarListas();

		} catch (Exception e) {
			logger.error(e.getClass() + ":" + e.getMessage());
			e.printStackTrace();
		}
	}

	public void atualizar() {
		try {

			logger.debug("Atualizando disciplina...");
			disciplinaService.atualizar(disciplina);

		} catch (Throwable e) {
			logger.error(e.getClass() + ":" + e.getMessage());
			e.printStackTrace();
		}
	}

	public void excluir() {
		try {

			logger.debug("Excluindo disciplina...");
			disciplinaService.excluir(disciplina);
			carregarListas();
			criar();

		} catch (Throwable e) {
			logger.error(e.getClass() + ":" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void onRowSelect(SelectEvent e){
		disciplina = disciplinaSelecionada;
	}
	
	public void onRowUnselect(UnselectEvent e){
		disciplina = new Disciplina();
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Disciplina getDisciplinaSelecionada() {
		return disciplinaSelecionada;
	}

	public void setDisciplinaSelecionada(Disciplina disciplinaSelecionada) {
		this.disciplinaSelecionada = disciplinaSelecionada;
	}

	public DisciplinaDataModel getDisciplinaDataModel() {
		return disciplinaDataModel;
	}

}
