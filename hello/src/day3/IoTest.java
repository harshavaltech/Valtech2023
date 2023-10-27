package day3;

import day3.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class IoTest {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
//		writeToFile();
//        readFromFile();
		rotateandwrite();
		rotateandRead();
}

private static void rotateandRead() throws IOException{
	
	Reader in = new Rot13Reader(new FileReader(new File("hello.txt")));
	char[]buffer=new char[1024];
	int bytesRead =in.read(buffer);
	in.close();
	System.out.println(new String(buffer,0,bytesRead));
		// TODO Auto-generated method stub
		
	}


	private static void rotateandwrite() throws IOException{
		// TODO Auto-generated method stub
		Writer out = new Rot13Writer(new FileWriter(new File("hello.txt")));
		out.write("hello");
		out.flush();
		out.close();
		//System.out.println("hello");
	}


	private static void readFromFile() throws IOException  {
		// TODO Auto-generated method stub
		FileInputStream is= new FileInputStream(new File ("hello world"));
		byte[] buffer=new byte[1024];
		int bytesRead =is.read(buffer);
		//String bytesRead;
		System.out.println("bytes read = "+bytesRead);
		System.out.println(new String (buffer,0,bytesRead));
	}






	private static void writeToFile() throws IOException{
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				String msg="Helo world";
				FileOutputStream fos=new FileOutputStream(new File("hello world"));
				fos.write(msg.getBytes());
				fos.flush();
				fos.close();
			}
	



	
	}


