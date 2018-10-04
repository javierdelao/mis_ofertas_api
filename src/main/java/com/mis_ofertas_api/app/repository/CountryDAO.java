package com.mis_ofertas_api.app.repository;

import com.mis_ofertas_api.app.model.Country;
import com.mis_ofertas_api.app.model.SystemUser;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

@Repository
public class CountryDAO extends BeanDAO<Country> {



}
