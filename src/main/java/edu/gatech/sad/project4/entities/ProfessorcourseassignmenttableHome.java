package edu.gatech.sad.project4.entities;

// Generated Nov 25, 2015 3:17:11 AM by Hibernate Tools 3.4.0.CR1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class Professorcourseassignmenttable.
 * @see edu.gatech.sad.project4.entities.Professorcourseassignmenttable
 * @author Hibernate Tools
 */
public class ProfessorcourseassignmenttableHome {

	private static final Log log = LogFactory
			.getLog(ProfessorcourseassignmenttableHome.class);

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

	public void persist(Professorcourseassignmenttable transientInstance) {
		log.debug("persisting Professorcourseassignmenttable instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Professorcourseassignmenttable instance) {
		log.debug("attaching dirty Professorcourseassignmenttable instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Professorcourseassignmenttable instance) {
		log.debug("attaching clean Professorcourseassignmenttable instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Professorcourseassignmenttable persistentInstance) {
		log.debug("deleting Professorcourseassignmenttable instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Professorcourseassignmenttable merge(
			Professorcourseassignmenttable detachedInstance) {
		log.debug("merging Professorcourseassignmenttable instance");
		try {
			Professorcourseassignmenttable result = (Professorcourseassignmenttable) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Professorcourseassignmenttable findById(
			edu.gatech.sad.project4.entities.ProfessorcourseassignmenttableId id) {
		log.debug("getting Professorcourseassignmenttable instance with id: "
				+ id);
		try {
			Professorcourseassignmenttable instance = (Professorcourseassignmenttable) sessionFactory
					.getCurrentSession()
					.get("edu.gatech.sad.project4.entities.Professorcourseassignmenttable",
							id);
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

	public List findByExample(Professorcourseassignmenttable instance) {
		log.debug("finding Professorcourseassignmenttable instance by example");
		try {
			List results = sessionFactory
					.getCurrentSession()
					.createCriteria(
							"edu.gatech.sad.project4.entities.Professorcourseassignmenttable")
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
