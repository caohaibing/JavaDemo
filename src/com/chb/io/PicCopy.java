/** 
 * 在Java视图下，Run As Java Application 运行
 */

package com.chb.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PicCopy {
	
	public static void main(String[] args){
		
		try{ 
			
			BufferedInputStream bufis=new BufferedInputStream(new FileInputStream("D:\\Java练习代码\\1.jpg")); 
			//新建一个空的图片文件  2.jpg
			BufferedOutputStream bufos=new BufferedOutputStream(new FileOutputStream("D:\\Java练习代码\\2.jpg"));  
			int by = 0;
			
			while((by=bufis.read())!=-1){ 
			
			   bufos.write(by);
			}  
			bufis.close();  
			bufos.close();
	        
	       }catch(IOException e){
	         e.printStackTrace(); 
	       }	
	}
	  
}
