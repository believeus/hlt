package com.hlt2008.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hlt2008.PaginationUtil.Page;
import com.hlt2008.PaginationUtil.Pageable;
import com.hlt2008.dao.BaseDao;


@Service
public class BaseService{
	@Resource
	private BaseDao baseDao;
	public void merge(Object object) {
		baseDao.merge(object);
	}

	public void saveOrUpdata(Object object) {
		baseDao.saveOrUpdata(object);
	}

	public void delete(Class<?> clazz, Integer id) {
		baseDao.delete(clazz, id);
	}

	public void delete(Object entity) {
		baseDao.delete(entity);
	}
	public void delete(Class<?> clazz,List<?> ids){
		baseDao.delete(clazz, ids);
	}
	public void delete(Class<?> clazz, String property, Object value) {
		baseDao.delete(clazz, property, value);
	}

	public Object findObject(String hql) {
		return baseDao.findObject(hql);
	}

	public Object findObject(Class<?> clazz, Object property, Object value) {
		return baseDao.findObject(clazz, property, value);
	}

	public Object findObject(Class<?> clazz, Integer id) {
		return baseDao.findObject(clazz, id);
	}

	public List<?> findObjectList(Class<?> clazz, Object property, Object value) {
		return baseDao.findObjectList(clazz, property, value);
	}

	public List<?> findObjectListOrderById(Class<?> clazz, Object property,
			Object value) {
		return baseDao.findObjectListOrderById(clazz, property, value);
	}

	public List<?> findObjectList(Class<?> clazz, Object property,
			Object value1, Object property2, Object value2) {
		return baseDao.findObjectList(clazz, property, value1, property2, value2);
	}

	public List<?> findObjectList(Class<?> clazz) {
		return baseDao.findObjectList(clazz);
	}

	public List<?> findObjectList(Class<?> clazz, Integer num) {
		return baseDao.findObjectList(clazz, num);
	}

	public List<?> findObjectList(Class<?> clazz, String property,
			Object value, int num) {
		return baseDao.findObjectList(clazz, property, value, num);
	}

	public List<?> findObjectList(String hql, Integer num) {
		return baseDao.findObjectList(hql,num);
	}
	
	public Page<?> findObjectList(String hql, Pageable pageable) {
		return (Page<?>) baseDao.getPageDateList(hql,pageable );
	}

}
