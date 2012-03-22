package br.com.saproweb.sistema.dao;

import java.io.Serializable;

import javax.inject.Named;

import br.com.saproweb.infra.hibernate.dao.GenericHibernateDao;
import br.com.saproweb.sistema.dominio.entidades.Curso;

@Named("cursoDao")
public class CursoDaoImpl extends GenericHibernateDao<Curso, Long> implements
		CursoDao, Serializable {

	private static final long serialVersionUID = 1L;

}
