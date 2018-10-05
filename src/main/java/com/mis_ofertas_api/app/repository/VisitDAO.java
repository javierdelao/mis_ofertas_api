package com.mis_ofertas_api.app.repository;

import com.mis_ofertas_api.app.model.Product;
import com.mis_ofertas_api.app.model.Valoration;
import com.mis_ofertas_api.app.model.Visit;
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
public class VisitDAO extends BeanDAO<Visit> {

    @Transactional(readOnly = true)
    public Visit visit(Long id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Visit> criteriaQuery = criteriaBuilder.createQuery(Visit.class);
        Root<Visit> root = criteriaQuery.from(Visit.class);
        Path<Long> idPath = root.get("id");
        criteriaQuery.select(root)
                .where(criteriaBuilder.equal(idPath, id));
        Query<Visit> query = session.createQuery(criteriaQuery);
        try {
            return query.getSingleResult();
        }catch (Exception e){
            return null;
        }
    }

    @Transactional(readOnly = true)
    public List<Visit> visits() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Visit> criteriaQuery = criteriaBuilder.createQuery(Visit.class);
        Root<Visit> root = criteriaQuery.from(Visit.class);
        criteriaQuery.select(root);
        Query<Visit> query = session.createQuery(criteriaQuery);
        try {
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

}
