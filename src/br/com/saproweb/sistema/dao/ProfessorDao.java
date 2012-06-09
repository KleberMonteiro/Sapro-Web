package br.com.saproweb.sistema.dao;

import br.com.saproweb.infra.hibernate.dao.GenericDao;
import br.com.saproweb.sistema.dominio.entidades.Professor;

public interface ProfessorDao extends GenericDao<Professor, Long> {

	public void desativar(Professor professor);

}
