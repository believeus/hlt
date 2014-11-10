package com.hlt2008.dao;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hlt2008.PaginationUtil.Page;
import com.hlt2008.PaginationUtil.Pageable;



public class BaseDao extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(BaseDao.class);
	@Resource
	private SessionFactory sessionFactory;


	public void merge(Object object) {
		HibernateTemplate ht = super.getHibernateTemplate();
		ht.merge(object);
		ht.flush();
	}

	public void saveOrUpdata(Object object) {
		HibernateTemplate ht = getHibernateTemplate();
		ht.saveOrUpdate(object);
		ht.flush();
	}

	public void delete(Class<?> clazz,List<?> idList){
		if (idList==null||idList.isEmpty()) {
			return;
		}
		String ids=idList.toString().replace("[", "(").replace("]", ")");
		final String hql = "delete from " + clazz.getName()+ " as entity where entity.id in "+ids;
		this.getHibernateTemplate().execute(
				new HibernateCallback<Object>() {

					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(hql);
						return query.executeUpdate();
					}
				});
	}
	public void delete(Class<?> clazz, final Integer id) {
		Object object = this.getHibernateTemplate().get(clazz, id);
		getHibernateTemplate().delete(object);
		this.getHibernateTemplate().flush();
	}
	
	public void delete(Object entity){
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}
	
	public void delete(Class<?> clazz, String property, final Object value) {
		HibernateTemplate ht = getHibernateTemplate();
		List<?> objectList = this.findObjectList(clazz, property, value);
		for (Object entity : objectList) {
			ht.delete(entity);
			ht.flush();
		}
	}
	
	public Object findObject(final String hql) {
		return this.getHibernateTemplate().execute(
				new HibernateCallback<Object>() {

					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(hql);
						return query.uniqueResult();
					}
				});
	}

	public Object findObject(Class<?> clazz, final Object property,
			final Object value) {
		final String hql = "from " + clazz.getName()+ " as entity where entity." + property + " =:value";
		log.debug("current hql:" + hql);
		return this.getHibernateTemplate().execute(
				new HibernateCallback<Object>() {

					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(hql);
						query.setParameter("value", value);
						return query.uniqueResult();
					}
				});
	}
	/** End Author:wuqiwei Data:2014-05-12 AddReason:根据属性获取对象 */

	public Object findObject(Class<?> clazz, Integer id) {
		return getHibernateTemplate().get(clazz, id);
	}
	public List<?> findObjectList(Class<?> clazz, final Object property,
			final Object value) {
		final String hql = "from " + clazz.getName()+ " as entity where entity." + property + " =:value";
		return (List<?>) this.getHibernateTemplate().execute(
				new HibernateCallback<Object>() {

					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(hql);
						query.setParameter("value", value);
						return query.list();
					}
				});
	}
	
	public List<?> findObjectListOrderById(Class<?> clazz, final Object property,
			final Object value) {
		final String hql = "from " + clazz.getName()+ " as entity where entity." + property + " =:value order by id desc";
		return (List<?>) this.getHibernateTemplate().execute(
				new HibernateCallback<Object>() {
					
					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(hql);
						query.setParameter("value", value);
						return query.list();
					}
				});
	}
	
	public List<?> findObjectList(Class<?> clazz, final Object property,
			final Object value1, final Object property2, final Object value2) {
		final String hql = "from " + clazz.getName()+ " as entity where entity." + property + " =:value1 and entity." + property2 + " =:value2 order by id desc";
		return (List<?>) this.getHibernateTemplate().execute(
				new HibernateCallback<Object>() {

					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(hql);
						query.setParameter("value1", value1);
						query.setParameter("value2", value2);
						return query.list();
					}
				});
	}
	
	public List<?> findObjectList(final Class<?> clazz){
		
		return (List<?>)getHibernateTemplate().execute(new HibernateCallback<Object>() {
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
					String hql="from "+clazz.getName();
					Query query = session.createQuery(hql);
					return query.list();
			}
			
		});
	}
 
	public List<?> findObjectList(final Class<?> clazz, final Integer num) {
		return (List<?>) getHibernateTemplate().execute(
				new HibernateCallback<Object>() {
					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						String hql = "from " + clazz.getName();
						Query query = session.createQuery(hql);
						query.setFirstResult(0);
						query.setMaxResults(num);
						List<?> list = query.list();
						return list;
					}
				});
	}

	public List<?> findObjectList(final Class<?> clazz, final String property,
			final Object value, final int num) {
		return (List<?>) getHibernateTemplate().execute(
				new HibernateCallback<Object>() {

					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						String hql = "from " + clazz.getName()+ " as entity where entity." + property + " =:value";
						Query query = session.createQuery(hql);
						query.setParameter("value", value);
						query.setFirstResult(0);
						query.setMaxResults(num);
						List<?> list = query.list();
						return list;
					}
				});
	}

	
	public List<?> findObjectList(final String hql, final Integer num) {
		Assert.assertNotNull(num);
		return (List<?>) getHibernateTemplate().execute(
				new HibernateCallback<Object>() {

					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(hql);
						query.setFirstResult(0);
						query.setMaxResults(num);
						List<?> list = query.list();
						return list;
					}
				});
	}
	
	public Page<?> getPageDateList(final String hql, final Pageable pageable) {
		return (Page<?>)getHibernateTemplate().execute(new HibernateCallback<Object>() {

			@SuppressWarnings("unchecked")
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				long total = query.list().size();
				query = session.createQuery(hql);
				int totalPages = (int) Math.ceil((double) total / (double) pageable.getPageSize());
				if (totalPages < pageable.getPageNumber()) {
					pageable.setPageNumber(totalPages);
				}
				query.setFirstResult((pageable.getPageNumber() - 1) * pageable.getPageSize());
				query.setMaxResults(pageable.getPageSize());
			    @SuppressWarnings("rawtypes")
				Page page = new Page(query.list(), (int)total, pageable);
			    return page;
			}
		});
	}
}