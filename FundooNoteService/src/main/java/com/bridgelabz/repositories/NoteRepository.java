package com.bridgelabz.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.models.Note;

@Repository
public interface NoteRepository extends CrudRepository<Note, Integer> {

	public Note findByIdAndUserId(int id, int userId);

	public Note findById(int userId);

	public List<Note> findAllNoteByUserId(int userId);

	public List<Note> findByUserIdAndReminderDateNotNull(int userId);

}