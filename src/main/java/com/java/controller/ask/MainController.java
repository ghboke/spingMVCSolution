package com.java.controller.ask;

import com.github.pagehelper.PageInfo;
import com.java.service.AskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/ajax")
public class MainController {

    @Autowired
    private AskService askService;
    @Autowired
    HttpServletRequest request;

    @RequestMapping("/test")
    public @ResponseBody
    String test() {
        return "123456";
    }

    @RequestMapping("/test2")
    public @ResponseBody
    String test2() {
        //System.out.println(request.getSession().getAttribute("abc"));
        return "123456";
    }

    @RequestMapping("/getAsks")
    public @ResponseBody
    List<Map<String, Object>> getAskList(int num) {
        List<Map<String, Object>> askList = askService.getAskList(num);
        for (Map<String, Object> map : askList) {
            Map<String, Object> asknameMap = askService.getAskClass((Integer) map.get("class"));
            map.put("classname", asknameMap.get("name"));
            map.put("classcolor", asknameMap.get("color"));
        }
        return askList;
    }



}
