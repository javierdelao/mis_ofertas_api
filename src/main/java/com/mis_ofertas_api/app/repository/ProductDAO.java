package com.mis_ofertas_api.app.repository;

import com.mis_ofertas_api.app.model.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;

@Repository
public class ProductDAO extends BeanDAO<Product> {

    @Transactional(readOnly = true)
    public Product product(Long id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> root = criteriaQuery.from(Product.class);
        Path<Long> idPath = root.get("id");
        criteriaQuery.select(root)
                .where(criteriaBuilder.equal(idPath, id));
        Query<Product> query = session.createQuery(criteriaQuery);
        try {
            Product product = query.getSingleResult();
            return product;
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public List<Product> products(SystemUser user, Boolean owner, Boolean active, Long areaId,Long storeId) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> root = criteriaQuery.from(Product.class);
        Predicate predicate = null;
        if (owner) {
            Path<SystemUser> systemUserPath = root.get("user");
            predicate = criteriaBuilder.and(criteriaBuilder.equal(systemUserPath, user));
        }
        if (active) {
            Path<Status> statuspath = root.get("status");
            Path<String> namePath = statuspath.get("name");
            if (predicate != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(namePath, "Activo"));
            } else {
                predicate = criteriaBuilder.and(criteriaBuilder.equal(namePath, "Activo"));
            }
        }
        if (areaId != null) {
            Path<Area> areaPath = root.get("area");
            Path<Long> areaIdPath = areaPath.get("id");
            if (predicate != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(areaIdPath, areaId));
            } else {
                predicate = criteriaBuilder.and(criteriaBuilder.equal(areaIdPath, areaId));
            }
        }
        if (storeId != null) {
            Path<SystemUser> userPath = root.get("user");
            Path<Store> storePath = userPath.get("store");
            Path<Long> storeIdpath = storePath.get("id");
            if (predicate != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(storeIdpath, storeId));
            } else {
                predicate = criteriaBuilder.and(criteriaBuilder.equal(storeIdpath, storeId));
            }
        }
        if (predicate != null) {
            criteriaQuery.select(root).where(predicate);
        } else {
            criteriaQuery.select(root);
        }
        Query<Product> query = session.createQuery(criteriaQuery);
        try {
            List<Product> products=query.getResultList();
            System.out.println(products.size());
            return products;
        } catch (Exception e) {
            return null;
        }
    }


    @Transactional(readOnly = true)
    public List<Product> productsByArea(Area area) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> root = criteriaQuery.from(Product.class);
        Path<Area>areaPath=root.get("area");
        Path<String> areaNamePath = areaPath.get("name");

        Path<Date>publicationDate=root.get("publicationDate");
        Path<Status> statuspath = root.get("status");
        Path<String> namePath = statuspath.get("name");
        Predicate predicate = criteriaBuilder.and(
                criteriaBuilder.equal(areaNamePath,area.getName()),
                criteriaBuilder.equal(namePath,"Activo")
        );
        criteriaQuery.select(root).where(predicate).orderBy(criteriaBuilder.desc(publicationDate));
        Query<Product> query = session.createQuery(criteriaQuery);

        query.setMaxResults(3);
        try {
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public List<Product> products(List<Long> productIds) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> root = criteriaQuery.from(Product.class);
        Path<Area>areaPath=root.get("area");
        Path<String> areaNamePath = areaPath.get("name");

        Path<Date>publicationDate=root.get("publicationDate");
        Path<Status> statuspath = root.get("status");
        Path<String> namePath = statuspath.get("name");
        Predicate predicate = criteriaBuilder.and(
                criteriaBuilder.equal(namePath,"Activo")
        );
        if(productIds!=null && productIds.size()>0){
            predicate=criteriaBuilder.and(predicate,criteriaBuilder.not(root.get("id").in(productIds)));
        }
        criteriaQuery.select(root).where(predicate).orderBy(criteriaBuilder.desc(publicationDate));
        Query<Product> query = session.createQuery(criteriaQuery);

        query.setMaxResults(3);
        try {
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

}
