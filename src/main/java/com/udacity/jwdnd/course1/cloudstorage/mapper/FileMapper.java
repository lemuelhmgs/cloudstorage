package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;



@Mapper
public interface  FileMapper {

  @Select("Select * from FILES where userid = #{userid}")
  List<File> getFileByUserid(@Param("userid") Integer userid);

  @Select("Select * from FILES where fileId = #{fileId}")
  File getFileByFileid(Integer fileId);

  @Insert("Insert into FILES (filename,contenttype,filesize,filedata,userid) values(#{filename}, #{contenttype},#{filesize}, #{filedata},#{userid})")
  @Options(useGeneratedKeys = true, keyProperty = "fileId")
  int uploadFile(File file);

  @Delete("Delete from FILES where fileId = #{fileId}")
  int deleteUserFile(Integer userid);

  @Select("SELECT filename FROM FILES WHERE userid = #{userid} and filename = #{filename}")
  String DuplicateFiles(@Param("userid") Integer userId, String filename);

}
