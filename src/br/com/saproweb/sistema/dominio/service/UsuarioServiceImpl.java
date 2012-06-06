package br.com.saproweb.sistema.dominio.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import br.com.saproweb.sistema.dao.UsuarioDao;
import br.com.saproweb.sistema.dominio.entidades.Usuario;

@Named("usuarioService")
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

	@Inject
	@Named("usuarioDao")
	private UsuarioDao usuarioDao;

	public UsuarioDao getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	public List<Usuario> buscarTodos() {
		return this.usuarioDao.buscarTodos();
	}

	public Usuario buscarPorId(Long id) {
		return this.usuarioDao.buscarPorId(id);
	}

	public void salvar(Usuario usuario) throws Exception {
		try {
			// Criptografando senha
			usuario.setSenha(DigestUtils.md5DigestAsHex(usuario.getPassword()
					.getBytes()));
			// Salvar
			this.usuarioDao.salvar(usuario);
		} catch (Exception e) {
			this.usuarioDao.salvar(usuario);
		}

	}

	public void excluir(Usuario usuario) {
		this.usuarioDao.excluir(usuario);
	}

	@Override
	public void atualizar(Usuario usuario) {
		this.usuarioDao.atualizar(usuario);
	}

	@Override
	public void alterarSenha(Usuario usuario) {
		// criptografando senha
		usuario.setSenha(DigestUtils.md5DigestAsHex(usuario.getPassword()
				.getBytes()));

		atualizar(usuario);
	}

}
