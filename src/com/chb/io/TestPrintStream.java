package com.chb.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Date;

public class TestPrintStream {
	 
	     public static void main(String[] args){  
	        BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));  
	        String s=null;  
	        try{  
	            OutputStreamWriter fw=new OutputStreamWriter(new FileOutputStream("D:\\JAVA练习代码\\123.txt",true));  
	            PrintWriter pr=new PrintWriter(fw);  
	            while((s=bf.readLine())!=null){  
	                if(s.equalsIgnoreCase("exit"))
	                	break;  
	                pr.println(s);  
	                System.out.println(s);  
	                pr.println("------------------");  
	                  
	            }  
	        pr.println("-------"+new Date()+"-------");  
	        pr.flush();  
	        pr.close();  
	        }catch(IOException e){  
	            e.printStackTrace();  
	        }      
	    }  
	  
}
