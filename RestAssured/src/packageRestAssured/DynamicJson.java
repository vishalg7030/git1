package packageRestAssured;
import static io.restassured.RestAssured.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJson {
	//-ea -Dtestng.dtd.http=true
	
	//Add books
	@Test (dataProvider="AddBookToLibrary")
	public void DynamicJsonMethod(String isbn, String aisle) 
	{
		
		RestAssured.baseURI="http://216.10.245.166";
		String response=given().log().all().header("content-type","application/json")
		.body(payload.AddBookMethod(isbn,aisle)).when().post("/Library/Addbook.php")
		.then().assertThat().statusCode(200).extract().response().asPrettyString();
		
		JsonPath js=new JsonPath(response);
		String msg=js.get("Msg");
		String id=js.get("ID");
		
		System.out.println("msg is : "+msg);
		System.out.println("ID is : "+id);
		
		
		
		//Delete books
		RestAssured.baseURI="http://216.10.245.166";
	   String delete_response=given().log().all().header("content-type","application/json")
	   .body(payload.DeleteBook("two9872")).when().post("/Library/DeleteBook.php")
	   .then().log().all().assertThat().statusCode(200).extract().response().asPrettyString();
	   
	   JsonPath js1=new JsonPath(delete_response);
	   String delete_msg=js1.get("msg");
	   System.out.println(delete_msg);
	   
	}
	
	@DataProvider(name="AddBookToLibrary")
	public Object[][] getData()
	{
		return new Object[][] {{"one","187"},{"two","9872"},{"three","8783"},{"four","784"},{"five","7675"}};
	}
	
	
	

}
