package com.bridgelabz.models;

public class NoteUpdateDTO {
	
	private int id;
	private String title;
	private String description;
	
	public NoteUpdateDTO() {

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

	@Override
	public String toString() {
		return "NoteUpdateDTO [id=" + id + ", title=" + title + ", description=" + description + "]";
	}
}