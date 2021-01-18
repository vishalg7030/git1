package packageRestAssured;

import java.util.List;

import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexParseJson {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JsonPath js= new JsonPath(payload.CourseID());
		//Print No of courses returned by API
		int courses_size= js.getInt("courses.size()");
		System.out.println("Course size : "+courses_size);
		
		//Print Purchase Amount
		String purchase_amount=js.getString("dashboard.purchaseAmount");
		System.out.println("Purchase amount : "+purchase_amount);

		//Print Title of the first course
		String first_course=js.getString("courses.title[0]");
		System.out.println("Title of the first course : "+first_course);
		
		//Print Title of the second course
		String second_course=js.getString("courses[1].title");
		System.out.println("Title of the second course : "+second_course);
		
		//Print Title of the third course
		String third_course=js.getString("courses[2].title");
		System.out.println("Title of the third course : "+third_course);

		//Print All course titles and their respective Prices
		int count=js.get("courses.size()");
		for(int i=0;i<count;i++)
		{
			 String all_course=js.getString("courses["+i+"].title");
			 
			 System.out.println("All course title : "+all_course);
			 /*
			 int course_price=js.get("courses["+i+"].price");
			 System.out.println("All course price : "+course_price);
			 */
			 // System.out.println accepts only String by default; 
			 //in order to pass the value as String without storing it into a variable
			 System.out.println("All course price : "+js.get("courses["+i+"].price"));
			 
		}
	

		//Print no of copies sold by RPA Course
		int course_size1=js.get("courses.size");
		for(int j=0;j<course_size1;j++)
		{
			String course_title=js.get("courses["+j+"].title");
			if(course_title.equalsIgnoreCase("RPA"))
			{
				//String course_price=js.get("courses["+j+"].price");
				System.out.println("RPA copies is : "+js.get("courses["+j+"].copies"));
				break;
			}
		}

		

	


	}

}
