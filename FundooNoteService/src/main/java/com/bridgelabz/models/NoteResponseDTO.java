package com.bridgelabz.models;

public class NoteResponseDTO {

	private int id;
	private String title;
	private String description;
	private String updatedDate;
	private String color;
	private String reminderDate;
	private String reminderTime;
	private String reminderFrequency;
	
	public NoteResponseDTO() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
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

	@Override
	public String toString() {
		return "NoteResponseDTO [id=" + id + ", title=" + title + ", description=" + description + ", updatedDate="
				+ updatedDate + ", color=" + color + ", reminderDate=" + reminderDate + ", reminderTime=" + reminderTime
				+ ", reminderFrequency=" + reminderFrequency + "]";
	}

}