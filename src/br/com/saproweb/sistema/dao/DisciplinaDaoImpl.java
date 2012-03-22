package br.com.saproweb.sistema.dao;

import java.io.Serializable;

import javax.inject.Named;

import br.com.saproweb.infra.hibernate.dao.GenericHibernateDao;
import br.com.saproweb.sistema.dominio.entidades.Disciplina;

@Named("disciplinaDao")
public class DisciplinaDaoImpl extends GenericHibernateDao<Disciplina, Long>
		implements DisciplinaDao, Serializable {

	private static final long serialVersionUID = 1L;

}
