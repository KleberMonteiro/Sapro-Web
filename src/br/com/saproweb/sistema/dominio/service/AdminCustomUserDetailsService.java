package br.com.saproweb.sistema.dominio.service;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import br.com.saproweb.sistema.dao.UsuarioDao;
import br.com.saproweb.sistema.dominio.entidades.Usuario;


@Named("adminCustomUserDetailsService")
@Transactional
public class AdminCustomUserDetailsService implements UserDetailsService,
		Serializable {

	private static final long serialVersionUID = 1L;

	private Logger logger = Logger
			.getLogger(AdminCustomUserDetailsService.class);

	@Inject
	@Named("usuarioDao")
	private UsuarioDao usuarioDao;

	@Override
	public Usuario loadUserByUsername(String arg0)
			throws UsernameNotFoundException, DataAccessException {
		Usuario o = null;
		try {
			o = usuarioDao.buscarPorLogin(arg0);
			if (o != null) {
				o.getAuthorities();
			} else {
				logger.debug("Usuario nao encontrado: Usuário " + arg0);
				throw new UsernameNotFoundException("Usuário " + arg0
						+ " inválido.");
			}
		} catch (DataAccessException e) {
			logger.debug("Falha ao recuperar do banco: "
					+ e.getStackTrace().toString());
			throw e;
		}
		return o;
	}

	public UsuarioDao getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

}
