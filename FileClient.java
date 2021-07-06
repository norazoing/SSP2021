
/*
   ClientFiles 폴더안의 test.txt를 소켓을 이용해 서버로 전송하는 클라이언트  예제
   
*/

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class FileClient {
    public static void main(String[] args) throws IOException {
    	SendToServer("test.txt");
    }
    
	public static void SendToServer(String strFilename) throws UnknownHostException, IOException
	{
    // to-do ip는 설정필요
		Socket s = new Socket("192.168.219.200", 27015);
		java.io.OutputStream out = s.getOutputStream();
    // 4k 씩 전송
		byte[] buffer = new byte[4096];
		int readLen;
		
        //get all the files from a directory
        FileInputStream inputStream = new FileInputStream("./ClientFiles/"+strFilename);
        while((readLen = inputStream.read(buffer)) != -1) {
        	out.write(buffer,0,readLen);
        }   
        inputStream.close();    
		
        s.close();
        
        System.out.println(strFilename+" is sent.");
	}
}
