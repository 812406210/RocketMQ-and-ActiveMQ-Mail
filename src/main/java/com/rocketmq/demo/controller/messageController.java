package com.rocketmq.demo.controller;

import com.rocketmq.demo.Utils.SendMessageUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class messageController {
    @RequestMapping("/sendPhoneMsg")
    public  String sendPhoneMsg(){
        String uid = "xxxxxxxx"; //SMS账号812406210@qq.com
        String key = "xxxxxxxxxxxx";  //账号密钥，不是登陆密码
        String sendPhoneNum = "15521331585";    //手机号
        String desc = "验证码:";   //发送内容
        SendMessageUtil.send(uid,key,sendPhoneNum,desc+SendMessageUtil.getRandomCode(6));
        return "sucess";
    }

}
