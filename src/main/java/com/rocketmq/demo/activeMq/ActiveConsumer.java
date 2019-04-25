package com.rocketmq.demo.activeMq;

import com.rocketmq.demo.Utils.SendMailUtil;
import com.rocketmq.demo.Utils.SendMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class ActiveConsumer {
    @Autowired
    SendMailUtil sendMailUtil;

    @JmsListener(destination = "my_msg")
    public void readMsg(String text) {
        if(text!=null){
            //发送短信
            String uid = "812406210@qq.com"; //SMS账号
            String key = "xxxxx";  //账号密钥，不是登陆密码
            String sendPhoneNum = "15521331585";    //手机号
            String desc = "验证码:";   //发送内容
            SendMessageUtil.send(uid,key,sendPhoneNum,desc+SendMessageUtil.getRandomCode(6));

            //发送邮件
            String senderMial = "812406210@qq.com";
            String receiveMail = "815899746@qq.com";
            String subject = "测试主题";
            String content = text;
            sendMailUtil.sendMail(senderMial,receiveMail,subject,content);

            //打印消息内容
            System.out.println("接收到消息：" + text);

        }

    }

    @JmsListener(destination = "my_map")
    public void readMap(Map map) {
        System.out.println(map);
    }
}
