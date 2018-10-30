package com.mis_ofertas_api.app.repository;

import com.mis_ofertas_api.app.model.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
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
    public List<Product> products(SystemUser user,Boolean owner,Boolean active) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> root = criteriaQuery.from(Product.class);
        Predicate predicate=null;
        if(owner){
            Path<SystemUser>systemUserPath=root.get("user");
            predicate=criteriaBuilder.and(criteriaBuilder.equal(systemUserPath,user));
        }
        if(active){
            Path<Status>statuspath=root.get("status");
            Path<String>namePath=statuspath.get("name");
            if(predicate!=null){
                predicate=criteriaBuilder.and(predicate,criteriaBuilder.equal(namePath,"Activo"));
            }else{
                predicate=criteriaBuilder.and(criteriaBuilder.equal(namePath,"Activo"));
            }
        }
        if(predicate!=null){
            criteriaQuery.select(root).where(predicate);
        }else{
            criteriaQuery.select(root);
        }
        Query<Product> query = session.createQuery(criteriaQuery);
        try {
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

}
