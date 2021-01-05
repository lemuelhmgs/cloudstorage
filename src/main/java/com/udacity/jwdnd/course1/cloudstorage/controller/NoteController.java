package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class NoteController {
  private final UserService userService;
  private final NoteService noteService;

  public NoteController(UserService userService,
      NoteService noteService) {
    this.userService = userService;
    this.noteService = noteService;
  }

  @PostMapping("/createNotes")
  public String noteController(@ModelAttribute Note note, Authentication authentication, Model model){

    System.out.println(note.getNotetitle() );
    System.out.println(note.getNotedescription());
    Integer UID = userService.getuid(authentication.getName());
    List<Note> notes = noteService.getAllNoteForUser(UID);
    note.setUserid(UID);
    model.addAttribute("notes",note);


    //Note note1 = new Note(note.getNotetitle(),note.getNotedescription(),UID);
    if(note.getNoteid() != null){
      noteService.updateNote(note);
      return "home";
    }


    noteService.addNote(note);
    return "home";
  }

  @GetMapping("/note/delete/{noteid}")
  public String deleteNote(@PathVariable Integer noteid){
    noteService.deleteNote(noteid);
    return "home";
  }
}
