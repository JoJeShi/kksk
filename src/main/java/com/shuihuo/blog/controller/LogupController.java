package com.shuihuo.blog.controller;

import com.shuihuo.blog.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

@Controller
public class LogupController {

    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping("/api/logup")
    @ResponseBody
    public Result logup(@RequestBody User requestUser) {
        String username = requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);

        if(userService.isExist(username)){
            return new Result(400);
        }else {
            userService.add(requestUser);
            return new Result(200);
        }
    }
}

