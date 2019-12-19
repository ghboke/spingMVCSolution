package com.java.controller.ask;

import com.java.service.AskService;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AskService askService;
    @Autowired
    HttpServletRequest request;

    @RequestMapping("/login")
    public @ResponseBody
    int login(String user, String pass) {
        List<Map<String, Object>> list = askService.login(user, pass);
        if (list.size() != 0) {
            request.getSession().setAttribute("username", user);
            request.getSession().setAttribute("userid", list.get(0).get("id"));
            request.getSession().setAttribute("islogin", 1);
            return 1;
        } else {
            return 0;
        }
    }
    @RequestMapping("/exit")
    public @ResponseBody
    int exit() {
        request.getSession().invalidate();
        return 1;
    }
    @RequestMapping("/loginInfo")
    public @ResponseBody
    String loginInfo() {

        int islogin = 0;

        if (request.getSession().getAttribute("islogin") != null) {
            islogin = 1;
        }
        if (islogin == 0) {
            return "0";

        } else {
            Map<String, Object> loginInfo = new HashMap<>();
            loginInfo.put("username", request.getSession().getAttribute("username"));
            ObjectMapper om = new ObjectMapper();
            String jsonStr = "";
            try {
                jsonStr = om.writeValueAsString(loginInfo);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return jsonStr;
        }
    }


}
