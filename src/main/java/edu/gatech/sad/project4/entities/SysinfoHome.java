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
 * Home object for domain model class Sysinfo.
 * @see edu.gatech.sad.project4.entities.Sysinfo
 * @author Hibernate Tools
 */
public class SysinfoHome {

	private static final Log log = LogFactory.getLog(SysinfoHome.class);

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

	public void persist(Sysinfo transientInstance) {
		log.debug("persisting Sysinfo instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Sysinfo instance) {
		log.debug("attaching dirty Sysinfo instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Sysinfo instance) {
		log.debug("attaching clean Sysinfo instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Sysinfo persistentInstance) {
		log.debug("deleting Sysinfo instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Sysinfo merge(Sysinfo detachedInstance) {
		log.debug("merging Sysinfo instance");
		try {
			Sysinfo result = (Sysinfo) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Sysinfo findById(edu.gatech.sad.project4.entities.SysinfoId id) {
		log.debug("getting Sysinfo instance with id: " + id);
		try {
			Sysinfo instance = (Sysinfo) sessionFactory.getCurrentSession()
					.get("edu.gatech.sad.project4.entities.Sysinfo", id);
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

	public List findByExample(Sysinfo instance) {
		log.debug("finding Sysinfo instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("edu.gatech.sad.project4.entities.Sysinfo")
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
