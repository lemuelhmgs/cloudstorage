package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class NoteService {
  private final NoteMapper noteMapper;

  public NoteService(NoteMapper noteMapper) {
    this.noteMapper = noteMapper;
  }

  public int addNote(Note note){

    return  noteMapper.insertNote(note);
  }

  public List<Note> getAllNoteForUser(Integer userId){
    return noteMapper.getNoteByUserid(userId);
  }

  public int deleteNote(Integer noteId){
    return noteMapper.deleteByNoteid(noteId);
  }

  public Note findNoteByNoteId(Integer noteId){
    return noteMapper.getNoteById(noteId);
  }

  public int updateNote(Note note){
    return noteMapper.updateNote(note);
  }


}
