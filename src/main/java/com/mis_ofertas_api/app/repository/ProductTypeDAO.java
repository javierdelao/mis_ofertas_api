package com.mis_ofertas_api.app.repository;

import com.mis_ofertas_api.app.model.Area;
import com.mis_ofertas_api.app.model.Product;
import com.mis_ofertas_api.app.model.ProductType;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
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

    @Transactional(readOnly = true)
    public List<ProductType> productTypes(String textSearch) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<ProductType> criteriaQuery = criteriaBuilder.createQuery(ProductType.class);
        Root<ProductType> root = criteriaQuery.from(ProductType.class);

        Predicate predicate = null;
        if (textSearch != null && !textSearch.equals("") && !textSearch.equals("null")) {
            Path<String> namePath = root.get("name");
            Path<String> descriptionPath = root.get("description");
            Predicate predicateAux = criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.lower(namePath), "%" + textSearch.toLowerCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.lower(descriptionPath), "%" + textSearch.toLowerCase() + "%")
            );
            if (predicate != null) {
                predicate = criteriaBuilder.and(predicate, predicateAux);
            } else {
                predicate = criteriaBuilder.and(predicateAux);
            }
        }
        if (predicate != null) {
            criteriaQuery.select(root).where(predicate);
        } else {
            criteriaQuery.select(root);
        }
        Query<ProductType> query = session.createQuery(criteriaQuery);
        try {
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

}
