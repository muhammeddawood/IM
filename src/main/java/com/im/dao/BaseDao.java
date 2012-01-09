package com.im.dao;

import java.io.Serializable;
import java.util.Collection;


public interface BaseDao {

	void deleteAll(Collection<?> entities);

	void update(Object entity);

	Serializable save(Object entity);

	void saveOrUpdate(Object entity);

	void delete(Object entity);

}
