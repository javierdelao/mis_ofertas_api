package com.mis_ofertas_api.app.repository;

import com.mis_ofertas_api.app.model.Area;
import com.mis_ofertas_api.app.model.Product;
import com.mis_ofertas_api.app.model.SystemUser;
import com.mis_ofertas_api.app.util.CustomProductList;
import com.mis_ofertas_api.app.util.CustomProductListItem;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AreaDAO extends BeanDAO<Area> {


    private ProductDAO productDAO;

    @Autowired
    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

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


    @Transactional(readOnly = true)
    public List<Tuple> areas(SystemUser user) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
        Root<Product> root = criteriaQuery.from(Product.class);


        criteriaQuery.multiselect(root);
        Query<Tuple> query = session.createQuery(criteriaQuery);
        try {
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public CustomProductList areas2(SystemUser user) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery(Object.class);


        Query<Object> query = session.createSQLQuery("select count(area.id) as cantidad,area.id,area.name from visit \n" +
                "inner join product on visit.product_id=product.id\n" +
                "inner join area on product.area_id=area.id\n" +
                "where visit.user_id=11\n" +
                "group by area.id,area.name\n" +
                "order by cantidad desc;");
        try {
            List<Object> tuples = query.getResultList();
            CustomProductList customProductList=new CustomProductList();
            customProductList.setCustomProductListItems(new ArrayList<CustomProductListItem>());
            List<Long>productIds=new ArrayList<>();
            for (Object tuple : tuples) {
                Object[] objects=(Object[])tuple;
                System.out.println(objects[1]);
                Area area=area(Long.parseLong(objects[1].toString()));
                CustomProductListItem customProductListItem=new CustomProductListItem();
                customProductListItem.setArea(area);
                List<Product>products=productDAO.productsByArea(area);
                for(Product product:products){
                    productIds.add(product.getId());
                }
                customProductListItem.setProducts(products);
                System.out.println(products);
                customProductList.getCustomProductListItems().add(customProductListItem);
            }
            CustomProductListItem customProductListItem=new CustomProductListItem();
            customProductListItem.setProducts(productDAO.products(productIds));
            customProductList.getCustomProductListItems().add(customProductListItem);
            return customProductList;
        } catch (Exception e) {
            return null;
        }

    }


}
