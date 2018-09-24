/*
 * Copyright (c) 2017 Business News Americas Limitada - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.mis_ofertas_api.app.repository;

import com.mis_ofertas_api.app.model.Bean;
import com.mis_ofertas_api.app.model.LazyCollectorBean;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.Valid;

/**
 * Created by Juan Francisco Rodríguez
 * <p>
 * A generic object repository with common IO functions
 **/
@Repository
public class BeanDAO<B extends Bean & LazyCollectorBean> {

    /**
     * Hibernate session factory
     */
    protected SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Set ups a predicate for a search query
     *
     * @param criteriaBuilder the criteria builder
     * @param root            the root predicate
     * @param search          the search query
     * @return a predicate or null
     */
    protected Predicate search(CriteriaBuilder criteriaBuilder, Root<B> root, String search) {
        return null;
    }

    /**
     * Set ups a n order for order
     *
     * @param criteriaBuilder the criteria builder
     * @param root            the root predicate
     * @param sortColumn      the sorted column index
     * @param dir             the direction of the sorted column "asc" or "desc"
     * @return an order or null
     */
    protected Order order(CriteriaBuilder criteriaBuilder, Root<B> root, Integer sortColumn, String dir) {
        return null;
    }

    /**
     * Inserts an object into the repository
     *
     * @param object the new object to insert
     */
    @Transactional(rollbackFor = Exception.class)
    public void insert(@Valid B object) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(object);
    }

    /**
     * Updates the properties of an object
     *
     * @param object the object to update in the repository
     */
    @Transactional
    public void update(@Valid B object) {
        Session session = sessionFactory.getCurrentSession();
        session.update(object);
    }

    /**
     * Deletes an object from the repository
     *
     * @param object the object to delete
     */
    @Transactional(rollbackFor = Exception.class)
    public void delete(B object) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(object);
    }

}