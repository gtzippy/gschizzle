package edu.gatech.sad.project4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import edu.gatech.sad.project4.entities.Administratortable;
import edu.gatech.sad.project4.entities.AdministratortableHome;
import edu.gatech.sad.project4.entities.Coursetable;
import edu.gatech.sad.project4.entities.CoursetableHome;
import edu.gatech.sad.project4.entities.Offeredcoursestable;
import edu.gatech.sad.project4.entities.Processingstatustable;
import edu.gatech.sad.project4.entities.Professorstable;
import edu.gatech.sad.project4.entities.ProfessorstableHome;
import edu.gatech.sad.project4.entities.Studentcourseassignmenttable;
import edu.gatech.sad.project4.entities.Studentpreferencestable;
import edu.gatech.sad.project4.entities.StudentpreferencestableHome;
import edu.gatech.sad.project4.entities.Studenttable;
import edu.gatech.sad.project4.entities.StudenttableHome;
import edu.gatech.sad.project4.entities.Tacourseassignmenttable;

public class InteractionLayer {
	private static final Log log = LogFactory.getLog(App.class);
	private SessionFactory sess;
	/**
	 * returns a single Professorstable object
	 * @param professorId
	 */
	public InteractionLayer(SessionFactory sessionFactory){
		sess = sessionFactory;
	}
	public Professorstable getProfessor(Integer professorId){
		Session s = sess.getCurrentSession();
		Transaction transaction = s.beginTransaction();
    	ProfessorstableHome ptHome = new ProfessorstableHome();
        Professorstable professor = ptHome.findById(professorId);
        transaction.rollback();
        log.info(professor);
        return professor;
	}
	/**
	 * returns a single Studenttable object
	 * @param studentId
	 */
	public void getStudent(Integer studentId){
		Session s = sess.getCurrentSession();
		Transaction transaction = s.beginTransaction();
    	StudenttableHome ptHome = new StudenttableHome();
        Studenttable student = ptHome.findById(studentId);
        log.info(student);
        transaction.rollback();
	}
	/**
	 * returns a course
	 * @param courseCode
	 */
	public Coursetable getCourse(String courseCode){
		Session s = sess.getCurrentSession();
		Transaction transaction = s.beginTransaction();
    	CoursetableHome ptHome = new CoursetableHome();
    	Coursetable course = ptHome.findById(courseCode);
        log.info(course);
        transaction.rollback();
        return course;
	}
	
	/**
	 * returns list of all courses
	 */
	public List<Coursetable> getCourseCatalog(){
		Session s = sess.getCurrentSession();
		Transaction transaction = s.beginTransaction();
		List<Coursetable> courses = s.createCriteria(Coursetable.class).list();
		for(Coursetable c: courses){
			log.info(c);
		}
		transaction.rollback();
		return courses;
	}
	/**
	 * returns list of all professors
	 */
	public void getAllProfessors(){
		Session s = sess.getCurrentSession();
		Transaction transaction = s.beginTransaction();
		List<Professorstable> allResults = s.createCriteria(Professorstable.class).list();	
		for(Professorstable c: allResults){
			log.info(c);
		}
	    transaction.rollback();
	}
	/**
	 * get all preferences for a given student
	 * @param studentId student id
	 * @return list of studentpreferencestable objects
	 */
	public List<Studentpreferencestable> getAllStudentPreferencesForStudent(Integer studentId){
		Session s = sess.getCurrentSession();
		Transaction transaction = s.beginTransaction();
		List<Studentpreferencestable> sptList = s.createCriteria(Studentpreferencestable.class).add(Restrictions.like("studentId", studentId)).list();
		transaction.rollback();
		return sptList;
	}
	/**
	 * returns a Studentpreferencetable object
	 * @param preferenceId
	 */
	public void getStudentPreference(Integer preferenceId){
		Session s = sess.getCurrentSession();
		Transaction transaction = s.beginTransaction();
    	StudentpreferencestableHome ptHome = new StudentpreferencestableHome();
    	Studentpreferencestable preference = ptHome.findById(preferenceId);
    	preference.toString();
        log.info(preference);
        transaction.commit();
	}
	/**
	 * edit number for desired coursed for student preference entry
	 * @param numCourses number of desired courses
	 * @param preferenceId preference id
	 */
	public void changeStudentPreferenceNumCoursesDesired(Integer numCourses, Integer preferenceId){
		Session s = sess.getCurrentSession();
		Transaction transaction = s.beginTransaction();
		StudentpreferencestableHome spsHome = new StudentpreferencestableHome();
		Studentpreferencestable student = spsHome.findById(preferenceId);
		student.setNumCoursesDesired(numCourses);
		spsHome.persist(student);
		transaction.commit();
		engineCall();
	}
	/**
	 * add courses to a student preference entry
	 * @param courses courses to remove
	 * @param preferenceId preference id
	 */
	public void addCoursesToStudentpreferencetable(String courses, Integer preferenceId){
		List<Coursetable> courseTableList = getCourseCatalog();
		Session s = sess.getCurrentSession();
		Transaction transaction = s.beginTransaction();
		StudentpreferencestableHome spsHome = new StudentpreferencestableHome();
		Studentpreferencestable student = spsHome.findById(preferenceId);
		List<String> coursesToAdd = convertStringToList(courses);
		List<String> currentCourses = convertStringToList(student.getCourses());
		for(String c: coursesToAdd){
			if(!currentCourses.contains(c) && isValidCourse(courseTableList, c)){
				currentCourses.add(c);
			}
		}
		String newCourseList = String.join(",", currentCourses);
		student.setCourses(newCourseList);
		spsHome.persist(student);
		transaction.commit();
		engineCall();
	}
	/**
	 * remove courses from a student preference entry
	 * @param courses courses to remove
	 * @param preferenceId preference id
	 */
	public void removeCoursesFromStudentpreferencetable(String courses, Integer preferenceId){
		List<Coursetable> courseTableList = getCourseCatalog();
		Session s = sess.getCurrentSession();
		Transaction transaction = s.beginTransaction();
		StudentpreferencestableHome spsHome = new StudentpreferencestableHome();
		Studentpreferencestable student = spsHome.findById(preferenceId);
		List<String> coursesToRemove = convertStringToList(courses);
		List<String> currentCourses = convertStringToList(student.getCourses());
		for(String c: coursesToRemove){
			if(currentCourses.contains(c)){
				currentCourses.remove(c);
			}
		}
		String newCourseList = String.join(",", currentCourses);
		student.setCourses(newCourseList);
		spsHome.persist(student);
		transaction.commit();
		engineCall();
	}
	/**
	 * add a new student preference entry
	 * @param studentId studnet Id
	 * @param courses desired courses
	 * @param numCoursesDesired number of courses desired
	 */
	public void addNewStudentpreference(Integer studentId, String courses, Integer numCoursesDesired){
		Session s = sess.getCurrentSession();
		Transaction transaction = s.beginTransaction();
		StudentpreferencestableHome spsHome = new StudentpreferencestableHome();
		Studentpreferencestable student = new Studentpreferencestable();
		student.setStudentId(studentId);
		student.setCourses(courses);
		student.setNumCoursesDesired(numCoursesDesired);
		//student.setDate(new Date());
		spsHome.persist(student);
		transaction.commit();
		engineCall();
	}
	/**
	 * remove a student preference entry
	 * @param preferenceId preference id
	 */
	public void deleteStudentpreference(Integer preferenceId){
		Session s = sess.getCurrentSession();
		Transaction transaction = s.beginTransaction();
		StudentpreferencestableHome spsHome = new StudentpreferencestableHome();		
		Studentpreferencestable student = spsHome.findById(preferenceId);
		spsHome.delete(student);
		transaction.commit();
		engineCall();
	}
	/**
	 * clear all courses from a student preference entry
	 * @param preferenceId preference id
	 */
	public void clearAllStudentpreferenceCourses(Integer preferenceId){
		Session s = sess.getCurrentSession();
		Transaction transaction = s.beginTransaction();
		StudentpreferencestableHome spsHome = new StudentpreferencestableHome();		
		Studentpreferencestable student = spsHome.findById(preferenceId);
		String emptyCourseList = String.join(",", new ArrayList<String>());
		student.setCourses(emptyCourseList);
		spsHome.persist(student);
		transaction.commit();
		engineCall();
	}
	/**
	 * set ta weighting for a course
	 * @param taWeighting ta weighting
	 * @param courseCode course code
	 */
	public void assignTaWeightingToCourse(Integer taWeighting, String courseCode){
		Session s = sess.getCurrentSession();
		Transaction transaction = s.beginTransaction();
		CoursetableHome cat = new CoursetableHome();
		Coursetable course = cat.findById(courseCode);
		course.setTaWeighting(taWeighting);
		cat.persist(course);
		log.info(course);
		transaction.commit();
		engineCall();		
	}
	/**
	 * change enrollment limit of a course
	 * @param enrollmentLimit enrollment limit
	 * @param courseCode coures code
	 */
	public void assignEnrollmentLimitToCourse(Integer enrollmentLimit, String courseCode){
		Session s = sess.getCurrentSession();
		Transaction transaction = s.beginTransaction();
		CoursetableHome cat = new CoursetableHome();
		Coursetable course = cat.findById(courseCode);
		course.setEnrollmentLimit(enrollmentLimit);
		cat.persist(course);
		log.info(course);
		transaction.commit();
		engineCall();
	}
	/**
	 * adds valid proficiencies to a professor.  A valid proficiency is define as being found in teh course catalog
	 * @param courseId
	 * @param professorId
	 */
	public void addProfessorProficiency(String courseId, Integer professorId){
		List<Coursetable> validCourses = getCourseCatalog();
		Session s = sess.getCurrentSession();
		Transaction transaction = s.beginTransaction();
		ProfessorstableHome ptHome = new ProfessorstableHome();
        Professorstable professor = ptHome.findById(professorId);
        String courses = professor.getCourses();
        List<String> currentProficiencey = new ArrayList<String>();
        currentProficiencey.addAll(convertStringToList(courses));
        List<String> newProficiencies = convertStringToList(courseId);
        for(String c: newProficiencies){
        	if(!currentProficiencey.contains(c) && isValidCourse(validCourses, c)){
        		currentProficiencey.add(c);
        	}
        }
        courses = String.join(",", currentProficiencey);
        professor.setCourses(courses);
		ptHome.persist(professor);
		log.info(professor);
		transaction.commit();
		engineCall();
	}
	/**
	 * remove proficiencies from a professor
	 * @param professorId professor id
	 * @param courseId course id
	 */
	public void removeProfessorProficiency(Integer professorId, String courseId){
		Session s = sess.getCurrentSession();
		Transaction transaction = s.beginTransaction();
		ProfessorstableHome ptHome = new ProfessorstableHome();
        Professorstable professor = ptHome.findById(professorId);
        List<String> proficienciesToRemove = convertStringToList(courseId);
        String courses = professor.getCourses();
        List<String> currentProficiencey = new ArrayList<String>();
        currentProficiencey.addAll(convertStringToList(courses));
        for(String c: proficienciesToRemove){
        	if(currentProficiencey.contains(c)){
        		currentProficiencey.remove(c);
        	}
        }
        courses = String.join(",", currentProficiencey);
        professor.setCourses(courses);
		ptHome.persist(professor);
		log.info(professor);
		transaction.commit();
		engineCall();
	}
	
	/**
	 * clear courses from professor proficiency
	 * @param professorId
	 */
	public void clearProfessorProficiency(Integer professorId){
		Session s = sess.getCurrentSession();
		Transaction transaction = s.beginTransaction();
		ProfessorstableHome ptHome = new ProfessorstableHome();
        Professorstable professor = ptHome.findById(professorId);
        professor.setCourses(null);
        log.info(professor);
		transaction.commit();
		engineCall();
	}
	
	/**
	 * set a student's ta flag
	 * @param ta ta flag: 1 = ta, 0 = not a ta
	 * @param studentId student id
	 */
	public void setStudentTa(Integer ta, Integer studentId){
		Session s = sess.getCurrentSession();
		Transaction transaction = s.beginTransaction();
		StudenttableHome sth = new StudenttableHome();
		Studenttable student = sth.findById(studentId);
		boolean taValue = false;
		if(ta == 1){taValue = true;}
		student.setTa(taValue);
		sth.persist(student);
		log.info(student);
		transaction.commit();
		engineCall();
	}
	/**
	 * add a course to student's completed course history
	 * @param courseCode course code
	 * @param studentId student id
	 */
	public void addCourseCompleteToStudent(String courseCode, Integer studentId){
		List<Coursetable> courseList = getCourseCatalog();
		Session s = sess.getCurrentSession();
		Transaction transaction = s.beginTransaction();
		StudenttableHome sth = new StudenttableHome();
		Studenttable student = sth.findById(studentId);		
		List<String> coursesTaken = new ArrayList<String>();
		coursesTaken.addAll(convertStringToList(student.getTakenCourses()));
		List<String> coursesRecentlyCompleted = convertStringToList(courseCode);
		for(String c: coursesRecentlyCompleted){
			if(!coursesTaken.contains(c) && isValidCourse(courseList, c)){
				coursesTaken.add(c);
			}
		}
		String updatedCourses = String.join(",", coursesTaken);
		student.setTakenCourses(updatedCourses);
		sth.persist(student);
		log.info(student);
		transaction.commit();
		engineCall();
	}
	/**
	 * remove a student 
	 * @param studentId student id
	 */
	public void removeStudent(Integer studentId){
		Session s = sess.getCurrentSession();
		Transaction transaction = s.beginTransaction();
		StudenttableHome sth = new StudenttableHome();
		Studenttable student = sth.findById(studentId);	
		sth.delete(student);
    	transaction.commit();
        engineCall();
	}
	/**
	 * remove a professor
	 * @param professorId professor id
	 */
	public void removeProfessor(Integer professorId){
		Session s = sess.getCurrentSession();
		Transaction transaction = s.beginTransaction();
		ProfessorstableHome pth = new ProfessorstableHome();
		Professorstable professor = pth.findById(professorId);
		pth.delete(professor);
    	transaction.commit();
        engineCall();
	}
	/**
	 * change a students's password
	 * @param studentId student id
	 * @param newPassword new password
	 */
	public void changeStudentPassword(Integer studentId, String newPassword){
		Session s = sess.getCurrentSession();
		Transaction transaction = s.beginTransaction();
		StudenttableHome sth = new StudenttableHome();
		Studenttable student = sth.findById(studentId);
		student.setPassword(newPassword);
		sth.persist(student);
		log.info(student);
		transaction.commit();
		engineCall();
	}
	/**
	 * change an asministrator's password
	 * @param administratorId administratorId
	 * @param newPassword new password
	 */
	public void changeAdministratorPassword(Integer administratorId, String newPassword){
		Session s = sess.getCurrentSession();
		Transaction transaction = s.beginTransaction();
		AdministratortableHome ath = new AdministratortableHome();
		Administratortable admin = ath.findById(administratorId);
		admin.setPassword(newPassword);
		ath.persist(admin);
		log.info(admin);
		transaction.commit();
	}
	
	
	/**
	 * Add a new student
	 * @param name  student name
	 * @param password student password
	 */
	public void addStudent(String name, String password){
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = s.beginTransaction();
    	StudenttableHome sth = new StudenttableHome();
    	Studenttable student = new Studenttable();
    	student.setName(name);
    	student.setPassword(password);
    	sth.persist(student);
        log.info(student);
        transaction.commit();
        engineCall();
	}
	/**
	 * add a new professor
	 * @param name professor name
	 */
	public void addProfessor(String name){
		Session s = sess.getCurrentSession();
		Transaction transaction = s.beginTransaction();
    	ProfessorstableHome pth = new ProfessorstableHome();
    	Professorstable professor = new Professorstable();
    	professor.setName(name);
    	pth.persist(professor);
    	transaction.commit();
    	engineCall();
	}
	/**
	 * get all currently offered coursesCodes 
	 * @return list of course codes
	 */
	public List<String> getOfferedCourses(){
		Session s = sess.getCurrentSession();
		Transaction transaction = s.beginTransaction();
		List<Offeredcoursestable> oct = s.createCriteria(Offeredcoursestable.class).list();
		List<String> courseCodes = new ArrayList<String>();
		for(Offeredcoursestable o: oct){
			courseCodes.add(o.getCourseCode());
		}
		transaction.rollback();
		return courseCodes;
	}
	/**
	 * get all students enrolled in a course
	 * @param courseCode course code
	 * @return list of student ids
	 */
	public List<Integer> getStudentsInCourse(String courseCode){
		List<Integer> studentIds = new ArrayList<Integer>();
		Session s = sess.getCurrentSession();
		Transaction transaction = s.beginTransaction();
		List<Studentcourseassignmenttable> students = s.createCriteria(Studentcourseassignmenttable.class).add(Restrictions.like("courseCode", courseCode)).list();
    	for(Studentcourseassignmenttable student: students){
    		studentIds.add(student.getId().getStudentId());
    	}
    	transaction.rollback();
    	return studentIds;
	}
	/**
	 * get most recently completed processingstatustable entry
	 * @return Processingstatustable fields as strings
	 */
	public String getLatestCompleteProcessingstatustableEntry(){
		Session s = sess.getCurrentSession();
		Transaction transaction = s.beginTransaction();
		List<Processingstatustable> pstList = s.createCriteria(Processingstatustable.class).add(Restrictions.like("completed", 1)).list();
		Processingstatustable pst = pstList.get(pstList.size()+1);
		transaction.rollback();
		return pst.toString();
	}
	/**
	 * get all TAs for a given course
	 * @param courseCode courseCode
	 * @return list of studentIds for tas
	 */
	public List<Integer> getAllTasForCourse(String courseCode){
		List<Integer> taList = new ArrayList<Integer>();
		Session s = sess.getCurrentSession();
		Transaction transaction = s.beginTransaction();
		List<Tacourseassignmenttable> tcat = s.createCriteria(Tacourseassignmenttable.class).add(Restrictions.like("courseCode", courseCode)).list();
		for(Tacourseassignmenttable t: tcat){
    		taList.add(t.getId().getStudentId());
    	}
		transaction.rollback();
		return taList;
	}
	
	
	/**
	 * returns list of all student TAs
	 */
	public List<Integer> getAllTas(){
		List<Integer> taList = new ArrayList<Integer>();
		Session s = sess.getCurrentSession();
		Transaction transaction = s.beginTransaction();
		List<Studenttable> allResults = s.createCriteria(Studenttable.class).add(Restrictions.like("ta", true)).list();
		for(Studenttable c: allResults){
			taList.add(c.getId());
		}
		transaction.rollback();
		return taList;
		
	}
	/**
	 * convert a comma separated string to a list of string
	 * @param commasList comma separated string
	 * @return List of Strings
	 */
	private List<String> convertStringToList(String commasList){
		if(commasList == null){
			return new ArrayList<String>();
		}
		List<String> list = Arrays.asList(commasList.split("\\s*,\\s*"));
		return list;
	}
	/**
	 * check if a course code is valid
	 * @param validCourses list of valid course codes
	 * @param courseCode course code to check
	 * @return returns true if course code is valid
	 */
	private boolean isValidCourse(List<Coursetable> validCourses, String courseCode){
		List<String> validCourseCodes = new ArrayList<String>();
		for(Coursetable c: validCourses){
			validCourseCodes.add(c.getCourseCode());
		}
		return validCourseCodes.contains(courseCode);
	}
	
	private void engineCall(){
		
	}

}
