package com.im.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class BaseDaoImpl implements BaseDao  {
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getById(Class<T> c, final Long id ){
		return (T) this.getCurrentSession().get( c, id );
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getAll(Class<T> c){
		return this.getCurrentSession()
				.createQuery( "from " + c.getName() ).list();
	}

	@Override
	public void create( Object entity ){
		this.getCurrentSession().persist( entity );
	}

	@Override
	public void update( Object entity ){
		this.getCurrentSession().merge( entity );
	}

	@Override
	public void delete( Object entity ){
		this.getCurrentSession().delete( entity );
	}
	
	@Override
	public <T> void deleteById(Class<T> t, final Long entityId ){
		final T entity = this.getById( t, entityId );
		this.delete( entity );
	}

	@Override
	public final Session getCurrentSession(){
		return this.sessionFactory.getCurrentSession();
	}
	
	@SuppressWarnings("rawtypes")
	public List find(String queryString, Object[] bindings) {
		Query queryObject = getCurrentSession().createQuery(queryString);
		if (bindings != null) {
			for (int i = 0; i < bindings.length; i++) {
				queryObject.setParameter(i, bindings[i]);
			}
		}
		return queryObject.list();
	}
	
	@SuppressWarnings("unchecked")
	public Object findUnique(String queryString,Object... values) {
		Object result = null;
		List<Object> results = find(queryString, values);
		if (results.size() > 0) {
			result = results.get(0);
		}
		return result;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}
