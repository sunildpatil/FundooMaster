package com.bridgelabz.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CollaboratorDetails")
public class Collaborator {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int collaboratorId;
	
	private int noteId;
	
	private String sharedTo;
	
	private int userId;
	
	public Collaborator() {

	}
	
	public int getCollaboratorId() {
		return collaboratorId;
	}

	public void setCollaboratorId(int collaboratorId) {
		this.collaboratorId = collaboratorId;
	}

	public int getNoteId() {
		return noteId;
	}

	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}

	public String getSharedTo() {
		return sharedTo;
	}

	public void setSharedTo(String sharedTo) {
		this.sharedTo = sharedTo;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Collaborator [collaboratorId=" + collaboratorId + ", noteId=" + noteId + ", sharedTo=" + sharedTo
				+ ", userId=" + userId + "]";
	}
}