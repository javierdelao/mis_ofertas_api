package com.mis_ofertas_api.app.repository;

import com.mis_ofertas_api.app.model.Area;
import com.mis_ofertas_api.app.model.Product;
import com.mis_ofertas_api.app.model.SystemUser;
import com.mis_ofertas_api.app.model.Valoration;
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
public class ValorationDAO extends BeanDAO<Valoration> {


    @Transactional(readOnly = true)
    public Valoration valoration(Long id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Valoration> criteriaQuery = criteriaBuilder.createQuery(Valoration.class);
        Root<Valoration> root = criteriaQuery.from(Valoration.class);
        Path<Long> idPath = root.get("id");
        criteriaQuery.select(root)
                .where(criteriaBuilder.equal(idPath, id));
        Query<Valoration> query = session.createQuery(criteriaQuery);
        try {
            return query.getSingleResult();
        }catch (Exception e){
            return null;
        }
    }

    @Transactional(readOnly = true)
    public Valoration valoration(SystemUser user, Product product) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Valoration> criteriaQuery = criteriaBuilder.createQuery(Valoration.class);
        Root<Valoration> root = criteriaQuery.from(Valoration.class);
        Path<SystemUser> systemUserPath = root.get("systemUser");
        Path<Product> productPath = root.get("product");

        criteriaQuery.select(root)
                .where(criteriaBuilder.equal(systemUserPath, user),
                        criteriaBuilder.equal(productPath, product));
        Query<Valoration> query = session.createQuery(criteriaQuery);
        try {
            return query.getSingleResult();
        }catch (Exception e){
            return null;
        }
    }

    @Transactional(readOnly = true)
    public List<Valoration> valorations() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Valoration> criteriaQuery = criteriaBuilder.createQuery(Valoration.class);
        Root<Valoration> root = criteriaQuery.from(Valoration.class);
        criteriaQuery.select(root);
        Query<Valoration> query = session.createQuery(criteriaQuery);
        try {
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }


}
