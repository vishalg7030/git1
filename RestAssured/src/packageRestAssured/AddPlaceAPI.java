package packageRestAssured;

import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.payload;
import static io.restassured.RestAssured.*;
import files.payload;

public class AddPlaceAPI {

	public static void main(String[] args) {
		// add BaseURI
		// given(), when() and then() method.
		
		RestAssured.baseURI= "https://rahulshettyacademy.com/";
		String response= given().log().all().queryParam("key", "qaclick1235").contentType("application/json")
		.body(payload.AddPlace()).when().post("/maps/api/place/add/json")			
		        .then().assertThat().statusCode(200).body("scope", equalTo("APP"))
		        .header("Server", "Apache/2.4.18 (Ubuntu)").extract().asPrettyString();
		
		System.out.println(response);
		
		JsonPath js= new JsonPath(response);
		String place_id=js.getString("place_id");
		System.out.println(place_id);
		
		//store the new address in a string
		String new_address="70 Summer walk, USA";
		
		//update address
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n" + 
				"\"place_id\":\""+place_id+"\",\r\n" + 
				"\"address\":\""+new_address+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}\r\n" + 
				"")
		.when().put("maps/api/place/update/json").then().log().all().assertThat().statusCode(200)
		.body("msg", equalTo("Address successfully updated")); // use org.hamcrest.Matchers.* static package for equalTo method.
		
		// get place
		String getPlaceResponse=given().queryParam("key","qaclick123").queryParam("place_id", place_id)
		.when().get("/maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().asPrettyString();
		
		JsonPath js1=new JsonPath(getPlaceResponse);
		String actualAddress=js1.getString("address");
		String actualLattitude=js1.getString("location.latitude");
		String actualLongitude=js1.getString("location.longitude");
		
		System.out.println("\n"+"Actual Address: "+actualAddress);
		System.out.println("Lattitude: "+actualLattitude);
		System.out.println("Longitude: "+actualLongitude);
		
		Assert.assertEquals(new_address,actualAddress);
		
		

	}

	


}
