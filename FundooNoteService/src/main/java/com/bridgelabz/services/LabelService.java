package com.bridgelabz.services;

import java.util.List;

import com.bridgelabz.exceptions.LabelException;
import com.bridgelabz.exceptions.NoteException;
import com.bridgelabz.models.LabelCreateDTO;
import com.bridgelabz.models.LabelResponseDTO;
import com.bridgelabz.models.LabelUpdateDTO;

public interface LabelService {

	public void createLabel(int userId, LabelCreateDTO labelCreateDTO);

	public void updateLabel(int userId, LabelUpdateDTO labelUpdateDTO) throws LabelException;

	public void updateLabelNoteId(int userId, int labelId, int noteId) throws LabelException, NoteException;

	public void updateLabelDeleteNoteId(int userId, int labelId, int noteId) throws LabelException, NoteException;

	public void deleteLabel(int userId, int labelId);

	public List<LabelResponseDTO> displayLabel(int userId);

}
