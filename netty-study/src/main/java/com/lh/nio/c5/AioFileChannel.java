package com.lh.nio.c5;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static com.lh.util.ByteBufferUtil.debugAll;

/**
 * 文件Aio 异步
 *      守护线程 在 其他线程结束后 也会直接结束
 */
@Slf4j
public class AioFileChannel {


    public static void main(String[] args) throws IOException {
        try (AsynchronousFileChannel channel = AsynchronousFileChannel.open(Paths.get("data.txt"), StandardOpenOption.READ)) {
            // 参数1. ByteBuffer;
            // 参数2. 读取的起始位置;
            // 参数3. 附件;
            // 参数4. 回调对象
            ByteBuffer buffer = ByteBuffer.allocate(16);
            log.debug("read begin...");
            channel.read(buffer, 0, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                // read 成功
                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    // result 实际读到的字节数
                    log.debug("read completed...{}", result);
                    attachment.flip();
                    debugAll(attachment);
                }

                // read 失败
                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    exc.printStackTrace(); // 打印异常
                }
            });
            log.debug("read end...");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.in.read(); // 接受控制台输入 无输入则停在此处;  用于防止主线程结束
    }
}
