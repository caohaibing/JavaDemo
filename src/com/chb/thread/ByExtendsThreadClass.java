/**
 * 通过扩展Thread类实现多线程 
 * @author chb
 */
package com.chb.thread;

public class ByExtendsThreadClass {
	
	public static void main(String[] args){
		System.out.println(Thread.currentThread().getName()+"线程开始运行!");
		
		new MultiThread("A").start();
		new MultiThread("B").start();
		
		System.out.println(Thread.currentThread().getName()+"线程运行结束!");	
	}
}

/*
 * 通过扩展Thread类来实现多线程
 */
class MultiThread extends Thread{
	public MultiThread(String threadName){
		super(threadName);
	}
	
	public void run(){
		System.out.println(getName()+"线程运行开始!");
		for(int i = 0; i<10; i++){
			System.out.println(i + " "+ getName());
			
			try{
				sleep((int)Math.random()*10);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName()+"线程运行结束!");
	}
}


