 import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.nio.file.Files;
import java.nio.file.Paths;

import files.payload;
 
public class basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub 
		
		  //Validate if Add place API is working as per expected //given, when and then
		  //given- All input details //When- Submit the API there - resource, http
		   //then- validate the response from Server
		
		  
		  RestAssured.baseURI = "https://rahulshettyacademy.com";
		  
		  String response =  
				 given().log().all().queryParam("Key", "qaclick123").header("Content-Type",
		  "application/json") .body(payload.addPlace()).when().post("maps/api/place/add/json")
		  .then().assertThat().statusCode(200).body("scope", equalTo("APP"))		//equalTo method is available in package -- import static org.hamcrest.Matchers.*;
		  .header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
		  
		  //Above code is used to add a place in google maps through API
		  //Table- MAps---If i tell u to add a place? Insert operation
		  
		  
		  
		  
		  
		  
		  
		  System.out.println(response);//----place id
		  		  
		  JsonPath js = new JsonPath(response);
		  String placeId= js.getString("place_id");
		  System.out.println(placeId);
		  
		  //Here by using below code, we can read any Json file from any location and convert it into string format
			/*
			 * String response2 = given().log().all().queryParam("Key",
			 * "qaclick123").header("Content-Type", "application/json").body(new
			 * String(Files.readAllBytes(Paths.get("Location where Json file is stored"))))
			 * .when().post("maps/api/place/get/json")
			 * .then().assertThat().statusCode(200).body("scope", equalTo("APP"))
			 * .header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
			 */
		  

	}

}
