/** 
* Java线程：线程的调度-优先级
* 并不能精确地控制线程调度的次序，每次运行时的结果不一样
* @author chb
*/
package com.chb.thread;

public class TestThreadPriority {
	
	public static void main(String[] args) { 
        Thread t1 = new MyThread1(); 
        Thread t2 = new Thread(new MyRunnable()); 
        t1.setPriority(10); 
        t2.setPriority(1); 

        t2.start(); 
        t1.start(); 
	} 
}

class MyThread1 extends Thread { 
	public void run() { 
		for (int i = 0; i < 10; i++) { 
	        System.out.println("线程1第" + i + "次执行！"); 
	        try { 
	                Thread.sleep(100); 
	        } catch (InterruptedException e) { 
	                e.printStackTrace(); 
	        } 
		} 
	} 
} 

class MyRunnable implements Runnable { 
	public void run() { 
        for (int i = 0; i < 10; i++) { 
            System.out.println("线程2第" + i + "次执行！"); 
            try { 
                    Thread.sleep(100); 
            } catch (InterruptedException e) { 
                    e.printStackTrace(); 
            } 
        } 
	} 
}
