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
import org.springframework.stereotype.Service;

import com.bridgelabz.exceptions.LabelException;
import com.bridgelabz.exceptions.NoteException;
import com.bridgelabz.models.Label;
import com.bridgelabz.models.LabelCreateDTO;
import com.bridgelabz.models.LabelResponseDTO;
import com.bridgelabz.models.LabelUpdateDTO;
import com.bridgelabz.models.Note;
import com.bridgelabz.repositories.LabelRepository;
import com.bridgelabz.repositories.NoteRepository;
import com.bridgelabz.utility.LabelHelper;
import com.bridgelabz.utility.MailProducer;

@Service
@Transactional
public class LabelServiceImpl implements LabelService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private LabelRepository labelRepository;

	@Autowired
	private NoteRepository noteRepository;
	
	@Autowired
	private LabelHelper labelHelper;

	private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	@Override
	public void createLabel(int userId, LabelCreateDTO labelCreateDTO) {

		Label label = modelMapper.map(labelCreateDTO, Label.class);
		label.setUserId(userId);

		Date date = new Date();
		label.setCreatedDate(dateFormat.format(date));
		label.setLastUpdateDate(dateFormat.format(date));

		labelRepository.save(label);
		
		Map<String, Object> mapLabel = new HashMap<>();
		mapLabel.put("labelId", label.getLabelId());
		mapLabel.put("labelName", label.getLabelName());
		mapLabel.put("createdDate", label.getCreatedDate());
		mapLabel.put("lastUpdateDate", label.getLastUpdateDate());
		mapLabel.put("userId", label.getLabelId());
		
		labelHelper.createLabel(Integer.toString(label.getLabelId()), mapLabel);
	}

	@Override
	public void updateLabel(int userId, LabelUpdateDTO labelUpdateDTO) throws LabelException {

		Label label = labelRepository.findByLabelIdAndUserId(labelUpdateDTO.getLabelId(), userId);

		if (label==null) {

			throw new LabelException("Label Not Found");
		}

		Date date = new Date();
		label.setLabelName(labelUpdateDTO.getLabelName());
		label.setLastUpdateDate(dateFormat.format(date));

		labelRepository.save(label);

		/*Optional<Label> optionalLabel = labelRepository.findByLabelIdAndUserId(labelUpdateDTO.getLabelId(), userId);

		if(!optionalLabel.isPresent()) {


		}*/

	}

	@Override
	public void updateLabelNoteId(int userId, int labelId, int noteId) throws LabelException, NoteException {

		Label label = labelRepository.findByLabelIdAndUserId(labelId, userId);

		if (label==null) {

			throw new LabelException("Label Not Found");
		}

		Note note = noteRepository.findByIdAndUserId(noteId, userId);

		if(note==null) {

			throw new NoteException("Note Not Found");
		}

		String id = label.getNoteId();

		String nid="";
		String nId;
		
		Set<String> set = new HashSet<>();
		
		if(id==null) {

			nId=noteId+",";
			label.setNoteId(nId);
			
			set.add(Integer.toString(noteId));
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

			set.add(Integer.toString(noteId));
			
			for (String s: set) {

				s = s+",";
				nid = nid + s; 
			}

			label.setNoteId(nid);
		}

//		labelRepository.save(label);
		
		System.out.println("set  "+set);
		
		Map<String, Object> mapLabel = new HashMap<>();
		mapLabel.put("labelId", label.getLabelId());
		mapLabel.put("labelName", label.getLabelName());
		mapLabel.put("createdDate", label.getCreatedDate());
		mapLabel.put("lastUpdateDate", label.getLastUpdateDate());
		mapLabel.put("noteId", set);
		mapLabel.put("userId", label.getUserId());
		
		System.out.println(mapLabel);
		
//		labelHelper.updateNote(Integer.toString(label.getLabelId()), mapLabel);
		
	} 

	@Override
	public void updateLabelDeleteNoteId(int userId, int labelId, int noteId) throws LabelException, NoteException {

		Label label = labelRepository.findByLabelIdAndUserId(labelId, userId);

		if (label==null) {

			throw new LabelException("Label Not Found");
		}

		Note note = noteRepository.findByIdAndUserId(noteId, userId);

		if(note==null) {

			throw new NoteException("Note Not Found");
		}

		String id = label.getNoteId();

		if(id==null) {
			System.out.println("note id null");
		} else {


			String[] array = id.split(",");	

			List<String> list = new ArrayList<>();

			for(int i=0;i<array.length;i++) {

				System.out.println("array : "+array[i]);
				list.add(array[i]);
			}

			System.out.println("List");
			System.out.println(list);

			if(list.contains(Integer.toString(noteId))) {

				System.out.println("Before Removing "+list);
				int index = list.indexOf(Integer.toString(noteId));
				list.remove(index);
				System.out.println("After Removing "+list);

				String nid="";

				for (String s: list) {

					s = s+",";
					nid = nid + s;
				}

				label.setNoteId(nid);
				labelRepository.save(label);

			} else {
				System.out.println("Not Working");
			}

		}	

	}

	@Override
	public void deleteLabel(int userId, int labelId) {

		Label label = labelRepository.findByLabelIdAndUserId(labelId, userId);

		if (label==null) {

		}

		labelRepository.deleteById(labelId);

	}

	@Override
	public List<LabelResponseDTO> displayLabel(int userId) {

		List<Label> list = labelRepository.findAllNoteByUserId(userId);

		if(list==null) {

		}

		List<LabelResponseDTO> labelList = new ArrayList<>();

		for(Label labelObject:list) {

			LabelResponseDTO labelResponseObject =  modelMapper.map(labelObject, LabelResponseDTO.class);
			labelList.add(labelResponseObject);
		}

		return labelList;
	}
}	
