package br.com.saproweb.sistema.dominio.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.saproweb.sistema.dao.ProfessorDao;
import br.com.saproweb.sistema.dominio.entidades.Professor;

@Named("professorService")
public class ProfessorServiceImpl implements ProfessorService, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	@Named("professorDao")
	private ProfessorDao professorDao;

	@Override
	public List<Professor> buscarTodos() {
		return professorDao.buscarTodos();
	}

	@Override
	public Professor buscarPorId(Long id) {
		return professorDao.buscarPorId(id);
	}

	@Override
	public void salvar(Professor entity) throws Exception {
		professorDao.salvar(entity);		
	}

	@Override
	public void excluir(Professor entity) {
		professorDao.excluir(entity);		
	}

	@Override
	public void atualizar(Professor entity) {
		professorDao.atualizar(entity);
	}	

}
