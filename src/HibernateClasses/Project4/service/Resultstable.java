package Project4.service;

// Generated Nov 21, 2015 2:35:13 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * Resultstable generated by hbm2java
 */
public class Resultstable implements java.io.Serializable {

	private int idResults;
	private int studentPreferenceId;
	private Date date;

	public Resultstable() {
	}

	public Resultstable(int idResults, int studentPreferenceId, Date date) {
		this.idResults = idResults;
		this.studentPreferenceId = studentPreferenceId;
		this.date = date;
	}

	public int getIdResults() {
		return this.idResults;
	}

	public void setIdResults(int idResults) {
		this.idResults = idResults;
	}

	public int getStudentPreferenceId() {
		return this.studentPreferenceId;
	}

	public void setStudentPreferenceId(int studentPreferenceId) {
		this.studentPreferenceId = studentPreferenceId;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
