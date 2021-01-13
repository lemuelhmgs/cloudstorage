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
  public String noteController(Authentication authentication, @ModelAttribute Note note, Model model){
    String errorMessage = null;
    System.out.println(note.getNoteid());
    System.out.println(note.getNotetitle() );
    System.out.println(note.getNotedescription());
    Integer UID = userService.getuid(authentication.getName());


    model.addAttribute("notes",this.noteService.getAllNoteForUser(UID));

    int currentCredId = 0;

    if(errorMessage == null){
      note.setUserid(UID);
      if(note.getNoteid() != null){
        currentCredId = noteService.updateNote(note);

      } else{
        currentCredId = noteService.addNote(note);
      }

      if(currentCredId < 0){
        errorMessage = "Something happened when adding credential. Please try again!";
      }

    }

    if (errorMessage == null){
      model.addAttribute("updateSuccess", true);
    } else {
      model.addAttribute("updateFail", errorMessage);
    }


    return "result";
  }

  @GetMapping("/note/delete/{noteid}")
  public String deleteNote(@PathVariable Integer noteid, Model model){

    String errorMessage = null;
    int currentCredId = 0;
    if(errorMessage == null){
      currentCredId = noteService.deleteNote(noteid);

      if(currentCredId<1){
        errorMessage = "There was an error when deleting Note. Please try again!";
      }
    }

    if(errorMessage == null){
      model.addAttribute("updateSuccess", true);
    }else {
      model.addAttribute("updateFail", errorMessage);
    }

    return "result";
  }
}
