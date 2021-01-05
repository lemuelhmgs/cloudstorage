package com.udacity.jwdnd.course1.cloudstorage.mapper;


import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

  @Select("Select * from USERS where username=#{username}")
  User getUser(String username);

  @Select("SELECT userid FROM USERS WHERE username = #{username}")
  Integer getUserId(String username);


  @Insert("Insert into USERS (username,salt,password,firstname,lastname) values(#{username},#{salt}, #{password},#{firstname}, #{lastname})")
  @Options(useGeneratedKeys = true, keyProperty = "userid")
  int insert(User user);



}
