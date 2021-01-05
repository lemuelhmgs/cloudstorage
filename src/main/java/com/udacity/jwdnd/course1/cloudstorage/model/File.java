package com.udacity.jwdnd.course1.cloudstorage.model;

import java.sql.Blob;

public class File {
private Integer fileId;
private String filename;
private String contenttype;
private String filesize;
private byte[] filedata;
private Integer userid;

  public File(String filename, String contenttype, String filesize,
      byte[] filedata, Integer userid) {

    this.filename = filename;
    this.contenttype = contenttype;
    this.filesize = filesize;
    this.filedata = filedata;
    this.userid = userid;

  }

  public Integer getFileId() {
    return fileId;
  }

  public String getFilename() {
    return filename;
  }

  public String getContenttype() {
    return contenttype;
  }

  public String getFilesize() {
    return filesize;
  }

  public byte[] getFiledata() {
    return filedata;
  }

  public Integer getUserid() {
    return userid;
  }
}
