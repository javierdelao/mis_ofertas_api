package com.mis_ofertas_api.app.repository;

import com.mis_ofertas_api.app.model.City;
import com.mis_ofertas_api.app.model.Country;
import com.mis_ofertas_api.app.model.Discount;
import com.mis_ofertas_api.app.model.SystemUser;
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
public class DiscountDAO extends BeanDAO<Discount> {

    @Transactional(readOnly = true)
    public Discount discount(Long id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Discount> criteriaQuery = criteriaBuilder.createQuery(Discount.class);
        Root<Discount> root = criteriaQuery.from(Discount.class);
        Path<Long> idPath = root.get("id");
        criteriaQuery.select(root)
                .where(criteriaBuilder.equal(idPath, id));
        Query<Discount> query = session.createQuery(criteriaQuery);
        try {
            Discount discount = query.getSingleResult();
            return discount;
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public List<Discount> discounts() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Discount> criteriaQuery = criteriaBuilder.createQuery(Discount.class);
        Root<Discount> root = criteriaQuery.from(Discount.class);
        criteriaQuery.select(root);
        Query<Discount> query = session.createQuery(criteriaQuery);
        try {
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public Boolean existCode(String code) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Discount> criteriaQuery = criteriaBuilder.createQuery(Discount.class);
        Root<Discount> root = criteriaQuery.from(Discount.class);
        Path<String> codePath = root.get("code");
        criteriaQuery.select(root).where(criteriaBuilder.equal(codePath, code));
        Query<Discount> query = session.createQuery(criteriaQuery);
        try {
            List<Discount>discounts=query.getResultList();
            if(discounts.size()>0){
                return true;
            }
            return false;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<Discount> discounts(Long userId) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Discount> criteriaQuery = criteriaBuilder.createQuery(Discount.class);
        Root<Discount> root = criteriaQuery.from(Discount.class);
        Path<SystemUser> systemUserPath = root.get("user");
        Path<Date> creationDatePath = root.get("creationDate");
        criteriaQuery.select(root).where(criteriaBuilder.equal(systemUserPath.get("id"), userId)).orderBy(criteriaBuilder.desc(creationDatePath));
        Query<Discount> query = session.createQuery(criteriaQuery);

        try {
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

}
