package com.mis_ofertas_api.app.repository;

import com.mis_ofertas_api.app.model.Area;
import com.mis_ofertas_api.app.model.Product;
import com.mis_ofertas_api.app.model.SystemUser;
import com.mis_ofertas_api.app.model.Valoration;
import com.mis_ofertas_api.app.util.ProductValorationAverage;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
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

    @Transactional(readOnly = true)
    public List<Valoration> valorations(Product product) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Valoration> criteriaQuery = criteriaBuilder.createQuery(Valoration.class);
        Root<Valoration> root = criteriaQuery.from(Valoration.class);
        Path<Product> productPath = root.get("product");

        criteriaQuery.select(root)
                .where(criteriaBuilder.equal(productPath.get("id"), product.getId()));
        Query<Valoration> query = session.createQuery(criteriaQuery);
        try {
            List<Valoration> valorations=query.getResultList();
            System.out.println("valoratiosn for report  "+valorations.size());
            return valorations;
        }catch (Exception e){
            return null;
        }
    }


    @Transactional(readOnly = true)
    public List<ProductValorationAverage> valorations(List<Product> products) {
        Session session = sessionFactory.getCurrentSession();
        List<ProductValorationAverage> productValorationAverages = new ArrayList<>();

        for(Product product:products){
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Valoration> criteriaQuery = criteriaBuilder.createQuery(Valoration.class);
            Root<Valoration> root = criteriaQuery.from(Valoration.class);
            Path<Product> productPath = root.get("product");

            criteriaQuery.select(root)
                    .where(criteriaBuilder.equal(productPath.get("id"), product.getId()));
            Query<Valoration> query = session.createQuery(criteriaQuery);
            try {
                List<Valoration> valorations=query.getResultList();
                System.out.println("valoratiosn for report  "+valorations.size());
                Double cont=0.0;
                Double total=0.0;
                for(Valoration valoration: valorations){
                    cont++;
                    total=total+valoration.getValoration_star();
                }

                Double result= total/cont;
                ProductValorationAverage productValorationAverage=new ProductValorationAverage();
                productValorationAverage.setProduct(product);
                productValorationAverage.setAverageValorationProduct(result);
                if(productValorationAverage.getAverageValorationProduct()>0){
                    productValorationAverages.add(productValorationAverage);
                }
            }catch (Exception e){
                return null;
            }
        }
        return productValorationAverages;
    }


}
