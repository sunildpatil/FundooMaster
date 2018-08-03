package com.bridgelabz.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bridgelabz.models.Label;

public interface LabelRepository extends CrudRepository<Label, Integer> {

	public Label findByLabelIdAndUserId(int labelId, int userId);
	
	public List<Label> findAllNoteByUserId(int userId);
	
	public Label findByLabelId(int id);
	
	/*@Query(value = "select * from Label where labelId in :abcd")
	public List<Label> findByLabelIdin(@Param("abcd") Collection<Integer> labelIds);*/
	
	/*@Query(value = "select from Label where labelId= :abcd")
	public List<Label> findByLabelIdin(@Param("abcd") Collection<Integer> labelIds);*/
}
