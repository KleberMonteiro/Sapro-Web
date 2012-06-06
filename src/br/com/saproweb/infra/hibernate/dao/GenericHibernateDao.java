package br.com.saproweb.infra.hibernate.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import br.com.saproweb.utils.Utils;

public class GenericHibernateDao<T, PK extends Serializable> extends
		HibernateDaoSupport implements GenericDao<T, PK> {

	private Class<T> tipo;

	@Autowired
	SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public GenericHibernateDao() {
		this.tipo = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	protected final Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<T> buscarTodos() throws DataAccessException {
		String hql = "FROM " + tipo.getName();

		Query query = getCurrentSession().createQuery(hql);

		return Utils.castList(tipo, query.list());
	}

	@SuppressWarnings("unchecked")
	@Override
	public T buscarPorId(PK id) throws DataAccessException {
		String hql = "FROM " + tipo.getName() + " WHERE id = :id";

		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("id", id);

		return (T) query.uniqueResult();
	}

	@Override
	public void salvar(T entity) throws DataAccessException {
		getCurrentSession().save(entity);
	}

	@Override
	public void excluir(T entity) throws DataAccessException {
		getCurrentSession().delete(entity);
	}

	@Override
	public void atualizar(T entity) throws DataAccessException {
		getCurrentSession().merge(entity);
	}

	public Class<T> getType() {
		return tipo;
	}
}
