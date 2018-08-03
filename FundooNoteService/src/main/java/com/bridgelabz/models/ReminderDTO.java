package com.bridgelabz.models;

public class ReminderDTO {
	
	private int noteId;
	
	private String reminderDate;
	
	private String reminderTime;
	
	private String reminderFrequency;

	public int getNoteId() {
		return noteId;
	}

	public String getReminderDate() {
		return reminderDate;
	}

	public void setReminderDate(String reminderDate) {
		this.reminderDate = reminderDate;
	}

	public String getReminderTime() {
		return reminderTime;
	}

	public void setReminderTime(String reminderTime) {
		this.reminderTime = reminderTime;
	}

	public String getReminderFrequency() {
		return reminderFrequency;
	}

	public void setReminderFrequency(String reminderFrequency) {
		this.reminderFrequency = reminderFrequency;
	}

	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}

	@Override
	public String toString() {
		return "ReminderDTO [noteId=" + noteId + ", reminderDate=" + reminderDate + ", reminderTime=" + reminderTime
				+ ", reminderFrequency=" + reminderFrequency + "]";
	}

}
