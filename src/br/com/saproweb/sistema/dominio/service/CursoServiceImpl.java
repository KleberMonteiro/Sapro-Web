package br.com.saproweb.sistema.dominio.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.saproweb.sistema.dao.CursoDao;
import br.com.saproweb.sistema.dominio.entidades.Curso;

@Named("cursoService")
public class CursoServiceImpl implements CursoService, Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	@Named("cursoDao")
	private CursoDao cursoDao;

	@Override
	public List<Curso> buscarTodos() {
		return cursoDao.buscarTodos();
	}

	@Override
	public Curso buscarPorId(Long id) {
		return cursoDao.buscarPorId(id);
	}

	@Override
	public void salvar(Curso entity) throws Exception {
		cursoDao.salvar(entity);
	}

	@Override
	public void excluir(Curso entity) {
		cursoDao.excluir(entity);
	}

	@Override
	public void atualizar(Curso entity) {
		cursoDao.atualizar(entity);
	}

}
