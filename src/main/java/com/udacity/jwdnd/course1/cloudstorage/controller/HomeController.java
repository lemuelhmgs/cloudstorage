package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

  private final FileService fileService;
  private final UserService userService;

  public HomeController(FileService fileService,
      UserService userService) {
    this.fileService = fileService;
    this.userService = userService;
  }

  @GetMapping("/home")
  public String getHomePage(Authentication authentication, Model model){
    Integer UID = userService.getuid(authentication.getName());
    List<File> files = fileService.getAllFilesForUser(UID);
    model.addAttribute("files",files);
    return "home";
}

  @RequestMapping("/result")
  public String result() {
    return "result";
  }

  @GetMapping("/file/delete/{fileId}")
  public String deleteFile(@PathVariable Integer fileId) {
    fileService.deleteFile(fileId);
    return "home";
  }

  @GetMapping("/file/view/{fileId}")
  public ResponseEntity<Resource> getFile(@PathVariable Integer fileId)  {
    File file = fileService.getFilebyId(fileId);
    return ResponseEntity.ok()
        .contentType(MediaType.parseMediaType(file.getContenttype())).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""
            + file.getFilename() + "\"").body(new
            ByteArrayResource(file.getFiledata()));
  }


}
