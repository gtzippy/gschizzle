package edu.gatech.sad.project4.entities;

// Generated Nov 25, 2015 3:17:11 AM by Hibernate Tools 3.4.0.CR1

/**
 * ProfessorcourseassignmenttableId generated by hbm2java
 */
public class ProfessorcourseassignmenttableId implements java.io.Serializable {

	private int processingStatusId;
	private int professorId;

	public ProfessorcourseassignmenttableId() {
	}

	public ProfessorcourseassignmenttableId(int processingStatusId,
			int professorId) {
		this.processingStatusId = processingStatusId;
		this.professorId = professorId;
	}

	public int getProcessingStatusId() {
		return this.processingStatusId;
	}

	public void setProcessingStatusId(int processingStatusId) {
		this.processingStatusId = processingStatusId;
	}

	public int getProfessorId() {
		return this.professorId;
	}

	public void setProfessorId(int professorId) {
		this.professorId = professorId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ProfessorcourseassignmenttableId))
			return false;
		ProfessorcourseassignmenttableId castOther = (ProfessorcourseassignmenttableId) other;

		return (this.getProcessingStatusId() == castOther
				.getProcessingStatusId())
				&& (this.getProfessorId() == castOther.getProfessorId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getProcessingStatusId();
		result = 37 * result + this.getProfessorId();
		return result;
	}

}