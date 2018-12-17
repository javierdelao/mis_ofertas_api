package com.mis_ofertas_api.app.repository;

import com.mis_ofertas_api.app.model.Product;
import com.mis_ofertas_api.app.model.Valoration;
import com.mis_ofertas_api.app.model.Visit;
import com.mis_ofertas_api.app.util.VisitReport;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class VisitDAO extends BeanDAO<Visit> {

    @Transactional(readOnly = true)
    public Visit visit(Long id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Visit> criteriaQuery = criteriaBuilder.createQuery(Visit.class);
        Root<Visit> root = criteriaQuery.from(Visit.class);
        Path<Long> idPath = root.get("id");
        criteriaQuery.select(root)
                .where(criteriaBuilder.equal(idPath, id));
        Query<Visit> query = session.createQuery(criteriaQuery);
        try {
            return query.getSingleResult();
        }catch (Exception e){
            return null;
        }
    }

    @Transactional(readOnly = true)
    public List<Visit> qta(Long productid) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Visit> criteriaQuery = criteriaBuilder.createQuery(Visit.class);
        Root<Visit> root = criteriaQuery.from(Visit.class);
        Path<Product> productPath = root.get("product");
        criteriaQuery.select(root)
                .where(criteriaBuilder.equal(productPath.get("id"), productid));
        Query<Visit> query = session.createQuery(criteriaQuery);
        try {
            return query.getResultList();
        }catch (Exception e){
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<VisitReport> qta2(List<Product>products) {
        Session session = sessionFactory.getCurrentSession();
        List<VisitReport> visitReports = new ArrayList<>();
        for(Product product:products){
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Visit> criteriaQuery = criteriaBuilder.createQuery(Visit.class);
            Root<Visit> root = criteriaQuery.from(Visit.class);
            Path<Product> productPath = root.get("product");
            criteriaQuery.select(root)
                    .where(criteriaBuilder.equal(productPath, product));
            Query<Visit> query = session.createQuery(criteriaQuery);
            try {
                List<Visit>visits=query.getResultList();
                VisitReport visitReport = new VisitReport();
                visitReport.setProduct(product);
                visitReport.setVisitQta(visits.size());
                visitReports.add(visitReport);
            }catch (Exception e){
                throw e;
            }
        }
        return visitReports;
    }

    @Transactional(readOnly = true)
    public List<Visit> visitPerDay(Long productid, Date date,Date date2) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Visit> criteriaQuery = criteriaBuilder.createQuery(Visit.class);
        Root<Visit> root = criteriaQuery.from(Visit.class);
        Path<Product> productPath = root.get("product");
        Path<Date> datePath = root.get("visitDate");
        criteriaQuery.select(root)
                .where(criteriaBuilder.and(
                        criteriaBuilder.equal(productPath.get("id"), productid),
                        criteriaBuilder.between(datePath,date2,date))
                );
        Query<Visit> query = session.createQuery(criteriaQuery);
        try {
            return query.getResultList();
        }catch (Exception e){
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<Visit> visits() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Visit> criteriaQuery = criteriaBuilder.createQuery(Visit.class);
        Root<Visit> root = criteriaQuery.from(Visit.class);
        criteriaQuery.select(root);
        Query<Visit> query = session.createQuery(criteriaQuery);
        try {
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

}
