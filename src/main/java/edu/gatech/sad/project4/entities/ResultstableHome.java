package edu.gatech.sad.project4.entities;

// Generated Nov 21, 2015 2:37:21 PM by Hibernate Tools 3.4.0.CR1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class Resultstable.
 * @see edu.gatech.sad.project4.entities.Resultstable
 * @author Hibernate Tools
 */
public class ResultstableHome {

	private static final Log log = LogFactory.getLog(ResultstableHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext()
					.lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Resultstable transientInstance) {
		log.debug("persisting Resultstable instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Resultstable instance) {
		log.debug("attaching dirty Resultstable instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Resultstable instance) {
		log.debug("attaching clean Resultstable instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Resultstable persistentInstance) {
		log.debug("deleting Resultstable instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Resultstable merge(Resultstable detachedInstance) {
		log.debug("merging Resultstable instance");
		try {
			Resultstable result = (Resultstable) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Resultstable findById(int id) {
		log.debug("getting Resultstable instance with id: " + id);
		try {
			Resultstable instance = (Resultstable) sessionFactory
					.getCurrentSession().get(
							"edu.gatech.sad.project4.entities.Resultstable", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Resultstable instance) {
		log.debug("finding Resultstable instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("edu.gatech.sad.project4.entities.Resultstable")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
