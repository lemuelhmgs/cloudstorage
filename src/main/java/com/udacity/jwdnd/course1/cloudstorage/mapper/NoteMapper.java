package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface NoteMapper {
  @Select("select * from NOTES where noteid = #{noteid}")
  Note getNoteById (Integer noteid);

  @Select("Select * from NOTES where userid = #{userid}")
  List<Note> getNoteByUserid(@Param("userid") Integer userid);

  @Delete("Delete from NOTES where noteid = #{noteid}")
  int deleteByNoteid(Integer noteid);

  @Insert("Insert into NOTES (notetitle,notedescription,userid) values(#{notetitle},#{notedescription}, #{userid})")
  @Options(useGeneratedKeys = true, keyProperty = "noteid")
  Integer insertNote(Note note);

  @Update("update NOTES set notetitle=#{notetitle}, notedescription=#{notedescription} where noteid=#{noteid}")
  Integer updateNote(Note note);



}
