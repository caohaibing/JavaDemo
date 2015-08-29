/**
 * 原子量虽然可以保证单个变量在某一个操作过程的安全，但无法保证你整个代码块，或者整个程序的安全性。
 * 因此，通常还应该使用锁等同步机制来控制整个程序的安全性。
 * 原子量：原子量即操作变量的操作是“原子的”，该操作不可再分，因此是线程安全的。
 * Java5之后，专门提供了用来进行单变量多线程并发安全访问的工具包java.util.concurrent.atomic，其中的类也很简单。
 * @author chb
 */
package com.chb.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestAtomic {
	public static void main(String[] args){
		//创建数目特定的线程池
		ExecutorService pool = Executors.newFixedThreadPool(2);
		//使用锁等同步机制来控制整个程序的安全性
		Lock lock = new ReentrantLock(false);
		
		Runnable t1 = new MyAtomicRunnable("张三", 2000,lock);
		Runnable t2 = new MyAtomicRunnable("李四", 3600,lock); 
        Runnable t3 = new MyAtomicRunnable("王五", 2700,lock); 
        Runnable t4 = new MyAtomicRunnable("老张", 600,lock); 
        Runnable t5 = new MyAtomicRunnable("老牛", 1300,lock); 
        Runnable t6 = new MyAtomicRunnable("胖子", 800,lock); 
        
        //执行各个线程
        pool.execute(t1);
        pool.execute(t2); 
        pool.execute(t3); 
        pool.execute(t4); 
        pool.execute(t5); 
        pool.execute(t6);
        
        //关闭线程池
        pool.shutdown();
	}
}

class MyAtomicRunnable implements Runnable{
	//原子量，静态变量（类成员变量），每个线程都可以自由操作
	private static AtomicLong aLong = new AtomicLong(10000);
	private String name;
	private int x;
	private Lock lock;
	
	MyAtomicRunnable(String name, int x, Lock lock){
		this.name = name;
		this.x = x;
		this.lock = lock;
	}
	
	public void run(){
		lock.lock();
		System.out.println(name +"执行了" + x + ", 当前余额为: "+ aLong.addAndGet(x));
		lock.unlock();
	}	
}
