import io.restassured.RestAssured.*;
import static io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import io.restassured.path.json.JsonPath;

import org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

@Test
public class CustomerInfo {
	public static void getResponseBody(){
		  given().when().get("http://demo.guru99.com/V4/sinkministatement.php?CUSTOMER_ID=68195&PASSWORD=1234!&Account_No=1").then().log()
		  .all();
		    
		 
		}
	
	@Test
	public static void getNewResponseBody(){
		 
		   given().queryParam("CUSTOMER_ID","68195")
		           .queryParam("PASSWORD","1234!")
		           .queryParam("Account_No","1")
		           .when().get("http://demo.guru99.com/V4/sinkministatement.php").then().log()
		           .body();
		}

}
