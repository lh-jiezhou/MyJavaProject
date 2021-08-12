package com.lh;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.ArrayList;


public class Nothing {


    public static void main(String[] args) throws InterruptedException {
        Solution s = new Solution();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(10000));
        final Scanner reader = new Scanner(System.in);
        final String next = reader.next();
        List<Line> lines = Arrays.stream(next.split(",")).map(str -> new StringLine(str))
                .collect(Collectors.toList());
        List<Line> result = s.translateAll(lines, "", threadPoolExecutor);
        String resultString = result.stream().map(l -> l.toString()).collect(Collectors.joining(","));
        System.out.println(resultString);
        reader.close();
        threadPoolExecutor.shutdown();
    }

    public interface Line {
        /**
         * translate the line to the specific language
         * @param language - the language to translate
         * @return the line of translated by the {@code language} */
        Line translate(String language);
    }

    public static class Solution {
        /**
         * translate the all lines to the specific language
         * @param lines the text lines of episode
         * @param language the language to translate
         * @return the lines of translated by the {@code language} */
        public List<Line> translateAll(List<Line> lines, String language, Executor executor) throws InterruptedException {
            Job<Line> job = new Job<>();
            for (Line line : lines) {
                Callable<Line> callable = () -> line.translate(language);
                job.newTask(callable);
            }
            job.execute(executor);
            return job.get();
        }
    }

    public static class Job<V> {

        List<Callable<V>> threads = new ArrayList<>();
        List<Future<V>> futures;

        public void newTask(Callable<V> runnable) {
            //待实现
            threads.add(runnable);
        }


        public void execute(Executor executor) {
            //待实现
            ExecutorService service = (ExecutorService) executor;
            try {
                futures = service.invokeAll(threads);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public List<V> get() throws InterruptedException {
            //待实现
            List<V> res = new ArrayList<>();
            for (Future<V> future: futures){
                try {
                    res.add(future.get());
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }

            return res;
        }

    }

    /**
     * translate the string line to upper case
     */
    public static class StringLine implements Line {
        private String text;

        public StringLine(String text) {
            this.text = text;
        }

        @Override
        public Line translate(String language) {
            return new StringLine(text.toUpperCase());
        }


        @Override
        public String toString() {
            return text;
        }
    }
}