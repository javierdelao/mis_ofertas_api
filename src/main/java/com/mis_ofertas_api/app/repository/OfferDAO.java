package com.mis_ofertas_api.app.repository;

import com.mis_ofertas_api.app.model.Offer;
import com.mis_ofertas_api.app.model.Product;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

@Repository
public class OfferDAO extends BeanDAO<Offer> {

    @Transactional(readOnly = true)
    public Offer offer(Long id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Offer> criteriaQuery = criteriaBuilder.createQuery(Offer.class);
        Root<Offer> root = criteriaQuery.from(Offer.class);
        Path<Long> idPath = root.get("id");
        criteriaQuery.select(root)
                .where(criteriaBuilder.equal(idPath, id));
        Query<Offer> query = session.createQuery(criteriaQuery);
        try {
            Offer offer = query.getSingleResult();
            return offer;
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public Offer offer(Product product) {
        Date date = new Date();
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Offer> criteriaQuery = criteriaBuilder.createQuery(Offer.class);
        Root<Offer> root = criteriaQuery.from(Offer.class);
        Path<Product> productPath = root.get("product");
        Path<Date> publicationDatePath = root.get("publicationDate");
        Path<Date> expirationDatePath = root.get("expirationDate");
        criteriaQuery.select(root)
                .where(criteriaBuilder.and(
                        criteriaBuilder.equal(productPath, product),
                        criteriaBuilder.lessThan(publicationDatePath, date),
                        criteriaBuilder.greaterThan(expirationDatePath, new Date())));
        Query<Offer> query = session.createQuery(criteriaQuery);
        try {
            Offer offer = query.getSingleResult();
            return offer;
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public List<Offer> offers() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Offer> criteriaQuery = criteriaBuilder.createQuery(Offer.class);
        Root<Offer> root = criteriaQuery.from(Offer.class);
        criteriaQuery.select(root);
        Query<Offer> query = session.createQuery(criteriaQuery);
        try {
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

}
