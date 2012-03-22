package br.com.saproweb.sistema.dominio.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.saproweb.sistema.dao.DisciplinaDao;
import br.com.saproweb.sistema.dominio.entidades.Disciplina;

@Named("disciplinaService")
public class DisciplinaServiceImpl implements DisciplinaService, Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	@Named("disciplinaDao")
	private DisciplinaDao disciplinaDao;
	
	@Override
	public List<Disciplina> buscarTodos() {
		return disciplinaDao.buscarTodos();
	}

	@Override
	public Disciplina buscarPorId(Long id) {
		return disciplinaDao.buscarPorId(id);
	}

	@Override
	public void salvar(Disciplina entity) throws Exception {
		disciplinaDao.salvar(entity);		
	}

	@Override
	public void excluir(Disciplina entity) {
		disciplinaDao.excluir(entity);		
	}

	@Override
	public void atualizar(Disciplina entity) {
		disciplinaDao.atualizar(entity);		
	}

}
