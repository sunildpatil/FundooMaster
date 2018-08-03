package com.bridgelabz.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bridgelabz.exceptions.LabelException;
import com.bridgelabz.exceptions.NoteException;
import com.bridgelabz.models.Collaborator;
import com.bridgelabz.models.Email;
import com.bridgelabz.models.Label;
import com.bridgelabz.models.Note;
import com.bridgelabz.models.NoteCreateDTO;
import com.bridgelabz.models.NoteResponseDTO;
import com.bridgelabz.models.NoteUpdateDTO;
import com.bridgelabz.models.ReminderDTO;
import com.bridgelabz.repositories.CollaboratorRepository;
import com.bridgelabz.repositories.LabelRepository;
import com.bridgelabz.repositories.NoteRepository;
import com.bridgelabz.utility.MailProducer;
import com.bridgelabz.utility.NoteHelper;

@Service
@Transactional
public class NoteServiceImpl implements NoteService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private NoteRepository noteRepository;
	
	@Autowired
	private LabelRepository labelRepository;
	
	@Autowired
	private CollaboratorRepository collaboratorRepository;
	
	@Value("${noteerrormsg}")
	private String noteErrorMsg;
	
	@Autowired
	private NoteHelper noteHelper;
	
	@Autowired
	private MailProducer mailProducer;

	private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	@Override
	public void createNote(NoteCreateDTO noteDTO, int userId) {
		
		Note note = modelMapper.map(noteDTO, Note.class);
		note.setUserId(userId);
		
		Date date = new Date();
		note.setCreatedDate(dateFormat.format(date));
		note.setUpdatedDate(dateFormat.format(date));
		
		noteRepository.save(note);
		
		String id = Integer.toString(note.getId());
		
		Map<String, Object> map = new HashMap<>();
		map.put("id", note.getId());
		map.put("title", note.getTitle());
		map.put("description", note.getDescription());
		map.put("createdDate", note.getCreatedDate());
		map.put("updatedDate", note.getUpdatedDate());
		map.put("color", note.getColor());
		map.put("userId", note.getUserId());
	
		noteHelper.createNote(id, map);
	}

	@Override
	public void updateNote(NoteUpdateDTO noteUpdateDTO, int userId) throws NoteException {
		
		Note note = noteRepository.findByIdAndUserId(noteUpdateDTO.getId(), userId);
		
		if(note==null) {
			
			throw new NoteException(noteErrorMsg);
		}

		Date date = new Date();
		note.setTitle(noteUpdateDTO.getTitle());
		note.setDescription(noteUpdateDTO.getDescription());
		note.setUpdatedDate(dateFormat.format(date));
	
		noteRepository.save(note);
		
		String id = Integer.toString(note.getId());	
		
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("title", note.getTitle());
		map.put("description", note.getDescription());
		map.put("createdDate", note.getCreatedDate());
		map.put("updatedDate", note.getUpdatedDate());
		map.put("color", note.getColor());
		map.put("userId", note.getUserId());
		
		noteHelper.updateNote(id, map);
	}
	
	@Override
	public void updateNoteAddLabelId(int noteId, int labelId, int userId) throws NoteException, LabelException {
		
		Label label = labelRepository.findByLabelIdAndUserId(labelId, userId);

		if (label==null) {

			throw new LabelException("Label Not Found");
		}

		Note note = noteRepository.findByIdAndUserId(noteId, userId);

		if(note==null) {

			throw new NoteException("Note Not Found");
		}
		
		/*String id = note.getLabelId();

		String lid="";
		String lId;
		
		Set<String> set = new HashSet<>();

		if(id==null) {

			lId=labelId+",";
			note.setLabelId(lId);
			
			set.add(Integer.toString(labelId));			
		} else {

			String[] array = id.split(",");

			for(int i=0;i<array.length;i++) {

				try {
					System.out.println("array : "+array[i]);
					set.add(array[i]);
				}
				catch(Exception exception) {

					System.out.println(exception.getMessage());
				}
			}

			set.add(Integer.toString(labelId));

			System.out.println("set "+set);

			for (String s: set) {
				
				s = s+",";
				lid = lid + s; 
			}

			note.setLabelId(lid);
		}

		System.out.println(label);*/
		
		String id = note.getLabelId();

		String lid="";
		String lId;
		
		Set<String> set = new HashSet<>();

		if(id==null) {

			lId=labelId+",";
			note.setLabelId(lId);
			
			set.add(Integer.toString(labelId));			
		} else {

			String[] array = id.split(",");

			for(int i=0;i<array.length;i++) {

				try {
					System.out.println("array : "+array[i]);
					set.add(array[i]);
				}
				catch(Exception exception) {
					System.out.println(exception.getMessage());
				}
			}

			set.add(Integer.toString(labelId));

			System.out.println("set "+set);

			for (String s: set) {
				
				s = s+",";
				lid = lid + s; 
			}

			note.setLabelId(lid);
		}

		noteRepository.save(note);
				
		Map<String, String> labelNameMap = new HashMap<>();
		List<Object> list = new ArrayList<>();
		
		for (String s: set) {
			
			Label labelName =  labelRepository.findByLabelId(Integer.parseInt(s));
			labelNameMap.put("labelId", Integer.toString(labelName.getLabelId()));
			labelNameMap.put("labelName", labelName.getLabelName());
			
			list.add(labelNameMap);	
		}
		
		System.out.println("Map");
		System.out.println(labelNameMap);
		
		System.out.println("List");
		System.out.println(list);
		
//		System.out.println(labelNameMap);
		
		Map<String, Object> map = new HashMap<>();
		map.put("id", note.getId());
		map.put("title", note.getTitle());
		map.put("description", note.getDescription());
		map.put("createdDate", note.getCreatedDate());
		map.put("updatedDate", note.getUpdatedDate());
		map.put("color", note.getColor());
		map.put("labels", list);
		map.put("userId", note.getUserId());
		
		System.out.println("Note Object");
		System.out.println(map);
		
		noteHelper.updateNote(Integer.toString(note.getId()), map);
	}
	
	@Override
	public void updateNoteDeleteLabelId(int noteId, int labelId, int userId) throws NoteException, LabelException {
		
		Label label = labelRepository.findByLabelIdAndUserId(labelId, userId);

		if (label==null) {

			throw new LabelException("Label Not Found");
		}

		Note note = noteRepository.findByIdAndUserId(noteId, userId);

		if(note==null) {

			throw new NoteException("Note Not Found");
		}

		String id = note.getLabelId(); 

		if(id==null) {
			System.out.println("label id null");
		} else {

			String[] array = id.split(",");	

			List<String> list = new ArrayList<>();

			for(int i=0;i<array.length;i++) {

				System.out.println("array : "+array[i]);
				list.add(array[i]);
			}

			System.out.println("List");
			System.out.println(list);

			if(list.contains(Integer.toString(labelId))) {

				System.out.println("Before Removing "+list);
				int index = list.indexOf(Integer.toString(labelId));
				list.remove(index);
				System.out.println("After Removing "+list);

				String lid="";

				for (String s: list) {

					s = s+",";
					lid = lid + s;
				}

				note.setLabelId(lid);
				noteRepository.save(note);

			} else {
				System.out.println("Not Working");
			}

		}	
		
	}

	@Override
	public void deleteNote(int noteId, int userId) throws NoteException {
		
		Note note = noteRepository.findByIdAndUserId(noteId, userId);
		
		if(note==null) {
			
			throw new NoteException(noteErrorMsg);
		}
				
		noteRepository.delete(note);
		
		String id = Integer.toString(note.getId());
		noteHelper.deleteNote(id);
	}
	
	@Override
	public List<NoteResponseDTO> displayNotes(int userId) throws NoteException {
		
		List<Note> list = noteRepository.findAllNoteByUserId(userId);
		
		if(list==null) {
			
			throw new NoteException(noteErrorMsg);
		}
		
		List<NoteResponseDTO> noteList = new ArrayList<>();

		for(Note noteObject:list) {
			
			NoteResponseDTO noteResponseObject =  modelMapper.map(noteObject, NoteResponseDTO.class);
			noteList.add(noteResponseObject);
		}
					
		return noteList;
	}

	@Override
	public void colorNote(int noteId, String color, int userId) throws NoteException  {
	
		Note note = noteRepository.findByIdAndUserId(noteId, userId);
				
		if(note==null) {
			
			throw new NoteException(noteErrorMsg);
		}
		
		note.setColor(color);

		noteRepository.save(note);
		
		String id = Integer.toString(note.getId());
		/*noteHelper.updateNote(id, note);*/
	}

	@Override
	public void addreminder(ReminderDTO reminderDTO, int userId) throws NoteException {
		
		Note note = noteRepository.findByIdAndUserId(reminderDTO.getNoteId(), userId);
		
		if(note==null) {
			
			throw new NoteException(noteErrorMsg);
		}
						
		note.setReminderDate(reminderDTO.getReminderDate());
		note.setReminderTime(reminderDTO.getReminderTime());
		note.setReminderFrequency(reminderDTO.getReminderFrequency());
						
		noteRepository.save(note);
	}

	@Override
	public void deletereminder(int noteId, int userId) throws NoteException {
		
		Note note = noteRepository.findByIdAndUserId(noteId, userId);
		
		if(note==null) {
			
			throw new NoteException(noteErrorMsg);
		}
		
		note.setReminderDate(null);
		note.setReminderTime(null);
		note.setReminderFrequency(null);

		noteRepository.save(note);	
	}

	@Override
	public List<NoteResponseDTO> displayNotesWithReminder(int userId) throws NoteException {
				
		List<Note> list = noteRepository.findByUserIdAndReminderDateNotNull(userId);
				
		if(list==null) {
			
			throw new NoteException(noteErrorMsg);
		}
		
		List<NoteResponseDTO> noteList = new ArrayList<>();

		for(Note noteObject:list) {
			
			NoteResponseDTO noteResponseObject =  modelMapper.map(noteObject, NoteResponseDTO.class);
			noteList.add(noteResponseObject);
		}
					
		return noteList;
	}

	@Override
	public void collaborator(int noteId, Set<String> emailId, String userEmailId, String userName, int userId) throws NoteException {
		
		Note note = noteRepository.findByIdAndUserId(noteId, userId);
		
		if(note==null) {
			
			throw new NoteException(noteErrorMsg);
		}
		
		String subject = "Google Keep Collaborator";
		String body = userName +" shared a Note with you " +note.getTitle();
		String from = userEmailId;
		
		String email="";
		
		for(String s:emailId) {
			
			email=email+s+",";	
		}
				
		Collaborator collaborator = new Collaborator();
		collaborator.setNoteId(noteId);
		collaborator.setUserId(userId);
		collaborator.setSharedTo(email);
		
		collaboratorRepository.save(collaborator);
		
		email="";
		
		for(String s:emailId) {
			
			email=email+s+",";
			
			Email emailObj = new Email();
			emailObj.setTo(s);
			emailObj.setFrom(from);
			emailObj.setSubject(subject);
			emailObj.setBody(body);
			
			mailProducer.send(emailObj);	
		}
		
	}

	@Override
	public void updateCollaborator(int noteId, Set<String> emailId, String userEmailId, String userName, int userId)
			throws NoteException {
		
		Note note = noteRepository.findByIdAndUserId(noteId, userId);
		
		if(note==null) {
			
			throw new NoteException(noteErrorMsg);
		}
		
		Collaborator collaborator = collaboratorRepository.findByNoteIdAndUserId(noteId, userId);
		
		String sharedTo = collaborator.getSharedTo();

		String tempSharedTo="";
		
		Set<String> set = new HashSet<>();

		if(sharedTo==null) {

			/*tempEmail=sharedTo+",";
			collaborator.setSharedTo(tempEmail);*/
			
			for(String s:emailId) {
				set.add(s);
				tempSharedTo=s+",";
			}
			
			System.out.println("Working for Null");
			System.out.println("set "+set);
			
			System.out.println("TempSharedTo : "+tempSharedTo);
			
			collaborator.setSharedTo(tempSharedTo);
			
		} else {

			String[] array = sharedTo.split(",");

			for(int i=0;i<array.length;i++) {

				try {
					System.out.println("array : "+array[i]);
					set.add(array[i]);
				}
				catch(Exception exception) {
					System.out.println(exception.getMessage());
				}
			}
			
			for(String s:emailId) {
				set.add(s);
			}

			System.out.println("Working for present Values");
			System.out.println("set "+set);

			for (String s: set) {
				
				s = s+",";
				tempSharedTo = tempSharedTo + s; 
			}	
					
			System.out.println(tempSharedTo);

			collaborator.setSharedTo(tempSharedTo);
		}

		System.out.println("Working final");
		System.out.println(collaborator);
		collaboratorRepository.save(collaborator);
		emailId.clear();
		set.clear();
		
	}
}