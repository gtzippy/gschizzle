package edu.gatech.sad.project4.entities;

// Generated Nov 23, 2015 11:41:06 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * Processingstatustable generated by hbm2java
 */
public class Processingstatustable implements java.io.Serializable {

	private int id;
	private Date registeredTime;
	private Date executionStartTime;
	private boolean completed;

	public Processingstatustable() {
	}

	public Processingstatustable(int id, Date registeredTime, boolean completed) {
		this.id = id;
		this.registeredTime = registeredTime;
		this.completed = completed;
	}

	public Processingstatustable(int id, Date registeredTime,
			Date executionStartTime, boolean completed) {
		this.id = id;
		this.registeredTime = registeredTime;
		this.executionStartTime = executionStartTime;
		this.completed = completed;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getRegisteredTime() {
		return this.registeredTime;
	}

	public void setRegisteredTime(Date registeredTime) {
		this.registeredTime = registeredTime;
	}

	public Date getExecutionStartTime() {
		return this.executionStartTime;
	}

	public void setExecutionStartTime(Date executionStartTime) {
		this.executionStartTime = executionStartTime;
	}

	public boolean isCompleted() {
		return this.completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

}