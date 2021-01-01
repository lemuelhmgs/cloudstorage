package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import java.io.IOException;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class UploadController {

  private final UserService userService;

  public UploadController(UserService userService,
      FileService fileService) {
    this.userService = userService;
    this.fileService = fileService;
  }

  private  final FileService fileService;


  @PostMapping("/upload")
  public String handleFileUpload(@RequestParam("fileUpload") MultipartFile multipartFile, Authentication authentication, Model model) throws IOException {
    String fileUploadError = null;

    Integer UID = userService.getuid(authentication.getName());
    String fs = String.valueOf(multipartFile.getSize());
    byte fileData[] = multipartFile.getBytes();

    if(fileData.length == 0) { //File cant be empty
      fileUploadError = "Invalid File Found. Please try again";
      return "result";
    }

    if (fileService.isDuplicateFileName(UID,multipartFile.getOriginalFilename())) {
      fileUploadError = "The File with same name already exists.";
      return "result";
    }

    File file = new File(multipartFile.getOriginalFilename(),multipartFile.getContentType(),fs,fileData,UID);
    int rowsAdded = fileService.addFile(file);
    if(rowsAdded < 1){
      fileUploadError = "There was an error uploading File. Please try again.";}

    if (fileUploadError == null) { model.addAttribute("fileUploadSuccess", true); }
    else { model.addAttribute("fileUploadError", fileUploadError); }

    return "result";
  }
}
