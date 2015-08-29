package com.chb.io;

import java.io.FileWriter;
import java.io.IOException;

public class TestFileWriter {   
	
   public static void main(String[] args){   
      FileWriter fw=null;   
      try{   
          fw=new FileWriter("D:\\JAVA练习代码\\123.txt");   //或�?? new FileWriter("D:/JAVA练习代码/123.txt");
          fw.write("abcdec");             
          fw.flush();   
          fw.write("kkkk");  
      }catch(IOException e){  
         System.out.println(e.toString());  
      }finally{  
        if(fw!=null){  
           try{  
             fw.close();  
           }catch (IOException e) {  
             System.out.println("close"+e.toString());  
           }  
       }  
    }  
  }  
}  