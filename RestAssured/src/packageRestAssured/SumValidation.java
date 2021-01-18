package packageRestAssured;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.payload;
import io.restassured.path.json.JsonPath;

public class SumValidation {
	//Verify if Sum of all Course prices matches with Purchase Amount
	@Test
	public void sumOfCourses()
	{
		JsonPath js=new JsonPath(payload.CourseID());
		int count=js.get("courses.size()");
		int total_price=0;
		
		for(int i=0; i<count;i++)
		{
			String course_title=js.get("courses["+i+"].title");
			int course_price=js.get("courses["+i+"].price");
			int course_copies=js.getInt("courses["+i+"].copies");
			int amount= course_price*course_copies;
			System.out.println("Amount of : " +course_title+" is : "+amount);
			
			total_price=total_price+amount;
		}
		
		System.out.println("Total course price is : "+total_price);
		int purchase_amount=js.get("dashboard.purchaseAmount");
		Assert.assertEquals(total_price, purchase_amount);
		
	}

}
