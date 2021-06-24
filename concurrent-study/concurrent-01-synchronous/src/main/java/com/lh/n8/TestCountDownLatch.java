package com.lh.n8;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.lh.n2.util.Sleeper.sleep;

/**
 * TestCountDownLatch 应用
 *      线程数倒计时
 *
 *      线程池的线程一般一直处于运行 所以不可用 join 代替 CountDownLatch 用于等待线程结束
 *           join 底层API
 *           CountDownLatch 偏高级API
 *
 *      模拟游戏加载
 *
 *      商城信息模拟 多线程获取信息 ； 等信息完成 再显示
 *          RestTemplate
 * @author lh
 */
@Slf4j(topic = "c.TestCountDownLatch")
public class TestCountDownLatch {

    public static void main(String[] args) throws InterruptedException {
        test6();
    }

    /**
     * 模拟游戏加载过程
     *
     * @throws InterruptedException
     */
    private static void test6() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(10);
        Random r = new Random();
        String[] all = new String[10];
        for (int j = 0; j < 10; j++) {
            // lambda 表达式只能引用 局部常量
            int k = j;
            service.submit(() -> {
                for (int i = 0; i <= 100; i++) {
                    // 随机睡眠时间，模拟网络延迟
                    try {
                        Thread.sleep(r.nextInt(100));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    all[k] = i + "%";
                    // 让后面的打印覆盖前面的打印结果 （"\r"回车: 回退到前面一行）
                    System.out.print("\r" + Arrays.toString(all));
                }
                // 计数减1
                latch.countDown();
            });
        }
        latch.await();
        System.out.println("\n游戏开始");
        // 停止线程池
        service.shutdown();
    }

    private static void test5() {
        CountDownLatch latch = new CountDownLatch(3);
        // 使用线程池
        ExecutorService service = Executors.newFixedThreadPool(4);
        service.submit(() -> {
            log.debug("begin");
            sleep(1);
            // 计数减一
            latch.countDown();
            log.debug("end... {}", latch.getCount() );
        });
        service.submit(() -> {
            log.debug("begin");
            sleep(1.5);
            // 计数减一
            latch.countDown();
            log.debug("end... {}", latch.getCount() );
        });
        service.submit(() -> {
            log.debug("begin");
            sleep(2);
            // 计数减一
            latch.countDown();
            log.debug("end... {}", latch.getCount() );
        });
        service.submit(() -> {
            try {
                log.debug("waiting...");
                latch.await();
                log.debug("wait end...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private static void test4() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);

        new Thread(() -> {
            log.debug("begin");
            sleep(1);
            // 计数减一
            latch.countDown();
            log.debug("end... {}", latch.getCount() );
        }).start();
        new Thread(() -> {
            log.debug("begin");
            sleep(2);
            // 计数减一
            latch.countDown();
            log.debug("end... {}", latch.getCount() );
        }).start();
        new Thread(() -> {
            log.debug("begin");
            sleep(1.5);
            // 计数减一
            latch.countDown();
            log.debug("end... {}", latch.getCount() );
        }).start();

        log.debug("waiting...");
        latch.await();
        log.debug("wait end...");
    }


}
