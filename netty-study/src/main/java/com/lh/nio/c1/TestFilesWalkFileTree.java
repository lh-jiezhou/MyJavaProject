package com.lh.nio.c1;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 遍历目录文件
 *   SimpleFileVisitor 用到的设计模式：访问者模式
 * 删除多级目录
 *  （用程序代码 去做删除 是非常危险的 （不走回收站））
 * 拷贝多级文件夹目录 walk
 *
 *  快捷键：
 *      在main中 选中代码后 ctrl + alt + M 生成方法 m1
 *      选中 代码后 ctrl + alt + T 可加 try {} catch{}
 */
public class TestFilesWalkFileTree {

    public static void main(String[] args) throws IOException{

        // 拷贝多级文件夹目录 walk
        String source = "C:\\Users\\89785\\Desktop\\Netty网络编程笔记\\删除多级目录test - 副本";
        String target = "C:\\Users\\89785\\Desktop\\Netty网络编程笔记\\删除多级目录test";

        Files.walk(Paths.get(source)).forEach(path -> {
            //
            try {
                String targetName = path.toString().replace(source, target);
                // path 是目录 或 文件
                if(Files.isDirectory(path)) { // 目录
                    Files.createDirectories(Paths.get(targetName));
                } else if(Files.isRegularFile(path)) { // 文件
                    Files.copy(path, Paths.get(targetName));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


    }

    // 删除多级目录 （用程序代码 去做删除 是非常危险的 （不走回收站））
    private static void m3() throws IOException {

//        Files.delete(Paths.get("C:\\Users\\89785\\Desktop\\Netty网络编程笔记\\删除多级目录test")); // 不为空删除失败 DirectoryNotEmptyException
        // 需要从里到外删除
        Files.walkFileTree(Paths.get("C:\\Users\\89785\\Desktop\\Netty网络编程笔记\\删除多级目录test"), new SimpleFileVisitor<Path>(){
//            @Override
//            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
//                System.out.println("====> 进入" + dir);
//                return super.preVisitDirectory(dir, attrs);
//            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
//                System.out.println(file);
                Files.delete(file); // 先删文件
                return super.visitFile(file, attrs);
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
//                System.out.println("<==== 退出" + dir);
                Files.delete(dir); // 再删文件夹
                return super.postVisitDirectory(dir, exc);
            }
        });
    }

    // 查看jar文件数量
    private static void m2() throws IOException {
        AtomicInteger jarCount = new AtomicInteger();
        Files.walkFileTree(Paths.get("D:\\Java\\jdk-11.0.6"), new SimpleFileVisitor<Path>(){

            // 找所有的jar文件
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if(file.toString().endsWith(".jar")){
                    System.out.println(file);
                    jarCount.incrementAndGet();
                }
                return super.visitFile(file, attrs);
            }
        });
        System.out.println("jarCount: "+jarCount);
    }

    // 查看 文件夹 和 文件 数量
    private static void m1() throws IOException {
        // 计数器 (不能直接用 int； 匿名类引用外部的局部变量问题)
        AtomicInteger dirCount =  new AtomicInteger();
        AtomicInteger fileCount = new AtomicInteger();
        Files.walkFileTree(Paths.get("D:\\Java\\jdk-11.0.6"), new SimpleFileVisitor<Path>(){

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println("====>" + dir);
                dirCount.incrementAndGet(); // 计数+1
                return super.preVisitDirectory(dir, attrs);
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println(file);
                fileCount.incrementAndGet();
                return super.visitFile(file, attrs);
            }
        });

        System.out.println("dirCount: "+dirCount);
        System.out.println("fileCount: "+fileCount);
    }

}
