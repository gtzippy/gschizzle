package edu.gatech.sad.project4;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import edu.gatech.sad.project4.entities.Professorstable;
import edu.gatech.sad.project4.entities.ProfessorstableHome;

public class App 
{
	private static final Log log = LogFactory.getLog(App.class);
    public static void main( String[] args )
    {
    	InteractionLayer iLayer = new InteractionLayer();
    	//iLayer.getStudtendsAllPastResults(1);
    	//iLayer.getCourse("CS 4495");
    	//iLayer.assignProfessorToCourse(1, "CS 4495");
    	//iLayer.assignEnrollmentLimitToCourse(50, "CS 4495");
    	//iLayer.assignTaWeightingToCourse(4, "CS 4495");
    	//iLayer.addProfessorProficiencey("CS 6035", 1);
    	//iLayer.removeProfessor(1);
    	//iLayer.getCourseCatalog();
    	iLayer.getAllTas();
    }
}
