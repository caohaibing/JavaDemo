package com.chb.thread;

/** 
* Java线程：线程的同步-同步代码块 
* 
* @author chb 
*/
public class TestThreadSynchronizatedBlock {
	public static void main(String[] args) { 
		UserAccount u = new UserAccount("张三", 100); 
        MyThreadBlock t1 = new MyThreadBlock("线程A", u, 20); 
        MyThreadBlock t2 = new MyThreadBlock("线程B", u, -60); 
        MyThreadBlock t3 = new MyThreadBlock("线程C", u, -80); 
        MyThreadBlock t4 = new MyThreadBlock("线程D", u, -30); 
        MyThreadBlock t5 = new MyThreadBlock("线程E", u, 32); 
        MyThreadBlock t6 = new MyThreadBlock("线程F", u, 21); 

        t1.start(); 
        t2.start(); 
        t3.start(); 
        t4.start(); 
        t5.start(); 
        t6.start(); 
	} 
}


class MyThreadBlock extends Thread { 
    private UserAccount u; 
    private int y = 0; 

    MyThreadBlock(String name, UserAccount u, int y) { 
        super(name); 
        this.u = u; 
        this.y = y; 
    } 

    public void run() { 
        u.oper(y); 
    } 
} 

class UserAccount { 
    private String code; 
    private int cash; 

    UserAccount(String code, int cash) { 
        this.code = code; 
        this.cash = cash; 
    } 

    public String getCode() { 
        return code; 
    } 

    public void setCode(String code) { 
        this.code = code; 
    } 

    /** 
     * 业务方法 
     * 
     * @param x 添加x万元 
     */ 
    public void oper(int x) { 
        try { 
            Thread.sleep(10L); 
            synchronized (this) { 
                    this.cash += x; 
                    System.out.println(Thread.currentThread().getName() + "运行结束，增加�??" + x +"”，当前用户账户余额为：" + cash); 
            } 
            Thread.sleep(10L); 
        } catch (InterruptedException e) { 
            e.printStackTrace(); 
        } 
    } 

    @Override 
    public String toString() { 
        return "User{" + 
                        "code='" + code + '\'' + 
                        ", cash=" + cash + 
                        '}'; 
    } 
}
