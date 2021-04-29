package files;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
//import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
//import static org.hamcrest.Matchers.*;

public class DynamicJson2 {

	
	
	  @Test(dataProvider = "BooksData") //Here dataProvider is available after
	  //installing TestNG. After that import 2 package --->1)import
	  //org.testng.annotations.DataProvider; //2)import
	  //org.testng.annotations.Test; 
	  public void addBook(String isbn, String aisle) {
	  RestAssured.baseURI = "http://216.10.245.166"; 
	  String response =
	  given().log().all().header("Content-Type", "application/json")
	  .body(payload.Addbook(isbn, aisle)).when().post("/Library/Addbook.php")
	  .then().log().all().assertThat().statusCode(200).extract().response().
	  asString();
	  
	  JsonPath js = new JsonPath(response); 
	  int id = js.getInt("ID");
	  //String id = js.get("ID");
	  System.out.println(id);
	  //in response i will id, name, infomation..... you will get as json respose from API
	  }
	  
	  
	  
	  
	  
	  @DataProvider(name="BooksData") public Object[][] getData() { return new
	  Object[][] {{"sdfr", "456"}, {"sdfg","755"}, {"wergt", "321"}}; }
	 
	  //By using
	  //above Dataprovider, I have developed an utility called Data provider and
	  //given 3 books details
	  
	  //Let us create test case to delete book
	 
	@Test
	public void deleteBook() {
		RestAssured.baseURI = "http://216.10.245.166";
		 given().log().all().queryParam("ID", "321wergt")
				.header("Content-Type", "application/json").when()
				.delete("/Library/DeleteBook.php").then().log().all()
				.assertThat().statusCode(200);

	
	  /*JsonPath js = new JsonPath(response); 
	  String id = js.get("ID");
	  System.out.println(id);*/
	  
	  }
	 

}
