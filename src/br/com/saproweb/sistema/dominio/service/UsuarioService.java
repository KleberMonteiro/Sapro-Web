package br.com.saproweb.sistema.dominio.service;

import java.util.List;

import br.com.saproweb.sistema.dominio.entidades.Usuario;

public interface UsuarioService {

	public List<Usuario> buscarTodos();

	public Usuario buscarPorId(Long id);

	public void salvar(Usuario usuario) throws Exception;

	public void excluir(Usuario usuario);

	public void atualizar(Usuario usuario);

	public void alterarSenha(Usuario usuario);

}
