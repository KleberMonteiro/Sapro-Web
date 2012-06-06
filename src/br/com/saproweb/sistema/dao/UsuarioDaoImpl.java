package br.com.saproweb.sistema.dao;

import java.io.Serializable;

import javax.inject.Named;

import org.hibernate.Query;

import br.com.saproweb.infra.hibernate.dao.GenericHibernateDao;
import br.com.saproweb.sistema.dominio.entidades.Usuario;

@Named("usuarioDao")
public class UsuarioDaoImpl extends GenericHibernateDao<Usuario, Long>
		implements UsuarioDao, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Usuario buscarPorLogin(String login) {
		String hql = "FROM Usuario usuario WHERE usuario.login=:login";
		Query query = getCurrentSession().createQuery(hql);
		query.setString("login", login);

		return (Usuario) query.uniqueResult();
	}
	
}
