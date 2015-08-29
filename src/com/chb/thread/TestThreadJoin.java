package com.chb.thread;

/** 
* Java线程：线程的调度-合并 
* 
* @author chb 
*/ 
public class TestThreadJoin { 
    public static void main(String[] args) { 
        Thread t2 = new MyThread2(); 
        t2.start(); 

        for (int i = 0; i < 20; i++) { 
            System.out.println("主线程第" + i + "次执行！"); 
            if (i > 2) try { 
                    //t2线程合并到主线程中，主线程停止执行过程，转�?�执行t2线程，直到t2执行完毕后继续�?? 
                    t2.join(); 
            } catch (InterruptedException e) { 
                    e.printStackTrace(); 
            } 
        } 
    } 
} 

class MyThread2 extends Thread { 
    public void run() { 
        for (int i = 0; i < 10; i++) { 
                System.out.println("线程2�?" + i + "次执行！"); 
        } 
    } 
}
