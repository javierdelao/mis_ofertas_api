package com.mis_ofertas_api.app.repository;

import com.mis_ofertas_api.app.model.Area;
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
public class AreaDAO extends BeanDAO<Area> {


    @Transactional(readOnly = true)
    public Area area(Long id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Area> criteriaQuery = criteriaBuilder.createQuery(Area.class);
        Root<Area> root = criteriaQuery.from(Area.class);
        Path<Long> idPath = root.get("id");
        criteriaQuery.select(root)
                .where(criteriaBuilder.equal(idPath, id));
        Query<Area> query = session.createQuery(criteriaQuery);
        try {
            Area area = query.getSingleResult();
            return area;
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public List<Area> areas() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Area> criteriaQuery = criteriaBuilder.createQuery(Area.class);
        Root<Area> root = criteriaQuery.from(Area.class);
        criteriaQuery.select(root);
        Query<Area> query = session.createQuery(criteriaQuery);
        try {
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }


}
