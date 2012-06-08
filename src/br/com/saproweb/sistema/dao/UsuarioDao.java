package br.com.saproweb.sistema.dao;

import br.com.saproweb.infra.hibernate.dao.GenericDao;
import br.com.saproweb.sistema.dominio.entidades.Usuario;

public interface UsuarioDao extends GenericDao<Usuario, Long> {
	
	public Usuario buscarPorEmail(String email);
	
}
