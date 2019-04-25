package com.rocketmq.demo.controller;

import com.rocketmq.demo.rocketMQ.ConsumerListener;
import com.rocketmq.demo.rocketMQ.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class testController {
    @Autowired
    ConsumerListener consumerListener;

    @Autowired
    Producer producer;

    @RequestMapping("/hello")
    public  String Hello() {
        return  "Hello";
    }

    @RequestMapping("/product")
    public  String Product() {
        String tag = "producerTag";
        String content ="RocketMQ content (内容)";
        producer.sendMsg(tag,content);
        return  "Product";
    }


    @RequestMapping("/product1")
    public  String Product1() {
        /**
         * 消费
         */
        String tag = "producerTag----------";
        String content ="RocketMQ content (内容)-----------";
        producer.sendMsg(tag,content);
        return  "Product----------";
    }

}
