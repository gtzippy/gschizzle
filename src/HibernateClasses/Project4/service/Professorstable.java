package Project4.service;

// Generated Nov 21, 2015 2:35:13 PM by Hibernate Tools 3.4.0.CR1

/**
 * Professorstable generated by hbm2java
 */
public class Professorstable implements java.io.Serializable {

	private int professorId;
	private String name;
	private String courses;

	public Professorstable() {
	}

	public Professorstable(int professorId, String name, String courses) {
		this.professorId = professorId;
		this.name = name;
		this.courses = courses;
	}

	public int getProfessorId() {
		return this.professorId;
	}

	public void setProfessorId(int professorId) {
		this.professorId = professorId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCourses() {
		return this.courses;
	}

	public void setCourses(String courses) {
		this.courses = courses;
	}

}
