package com.im.dao;

import java.util.List;

import org.hibernate.Session;

public interface BaseDao {

	<T> T getById(Class<T> c, Long id);

	<T> List<T> getAll(Class<T> c);

	void create(Object entity);

	void update(Object entity);

	void delete(Object entity);

	<T> void deleteById(Class<T> t, Long entityId);

	Session getCurrentSession();

}
