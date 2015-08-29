/**
 * @author chb
 * 
 * 条件变量就是表示条件的一种变量。但是必须说明，这里的条件是没有实际含义的，仅仅是个标记而已，并且条件的含义往往通过代码来赋予其含义
 * 条件变量都实现了java.util.concurrent.locks.Condition接口，
 * 条件变量的实例化是通过一个Lock对象上调用newCondition()方法来获取的，这样，条件就和一个锁对象绑定起来了。
 * 因此，Java中的条件变量只能和锁配合使用，来控制并发程序访问竞争资源的安全。
 */
package com.chb.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestLocksCondition {
	public static void main(String[] args){
		//创建并发访问的账户
		MyCount myCount = new MyCount("362321199005206511", 10000);
		
		//创建一个线程池
		ExecutorService pool = Executors.newFixedThreadPool(2);
		
		Thread t1 = new SaveThread("张三",myCount,2000);
		Thread t2 = new SaveThread("李四", myCount, 3600); 
        Thread t3 = new DrawThread("王五", myCount, 2700); 
        Thread t4 = new SaveThread("老张", myCount, 600); 
        Thread t5 = new DrawThread("老牛", myCount, 6000); 
        Thread t6 = new DrawThread("胖子", myCount, 800);
        
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

/**
 * 存款线程类
 */
class SaveThread extends Thread{
	private String name;
	private MyCount myCount;
	private int x;
	
	SaveThread(String name,MyCount myCount,int x){
		this.name = name;
		this.myCount = myCount;
		this.x = x;
	}
	
	public void run(){
		myCount.saving(x,name);
	}
}

/**
 * 取款线程类
 */
class DrawThread extends Thread{
	private String name;
	private MyCount myCount;
	private int x;
	
	DrawThread(String name,MyCount myCount,int x){
		this.name = name;
		this.myCount = myCount;
		this.x = x;
	}
	
	public void run(){
		myCount.drawing(x,name);
	}
}

/**
 * 普通银行账户，不可透支
 * 
 * 一个类只能在 本包 中访问，可以直接声明为class；若要在外部的包中也能访问，需要声明为 public class
 */
class MyCount{
	private String oid;
	private int cash;
	private Lock lock = new ReentrantLock();  //账户锁
	private Condition _save = lock.newCondition(); //存款条件
	private Condition _draw = lock.newCondition(); //取款条件
	
	MyCount(String oid, int cash){
		this.oid = oid;
		this.cash = cash;
	}
	
	/**
	 * 存款操作
	 * 
	 * @param x  操作金额
	 * @param name 操作人
	 */
	public void saving(int x,String name){
		lock.lock();  //获取锁
		
		if(x > 0){
			cash += x;  //存款
			System.out.println(name + " 存款 " + x + ", 当前余额： " + cash);
		}
		
		_draw.signalAll();   //唤醒所有等待的取款线程
		
		//解锁
		lock.unlock();
	}
	
	/**
	 * 取款操作
	 * 
	 * @param x 操作金额
	 * @param name 操作人
	 */
	public void drawing(int x, String name){
		//获取锁
		lock.lock();
			
		try{
			if(cash - x < 0){
				_draw.await();
			}else{
				cash -= x;
				System.out.println(name + " 取款 " + x + ", 当前余额： " + cash);
			}
			_save.signalAll();  //唤醒所有等待的存款线程
		}catch(InterruptedException e){
			e.printStackTrace();
		}finally{
			//解锁（切记）
			lock.unlock();
		}	
		
		
		//		if(cash - x < 0){
		//		try{
		//		_draw.await();
		//		}catch(InterruptedException e){
		//			e.printStackTrace();
		//		}
		//	}else{
		//		cash -= x;
		//		System.out.println(name + " 取款 " + x + ", 当前余额： " + cash);
		//	}
		//	_save.signalAll();  //唤醒所有等待的存款线程
		// 
		//	//解锁（切记）
		//	lock.unlock();
	}	
}

