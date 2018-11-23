package com.mis_ofertas_api.app.repository;

import com.mis_ofertas_api.app.model.Note;
import com.mis_ofertas_api.app.model.Product;
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
public class NoteDAO extends BeanDAO<Note> {

    @Transactional(readOnly = true)
    public Note note(Long id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Note> criteriaQuery = criteriaBuilder.createQuery(Note.class);
        Root<Note> root = criteriaQuery.from(Note.class);
        Path<Long> idPath = root.get("id");
        criteriaQuery.select(root)
                .where(criteriaBuilder.equal(idPath, id));
        Query<Note> query = session.createQuery(criteriaQuery);
        try {
            Note note = query.getSingleResult();
            return note;
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public List<Note> notes() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Note> criteriaQuery = criteriaBuilder.createQuery(Note.class);
        Root<Note> root = criteriaQuery.from(Note.class);
        criteriaQuery.select(root);
        Query<Note> query = session.createQuery(criteriaQuery);
        try {
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public List<Note> notes(Product product) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Note> criteriaQuery = criteriaBuilder.createQuery(Note.class);
        Root<Note> root = criteriaQuery.from(Note.class);
        Path<Product>productPath=root.get("product");
        criteriaQuery.select(root).where(criteriaBuilder.equal(productPath,product));
        Query<Note> query = session.createQuery(criteriaQuery);
        try {
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }


}
