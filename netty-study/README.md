# Netty学习

## 一、NIO基础

### 1.三大组件

#### 1.1 Channel & Buffer

- Channel: 数据传输通道；读写数据的双向通道，可以从 channel 将数据读入 buffer，也可以将 buffer 的数据写入 channel，而之前的 stream 要么是输入，要么是输出，channel 比 stream 更为底层
- Buffer: 内存缓冲区；应用程序、文件以及网络之间的数据桥梁