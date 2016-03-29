package com.it.forum.domain.dao.impl;


import com.it.forum.domain.dao.IGenericDao;
import com.it.forum.domain.enumx.OrderType;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@Repository
public abstract class GenericDaoImpl<T, PK extends Serializable> implements IGenericDao<T, PK> {

    @Resource(name = "hibernateTemplate")
    private HibernateTemplate hibernateTemplate;

    private Class<T> type;

    public GenericDaoImpl() {
    }

    public GenericDaoImpl(Class<T> type) {
        this.type = type;
    }

    @Override
    public <T> void save(T entity) {
        getHibernateTemplate().persist(entity);
    }

    @Override
    public <T> void saveOrUpdate(T entity) {
        getHibernateTemplate().saveOrUpdate(entity);
    }


    @Override
    public Boolean delete(T entity) {
        try {
            getSession().delete(entity);
            getSession().flush();
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    @Override
    public <T> T edit(T entity) {
        return (T) getHibernateTemplate().merge(entity);
    }

    @Override
    public <T> List<T> findAll() {
        return getSession().createCriteria(getType()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    public <T> List<T> findAll(String field, OrderType orderType) {
        Criteria criteria = getSession().createCriteria(getType());
        if (OrderType.ASC.equals(orderType)) {
            criteria.addOrder(Order.asc(field));
        } else {
            criteria.addOrder(Order.desc(field));
        }

        return criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    public <T> T findById(PK entityId) {
        return (T) getHibernateTemplate().get(type, entityId);
    }

    @Override
    public <T> Integer countAll() {
        return (Integer) getSession().createCriteria(getType())
                .setProjection(Projections.rowCount())
                .uniqueResult();
    }

    @Override
    public void initialize(Object proxy) {
        Hibernate.initialize(proxy);
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public Session getSession() {
        return hibernateTemplate.getSessionFactory().getCurrentSession();
    }

    public void setType(Class<T> type) {
        this.type = type;
    }

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public Class<T> getType() {
        return type;
    }


}


