package com.mis_ofertas_api.app.repository;

import com.mis_ofertas_api.app.model.Area;
import com.mis_ofertas_api.app.model.Offer;
import com.mis_ofertas_api.app.model.OfferType;
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
public class OfferTypeDAO extends BeanDAO<OfferType> {

    @Transactional(readOnly = true)
    public OfferType offerType(Long id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<OfferType> criteriaQuery = criteriaBuilder.createQuery(OfferType.class);
        Root<OfferType> root = criteriaQuery.from(OfferType.class);
        Path<Long> idPath = root.get("id");
        criteriaQuery.select(root)
                .where(criteriaBuilder.equal(idPath, id));
        Query<OfferType> query = session.createQuery(criteriaQuery);
        try {
            OfferType offerType = query.getSingleResult();
            return offerType;
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public List<OfferType> offerTypes() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<OfferType> criteriaQuery = criteriaBuilder.createQuery(OfferType.class);
        Root<OfferType> root = criteriaQuery.from(OfferType.class);
        criteriaQuery.select(root);
        Query<OfferType> query = session.createQuery(criteriaQuery);
        try {
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

}
