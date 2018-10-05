package com.mis_ofertas_api.app.repository;

import com.mis_ofertas_api.app.model.Commune;
import com.mis_ofertas_api.app.model.Status;
import com.mis_ofertas_api.app.model.Store;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class StoreDAO extends BeanDAO<Store> {

    @Transactional(readOnly = true)
    public Store store(Long id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Store> criteriaQuery = criteriaBuilder.createQuery(Store.class);
        Root<Store> root = criteriaQuery.from(Store.class);
        Path<Long> idPath = root.get("id");
        criteriaQuery.select(root)
                .where(criteriaBuilder.equal(idPath, id));
        Query<Store> query = session.createQuery(criteriaQuery);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public List<Store> stores() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Store> criteriaQuery = criteriaBuilder.createQuery(Store.class);
        Root<Store> root = criteriaQuery.from(Store.class);
        criteriaQuery.select(root);
        Query<Store> query = session.createQuery(criteriaQuery);
        try {
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

}
