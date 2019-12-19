package com.java.service.impl;

import com.github.pagehelper.PageHelper;
import com.java.mapper.AskMapper;
import com.java.service.AskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class AskServiceImpl implements AskService {

    @Autowired
    private AskMapper askMapper;


    @Override
    public List<Map<String,Object>> getAskList(int num){
        return askMapper.selectAsklist(num);
    }

    @Override
    public List<Map<String, Object>> login(String user, String pass) {
        return askMapper.login(user, pass);
    }

    @Override
    public Map<String, Object> getUserInfo(int id) {
        return askMapper.getUserInfo(id);
    }

    @Override
    public  Map<String, Object> getAskClass(int id) {
        return askMapper.getAskClass(id);
    }

    @Override
    public Map<String, Object> getAsk(int id) {
        return askMapper.getAsk(id);
    }

    @Override
    public int addAsk(String title, String content, int classid, int userid, String creattime) {
        return askMapper.addAsk(title,content,classid,creattime,userid);
    }

    @Override
    public int getTotalAsk() {
        return askMapper.getTotalAsk();
    }

    @Override
    public List<Map<String,Object>>  getAskClassJson() {
        return askMapper.getAskClassJson();
    }

    @Override
    public List<Map<String, Object>> search(String content) {
        return askMapper.search(content);
    }

    @Override
    public int upDateView(int id) {
        return askMapper.upDateView(id);
    }

    @Override
    public int delask(int id) {
        return askMapper.deleteAsk(id);
    }


}
