package br.com.saproweb.sistema.dao;

import java.io.Serializable;

import javax.inject.Named;

import br.com.saproweb.infra.hibernate.dao.GenericHibernateDao;
import br.com.saproweb.sistema.dominio.entidades.Professor;
import br.com.saproweb.utils.enumeration.StatusEnum;

@Named("professorDao")
public class ProfessorDaoImpl extends GenericHibernateDao<Professor, Long>
		implements ProfessorDao, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public void desativar(Professor professor) {
		professor.setStatus(StatusEnum.DELETADO);
		atualizar(professor);		
	}

}
