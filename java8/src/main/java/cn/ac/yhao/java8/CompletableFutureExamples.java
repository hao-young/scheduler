package cn.ac.yhao.java8;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static java.lang.Thread.sleep;

/**
 * @description: CompletableFuture
 * @author: Daniel Young
 * @create: 2021-10-12 10:53
 */
public class CompletableFutureExamples {

    @Test
    public void testDemo1() {
        // 创建异步执行任务:
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(CompletableFutureExamples::fetchPrice);
        // 如果执行成功:
        cf.thenAccept((result) -> {
            System.out.println("price: " + result);
        });
        // 如果执行异常:
        cf.exceptionally((e) -> {
            System.err.println(e.getMessage());
            return null;
        });
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        try {
            sleep(200);
        } catch (InterruptedException e) {
        }
    }

    /**
     * CompletableFuture 串行执行
     */
    @Test
    public void testDemo2() {
        //第一个任务
        CompletableFuture<String> cfQuery = CompletableFuture.supplyAsync(() -> {
            return queryCode("石油");
        });
        //cfQuery成功后继续执行下一个任务:
        CompletableFuture<Double> cfFetch = cfQuery.thenApplyAsync((code) -> {
            return fetchPrice(code);
        });
        //cfFetch成功后打印结果:
        cfFetch.thenAccept((result)->{
            System.out.println("price:"+ result);
        });
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        try {
            sleep(2000);
        } catch (InterruptedException e) {
        }

    }

    /**
     * CompletableFuture 并行执行
     */
    @Test
    public void testDemo3() {
        //两个CompletableFuture执行异步查询:
        CompletableFuture<String> cfqueryFromSina = CompletableFuture.supplyAsync(() -> {
            return queryCode("石油", "https://finance.sina.com.cn/code/");
        });
        CompletableFuture<String> cfqueryFrom163 = CompletableFuture.supplyAsync(() -> {
            return queryCode("石油", "https://money.163.com/code/");
        });

        // 用anyOf合并为一个新的CompletableFuture:
        CompletableFuture<Object> cfQuery = CompletableFuture.anyOf(cfqueryFromSina, cfqueryFrom163);

        // 两个CompletableFuture执行异步查询:
        CompletableFuture<Double> cfFetchFromSina = cfQuery.thenApplyAsync((code) -> {
            return fetchPrice((String) code, "https://finance.sina.com.cn/price/");
        });
        CompletableFuture<Double> cfFetchFrom163 = cfQuery.thenApplyAsync((code) -> {
            return fetchPrice((String) code, "https://money.163.com/price/");
        });

        // 用anyOf合并为一个新的CompletableFuture:
        CompletableFuture<Object> cfFetch = CompletableFuture.anyOf(cfFetchFrom163, cfFetchFromSina);
        // 最终结果:
        cfFetch.thenAccept((result) -> {
            System.out.println("price:" + result);
        });
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        try {
            sleep(200);
        } catch (InterruptedException e) {
        }


    }


    static Double fetchPrice() {
        try {
            sleep(100);
        } catch (InterruptedException e) {
        }
        if (Math.random() < 0.3) {
            throw new RuntimeException("fetch price failed!");
        }
        return 5 + Math.random() * 20;
    }

    static String queryCode(String name) {
        try {
            sleep(100);
        } catch (InterruptedException e) {
        }
        return "601857";
    }

    static Double fetchPrice(String code) {
        try {
            sleep(100);
        } catch (InterruptedException e) {
        }
        return 5 + Math.random() * 20;
    }

    static String queryCode(String name, String url) {
        System.out.println("query code from " + url + "...");
        try {
            sleep((long) (Math.random() * 100));
        } catch (InterruptedException e) {
        }
        return "601857";
    }

    static Double fetchPrice(String code, String url) {
        System.out.println("query price from " + url + "...");
        try {
            sleep((long) (Math.random() * 100));
        } catch (InterruptedException e) {
        }
        return 5 + Math.random() * 20;
    }
}
