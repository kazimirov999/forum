package com.it.forum.domain.dao;

        import com.it.forum.domain.enumx.OrderType;
        import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public interface IGenericDao<T, PK extends Serializable> {

    <T> void save(T entity);

    <T> void saveOrUpdate(T entity);

    Boolean delete(T entity);

    <T> T edit(T entity);

    @Transactional(readOnly = false)
    <T> List<T> findAll();

    @Transactional(readOnly = false)
    <T> List<T> findAll(String field, OrderType orderType);

    <T> T findById(PK entityId);

    @Transactional(readOnly = false)
    <T> Integer countAll();

    void initialize(Object proxy);
}