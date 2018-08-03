package com.bridgelabz.models;

public class NoteCreateDTO {

	private String title;
	private String description;
	private String color = "#fff";
		
	public NoteCreateDTO() {

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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "NoteCreateDTO [title=" + title + ", description=" + description + ", color=" + color + "]";
	}
}
