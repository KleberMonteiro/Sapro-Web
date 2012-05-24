package br.com.saproweb.sistema.dao;

import java.io.Serializable;

import javax.inject.Named;

import br.com.saproweb.infra.hibernate.dao.GenericHibernateDao;
import br.com.saproweb.sistema.dominio.entidades.Turma;

@Named("turmaDao")
public class TurmaDaoImpl extends GenericHibernateDao<Turma, Long> implements
		TurmaDao, Serializable {

	private static final long serialVersionUID = 1L;

}
