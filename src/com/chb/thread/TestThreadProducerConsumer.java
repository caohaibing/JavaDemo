package com.chb.thread;

/** 
* Java线程：并发协�?-生产者消费�?�模�? 
* 
* @author chb
*/ 
public class TestThreadProducerConsumer { 
    public static void main(String[] args) { 
        Godown godown = new Godown(30); 
        Consumer c1 = new Consumer(50, godown); 
        Consumer c2 = new Consumer(20, godown); 
        Consumer c3 = new Consumer(30, godown); 
        Producer p1 = new Producer(10, godown); 
        Producer p2 = new Producer(10, godown); 
        Producer p3 = new Producer(10, godown); 
        Producer p4 = new Producer(10, godown); 
        Producer p5 = new Producer(10, godown); 
        Producer p6 = new Producer(10, godown); 
        Producer p7 = new Producer(80, godown); 

        c1.start(); 
        c2.start(); 
        c3.start(); 
        p1.start(); 
        p2.start(); 
        p3.start(); 
        p4.start(); 
        p5.start(); 
        p6.start(); 
        p7.start(); 
    } 
} 

/** 
* 仓库 
*/ 
class Godown { 
    public static final int max_size = 100; //�?大库存量 
    public int curnum;     //当前库存�? 

    Godown() { 
    } 

    Godown(int curnum) { 
            this.curnum = curnum; 
    } 

    /** 
     * 生产指定数量的产�? 
     * 
     * @param neednum 
     */ 
    public synchronized void produce(int neednum) { 
        //测试是否�?要生�? 
        while (neednum + curnum > max_size) { 
            System.out.println("要生产的产品数量" + neednum + "超过剩余库存�?" + (max_size - curnum) + "，暂时不能执行生产任�?!"); 
            try { 
                //当前的生产线程等�? 
                wait(); 
            } catch (InterruptedException e) { 
                e.printStackTrace(); 
            } 
        } 
        //满足生产条件，则进行生产，这里简单的更改当前库存�? 
        curnum += neednum; 
        System.out.println("已经生产�?" + neednum + "个产品，现仓储量�?" + curnum); 
        
        //唤醒在此对象监视器上等待的所有线�? 
        notifyAll(); 
    } 

    /** 
     * 消费指定数量的产�? 
     * 
     * @param neednum 
     */ 
    public synchronized void consume(int neednum) { 
        //测试是否可消�? 
        while (curnum < neednum) { 
            try { 
                    //当前的生产线程等�? 
                    wait(); 
            } catch (InterruptedException e) { 
                    e.printStackTrace(); 
            } 
        } 
        //满足消费条件，则进行消费，这里简单的更改当前库存�? 
        curnum -= neednum; 
        System.out.println("已经消费�?" + neednum + "个产品，现仓储量�?" + curnum); 
        
        //唤醒在此对象监视器上等待的所有线�? 
        notifyAll(); 
    } 
} 

/** 
* 生产�? 
*/ 
class Producer extends Thread { 
    private int neednum;              //生产产品的数�? 
    private Godown godown;            //仓库 

    Producer(int neednum, Godown godown) { 
        this.neednum = neednum; 
        this.godown = godown; 
    } 

    public void run() { 
        //生产指定数量的产�? 
        godown.produce(neednum); 
    } 
} 

/** 
* 消费�? 
*/ 
class Consumer extends Thread { 
    private int neednum;              //生产产品的数�? 
    private Godown godown;            //仓库 

    Consumer(int neednum, Godown godown) { 
        this.neednum = neednum; 
        this.godown = godown; 
    } 

    public void run() { 
        //消费指定数量的产�? 
        godown.consume(neednum); 
    } 
}
