package com.java.controller.ask;

import com.java.pojo.postData;
import com.java.service.AskService;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    private AskService askService;
    @Autowired
    HttpServletRequest request;

    public boolean isLogin() {
        if (request.getSession().getAttribute("islogin") != null) {
            return true;
        }
        return false;
    }

    public int getUserid() {
        if (request.getSession().getAttribute("userid") != null) {
            return (int) request.getSession().getAttribute("userid");
        }
        return 0;
    }


    @RequestMapping("/newpost")
    public @ResponseBody
    Map<String, String> newpost(String json) {
        Map<String, String> map = new HashMap<>();

        if (isLogin() == false) {
            map.put("msg", "需要登录");
            map.put("code", "0");
            return map;
        }

        ObjectMapper om = new ObjectMapper();
        postData postData = null;
        try {
            postData = om.readValue(json, postData.class);


        } catch (IOException e) {
            e.printStackTrace();
            map.put("msg", "数据错误");
            map.put("code", "0");
            return map;
        }
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //System.out.println("新增数据");
        if (askService.addAsk(postData.getTitle(), postData.getContent(), postData.getClassid(), getUserid(), formatter.format(date)) == 1) {
            map.put("msg", "发布成功!");
            map.put("code", "1");
        } else {
            map.put("msg", "未知错误");
            map.put("code", "0");
        }
        return map;
    }

    @RequestMapping("/gettotalask")
    public @ResponseBody
    int getTotalAsk() {
        return askService.getTotalAsk();
    }


    @RequestMapping("/getaskclass")
    public @ResponseBody
    List<Map<String, Object>> getAskClass() {
        return askService.getAskClassJson();
    }


    /**
     * 获取方案详细信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/getAsk")
    public @ResponseBody
    Map<String, Object> getAsk(int id) {
        Map<String, Object> ask = askService.getAsk(id);
        askService.upDateView(id);

        System.out.println(ask);
        Map<String, Object> asknameMap = askService.getAskClass((Integer) ask.get("class"));
        Map<String, Object> userInfo = askService.getUserInfo((Integer) ask.get("userid"));

        ask.put("classname", asknameMap.get("name"));
        ask.put("classcolor", asknameMap.get("color"));
        ask.put("username", userInfo.get("name"));

        if (isLogin()) {
            if (getUserid() == (int) ask.get("userid")) {
                ask.put("own", 1);
            } else {
                ask.put("own", 0);
            }

        }
        return ask;
    }

    @RequestMapping("/search")
    public @ResponseBody
    List<Map<String, Object>> search(String key) {


        List<Map<String, Object>> asklist = askService.search(key);

        for (Map<String, Object> map : asklist) {
            Map<String, Object> asknameMap = askService.getAskClass((Integer) map.get("class"));
            map.put("classname", asknameMap.get("name"));
            map.put("classcolor", asknameMap.get("color"));
        }
        return asklist;
    }

    @RequestMapping("/delask")
    public @ResponseBody
    Map<String, Object> delask(int id) {

        Map<String, Object> map = new HashMap<>();

        if (!isLogin()) {
            map.put("code", 0);
            map.put("msg", "请登录后操作");
        }else {
            if (askService.delask(id) == 1) {
                map.put("code", 1);
                map.put("msg", "删除成功！");
            }else {
                map.put("code", 0);
                map.put("msg", "删除失败！");
            }

        }
        return map;
    }
}
