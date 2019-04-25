package com.rocketmq.demo.activeMq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ActiveProducer {
    //注入jsmtemplate
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @RequestMapping("/sendMsg")
    public void sendMsg(String msg) {
        jmsMessagingTemplate.convertAndSend("my_msg", msg);
        System.out.println("msg发送成功");
    }

    @RequestMapping("/sendMap")
    public void sendMap() {
        Map map = new HashMap();
        map.put("mobile", "13888888888");
        map.put("content", "王总喜提兰博基尼");
        jmsMessagingTemplate.convertAndSend("my_map", map);
        System.out.println("map发送成功");
    }
}