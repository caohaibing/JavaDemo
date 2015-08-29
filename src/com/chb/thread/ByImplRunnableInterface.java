/*
 * 通过实现Runnable接口实现Java多线程
 * @author chb
 */
package com.chb.thread;

public class ByImplRunnableInterface implements Runnable{
	
	public static void main(String[] args){
		System.out.println(Thread.currentThread().getName() + "线程开始运行!");
		
		ByImplRunnableInterface test = new ByImplRunnableInterface();
		Thread thread1 = new Thread(test);
		Thread thread2 = new Thread(test);
		
		thread1.start();
		thread2.start();
		
		System.out.println(Thread.currentThread().getName() + "线程运行结束!");
	}
	
	//实现Runnable接口，类class里必须实现run()方法
	public void run(){
		System.out.println(Thread.currentThread().getName() + "线程开始运行!");
		
		for(int i = 0; i < 10; i++){
			System.out.println(i + " " + Thread.currentThread().getName());
			
			try{
				Thread.sleep((int) Math.random()*10);
			}catch(InterruptedException e){
				e.printStackTrace();				
			}
		}
			
		System.out.println(Thread.currentThread().getName() + "线程运行结束!");	
	}	
}
