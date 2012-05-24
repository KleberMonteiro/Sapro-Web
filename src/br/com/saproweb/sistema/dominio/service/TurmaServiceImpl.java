package br.com.saproweb.sistema.dominio.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.saproweb.sistema.dao.TurmaDao;
import br.com.saproweb.sistema.dominio.entidades.Turma;

@Named("turmaService")
public class TurmaServiceImpl implements TurmaService, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	@Named("turmaDao")
	private TurmaDao turmaDao;

	@Override
	public List<Turma> buscarTodos() {
		return turmaDao.buscarTodos();
	}

	@Override
	public Turma buscarPorId(Long id) {
		return turmaDao.buscarPorId(id);
	}

	@Override
	public void salvar(Turma entity) throws Exception {
		turmaDao.salvar(entity);
	}

	@Override
	public void excluir(Turma entity) {
		turmaDao.excluir(entity);
	}

	@Override
	public void atualizar(Turma entity) {
		turmaDao.atualizar(entity);
	}

}
