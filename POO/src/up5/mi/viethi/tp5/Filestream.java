package up5.mi.viethi.tp5;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.xml.sax.HandlerBase;

public class Filestream {

	private static void writeTwoBytes(File file, int i, int j) throws IOException {
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(i); fos.write(j);
		fos.close();
	}
	
	private static void printBytesFromFile(File file) throws IOException {
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		
		int value = 0;
		while ( (value = bis.read() ) != -1) 
			System.out.print(value+" ");
		
		bis.close();
		System.out.println();
	}
	
	private static String getStringFromFile(File file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		int value = 0; 
		String res = "";
		while ( (value = br.read() ) != -1) 
			res += (char)value;
		
		br.close();
		return res;
	}
	
	
	private static String getStringFromFile(File file, String encode) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),encode));
        
        int value = 0;
        String res = "";
		while ( (value = br.read() ) != -1)
			res += (char)value;
		
		br.close();
		return res;
	}
	
	public static void main(String[] args) throws IOException {
		File file = new File("exemple.txt");
		System.out.println("File : "+file.getAbsolutePath());
		// question a
		writeTwoBytes(file, 195,169);
		// question b
		printBytesFromFile(file); // affiche 195 169
		// question c
		System.out.println(getStringFromFile(file)); // affiche é ou affiche Ã©
		//question d
		System.out.println(getStringFromFile(file,"UTF-8")); // é
		System.out.println(getStringFromFile(file,"ISO-8859-1")); // Ã©
	}

}
