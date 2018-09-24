package com.mis_ofertas_api.app.repository;

import com.mis_ofertas_api.app.model.SystemUser;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

@Repository
public class UserDAO extends BeanDAO<SystemUser> {

    @Transactional(readOnly = true)
    public SystemUser user(SystemUser usuarioParam) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<SystemUser> criteriaQuery = criteriaBuilder.createQuery(SystemUser.class);
        Root<SystemUser> root = criteriaQuery.from(SystemUser.class);
        Path<String> rutPath = root.get("rut");
        criteriaQuery.select(root)
                .where(criteriaBuilder.equal(rutPath, usuarioParam.getRut()));
        Query<SystemUser> query = session.createQuery(criteriaQuery);
        try {
            SystemUser Usuario = query.getSingleResult();
            return Usuario;
        }catch (Exception e){
            return null;
        }
    }

}
