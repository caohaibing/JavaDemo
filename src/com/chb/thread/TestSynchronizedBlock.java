/**
 * @author chb
 * Java线程：不用锁和条件变量，使用 Synchronized代码块
 *
 */
package com.chb.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors; 

public class TestSynchronizedBlock {
	public static void main(String[] args){
		
		//创建并发访问的账户
		Account myCount = new Account("362321199005206511", 10000);
			
		//创建一个线程池
		ExecutorService pool = Executors.newFixedThreadPool(2);
		
		Thread t1 = new SaveMoneyThread("张三",myCount,2000);
		Thread t2 = new SaveMoneyThread("李四", myCount, 3600); 
	    Thread t3 = new DrawMoneyThread("王五", myCount, 2700); 
	    Thread t4 = new SaveMoneyThread("老张", myCount, 600); 
	    Thread t5 = new DrawMoneyThread("老牛", myCount, 6000); 
	    Thread t6 = new DrawMoneyThread("胖子", myCount, 800);
	    
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
class SaveMoneyThread extends Thread{
	private String name;
	private Account myCount;
	private int x;
	
	SaveMoneyThread(String name,Account myCount,int x){
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
class DrawMoneyThread extends Thread{
	private String name;
	private Account myCount;
	private int x;
	
	DrawMoneyThread(String name,Account myCount,int x){
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
 */
class Account{
	private String oid;
	private int cash; 
	
	Account(String oid, int cash){
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
		
		if(x > 0){
			synchronized(this){
				cash += x;  //存款
				System.out.println(name + " 存款 " + x + ", 当前余额： " + cash);
				notifyAll();  //唤醒所有等待线程
			}
		}
	}
	
	/**
	 * 取款操作
	 * 
	 * @param x 操作金额
	 * @param name 操作人
	 */
	public void drawing(int x, String name){
		synchronized(this){
			if(cash - x < 0){
				try{
					wait();
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}else{
				cash -= x;
				System.out.println(name + " 取款 " + x + ", 当前余额： " + cash);
			}
			notifyAll();  //唤醒所有等待操作
		}
	}	  
}


