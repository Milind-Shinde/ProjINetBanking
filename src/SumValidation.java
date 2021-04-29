

import org.junit.jupiter.api.Test;
import org.testng.Assert;

import files.payload;
import io.restassured.path.json.JsonPath;

public class SumValidation {

	@Test
	public void SumOfCources()
	{
		JsonPath js = new JsonPath(payload.CoursePrice());
		int count= js.getInt("courses.size()"); 
		int sum = 0;
		for(int i=0; i<count; i++)
		{
			
			int priceCount = js.getInt("courses["+i+"].price");
					
			int copiesCount = js.getInt("courses["+i+"].copies");
			int result = priceCount * copiesCount;
		    sum = sum + result;
						
		}
		System.out.println(sum);
		int purchaseprice = js.getInt("dashboard.purchaseAmount");
		Assert.assertEquals(sum, purchaseprice);
		
		/*
		 * if(sum == purchaseprice) {
		 * System.out.println("Sum of all course price matches with purchase amount"); }
		 * else { System.out.
		 * println("Sum of all course price DO NOT matches with purchase amount"); }
		 */
	}
}
