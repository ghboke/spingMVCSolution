package com.java.mapper;

import org.apache.ibatis.annotations.*;


import java.util.List;
import java.util.Map;


public interface AskMapper {
    @Select({" SELECT id,title,class,view FROM asklist LIMIT 0,${num}"})
    List<Map<String, Object>> selectAsklist(@Param("num") int num);

    @Select({"SELECT * FROM user WHERE username='${user}' and password='${pass}'"})
    List<Map<String, Object>> login(@Param("user") String user, @Param("pass") String pass);

    @Select({"SELECT * FROM askclass WHERE id=${id}"})
    Map<String, Object> getAskClass(@Param("id") int id);

    @Select({"SELECT * FROM user WHERE id=${id}"})
    Map<String, Object> getUserInfo(@Param("id") int id);

    @Select({"SELECT * FROM asklist WHERE id=${id}"})
    Map<String, Object> getAsk(@Param("id") int id);


    @Insert({"INSERT INTO asklist(`title`, `content`, `class`,`creattime`,`view`,`userid`) VALUES ('${title}', '${content}', ${classid}, '${creattime}',0,${userid})"})
    int addAsk(@Param("title") String title, @Param("content") String content, @Param("classid") int classid, @Param("creattime") String creattime, @Param("userid") int userid);

    @Select({"SELECT  count(1) from asklist"})
    int getTotalAsk();

    @Select({"SELECT * FROM askclass"})
    List<Map<String, Object>> getAskClassJson();


    @Update({"UPDATE asklist SET `view` = `view`+1 WHERE `id` = ${id}"})
    int upDateView(@Param("id") int id);

    @Select({"UPDATE asklist SET `view` = `view`+1 WHERE `id` = ${id}"})
    List<Map<String, Object>> upDateAsk(@Param("id") int id);

    @Select({"SELECT id,title,class,view FROM asklist WHERE content LIKE '%${content}%' or title LIKE '%${content}%' "})
    List<Map<String, Object>> search(@Param("content") String content);

    @Delete({"DELETE FROM `asklist` WHERE `id` = ${id}"})
    int deleteAsk(@Param("id") int id);
}