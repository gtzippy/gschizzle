package edu.gatech.sad.project4;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import edu.gatech.sad.project4.entities.Administratortable;
import edu.gatech.sad.project4.entities.Coursetable;
import edu.gatech.sad.project4.entities.CoursetableHome;
import edu.gatech.sad.project4.entities.Professorstable;
import edu.gatech.sad.project4.entities.ProfessorstableHome;
import edu.gatech.sad.project4.entities.Resultstable;
import edu.gatech.sad.project4.entities.ResultstableHome;
import edu.gatech.sad.project4.entities.Studentpreferencestable;
import edu.gatech.sad.project4.entities.StudentpreferencestableHome;
import edu.gatech.sad.project4.entities.Studenttable;
import edu.gatech.sad.project4.entities.StudenttableHome;

public class InteractionLayer {
	private static final Log log = LogFactory.getLog(App.class);
	public void getProfessor(int id){
		SessionFactory sessionFactory = new Configuration().addClass(Professorstable.class).configure().buildSessionFactory();
    	try {
			Context ctx = new InitialContext();
			ctx.rebind("SessionFactory", sessionFactory);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	ProfessorstableHome ptHome = new ProfessorstableHome();
    	Transaction transaction = sessionFactory.getCurrentSession().beginTransaction();
        Professorstable professor = ptHome.findById(id);
        professor.setName("Marco");
        log.info(professor);
        transaction.rollback();
	}
	
	public void getStudent(int id){
		SessionFactory sessionFactory = new Configuration().addClass(Studenttable.class).configure().buildSessionFactory();
    	try {
			Context ctx = new InitialContext();
			ctx.rebind("SessionFactory", sessionFactory);

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	StudenttableHome ptHome = new StudenttableHome();
    	Transaction transaction = sessionFactory.getCurrentSession().beginTransaction();
        Studenttable student = ptHome.findById(id);
        student.setName("Marco");
        log.info(student);
        transaction.rollback();
	}
	
	public void getCourse(String id){
		SessionFactory sessionFactory = new Configuration().addClass(Coursetable.class).configure().buildSessionFactory();
    	try {
			Context ctx = new InitialContext();
			ctx.rebind("SessionFactory", sessionFactory);

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	CoursetableHome ptHome = new CoursetableHome();
    	Transaction transaction = sessionFactory.getCurrentSession().beginTransaction();
    	Coursetable course = ptHome.findById(id);
        log.info(course);
        transaction.rollback();
	}
	
	public void getResult(int studentId){
		SessionFactory sessionFactory = new Configuration().addClass(Resultstable.class).configure().buildSessionFactory();
    	try {
			Context ctx = new InitialContext();
			ctx.rebind("SessionFactory", sessionFactory);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	ResultstableHome ptHome = new ResultstableHome();
    	Transaction transaction = sessionFactory.getCurrentSession().beginTransaction();
    	Resultstable result = ptHome.findById(studentId);
    	result.toString();
        log.info(result);
        transaction.rollback();
	}
	
	public void getStudentPreference(int id){
		SessionFactory sessionFactory = new Configuration().addClass(Studentpreferencestable.class).configure().buildSessionFactory();
    	try {
			Context ctx = new InitialContext();
			ctx.rebind("SessionFactory", sessionFactory);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	StudentpreferencestableHome ptHome = new StudentpreferencestableHome();
    	Transaction transaction = sessionFactory.getCurrentSession().beginTransaction();
    	Studentpreferencestable preference = ptHome.findById(id);
    	preference.toString();
        log.info(preference);
        transaction.rollback();
	}
	
	public void getStudtendsAllPastResults(int studentId){
		SessionFactory sessionFactory = new Configuration().addClass(Resultstable.class).configure().buildSessionFactory();
    	try {
			Context ctx = new InitialContext();
			ctx.rebind("SessionFactory", sessionFactory);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Transaction transaction = sessionFactory.getCurrentSession().beginTransaction();
    	List<Resultstable> allResults = sessionFactory.getCurrentSession().createQuery("FROM Resultstable e WHERE e.studentPreferenceId = '"+studentId+"'").list();
    	ResultstableHome ptHome = new ResultstableHome();
    	for(Resultstable result: allResults){
    		result.toString();
            log.info(result);
    	}    	
        transaction.rollback();
        
	}
	
	public void getCourseCatalog(){
		SessionFactory sessionFactory = new Configuration().addClass(Coursetable.class).configure().buildSessionFactory();
		try {
			Context ctx = new InitialContext();
			ctx.rebind("SessionFactory", sessionFactory);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Transaction transaction = sessionFactory.getCurrentSession().beginTransaction();
		List<Coursetable> allResults = sessionFactory.getCurrentSession().createQuery("FROM Coursetable").list();
		for(Coursetable c: allResults){
			log.info(c);
		}
    	transaction.rollback();
	}
	
	public void getAllProfessorsCatalog(){
		SessionFactory sessionFactory = new Configuration().addClass(Professorstable.class).configure().buildSessionFactory();
		try {
			Context ctx = new InitialContext();
			ctx.rebind("SessionFactory", sessionFactory);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Transaction transaction = sessionFactory.getCurrentSession().beginTransaction();
		List<Coursetable> allResults = sessionFactory.getCurrentSession().createQuery("FROM Professorstable").list();
		for(Coursetable c: allResults){
			log.info(c);
		}
    	transaction.rollback();
	}
	
	public void getAllTas(){
		SessionFactory sessionFactory = new Configuration().addClass(Studenttable.class).configure().buildSessionFactory();
		try {
			Context ctx = new InitialContext();
			ctx.rebind("SessionFactory", sessionFactory);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Transaction transaction = sessionFactory.getCurrentSession().beginTransaction();
		List<Studenttable> allResults = sessionFactory.getCurrentSession().createQuery("FROM Studenttable e WHERE e.ta = "+1).list();
		for(Studenttable c: allResults){
			log.info(c);
		}
    	transaction.rollback();
	}
	

	
	public void assignProfessorToCourse(int professorId, String courseCode){
		SessionFactory sessionFactory = new Configuration().addClass(Coursetable.class).configure().buildSessionFactory();
		try {
			Context ctx = new InitialContext();
			ctx.rebind("SessionFactory", sessionFactory);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Transaction transaction = sessionFactory.getCurrentSession().beginTransaction();
		String hql = "UPDATE Coursetable set professorId = :professorId "
				+ "WHERE courseCode = :courseCode";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("professorId", professorId);
		query.setParameter("courseCode", courseCode);
		int result = query.executeUpdate();
		log.info(result);	
		transaction.commit();
				
	}
	
	public void assignTaWeightingToCourse(int taWeighting, String courseCode){
		SessionFactory sessionFactory = new Configuration().addClass(Coursetable.class).configure().buildSessionFactory();
		try {
			Context ctx = new InitialContext();
			ctx.rebind("SessionFactory", sessionFactory);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Transaction transaction = sessionFactory.getCurrentSession().beginTransaction();
		String hql = "UPDATE Coursetable set taWeighting = :taWeighting "
				+ "WHERE courseCode = :courseCode";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("taWeighting", taWeighting);
		query.setParameter("courseCode", courseCode);
		int result = query.executeUpdate();
		log.info(result);	
		transaction.commit();				
	}
	
	public void assignEnrollmentLimitToCourse(int enrollmentLimit, String courseCode){
		SessionFactory sessionFactory = new Configuration().addClass(Coursetable.class).configure().buildSessionFactory();
		try {
			Context ctx = new InitialContext();
			ctx.rebind("SessionFactory", sessionFactory);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Transaction transaction = sessionFactory.getCurrentSession().beginTransaction();
		String hql = "UPDATE Coursetable set enrollmentLimit = :enrollmentLimit "
				+ "WHERE courseCode = :courseCode";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("enrollmentLimit", enrollmentLimit);
		query.setParameter("courseCode", courseCode);
		int result = query.executeUpdate();
		log.info(result);	
		transaction.commit();				
	}
	
	public void addProfessorProficiencey(String courseId, int professorId){
		SessionFactory sessionFactory = new Configuration().addClass(Professorstable.class).configure().buildSessionFactory();
		try {
			Context ctx = new InitialContext();
			ctx.rebind("SessionFactory", sessionFactory);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Transaction transaction = sessionFactory.getCurrentSession().beginTransaction();
		ProfessorstableHome ptHome = new ProfessorstableHome();
        Professorstable professor = ptHome.findById(professorId);
        String courses = professor.getCourses();
        if(courses == null){
        	courses = "";
        }
        courses = courses +  courseId+",";
		String hql = "UPDATE Professorstable set courses = :courses "
				+ "WHERE professorId = :professorId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("courses", courses);
		query.setParameter("professorId", professorId);
		int result = query.executeUpdate();
		log.info(result);	
		transaction.commit();	
	}
	
	public void setStudentTa(int ta, int studentId){
		SessionFactory sessionFactory = new Configuration().addClass(Studenttable.class).configure().buildSessionFactory();
		try {
			Context ctx = new InitialContext();
			ctx.rebind("SessionFactory", sessionFactory);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Transaction transaction = sessionFactory.getCurrentSession().beginTransaction();
		String hql = "UPDATE Studentstable set ta = :ta "
				+ "WHERE studentId = :studentId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("ta", ta);
		query.setParameter("studentId", studentId);
		int result = query.executeUpdate();
		log.info(result);
		transaction.commit();		
	}
	
	public void addAddCourseCompleteToStudent(String courseCode, int studentId){
		SessionFactory sessionFactory = new Configuration().addClass(Studenttable.class).configure().buildSessionFactory();
		try {
			Context ctx = new InitialContext();
			ctx.rebind("SessionFactory", sessionFactory);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Transaction transaction = sessionFactory.getCurrentSession().beginTransaction();
		StudenttableHome ptHome = new StudenttableHome();
        Studenttable student = ptHome.findById(studentId);
        String takenCourses = student.getTakenCourses();
        if(takenCourses == null){
        	takenCourses = "";
        }
        takenCourses = takenCourses +  courseCode+",";
		String hql = "UPDATE Studentstable set takenCourses = :courses "
				+ "WHERE studentId = :studentId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("takenCourses", takenCourses);
		query.setParameter("studentId", studentId);
		int result = query.executeUpdate();
		log.info(result);
		transaction.commit();
	}
	
	public void removeStudent(int studentId){
		SessionFactory sessionFactory = new Configuration().addClass(Studenttable.class).configure().buildSessionFactory();
    	try {
			Context ctx = new InitialContext();
			ctx.rebind("SessionFactory", sessionFactory);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Transaction transaction = sessionFactory.getCurrentSession().beginTransaction();
    	String hql = "DELETE FROM Studenttable "
				+ "WHERE studentId = :studentId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("studentId", studentId);
		int result = query.executeUpdate();
        log.info(result);
        transaction.commit();	
	}
	
	public void removeProfessor(int professorId){
		SessionFactory sessionFactory = new Configuration().addClass(Professorstable.class).configure().buildSessionFactory();
    	try {
			Context ctx = new InitialContext();
			ctx.rebind("SessionFactory", sessionFactory);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Transaction transaction = sessionFactory.getCurrentSession().beginTransaction();
    	String hql = "DELETE FROM Professorstable "
				+ "WHERE professorId = :professorId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("professorId", professorId);
		int result = query.executeUpdate();
        log.info(result);
        transaction.commit();		
	}
	
	public void changeStudentPassword(int studentId, String newPassword){
		SessionFactory sessionFactory = new Configuration().addClass(Studenttable.class).configure().buildSessionFactory();
		try {
			Context ctx = new InitialContext();
			ctx.rebind("SessionFactory", sessionFactory);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Transaction transaction = sessionFactory.getCurrentSession().beginTransaction();
		String hql = "UPDATE Studentstable set password = :newPassword "
				+ "WHERE studentId = :studentId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("newPassword", newPassword);
		query.setParameter("studentId", studentId);
		int result = query.executeUpdate();
		log.info(result);
		transaction.commit();
	}
	
	public void changeAdministratorPassword(int administratorId, String newPassword){
		SessionFactory sessionFactory = new Configuration().addClass(Administratortable.class).configure().buildSessionFactory();
		try {
			Context ctx = new InitialContext();
			ctx.rebind("SessionFactory", sessionFactory);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Transaction transaction = sessionFactory.getCurrentSession().beginTransaction();
		String hql = "UPDATE Administratortable set password = :newPassword "
				+ "WHERE id = :administratorId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("newPassword", newPassword);
		query.setParameter("administratorId", administratorId);
		int result = query.executeUpdate();
		log.info(result);
		transaction.commit();
	}
	
	
	
//	public void addStudent(String name, String password){
//		SessionFactory sessionFactory = new Configuration().addClass(Studenttable.class).configure().buildSessionFactory();
//    	try {
//			Context ctx = new InitialContext();
//			ctx.rebind("SessionFactory", sessionFactory);
//		} catch (NamingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    	Transaction transaction = sessionFactory.getCurrentSession().beginTransaction();
////    	String hql = "INSERT INTO Studenttable "
////				+ "WHERE studentId = :studentId";
//    	String hql = "INTER INTO Studenttable (name, password) SELECT 1,?";
//		Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter(name, name).setParameter(passowrd, password);
//		//query.setParameter("studentId", studentId);
//		int result = query.executeUpdate();
//        log.info(result);
//        transaction.commit();
//	}
	
}
