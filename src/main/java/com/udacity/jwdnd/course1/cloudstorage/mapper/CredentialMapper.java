package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
/*
private Integer credentialid;
  private String url;
  private String username;
  private String key;
  private String password;
  private Integer userid;
 */

@Mapper
public interface CredentialMapper {

  @Select("Select * from CREDENTIALS where credentialid=#{credentialid}")
  Credential getCredById(Integer credentialid);

  @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userid}")
  List<Credential> getAllCredByUserid(@Param("userid") Integer userid);

  @Select("select * from CREDENTIALS where userid= #{userid} and credentialid=#{credentialid}")
  Integer checkIfExist(Integer userid,Integer credentialid);


  @Insert("Insert into CREDENTIALS (url,username,key,password,userid) values(#{url},#{username}, #{key},#{password}, #{userid})")
  @Options(useGeneratedKeys = true, keyProperty = "credentialid")
  Integer insertCred(Credential credential);

  @Delete("Delete from CREDENTIALS where credentialid = #{credentialid}")
  int deleteByCredid(Integer credentialid);

  @Update("update CREDENTIALS set url=#{url},username=#{username}, password=#{password} where credentialid=#{credentialid}")
  Integer updateCred(Credential credential);


}
