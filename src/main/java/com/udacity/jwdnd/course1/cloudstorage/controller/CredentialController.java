package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CredentialController {
  private final UserService userService;
  private final CredentialService credentialService;
  private final EncryptionService encryptionService;

  public CredentialController(UserService userService,
      CredentialService credentialService,
      EncryptionService encryptionService) {
    this.userService = userService;
    this.credentialService = credentialService;
    this.encryptionService = encryptionService;
  }



  @PostMapping("/createCredential")
  public String addCred (@ModelAttribute Credential credential, Model model, Authentication authentication){
    String errorMessage = null;

    Integer UID = userService.getuid(authentication.getName());

    List<Credential> credentials = credentialService.getAllCredForUser(UID);
    //if(!credentials.isEmpty()){
    //  credential.setCredentialid(credentials.get(0).getCredentialid().intValue());
    //}

    //model.addAttribute("creds",this.credentialService.getCredentialById(UID));
    model.addAttribute("credentials", credentials);

    if(errorMessage == null){
      credential.setUserid(UID);

      SecureRandom random = new SecureRandom();
      byte[] key = new byte[16];
      random.nextBytes(key);
      String encodeKey = Base64.getEncoder().encodeToString(key);

      credential.setKey(encodeKey);

      String encryptedPass = encryptionService.encryptValue(credential.getPassword(), credential.getKey());
      credential.setPassword(encryptedPass);
      int currentCredId = 0;

      if(credential.getCredentialid() != null){
        currentCredId = credentialService.update(credential);

      } else {

        currentCredId = credentialService.addCred(credential);
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

  @GetMapping("/credential/decrypt-password/{credentialid}")
  public ResponseEntity<String> decryptPassword(@PathVariable("credentialid") int credentialid){
    Credential credential = credentialService.getCredentialById(credentialid);
    String decryptPassword = encryptionService.decryptValue(credential.getPassword(), credential.getKey());

    return ResponseEntity.ok(decryptPassword);
  }

  @GetMapping("/credential/delete/{credentialid}")
  public String deleteCred(@PathVariable("credentialid") int credentialid, Model model){
    String errorMessage = null;

    if(errorMessage == null){
      int deletedCredId = credentialService.deleteCredential(credentialid);

      if(deletedCredId<1){
        errorMessage = "There was an error when deleting Credential. Please try again!";
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
