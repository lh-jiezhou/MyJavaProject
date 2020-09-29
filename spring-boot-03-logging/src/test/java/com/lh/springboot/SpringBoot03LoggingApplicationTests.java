package com.lh.springboot;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBoot03LoggingApplicationTests {

    // 记录器
    Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    void contextLoads() {
        //System.out.println(); //采用日志,不使用sout输出

        // 常用方法
        // 日志的级别：由高到低 trace<debug<info<warn<error
        // 可以调整输出的日志级别，日志就只会在这个级别以后的高级别生效

        logger.trace("这是trace日志..");
        logger.debug("这是debug日志..");
        // SpringBoot默认给我们使用的是info级别的（root级别）, 在配置文件中可进行调整logging.level
        logger.info("这是info日志..");
        logger.warn("这是warn日志..");
        logger.error("这是error日志..");

    }

}
