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
 * Home object for domain model class Professorstable.
 * @see edu.gatech.sad.project4.entities.Professorstable
 * @author Hibernate Tools
 */
public class ProfessorstableHome {

	private static final Log log = LogFactory.getLog(ProfessorstableHome.class);

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

	public void persist(Professorstable transientInstance) {
		log.debug("persisting Professorstable instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Professorstable instance) {
		log.debug("attaching dirty Professorstable instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Professorstable instance) {
		log.debug("attaching clean Professorstable instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Professorstable persistentInstance) {
		log.debug("deleting Professorstable instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Professorstable merge(Professorstable detachedInstance) {
		log.debug("merging Professorstable instance");
		try {
			Professorstable result = (Professorstable) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Professorstable findById(int id) {
		log.debug("getting Professorstable instance with id: " + id);
		try {
			Professorstable instance = (Professorstable) sessionFactory
					.getCurrentSession().get(
							"edu.gatech.sad.project4.entities.Professorstable", id);
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

	public List findByExample(Professorstable instance) {
		log.debug("finding Professorstable instance by example");
		try {
			List results = sessionFactory
					.getCurrentSession()
					.createCriteria("edu.gatech.sad.project4.entities.Professorstable")
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
