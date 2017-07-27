package edu.curso.java.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("unchecked")
public class GenericDAOImp <T, PK extends Serializable> implements GenericDAO<T, PK> {
	
	private Class<T> type;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public GenericDAOImp() {
	    ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
	    this.type = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
	}

	@Override
	public PK create(T o) {
		return (PK) sessionFactory.getCurrentSession().save(o);
	}

	@Override
	public T read(PK id) {
		return (T) sessionFactory.getCurrentSession().get(type, id);
	}

	@Override
	public void update(T o) {
		sessionFactory.getCurrentSession().update(o);
	}

	@Override
	public List<T> returnList() {
		String sql = "from " + type.getName() + " as c where c.borrado = false";
		return sessionFactory.getCurrentSession().createQuery(sql).list();
	}
	
	@Override
	public void delete(PK id) {
		sessionFactory.getCurrentSession().delete((T) sessionFactory.getCurrentSession().get(type, id));
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}	
}
