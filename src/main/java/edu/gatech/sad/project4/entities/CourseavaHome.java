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
 * Home object for domain model class Courseava.
 * @see edu.gatech.sad.project4.entities.Courseava
 * @author Hibernate Tools
 */
public class CourseavaHome {

	private static final Log log = LogFactory.getLog(CourseavaHome.class);

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

	public void persist(Courseava transientInstance) {
		log.debug("persisting Courseava instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Courseava instance) {
		log.debug("attaching dirty Courseava instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Courseava instance) {
		log.debug("attaching clean Courseava instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Courseava persistentInstance) {
		log.debug("deleting Courseava instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Courseava merge(Courseava detachedInstance) {
		log.debug("merging Courseava instance");
		try {
			Courseava result = (Courseava) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Courseava findById(java.lang.String id) {
		log.debug("getting Courseava instance with id: " + id);
		try {
			Courseava instance = (Courseava) sessionFactory.getCurrentSession()
					.get("edu.gatech.sad.project4.entities.Courseava", id);
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

	public List findByExample(Courseava instance) {
		log.debug("finding Courseava instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("edu.gatech.sad.project4.entities.Courseava")
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
