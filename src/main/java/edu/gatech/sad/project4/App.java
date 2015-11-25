package edu.gatech.sad.project4;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import edu.gatech.sad.project4.entities.Administratortable;
import edu.gatech.sad.project4.entities.Coursetable;
import edu.gatech.sad.project4.entities.Offeredcoursestable;
import edu.gatech.sad.project4.entities.Processingstatustable;
import edu.gatech.sad.project4.entities.Professorcourseassignmenttable;
import edu.gatech.sad.project4.entities.Professorstable;
import edu.gatech.sad.project4.entities.Studentcourseassignmenttable;
import edu.gatech.sad.project4.entities.Studentpreferencestable;
import edu.gatech.sad.project4.entities.Studenttable;
import edu.gatech.sad.project4.entities.Tacourseassignmenttable;

public class App 
{
	private static final Log log = LogFactory.getLog(App.class);
    public static void main( String[] args )
    {
    	SessionFactory sessionFactory = new Configuration().addClass(Professorstable.class)
    			.addClass(Studenttable.class)
    			.addClass(Administratortable.class)
    			.addClass(Studentpreferencestable.class).addClass(Processingstatustable.class)
    			.addClass(Coursetable.class).addClass(Professorcourseassignmenttable.class)
    			.addClass(Offeredcoursestable.class).addClass(Studentcourseassignmenttable.class)
    			.addClass(Tacourseassignmenttable.class).configure().buildSessionFactory();
    	try {
			Context ctx = new InitialContext();
			ctx.rebind("SessionFactory", sessionFactory);

		} catch (NamingException e) {
			log.error(e.getStackTrace());
			e.printStackTrace();
		}

    	//main class testing calls
    	
    	InteractionLayer iLayer = new InteractionLayer(sessionFactory);
    	//iLayer.getStudtendsAllPastResults(1);
    	//iLayer.getCourse("CS 4495");
    	//iLayer.assignProfessorToCourse(1, "CS 4495");
    	//iLayer.assignEnrollmentLimitToCourse(200, "CS 6300");
    	//iLayer.assignTaWeightingToCourse(8, "CS 4495");
    	//iLayer.addProfessorProficiency("CS 6035, CS 6300, moo face", 1);
    	//iLayer.removeProfessorProficiency(1, "CS 6035");
    	//iLayer.clearProfessorProficiencey(1);
    	//iLayer.removeProfessor(1);
    	iLayer.getCourseCatalog();
    	//iLayer.getAllProfessors();
    	//iLayer.getAllTas();
    	//iLayer.addStudent("Django","silence");
    	//iLayer.changeStudentPreferenceNumCoursesDesired(5, 3);
    	//iLayer.addNewStudentpreference(6809, "", 5);
    	//iLayer.deleteStudentpreference(606);
    	//iLayer.getAllStudentPreferencesForStudent(1);
    	//iLayer.setStudentTa(1, 4);
    	//iLayer.addCourseCompleteToStudent("CS 6300, moo cow moo", 1);
    }
}
