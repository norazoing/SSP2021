
package test;

import java.io.File;
import java.nio.ByteBuffer;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.client.api.Response;
import org.eclipse.jetty.client.api.Result;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.http.HttpMethod;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


//파일 클라이언트 구현 - FileClient만 MyClient로 클래스명만 변경
public class MyClient {

	public static void main(String[] args) throws Exception  {
		String strFileList = getFileList();
		
		HttpClient httpClient = new HttpClient();
		httpClient.start();
		//127.0.0.1 ip를 localhost로 변경
		Request request = httpClient.newRequest("http://192.168.219.200:8080/fileList").method(HttpMethod.POST);
		request.header(HttpHeader.CONTENT_TYPE, "application/json");
		request.content(new StringContentProvider(strFileList,"utf-8"));
		ContentResponse contentRes = request.send();
		
		System.out.println(contentRes.getContentAsString());		
		httpClient.stop();
	}
	
	private static String getFileList() {
		Gson gson = new Gson();
		JsonObject jo = new JsonObject();
        File directory = new File("./Input");
		jo.addProperty("Folder", "Input");  // { folder : input , files : [ filename1, filename2,...] }
        JsonArray jarr = new JsonArray();
        File[] fList = directory.listFiles();
        for (File file : fList) {
        	jarr.add(file.getName());
        }		
        jo.add("FILES", jarr);
        
        String res = jo.toString();
        return res; 
	}
}


/* Data client - 시간요청

package test;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.http.HttpMethod;

public class DateClient {

	public static void main(String[] args) throws Exception {
		HttpClient httpClient = new HttpClient();
		httpClient.start();
		ContentResponse contentRes = httpClient.newRequest("http://127.0.0.1:8080/requestDate").method(HttpMethod.GET)
				.send();
		System.out.println(contentRes.getContentAsString());
	}
}

*/





/* 원래 기본 소스

package test;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.http.HttpMethod;

public class MyClient {

	public static void main(String[] args) throws Exception {
		HttpClient httpClient = new HttpClient();
		httpClient.start();
		ContentResponse contentRes = httpClient.newRequest("http://localhost:8080/mypath").method(HttpMethod.GET)
				.send();
		System.out.println(contentRes.getContentAsString());
	}
}
*/
