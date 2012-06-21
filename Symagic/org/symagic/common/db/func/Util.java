package org.symagic.common.db.func;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Util {
	/**
	 * 获取字节数组的MD5加密后的16进制字符串(32bit)
	 * @param source	字节数组(byte[])
	 * @return			String: 32个字符串
	 */
	public static String getMD5(byte[] source)
	{
		String s =	null;
		char hexDigits[] = {'0', '1', '2', '3', '4',
							'5', '6', '7', '8', '9',
							'a', 'b', 'c', 'd', 'e',
							'f'};
		try 
		{
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			md.update(source);
			byte tmp[] = md.digest();
			char str[] = new char[16*2];
			
			int k = 0;
			for (int i=0; i<16; i++) {
				byte byte0 = tmp[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			s = new String(str);
			
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		return s;
	}
	
	public static String readFile(String path)
	{
		File file = null;
		BufferedReader br = null;
		StringBuffer buffer = null;
		  try{
		   file = new File(path);
		   buffer = new StringBuffer();
		   InputStreamReader isr = new InputStreamReader(new FileInputStream(file),"utf-8");
		   br = new BufferedReader(isr); 
		   int s;
		   while((s = br.read())!=-1){
		    buffer.append((char)s);
		   }
		   return buffer.toString();
		  }catch(Exception e){
		   e.printStackTrace();
		  }
		  return null;
	}
}







