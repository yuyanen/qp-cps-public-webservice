package qp.scs.repository;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import qp.scs.model.User;
import qp.scs.model.api.Entity;

//This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
//CRUD refers Create, Read, Update, Delete
@Repository(value = "baseRepository")
@Transactional
public class BaseRepository {

	protected transient Logger logger = LoggerFactory.getLogger(getClass());

//	@Autowired
//	private SessionFactory sessionFactory;
//
//	protected Session getSession() {
//		return sessionFactory.getCurrentSession();
//	}
	
    @Autowired
    private EntityManager entityManager;

    private Session getSession() {
        return entityManager.unwrap(Session.class);
    }

	/**
	 * Creates a hibernate query based on the HQL string
	 * 
	 * @param hql
	 * @return
	 */
	protected Query createQuery(String hql) {
		return getSession().createQuery(hql);
	}

	/**
	 * Retrieves the object with the specified id
	 * 
	 * @param cls
	 * @param id
	 * @return
	 */
	public <T extends Entity> T get(Class<T> cls, Serializable id) {
		T instance = getSession().get(cls, id);

		return instance;
	}
	
	public <T extends Entity> void save(T t) {
		getSession().save(t);
	}
}