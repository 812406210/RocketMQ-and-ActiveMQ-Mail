package com.rocketmq.demo.controller;

import com.rocketmq.demo.Utils.SendMailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
public class mailController {
    @Autowired
    SendMailUtil sendMailUtil;

    /**
     * 发送文本消息，不带附件
     * @return
     */
    @ResponseBody
    @RequestMapping("/send")
    public Object sendEmail() {
        String senderMial = "812406210@qq.com";
        String receiveMail = "815899746@qq.com";
        String subject = "测试主题";
        String text = "测试内容";
        return sendMailUtil.sendMail(senderMial,receiveMail,subject,text);
    }

    @GetMapping("/sendFile")
    public String sendAttachmentsMail() {
        String senderMial = "812406210@qq.com";
        String receiveMail = "815899746@qq.com";
        String subject = "测试主题";
        String text = "测试内容";
        String[] path={"C:\\Users\\yangwj\\Desktop\\公司\\联通正式员工详细信息表.xls"};
        return  sendMailUtil.sendMailFile(senderMial,receiveMail,subject,text,path);

    }


}