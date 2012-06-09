package br.com.saproweb.sistema.dao;

import java.io.Serializable;

import javax.inject.Named;

import br.com.saproweb.infra.hibernate.dao.GenericHibernateDao;
import br.com.saproweb.sistema.dominio.entidades.Disciplina;
import br.com.saproweb.utils.enumeration.StatusEnum;

@Named("disciplinaDao")
public class DisciplinaDaoImpl extends GenericHibernateDao<Disciplina, Long>
		implements DisciplinaDao, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public void desativar(Disciplina disciplina) {
		disciplina.setStatus(StatusEnum.DELETADO);
		atualizar(disciplina);
	}

}
