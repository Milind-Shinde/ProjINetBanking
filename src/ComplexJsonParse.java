import files.payload;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
			
		JsonPath js = new JsonPath(payload.CoursePrice());
	//test 1 : print no of courses returned by API
		int count= js.getInt("courses.size()"); 	//size method can be used only with array
		System.out.println(count);
		
	//test 2: print purchase amount
	
	  int purchaseAmount = js.getInt("dashboard.purchaseAmount");
	  System.out.println(purchaseAmount);
	 
		
	//test 3 : print title of the third course
	
	  String CourseNm = js.getString("courses[2].title");
	  System.out.println(CourseNm); 
	  System.out.println();
	 
		
	//test 4: Print all course titles and their respective prices
		//Here we will iterate through json using for loop
	
	  for (int i=0; i<count; i++) { 
	  //String CourseName = js.getString("courses["+i+"].title");
	  System.out.println(js.getString("courses["+i+"].title")); //we can directly print output here using toString() 
	  int Price = js.get("courses["+i+"].price"); 
	  System.out.println(Price);
	  System.out.println(); 
	  }
	 
		
	//Test 5: Print no of copies sold by RPA course
	
	  System.out.println("Print no of copies sold by Cypress course"); 
	  
	  for (int i=0;	  i<count; i++)
	  { 
	  String CourseName = js.getString("courses["+i+"].title");
	  if(CourseName.equalsIgnoreCase("Cypress")) 
	  { 
	  int NoOfCopies = js.getInt("courses["+i+"].copies"); 
	  System.out.println(NoOfCopies); //once we found our RPA through loop, then loop should exit; so that use break 
	  break; 
	  }
	  }
	  
	 
		
	//Test 6 : Verify if sum of all course prices matches with purchase amount.
	/*
	 * System.out.
	 * println("Verify if sum of all course prices matches with purchase amount.");
	 * SumValidation sm = new SumValidation(); sm.SumOfCources();
	 */
		
		} 

}
