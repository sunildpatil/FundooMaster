package com.bridgelabz.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="LabelDetails")
public class Label {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int labelId;
	
	@NotNull(message="Please Enter Valid Label Name")
	@NotEmpty(message="Label Name should Not be Empty")
	private String labelName;
	
	private String createdDate;
	
	private String lastUpdateDate;
	
	private String noteId;
	
	private int userId;
	
	public Label() {

	}

	public int getLabelId() {
		return labelId;
	}

	public void setLabelId(int labelId) {
		this.labelId = labelId;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getNoteId() {
		return noteId;
	}

	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Label [labelId=" + labelId + ", labelName=" + labelName + ", createdDate=" + createdDate
				+ ", lastUpdateDate=" + lastUpdateDate + ", noteId=" + noteId + ", userId=" + userId + "]";
	}
}