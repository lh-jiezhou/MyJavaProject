package com.lh.pattern;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

import static com.lh.pattern.Downloader.*;

/**
 * 同步模式之保护性暂停
 *  线程1与线程2同构 GuardedObject交流
 */
@Slf4j(topic = "c.TestGuardedObject")
public class TestGuardedObject {
    public static void main(String[] args) {
        GuardedObject guardedObject = new GuardedObject();
        new Thread(() -> {
            try {
                List<String> response = download();
                log.debug("download complete...");
                guardedObject.complete(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        log.debug("waiting...");
        Object response = guardedObject.get();
        log.debug("get response: [{}] lines", ((List<String>) response).size());

    }


}

class GuardedObject {

    private Object response;
    private final Object lock = new Object();

    public Object get() {
        synchronized (lock) {
            // 条件不满足则等待
            while (response == null) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return response;
        }
    }

    public void complete(Object response) {
        synchronized (lock) {
            // 条件满足，通知等待线程
            this.response = response;
            lock.notifyAll();
        }
    }
}