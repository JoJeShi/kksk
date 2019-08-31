package com.shuihuo.blog.controller;

import com.shuihuo.blog.dao.UserDao;
import com.shuihuo.blog.entity.User;
import com.shuihuo.blog.result.Result;
import com.shuihuo.blog.result.ResultFactory;
import com.shuihuo.blog.tools.RandomCheckNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;

@CrossOrigin
@RestController
public class RegisterController {
    @Autowired
    private UserDao userDao;

    @Autowired
    private ResultFactory resultFactory;

    @Autowired
    private JavaMailSender javaMailSender;

    @PostMapping(value = "/api/checkmail")
    public Result checkmail(@Valid @RequestBody User requestUser, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return resultFactory.buildFailResult("系统错误，注册失败");
        }
        User registeredUser = userDao.findUserByMailAddress(requestUser.getMailAddress());
        if(registeredUser != null) {
            return resultFactory.buildFailResult("邮箱已被占用");
        }else{
            RandomCheckNumber randomCheckNumber=new RandomCheckNumber();
            String code=randomCheckNumber.randomCodeGenerator();
            this.sendSimpleMail(registeredUser.getMailAddress(),code);

            return resultFactory.buildSuccessResult(code,registeredUser);
        }
    }

    public void sendSimpleMail(String mailAddress,String code){
        MimeMessage message=null;
        try{
            message=javaMailSender.createMimeMessage();
            MimeMessageHelper helper=new MimeMessageHelper(message,true);
            helper.setFrom("xuyukai6@163.com");
            helper.setTo(mailAddress);
            helper.setSubject("标题：欢迎来到WSDN");

            StringBuffer stringBuffer=new StringBuffer();
            stringBuffer.append("<h1>欢迎来到WSDN</h1>")
                    .append("<p style='color:#F00'>"+code+"</p>");
            helper.setText(stringBuffer.toString(),true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @PostMapping(value = "/api/register")
    public Result register(@Valid @RequestBody User requestUser, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return resultFactory.buildFailResult("系统错误，注册失败");
        }
        User registeredUser = userDao.findUserByMailAddress(requestUser.getMailAddress());
        if(registeredUser != null) {
            return resultFactory.buildFailResult("邮箱已被占用");
        }else{
            userDao.save(registeredUser);

            return resultFactory.buildSuccessResult("注册成功",registeredUser);
        }
    }
}

