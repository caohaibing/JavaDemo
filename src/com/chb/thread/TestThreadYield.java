/** 
* Java线程：线程的调度-让步 
* 
* @author chb
*/ 
package com.chb.thread;

public class TestThreadYield { 
	
    public static void main(String[] args) { 
        Thread t1 = new MyThreadA(); 
        Thread t2 = new Thread(new MyRunnableB()); 

        t2.start(); 
        t1.start(); 
    } 
} 

class MyThreadA extends Thread { 
    public void run() { 
        for (int i = 0; i < 10; i++) { 
                System.out.println("线程1�?" + i + "次执行！"); 
        } 
    } 
} 

class MyRunnableB implements Runnable { 
    public void run() { 
        for (int i = 0; i < 10; i++) { 
                System.out.println("线程2�?" + i + "次执行！"); 
                Thread.yield(); 
        } 
    } 
}


