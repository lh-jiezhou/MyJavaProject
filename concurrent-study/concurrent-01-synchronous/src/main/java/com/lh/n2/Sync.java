package com.lh.n2;


import com.lh.Constants;
import com.lh.n2.util.FileReader;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Sync")
public class Sync {

    // 同步
    public static void main(String[] args) {
        FileReader.read(Constants.MP4_FULL_PATH);
        log.debug("do other things ...");
    }

}
