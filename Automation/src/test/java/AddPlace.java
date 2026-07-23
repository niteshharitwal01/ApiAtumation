import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;


public class AddPlace {

	public static void main(String[] args) 
//	@Test
//  void mapp()
	{
		// JSON file is parsed from a class into Body
		System.out.println("************** Test Started for Creating Map  *****************");
		RestAssured.baseURI="https://rahulshettyacademy.com";
		given().relaxedHTTPSValidation().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body(PayloadMapJSON.addMap())
		.when().post("maps/api/place/add/json/").then().log().all().assertThat().statusCode(200);
		
		/*  JSON is parsed in Body
		 RestAssured.baseURI="https://rahulshettyacademy.com";
		given().relaxedHTTPSValidation().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body("{     "location": {         "lat": -38.383494,        "lng": 33.427362     },
    	"accuracy": 50,     "name": "Rahul Shetty Academy",    "phone_number": "(+91) 983 893 3937",    "address": "29, side layout, cohen 09",
    	"types": [         "shoe park",        "shop"     ],     "website": "http://rahulshettyacademy.com",     "language": "French-IN" }")
		.when().post("maps/api/place/add/json/").then().log().all().assertThat().statusCode(200)
		 */
		
		String response=given().relaxedHTTPSValidation().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
				.body(PayloadMapJSON.addMap())
				.when().post("maps/api/place/add/json/").then().log().all().assertThat().statusCode(200).extract().response().asString();
			
		System.out.println(response);
				
		JsonPath js=new JsonPath(response); // Create 
				
		String placeid=js.getString("place_id"); //Get the Place id for Map
		
		System.out.println("***************" +placeid);
		
		System.out.println("************** Test Started for Getting Map  *****************");
		
		//***************** This code is for Get Map *************************************************
		// TODO Auto-generated method stub
		
		//Validation for status code 200
		given().relaxedHTTPSValidation().queryParam("key", "qaclick123").queryParam("place_id", placeid).
		header("Content-Type","application/json").when().get("maps/api/place/get/json/").then().log().all().assertThat().statusCode(200);
		//Validation for response body
		given().relaxedHTTPSValidation().queryParam("key", "qaclick123").queryParam("place_id", placeid).header("Content-Type","application/json").
		when().get("maps/api/place/get/json/").then().log().all().assertThat().statusCode(200).body("address", equalTo("29, side layout, cohen 09"));
		//Validation for Header field
		given().relaxedHTTPSValidation().queryParam("key", "qaclick123").queryParam("place_id", placeid).header("Content-Type","application/json").
		when().get("maps/api/place/get/json/").then().log().all().assertThat().statusCode(200).header("Server", equalTo("Apache/2.4.52 (Ubuntu)"));
		
		//System.out.println("*******************************");
				
		System.out.println("************** Test Started for Updating Map Address  *****************");
		/*
		given().relaxedHTTPSValidation().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body(PayloadMapJSON.updateMap(placeid))
		.when().post("maps/api/place/update/json/").then().log().all().assertThat().statusCode(200);
		*/
		String updateresponse=given().relaxedHTTPSValidation().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
				.body(PayloadMapJSON.updateMap(placeid))
				.when().put("maps/api/place/update/json/").then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println("************************" +updateresponse + "**********************");
		JsonPath js1=new JsonPath(updateresponse); // Create 
		String address=js1.getString("msg"); //Get the Place id for Map
		System.out.println("***************" +address);
		
		System.out.println("************** Test Started for Address comparision  *****************");
		
		//***************** This code is for Get Map *************************************************
		given().relaxedHTTPSValidation().queryParam("key", "qaclick123").queryParam("place_id", placeid).header("Content-Type","application/json").
		when().get("maps/api/place/get/json/").then().log().all().assertThat().statusCode(200).body("address", equalTo("TallantWorth Trl"));
		
		
		System.out.println("************** Test Started for Deleting Map  *****************");
	//	given().relaxedHTTPSValidation().log().all().header("Content-Type","application/json").body(PayloadMapJSON.deleteMap(placeid))
	//	.when().delete("maps/api/place/delete/json/").then().log().all().assertThat().statusCode(200);
		
		given().relaxedHTTPSValidation().log().all().header("Content-Type","application/json").body("{\r\n"
				+ "    \"place_id\": \"" +placeid+"\"\r\n"
				+ "}")
		.when().delete("maps/api/place/delete/json/").then().log().all().assertThat().statusCode(200);
		
		//given().relaxedHTTPSValidation().queryParam("key", "qaclick123").queryParam("place_id", placeid).header("Content-Type","application/json").
		//when().get("maps/api/place/get/json/").then().log().all().assertThat().statusCode(200).body("address", equalTo("TallantWorth Trl"));
		
		given().relaxedHTTPSValidation().queryParam("key", "qaclick123").queryParam("place_id", placeid).header("Content-Type","application/json").
		when().get("maps/api/place/get/json/").then().log().all().assertThat().statusCode(404);
		
		System.out.println("************** Test Ended ******************");	 
	}

}
