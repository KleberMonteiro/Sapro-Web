package br.com.saproweb.sistema.dominio.service;

import java.util.List;

import br.com.saproweb.sistema.dominio.entidades.Turma;

public interface TurmaService {

	public List<Turma> buscarTodos();

	public Turma buscarPorId(Long id);

	public void salvar(Turma entity) throws Exception;

	public void excluir(Turma entity);

	public void atualizar(Turma entity);

}
