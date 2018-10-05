package com.mis_ofertas_api.app.repository;

import com.mis_ofertas_api.app.model.Area;
import com.mis_ofertas_api.app.model.City;
import com.mis_ofertas_api.app.model.Commune;
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
public class CommuneDAO extends BeanDAO<Commune> {

    @Transactional(readOnly = true)
    public Commune commune(Long id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Commune> criteriaQuery = criteriaBuilder.createQuery(Commune.class);
        Root<Commune> root = criteriaQuery.from(Commune.class);
        Path<Long> idPath = root.get("id");
        criteriaQuery.select(root)
                .where(criteriaBuilder.equal(idPath, id));
        Query<Commune> query = session.createQuery(criteriaQuery);
        try {
            Commune commune = query.getSingleResult();
            return commune;
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public List<Commune> communes() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Commune> criteriaQuery = criteriaBuilder.createQuery(Commune.class);
        Root<Commune> root = criteriaQuery.from(Commune.class);
        criteriaQuery.select(root);
        Query<Commune> query = session.createQuery(criteriaQuery);
        try {
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

}
