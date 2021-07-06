// Get과 Post를 처리할 수 있는 기본구조를 설정한 소스 

package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.*;

public class MyServlet extends HttpServlet {

	// Get으로는 시간을 성공코드와 시간을 리턴
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("Request : "+ req.getRequestURL());
		
		res.setStatus(200);
		res.getWriter().write(new Date().toString());
	}
	
	// Post로는 서버의 ./OUTPUT 폴더가 없으면 만들고 현재시간으로 수신파일 복사저장
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("Request : "+ req.getRequestURL());
		////////////////////////////////////////////////
		File destFolder = new File("./OUTPUT");
		if(!destFolder.exists()) {
		    destFolder.mkdirs(); 
		}
		
		LocalTime currentTime = LocalTime.now();
		String fname = String.format("./OUTPUT/%02d%02d%02d.json", currentTime.getHour(), currentTime.getMinute(), currentTime.getSecond());
	    PrintWriter printWriter = new PrintWriter(new FileWriter(new File(fname)));
	    
        BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String buffer;
        while ((buffer = input.readLine()) != null) {
        	printWriter.print(buffer);
        }        
		input.close();		
		printWriter.close();
		/////////////////////////////////////////////////
		// 성공코드와 파일명 저장되었다고 전송
		res.setStatus(200);
		res.getWriter().write(fname + " saved!");
	}
}


/*원래 소스 시간전송 약간 수정본
package test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.*;


public class MyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	
    SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
    Date time = new Date();
    String time1 = format1.format(time);     
    // 클라이언트에 시간 전송
     
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setStatus(200);
		res.getWriter().write("Hello!");
		res.getWriter().write(time1);
	}
}
*/
