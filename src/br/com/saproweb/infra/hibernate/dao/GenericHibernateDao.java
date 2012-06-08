package br.com.saproweb.infra.hibernate.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class GenericHibernateDao<T, PK extends Serializable> extends
		HibernateDaoSupport implements GenericDao<T, PK> {

	private Class<T> tipo;

	@SuppressWarnings("unchecked")
	public GenericHibernateDao() {
		this.tipo = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Inject
	public void init(SessionFactory factory) {
		setSessionFactory(factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> buscarTodos() throws DataAccessException {
		return (List<T>) getHibernateTemplate().find("from " + tipo.getName());
	}

	@Override
	public T buscarPorId(PK id) throws DataAccessException {
		return (T) getHibernateTemplate().get(this.getType(), id);
	}

	@Override
	public void salvar(T entity) throws DataAccessException {
		getHibernateTemplate().saveOrUpdate(entity);
	}

	@Override
	public void excluir(T entity) throws DataAccessException {
		getHibernateTemplate().delete(entity);
	}

	@Override
	public void atualizar(T entity) throws DataAccessException {
		getHibernateTemplate().update(entity);
	}

	public Class<T> getType() {
		return tipo;
	}
}
