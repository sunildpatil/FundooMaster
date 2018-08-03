package com.bridgelabz.repositories;

import org.springframework.data.repository.CrudRepository;

import com.bridgelabz.models.Collaborator;

public interface CollaboratorRepository extends CrudRepository<Collaborator, Integer> {

	public Collaborator findByNoteIdAndUserId(int id, int userId);
	
}
