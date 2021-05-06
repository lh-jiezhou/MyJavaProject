package com.lh.n2;


import com.lh.Constants;
import com.lh.n2.util.FileReader;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Async")
public class Async {

    public static void main(String[] args) {
        // 异步
        new Thread(() -> FileReader.read(Constants.MP4_FULL_PATH)).start();
        log.debug("do other things ...");
    }

}
