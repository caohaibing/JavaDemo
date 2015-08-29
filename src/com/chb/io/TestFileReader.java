/**
 * 读一个字符就存入字符数组里，读完1Kb再打�?
 */
package com.chb.io;

import java.io.FileReader;
import java.io.IOException;

public class TestFileReader {

    public static void main(String[] args){ 
       FileReader fr=null; 
       try{ 
	          fr=new FileReader("D:/JAVA练习代码/abc.txt"); 
	          char[]buf = new char[1024]; //该长度�?�常都是1024的整数�??
	          int len=0; 
	          while((len=fr.read(buf))!=-1){
	             System.out.println(new String(buf,0,len));
	          }
       	}catch(IOException e){
       			System.out.println(e);        
       	}finally{
    	   if(fr!=null){
    		   try{                     
    			   fr.close();
    		   }catch(IOException e){
    			   System.out.println("close"+e);
    		   }
    	   }
        }
    }
}
