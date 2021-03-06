package com.mis_ofertas_api.app.repository;

import com.mis_ofertas_api.app.model.Commune;
import com.mis_ofertas_api.app.model.Country;
import com.mis_ofertas_api.app.model.SystemUser;
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
public class CountryDAO extends BeanDAO<Country> {

    @Transactional(readOnly = true)
    public Country country(Long id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Country> criteriaQuery = criteriaBuilder.createQuery(Country.class);
        Root<Country> root = criteriaQuery.from(Country.class);
        Path<Long> idPath = root.get("id");
        criteriaQuery.select(root)
                .where(criteriaBuilder.equal(idPath, id));
        Query<Country> query = session.createQuery(criteriaQuery);
        try {
            Country country = query.getSingleResult();
            return country;
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public List<Country> countries() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Country> criteriaQuery = criteriaBuilder.createQuery(Country.class);
        Root<Country> root = criteriaQuery.from(Country.class);
        criteriaQuery.select(root);
        Query<Country> query = session.createQuery(criteriaQuery);
        try {
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

}
