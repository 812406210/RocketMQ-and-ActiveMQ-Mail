package com.rocketmq.demo.rocketMQ;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by Administrator on 2018/8/1.
 */
@Component
public class Producer {

    private DefaultMQProducer producer;
    private static final String NamesrvAddr = "139.199.64.189:9876";
    private String topic = "LRW_COM_TOPIC_PRO";
    private String producerGroup="CID_LRW_DEV_SUBS_PRO";;

    public Producer() {
        producer = new DefaultMQProducer(producerGroup);
        producer.setNamesrvAddr(NamesrvAddr);
        try {
            producer.start();
        } catch (MQClientException e) {
            System.out.println("producer start failed");
            return;
        }
    }

    /**
     * 发送消息
     *
     * @param tag 标签
     * @param content 发送内容
     */
    public void sendMsg(String tag,String content) {
        try {
            Message msg = new Message(topic,tag, content.getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = producer.send(msg);
            if (sendResult != null) {
                System.out.println(new Date() + " Send mq message success. Topic is:" + this.topic + " msgId is: " + sendResult.getMsgId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Send mq message failed. Topic is:" + topic);
        } finally {
           // producer.shutdown();
        }
    }
}
