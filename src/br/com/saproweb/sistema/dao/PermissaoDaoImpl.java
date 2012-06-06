package br.com.saproweb.sistema.dao;

import javax.inject.Named;

import br.com.saproweb.infra.hibernate.dao.GenericHibernateDao;
import br.com.saproweb.sistema.dominio.entidades.Permissao;

@Named("permissaoDao")
public class PermissaoDaoImpl extends GenericHibernateDao<Permissao, Long>
		implements PermissaoDao {

}
