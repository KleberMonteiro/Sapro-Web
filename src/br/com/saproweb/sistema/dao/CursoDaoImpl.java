package br.com.saproweb.sistema.dao;

import java.io.Serializable;

import javax.inject.Named;

import br.com.saproweb.infra.hibernate.dao.GenericHibernateDao;
import br.com.saproweb.sistema.dominio.entidades.Curso;
import br.com.saproweb.utils.enumeration.StatusEnum;

@Named("cursoDao")
public class CursoDaoImpl extends GenericHibernateDao<Curso, Long> implements
		CursoDao, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public void desativar(Curso curso) {
		curso.setStatus(StatusEnum.DELETADO);
		atualizar(curso);
	}

}
