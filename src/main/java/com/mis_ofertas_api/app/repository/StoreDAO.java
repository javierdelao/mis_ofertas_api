package com.mis_ofertas_api.app.repository;

import com.mis_ofertas_api.app.model.Commune;
import com.mis_ofertas_api.app.model.Status;
import com.mis_ofertas_api.app.model.Store;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
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

    @Transactional(readOnly = true)
    public List<Store> stores(Long CommuneId, String textSearch) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Store> criteriaQuery = criteriaBuilder.createQuery(Store.class);
        Root<Store> root = criteriaQuery.from(Store.class);

        Predicate predicate = null;

        if (CommuneId != null && !CommuneId.equals(Long.parseLong("0"))) {
            Path<Commune> communePath = root.get("commune");
            Path<Long> idPath = communePath.get("id");
            if (predicate != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(idPath, CommuneId));
            } else {
                predicate = criteriaBuilder.and(criteriaBuilder.equal(idPath, CommuneId));
            }
        }

        if (textSearch != null && !textSearch.equals("") && !textSearch.equals("null")) {
            Path<String> namePath = root.get("name");
            Predicate predicateAux = criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.lower(namePath), "%" + textSearch.toLowerCase() + "%")
            );
            if (predicate != null) {
                predicate = criteriaBuilder.and(predicate, predicateAux);
            } else {
                predicate = criteriaBuilder.and(predicateAux);
            }
        }

        if (predicate != null) {
            criteriaQuery.select(root).where(predicate);
        } else {
            criteriaQuery.select(root);
        }
        Query<Store> query = session.createQuery(criteriaQuery);
        try {
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

}
