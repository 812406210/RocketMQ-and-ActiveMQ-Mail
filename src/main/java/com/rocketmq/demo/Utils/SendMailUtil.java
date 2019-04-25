package com.rocketmq.demo.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class SendMailUtil {
    @Autowired
    JavaMailSender mailSender;

    /**
     * 发送文本消息
     * @param senderMail    发送者邮箱
     * @param receiveMail   接受者邮箱
     * @param subject   主题
     * @param text  主体内容
     * @return
     */
    public  String  sendMail(String senderMail,String receiveMail,String subject,String text){
        try {
            final MimeMessage message = this.mailSender.createMimeMessage();
            final MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom(senderMail);//发送者邮箱
            helper.setTo(receiveMail);//接收者邮箱
            helper.setSubject(subject);
            helper.setText(text);
            this.mailSender.send(message);
            return "sucesss";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "error";
        }
    }


    /**
     * 带附件发送邮件----可多个附件 图片，doc都可以发送
     * @param senderMail  发送者邮箱
     * @param receiveMail 接受者邮箱
     * @param subject   主题
     * @param text      主体内容
     * @param fileArray    附件路径
     * @return
     */
    public  String  sendMailFile(String senderMail,String receiveMail,String subject,String text,String[] fileArray) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(senderMail);
            helper.setTo(receiveMail);
            helper.setSubject(subject);
            helper.setText(text);
            //验证文件数据是否为空
            if (null != fileArray) {
                FileSystemResource file = null;
                for (int i = 0; i < fileArray.length; i++) {
                    //添加附件
                    file = new FileSystemResource(fileArray[i]);
                    helper.addAttachment(fileArray[i].substring(fileArray[i].lastIndexOf(File.separator)), file);
                }
            }
            mailSender.send(message);
            System.out.println("带附件的邮件发送成功");
            return "sucesss";
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("发送带附件的邮件失败");
            return "error";
        }
    }
}
