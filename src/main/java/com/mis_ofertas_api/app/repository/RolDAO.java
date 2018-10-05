package com.mis_ofertas_api.app.repository;

import com.mis_ofertas_api.app.model.ProductType;
import com.mis_ofertas_api.app.model.Rol;
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
public class RolDAO extends BeanDAO<Rol> {


    @Transactional(readOnly = true)
    public Rol rol(Long id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Rol> criteriaQuery = criteriaBuilder.createQuery(Rol.class);
        Root<Rol> root = criteriaQuery.from(Rol.class);
        Path<Long> idPath = root.get("id");
        criteriaQuery.select(root)
                .where(criteriaBuilder.equal(idPath, id));
        Query<Rol> query = session.createQuery(criteriaQuery);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public List<Rol> rols() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Rol> criteriaQuery = criteriaBuilder.createQuery(Rol.class);
        Root<Rol> root = criteriaQuery.from(Rol.class);
        criteriaQuery.select(root);
        Query<Rol> query = session.createQuery(criteriaQuery);
        try {
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

}
