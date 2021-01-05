package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FileService {
private FileMapper fileMapper;

  public FileService(FileMapper fileMapper) {
    this.fileMapper = fileMapper;
  }

  public int addFile(File file){
    return fileMapper.uploadFile(file);
  }

  public List<File> getAllFilesForUser(Integer userid){
    return fileMapper.getFileByUserid(userid);
  }

  public int deleteFile(Integer fileid){
    return fileMapper.deleteUserFile(fileid);
  }

  public File getFilebyId(Integer fileid){
    return fileMapper.getFileByFileid(fileid);
  }

  public boolean isDuplicateFileName(Integer userId, String fileName)
  {
    return fileMapper.DuplicateFiles(userId, fileName) != null;
  }
}
