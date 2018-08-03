package com.bridgelabz.models;

public class LabelResponseDTO {

	private int labelId;
	
	private String labelName;
	
	private String createdDate;
	
	private String lastUpdateDate;
	
	private String noteId;

	public LabelResponseDTO() {

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

	@Override
	public String toString() {
		return "LabelResponse [labelId=" + labelId + ", labelName=" + labelName + ", createdDate=" + createdDate
				+ ", lastUpdateDate=" + lastUpdateDate + ", noteId=" + noteId + "]";
	}
}
