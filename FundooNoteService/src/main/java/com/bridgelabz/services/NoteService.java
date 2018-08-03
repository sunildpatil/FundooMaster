package com.bridgelabz.services;

import java.util.List;
import java.util.Set;

import com.bridgelabz.exceptions.LabelException;
import com.bridgelabz.exceptions.NoteException;
import com.bridgelabz.models.NoteCreateDTO;
import com.bridgelabz.models.NoteResponseDTO;
import com.bridgelabz.models.NoteUpdateDTO;
import com.bridgelabz.models.ReminderDTO;

public interface NoteService {

	public void createNote(NoteCreateDTO noteDTO, int userId);

	public void updateNote(NoteUpdateDTO noteUpdateDTO, int userId) throws NoteException;

	public void updateNoteAddLabelId(int noteId, int labelId, int userId) throws NoteException, LabelException;

	public void updateNoteDeleteLabelId(int noteId, int labelId, int userId) throws NoteException, LabelException;

	public void deleteNote(int noteId, int userId) throws NoteException;

	public List<NoteResponseDTO> displayNotes(int userId) throws NoteException;

	public void colorNote(int noteId, String color, int userId) throws NoteException;

	public void addreminder(ReminderDTO reminderDTO, int userId) throws NoteException;

	public void deletereminder(int noteId, int userId) throws NoteException;

	public List<NoteResponseDTO> displayNotesWithReminder(int userId) throws NoteException; 

	public void collaborator(int noteId, Set<String> emailId, String userEmailId, String userName, int userId) throws NoteException;
	
	public void updateCollaborator(int noteId, Set<String> emailId, String userEmailId, String userName, int userId) throws NoteException;

}