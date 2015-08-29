package com.chb.exception;

/**
 * @author chb
 * 1. 使用 throws 声明的方法表示此方法不处理异常，而交给方法的调用处进行处理
 * 2. 不要再主方法中使用 throws，这样的话程序出现问题会交给JVM来处理，将导致程序中断
 *
 */

class Math{
	public int div(int i,int j) throws Exception{
		System.out.println("****计算开始****");
		
		int tmp = 0;
		try{
			tmp = i/j;
		}catch(Exception e){
			throw e;  //交给方法调用处处理
		}finally{
			System.out.println("****计算结束****");
		}
		return tmp;		
	}
}

public class ExceptionDemo {
	public static void main(String[] args){
		Math m = new Math();  //实例化Math对象
		try{
			System.out.println("除法操作: " + m.div(10, 0));
		}catch(Exception e){
			System.out.println("异常产生: " + e);
		}
	}

}
