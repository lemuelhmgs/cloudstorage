package com.udacity.jwdnd.course1.cloudstorage.model;

public class Credential {
  private Integer credentialid;
  private String url;
  private String username;
  private String key;
  private String password;
  private Integer userid;

  public Credential(Integer credentialid, String url, String username, String key,
      String password, Integer userid) {
    this.credentialid = credentialid;
    this.url = url;
    this.username = username;
    this.key = key;
    this.password = password;
    this.userid = userid;
  }

  public Credential() {
  }

  public void setCredentialid(Integer credentialid) {
    this.credentialid = credentialid;
  }

  public Integer getCredentialid() {
    return credentialid;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setUserid(Integer userid) {
    this.userid = userid;
  }

  public String getUrl() {
    return url;
  }

  public String getUsername() {
    return username;
  }

  public String getKey() {
    return key;
  }

  public String getPassword() {
    return password;
  }

  public Integer getUserid() {
    return userid;
  }


}
