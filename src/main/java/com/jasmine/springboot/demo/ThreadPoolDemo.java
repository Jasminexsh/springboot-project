package com.jasmine.springboot.demo;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 线程池Demo
 *
 * @author xieshanghan
 * @version ThreadPoolDemo.java, v 0.1, 2023年02月03日 13:42 xieshanghan
 */
public class ThreadPoolDemo {

    public static void main(String[] args) {
        demo1();
        demo2();
        demo3();
        demo4();
        demo5();
    }

    /**
     * ThreadLocal 子线程无法取到父线程的值
     */
    private static void demo1() {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("demo1");
        new Thread(() -> {
            //这里并拿不到
            System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
        }).start();
    }

    /**
     * InheritableThreadLocal 子线程中取父线程的值
     */
    private static void demo2() {
        InheritableThreadLocal<String> threadLocal = new InheritableThreadLocal<>();
        threadLocal.set("demo2");
        new Thread(() -> {
            //可以拿到
            System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
        }).start();
    }

    /**
     * 线程池中，使用InheritableThreadLocal，子线程无法取到父线程的值
     */
    private static void demo3() {
        InheritableThreadLocal<String> threadLocal = new InheritableThreadLocal<>();
        ExecutorService threadPool = Executors.newFixedThreadPool(1);
        threadLocal.set("demo3-1");
        Semaphore semaphore = new Semaphore(1);
        threadPool.submit(() -> {
            System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
        });
        try {
            semaphore.acquire();
        } catch (Exception e) {
            throw new RuntimeException("semaphore acquire first exception.");
        }
        threadLocal.set("demo3-2");
        threadPool.submit(() -> {
            semaphore.release();
            System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
        });
        try {
            semaphore.acquire();
        } catch (Exception e) {
            throw new RuntimeException("semaphore acquire second exception");
        }
        threadPool.shutdown();
    }

    /**
     * TransmittableThreadLocal 实现线程池间线程值的传递
     * TtlRunnable修饰Runnable
     */
    private static void demo4() {
        TransmittableThreadLocal<String> threadLocal = new TransmittableThreadLocal<>();
        ExecutorService threadPool = Executors.newFixedThreadPool(1);
        threadLocal.set("demo4-1");
        Semaphore semaphore = new Semaphore(2);
        // 额外的处理，生成修饰了的对象ttlRunnable
        Runnable task1 = TtlRunnable.get(() -> {
            System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
        });
        threadPool.submit(task1);
        try {
            semaphore.acquire();
        } catch (Exception e) {
            throw new RuntimeException("semaphore acquire first exception.");
        }
        threadLocal.remove();
        threadLocal.set("demo4-2");
        // 额外的处理，生成修饰了的对象ttlCallable
        Runnable task2 = TtlRunnable.get(() -> {
            System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
        });
        threadPool.submit(task2);
        try {
            semaphore.acquire();
        } catch (Exception e) {
            throw new RuntimeException("semaphore acquire second exception.");
        }
        threadPool.shutdown();
    }

    private static void demo5() {
        TransmittableThreadLocal<String> threadLocal = new TransmittableThreadLocal<>();
        ExecutorService threadPool = Executors.newFixedThreadPool(1);
        threadPool = TtlExecutors.getTtlExecutorService(threadPool);
        threadLocal.set("demo5-1");
        Semaphore semaphore = new Semaphore(2);
        Runnable task1 = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
            }
        };
        threadPool.submit(task1);
        try {
            semaphore.acquire();
        } catch (Exception e) {
            throw new RuntimeException("semaphore acquire first exception.");
        }
        threadLocal.remove();
        threadLocal.set("demo5-2");
        Runnable task2 = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
            }
        };
        threadPool.submit(task2);
        try {
            semaphore.acquire();
        } catch (Exception e) {
            throw new RuntimeException("semaphore acquire second exception.");
        }
        threadPool.shutdown();
    }

}