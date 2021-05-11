package com.lh.test;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

import static com.lh.n2.util.Sleeper.sleep;

/**
 * 生产者消费者模式
 *  线程间通信Id很重要
 */
@Slf4j(topic = "c.Test21")
public class Test21 {

    // 测试
    public static void main(String[] args) {

        // 创建消息队列
        MessageQueue queue = new MessageQueue(2);
        // 创建三个生产者线程, 再创建一个消费者线程
        for(int i=0; i<3; i++){
            // lambda 表达式里面的局部变量 必须是final(不可变), 而i是变化的, 所以新建id
            int id = i;
            new Thread(() -> {
                // 生产者生产消息
                queue.put(new Message(id, "值"+id));
            }, "生产者" + i).start();
        }

        new Thread(() -> {
            while (true){
                sleep(1);
                Message message = queue.take();
            }
        }, "消费者").start();
    }


}

// 消息队列类, Java线程之间通信
@Slf4j(topic = "c.MessageQueue")
class MessageQueue{

    // 集合,用于存消息 (消息队列集合)
    private LinkedList<Message> list = new LinkedList<>();
    // 队列容量
    private int capcity;

    public MessageQueue(int capcity) { // 用户决定容量
        this.capcity = capcity;
    }

    // 获取消息
    public Message take(){ // 线程间通信Id很重要, 所有不能直接返回Object
        // 检查队列是否为空
        synchronized (list){
            while(list.isEmpty()){
                try {
                    log.debug("队列为空, 消费者线程等待");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 从队列头部获取消息返回, 并移除
            Message message = list.removeFirst();
            log.debug("已消费消息", message);
            // 通知生产者, 有空位了, 唤醒等待
            list.notifyAll();
            return message;

        }
    }

    // 存入消息
    public void put(Message message){
        synchronized (list){
            // 检查队列是否已经满
            while (list.size() == capcity){
                try {
                    log.debug("队列已满, 生产线程等待");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 一旦有空位,将消息加到尾部
            list.addLast(message);
            log.debug("已生产消息 {}", message);
            // 加入后通知消费者有可用的Message,唤醒等待
            list.notifyAll();
        }
    }

}

@Slf4j(topic = "c.Message")
final class Message{ // final 不能有子类, 保证线程安全
    private int id;
    private Object value;

    public Message(int id, Object value) {
        this.id = id;
        this.value = value;
    }

    // 只有get方法意味着内部状态不可以改变,只有构造时赋初值
    // 使线程安全
    public int getId() {
        return id;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", value=" + value +
                '}';
    }
}
