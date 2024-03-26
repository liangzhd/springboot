package org.example.bigevent;

import org.junit.jupiter.api.Test;

public class ThreadLocalTest {
    @Test
    public void testThreadLocal() {
        ThreadLocal tl = new ThreadLocal();

        new Thread(() -> {
            tl.set("线程1");
            System.out.println(Thread.currentThread().getName()+": "+tl.get());
            System.out.println(Thread.currentThread().getName()+": "+tl.get());
            System.out.println(Thread.currentThread().getName()+": "+tl.get());
        },"蓝色").start();

        new Thread(() -> {
            tl.set("线程2");
            System.out.println(Thread.currentThread().getName()+": "+tl.get());
            System.out.println(Thread.currentThread().getName()+": "+tl.get());
            System.out.println(Thread.currentThread().getName()+": "+tl.get());
        },"绿色").start();
    }
}

