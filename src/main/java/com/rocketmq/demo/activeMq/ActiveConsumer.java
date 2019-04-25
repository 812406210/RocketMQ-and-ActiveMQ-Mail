package com.rocketmq.demo.activeMq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class ActiveConsumer {
    @JmsListener(destination = "my_msg")
    public void readMsg(String text) {
        System.out.println("接收到消息：" + text);
    }

    @JmsListener(destination = "my_map")
    public void readMap(Map map) {
        System.out.println(map);
    }
}