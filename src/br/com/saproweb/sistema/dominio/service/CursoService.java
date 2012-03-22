package br.com.saproweb.sistema.dominio.service;

import java.util.List;

import br.com.saproweb.sistema.dominio.entidades.Curso;

public interface CursoService {
	
	public List<Curso> buscarTodos();

	public Curso buscarPorId(Long id);

	public void salvar(Curso entity) throws Exception;

	public void excluir(Curso entity);

	public void atualizar(Curso entity);

}
