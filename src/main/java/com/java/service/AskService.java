package com.java.service;

import java.util.List;
import java.util.Map;

public interface AskService {
    List<Map<String,Object>> getAskList(int num);
    List<Map<String,Object>> login(String user, String pass);

    Map<String, Object> getUserInfo(int id);

    Map<String, Object> getAskClass(int id);
    Map<String, Object> getAsk(int id);
    int addAsk(String title, String content, int classid,int userid,String creattime);
    int getTotalAsk();
    List<Map<String,Object>> getAskClassJson();
    List<Map<String,Object>> search(String content);
    int upDateView(int id);
    int delask(int id);

}
