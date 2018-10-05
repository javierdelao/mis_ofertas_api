package com.mis_ofertas_api.app.repository;

import com.mis_ofertas_api.app.model.Area;
import com.mis_ofertas_api.app.model.Product;
import com.mis_ofertas_api.app.model.ProductType;
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
public class ProductTypeDAO extends BeanDAO<ProductType> {

    @Transactional(readOnly = true)
    public ProductType productType(Long id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<ProductType> criteriaQuery = criteriaBuilder.createQuery(ProductType.class);
        Root<ProductType> root = criteriaQuery.from(ProductType.class);
        Path<Long> idPath = root.get("id");
        criteriaQuery.select(root)
                .where(criteriaBuilder.equal(idPath, id));
        Query<ProductType> query = session.createQuery(criteriaQuery);
        try {
            ProductType productType = query.getSingleResult();
            return productType;
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public List<ProductType> productTypes() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<ProductType> criteriaQuery = criteriaBuilder.createQuery(ProductType.class);
        Root<ProductType> root = criteriaQuery.from(ProductType.class);
        criteriaQuery.select(root);
        Query<ProductType> query = session.createQuery(criteriaQuery);
        try {
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

}
