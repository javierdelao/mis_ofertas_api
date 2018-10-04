package com.mis_ofertas_api.app.repository;

import com.mis_ofertas_api.app.model.Area;
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
            Valoration valoration = query.getSingleResult();
            return valoration;
        }catch (Exception e){
            return null;
        }
    }


}
