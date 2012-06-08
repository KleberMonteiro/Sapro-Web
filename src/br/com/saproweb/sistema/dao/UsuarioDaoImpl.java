package br.com.saproweb.sistema.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Named;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import br.com.saproweb.infra.hibernate.dao.GenericHibernateDao;
import br.com.saproweb.sistema.dominio.entidades.Usuario;

@Named("usuarioDao")
public class UsuarioDaoImpl extends GenericHibernateDao<Usuario, Long>
		implements UsuarioDao, Serializable {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public Usuario buscarPorEmail(String email) {
		List<Usuario> usuarios = (List<Usuario>) getHibernateTemplate()
				.findByCriteria(
						DetachedCriteria.forClass(Usuario.class).add(
								Restrictions.eq("email", email)));

		return usuarios.get(0);
	}

}
