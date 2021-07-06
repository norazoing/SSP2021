/*
INPUT 폴더 내의 파일 목록과 크기를 출력하시오. 
크기가 2Kbyte가 넘는 파일들은 모두 OUTPUT 폴더로 복사하시오. OUTPUT 폴
더가 존재하지 않을 경우 생성하시오. 
단, 파일 복사 시 바이너리 파일을 버퍼에 읽고 쓰는 방식으로 구현하고, 버퍼
의 크기는 512Byte로 설정하시오.
*/

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class PracFile
{
	public static void main(String[] args) {
        File directory = new File("./INPUT");
        //get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList) {
        	System.out.println(file.getName()+":	"+file.length()+"bytes");
        	if (file.length() > 2048)
        	{
        		MyCopyFile(file.getName());
        	}
        }		
	}
	
	static void MyCopyFile(String filename)
	{
		final int BUFFER_SIZE = 512;
        int readLen;
        try {
    		// Create Folder
    		File destFolder = new File("./OUTPUT");
    		if(!destFolder.exists()) {
    			destFolder.mkdirs(); 
    		}        	
        	
    		// Copy File
            InputStream inputStream = new FileInputStream("./INPUT/"+filename);
            OutputStream outputStream = new FileOutputStream("./OUTPUT/"+filename);
 
            byte[] buffer = new byte[BUFFER_SIZE];
 
            while ((readLen = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, readLen);
            }
            
            inputStream.close();
            outputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }		
	}
}
