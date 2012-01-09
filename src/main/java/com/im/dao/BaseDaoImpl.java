package com.im.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class BaseDaoImpl extends HibernateDaoSupport implements BaseDao {

	/* (non-Javadoc)
	 * @see com.mitrix.dao.TemplatesDao#get(java.lang.Class, java.io.Serializable)
	 */
	public <T> T get(Class<T> c, Serializable key) {
		return (T) getHibernateTemplate().get(c,key);
	}

	/* (non-Javadoc)
	 * @see com.mitrix.dao.TemplatesDao#getAll(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> getAll(Class<T> c) {
		return (List<T>) getHibernateTemplate().find("from "+c.getName());
	}

	/* (non-Javadoc)
	 * @see com.mitrix.dao.TemplatesDao#load(java.lang.Class, java.io.Serializable)
	 */
	
	public <T> T load(Class<T> c, Serializable key) {
		return (T) getHibernateTemplate().load(c, key);
	}

    /* (non-Javadoc)
	 * @see com.mitrix.dao.TemplatesDao#loadAll(java.lang.Class, java.io.Serializable)
	 */
	
	public <T> List<T> loadAll(Class<T> c) {
		return (List<T>)getHibernateTemplate().loadAll(c);
	}

	/* (non-Javadoc)
	 * @see com.mitrix.dao.TemplatesDao#update(java.lang.Class, T)
	 */
	@Override
	public void update(Object entity) {
		getHibernateTemplate().update(entity);
	}
	
	/* (non-Javadoc)
	 * @see com.mitrix.dao.TemplatesDao#save(java.lang.Class, T)
	 */
	@Override
	public Serializable save(Object entity) {
		return getHibernateTemplate().save(entity);
	}

	/* (non-Javadoc)
	 * @see com.mitrix.dao.TemplatesDao#saveOrUpdate(java.lang.Class, T)
	 */
	@Override
	public void saveOrUpdate(Object entity){
		getHibernateTemplate().saveOrUpdate(entity);
	};

	/* (non-Javadoc)
	 * @see com.mitrix.dao.TemplatesDao#delete(java.lang.Class, java.io.Serializable)
	 */
	@Override
	public void delete(Object entity){
		getHibernateTemplate().delete(entity);
	};


	/* (non-Javadoc)
	 * @see com.mitrix.dao.TemplatesDao#find(java.lang.String)
	 */
	public List find(String queryString) {
		return getHibernateTemplate().find(queryString);
	}

	/* (non-Javadoc)
	 * @see com.mitrix.dao.TemplatesDao#find(java.lang.String, java.lang.Object[])
	 */
	public List find(String queryString, Object[] bindings) {
		return getHibernateTemplate().find(queryString, bindings);
	}

	/*
	 * (non-Javadoc)
	 * @see com.mitrix.dao.TemplatesDao#find(java.lang.String, java.lang.Object[], int, int)
	 */
	public List find(String queryString, Object[] bindings, int startRow, int maxRows) {
		//create the query
		Query query = getSession().createQuery(queryString);
		//set the bindings
		if (bindings != null) {
			for (int i = 0; i < bindings.length; i++) {
				query.setParameter(i, bindings[i]);
			}
		}
		//and the boundaries
		query.setFirstResult(startRow);
		query.setMaxResults(maxRows);
		
		return query.list();
	}


	@SuppressWarnings("unchecked")
	public Object findUnique(String queryString,Object... values) {
		Object result = null;
		List<Object> results = getHibernateTemplate().find(queryString, values);
		if(results.size() > 0)
			result = results.get(0);

		return result;
	}
	
	public List findByNamedParams(String queryString, String[] params,
			Object[] values) {
		return getHibernateTemplate().findByNamedParam(queryString, params, values);
	}
	
	/* (non-Javadoc)
	 * @see com.mitrix.dao.TemplatesDao#evict(java.lang.Object)
	 */
	public void evict(Object entity) {
		getHibernateTemplate().evict(entity);
	}

	/* (non-Javadoc)
	 * @see com.mitrix.dao.TemplatesDao#flush()
	 */
	public void flush() {
		getHibernateTemplate().flush();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.mitrix.dao.TemplatesDao#refresh(java.lang.Object)
	 */
	public void refresh(Object entity) {
		getHibernateTemplate().refresh(entity);
	}

    @SuppressWarnings("unchecked")
	public <T> List<T> findByExample(T exampleEntity) {
		return getHibernateTemplate().findByExample(exampleEntity);
	}
	
	protected Criteria createCriteria(Class persistentClass) {
		return getSession().createCriteria(persistentClass);
	}
	
	public void saveOrUpdateAll(Collection entities) throws DataAccessException {
		getHibernateTemplate().saveOrUpdateAll(entities);
		
	}
	
	public <T> void saveOrUpdateInBulk(List<T> entities) {
		int batchSize = Math.min(1000, entities.size());
		for (int i = 0; i < entities.size(); i+=batchSize) {
			//subList() - exclusive of the to index, so no need to decrement the end by 1.
			//int end = i + Math.min(batchSize, entities.size() - i) - 1; 
			int end = i + Math.min(batchSize, entities.size() - i);
			List<T> subSetEntities = entities.subList(i, end==0?1:end);
			for (Object entity : subSetEntities) {
				getHibernateTemplate().saveOrUpdate(entity);
			}
			getHibernateTemplate().flush();
			for (Object entity : subSetEntities) {
				getHibernateTemplate().evict(entity);
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public List findByCriteria(DetachedCriteria criteria) throws DataAccessException {
		return getHibernateTemplate().findByCriteria(criteria);
	}

	@SuppressWarnings("rawtypes")
	public List findByCriteria(DetachedCriteria criteria, int firstResult, int maxResults) throws DataAccessException {
		return getHibernateTemplate().findByCriteria(criteria, firstResult, maxResults);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> getAll(Class<T> c, List<Long> ids,String idPropertyName) {
		if(ids==null || ids.isEmpty()){
			return Collections.EMPTY_LIST;
		}
		DetachedCriteria criteria=DetachedCriteria.forClass(c)
		                                  .add(Restrictions.in(idPropertyName, ids));
		
		return findByCriteria(criteria);
	}

	/*
	 * (non-Javadoc)
	 * @see com.mitrix.dao.TemplatesDao#executeUpdate(java.lang.String, java.lang.Object[])
	 */
	public int executeUpdate(String hql, Object... values) {
		return getHibernateTemplate().bulkUpdate(hql, values);
	}
	
	@Override
	public void deleteAll(Collection<?> entities) {
		getHibernateTemplate().deleteAll(entities);
		
	}

	public Query getCachableQuery(String queryString, String cacheRegionName) {
		Query query=getSession().createQuery(queryString);
		query.setCacheable(true);
		if(StringUtils.isNotEmpty(cacheRegionName)){
			query.setCacheRegion(cacheRegionName);
		}
		return query;
	}
	
	public Query getNamedQuery(String queryName){
		return getSession().getNamedQuery(queryName);
	}
	
	public Query getCachableNamedQuery(String queryName){
		Query query=getNamedQuery(queryName);
		query.setCacheable(true);
		return query;
	}
	
	public Query getCachableQuery(String query) {
		return getCachableQuery(query, null);
	}
}
