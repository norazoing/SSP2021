
/*
 sample.json 파일을 읽은 후, 첫 번째 level의 Value Type을 알아내어 다음과 같이 출력하시오.
Key : name / Value Type : String
Key : age / Value Type : Number
Key : married / Value Type : Boolean
Key : specialty / Value Type : Array
Key : vaccine / Value Type : Object
Key : children / Value Type : Array
Key : address / Value Type : null  
 */

package test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class MyJson3 {

	public static void main(String[] args) {
		String filePath = "sample.json";
		Path jsonFilePath = Paths.get(filePath);
		try {
			String wholeData = new String(Files.readAllBytes(jsonFilePath));  // 좀 다른방법으로 파일읽음
			// Gson객체를 만들고 Json파일로부터 읽어서 Jsonobject에 넣는다.
			Gson gson = new Gson();
			JsonObject jsonObj = gson.fromJson(wholeData, JsonObject.class);
			

            // keyset으로 모든 요소를 돌면서 타입을 출력
			for (String key : jsonObj.keySet()) {
				System.out.print("Key : "+key+" / Value Type : ");
				// Jsonobject내의 하나하나는 JsonElement임.
				JsonElement je = jsonObj.get(key);
				if (je.isJsonPrimitive()) {
					if (je.getAsJsonPrimitive().isString()) {
						System.out.println("String"); 
					}
					else if (je.getAsJsonPrimitive().isNumber())
					{
						System.out.println("Number");
					}
					else if (je.getAsJsonPrimitive().isBoolean()) {
						System.out.println("Boolean");
					}
					else if (je.getAsJsonPrimitive().isJsonNull()) {
						System.out.println("null");
					}
				}
				else if (je.isJsonArray()) {
					System.out.println("Array"); // 재귀함수로 Array내 모든 tree구조를 출력가능.
				}
				else if (je.isJsonObject()) {
					System.out.println("Object");					
				}
				else if (je.isJsonNull()) {
					System.out.println("null");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

/*
package test;

import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

public class MyJson {

	public static void main(String[] args) {
		String filePath = "sample.json";
		try {
			//Gson객체 생성후 현재 폴더의 sample.json파일로 부터 읽어서 JsonObject 생성
			Gson gson = new Gson();
			JsonReader reader = new JsonReader(new FileReader(filePath)); 
			JsonObject jsonObj = gson.fromJson(reader, JsonObject.class);

			String name = jsonObj.get("name").getAsString(); // 타입을 아는 경우
			int age = jsonObj.get("age").getAsInt();               // 타입을 아는 경우만 사용
			System.out.println("name:"+name + "("+age+")");
			
			// Json Array선언해서 get(1)은 두번째 배열에 있는 name과 age를 가져와서 출력
			JsonArray jsonArr = jsonObj.get("children").getAsJsonArray();
			JsonObject jsonObj2 = jsonArr.get(1).getAsJsonObject();
			name = jsonObj2.get("name").getAsString();
			age = jsonObj2.get("age").getAsInt();
			System.out.println("name:"+name + "("+age+")");
					
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
*/
/*{"name":"spiderman",
 * "age":45,"married":true,
 * "specialty":["martial art","gun"],
 * "vaccine":{"1st":"done","2nd":"expected","3rd":null},
 * "children":[{"name":"spiderboy","age":10},{"name":"spidergirl","age":8}],
 * "address":null}
 */



/*
package test;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class MyJson {

	public static void main(String[] args) {
		//JsonElement jsonElement = JsonParser.parseString("{ \"key\": \"spiderman\" }");
     	//System.out.println(jsonElement.toString());
     	//String arr[] = {"martial art", "gun"};
     	

		JsonObject jsonObj = new JsonObject();
		jsonObj.addProperty("name", "spiderman");
		jsonObj.addProperty("age", 45);
		jsonObj.addProperty("married", true);
		
		JsonArray jsonArr = new JsonArray();
		jsonArr.add("martial art");
		jsonArr.add("gun");
		jsonObj.add("specialty", jsonArr);
		
		JsonObject jsonObj2 = new JsonObject();
		jsonObj2.addProperty("1st", "done");
		jsonObj2.addProperty("2nd", "expected");
		jsonObj2.add("3rd", null);		
		jsonObj.add("vaccine", jsonObj2);
	
		JsonArray jsonArr2 = new JsonArray();
		jsonObj2 = new JsonObject();
		jsonObj2.addProperty("name", "spiderboy");
		jsonObj2.addProperty("age", 10);
		jsonArr2.add(jsonObj2);
	
		jsonObj2 = new JsonObject();
		jsonObj2.addProperty("name", "spidergirl");
		jsonObj2.addProperty("age", 8);
		jsonArr2.add(jsonObj2);

		jsonObj.add("children", jsonArr2);
		jsonObj.add("address", null);
		
		try (Writer writer = new FileWriter("sample.json")) {
		    Gson gson = new GsonBuilder().serializeNulls().create();
		    gson.toJson(jsonObj, writer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
*/

/*

{
"name":"spiderman",
"age":45,
"married":true,
"specialty":["martial art", "gun"],
"vaccine":{"1st":"done","2nd":"expected","3rd":null},
"children": [{"name":"spiderboy", "age":10}, {"name":"spidergirl", "age":8}],
"adress":null
}
*/
