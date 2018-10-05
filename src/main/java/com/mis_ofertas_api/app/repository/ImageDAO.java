package com.mis_ofertas_api.app.repository;

import com.mis_ofertas_api.app.model.Image;
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
public class ImageDAO extends BeanDAO<Image> {


    @Transactional(readOnly = true)
    public Image image(Long id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Image> criteriaQuery = criteriaBuilder.createQuery(Image.class);
        Root<Image> root = criteriaQuery.from(Image.class);
        Path<Long> idPath = root.get("id");
        criteriaQuery.select(root)
                .where(criteriaBuilder.equal(idPath, id));
        Query<Image> query = session.createQuery(criteriaQuery);
        try {
            Image image = query.getSingleResult();
            return image;
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public List<Image> images() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Image> criteriaQuery = criteriaBuilder.createQuery(Image.class);
        Root<Image> root = criteriaQuery.from(Image.class);
        criteriaQuery.select(root);
        Query<Image> query = session.createQuery(criteriaQuery);
        try {
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

}
