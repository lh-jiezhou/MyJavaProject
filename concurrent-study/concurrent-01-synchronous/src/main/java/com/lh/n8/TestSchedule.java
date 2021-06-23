package com.lh.n8;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 线程池应用：定时任务
 *      每周四执行
 *
 * 一个类(时间处理)
 *      Duration 时间间隔运算
 */
public class TestSchedule {

    /**
     * 如何让每周四 18:00:00 定时执行任务
     *
     */
    public static void main(String[] args) {

        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        // 获取周四时间(后面最近的周四)
        LocalDateTime time = now.withHour(18).withMinute(0).withSecond(0).withNano(0).with(DayOfWeek.THURSDAY);
        // 如果当前时间大于周四 需要找到下周周四
        if(now.compareTo(time) > 0){
            // 加一周
            time = time.plusWeeks(1);
         }
        System.out.println(time);
        

        //  initialDelay: 代表当前时间和周四的时间差
        //  period: 一周的间隔时间
        long period = 1000 * 60 * 60 * 24 * 7;
        long initialDelay = Duration.between(now, time).toMillis();
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
        pool.scheduleAtFixedRate(() -> {
            System.out.println("running...");
        }, initialDelay, period, TimeUnit.MILLISECONDS);

    }
}
