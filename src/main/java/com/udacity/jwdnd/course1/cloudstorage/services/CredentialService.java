package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CredentialService {
private final CredentialMapper credentialMapper;
private final HashService hashService;

  public CredentialService(
      CredentialMapper credentialMapper,
      HashService hashService) {
    this.credentialMapper = credentialMapper;
    this.hashService = hashService;
  }

  public int addCred(Credential credential){

    return credentialMapper.insertCred(credential);
  }

  public List<Credential> getAllCredForUser(Integer userid){
    return credentialMapper.getAllCredByUserid(userid);
  }

  public  Credential getCredentialById (Integer userid){
    return credentialMapper.getCredById(userid);
  }

  public int deleteCredential (Integer credid){
    return credentialMapper.deleteByCredid(credid);
  }

  public int update(Credential credential){
    return credentialMapper.updateCred(credential);
  }

  public int checkDup(Integer userId, Integer credentialId){
    return credentialMapper.checkIfExist(userId,credentialId);
  }

}
