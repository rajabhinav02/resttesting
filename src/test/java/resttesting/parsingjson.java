package resttesting;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class parsingjson {

	
	@Test
public void testparsing() {	

	JsonPath js = new JsonPath(body.getodyresponse());
	
	int count=js.getInt("courses.size()");
	
	System.out.println(count);
	int Sum =0;
	for (int i=0; i<count; i++) {
		int price = js.getInt("courses["+i+"].price");
		int copy = js.getInt("courses["+i+"].copies");
		System.out.println(price*copy);
		Sum = Sum+(price*copy);
		
	}
	
	Assert.assertEquals(js.getInt("dashboard.purchaseAmount"), Sum);
	
	for(int i=0; i<count; i++) {
		String title=js.get("courses["+i+"].title");
		System.out.println(title);
		
		int price = js.get("courses["+i+"].price");
		System.out.println(price);
	}
	
	for (int i=0; i<count; i++) {
		if (js.get("courses["+i+"].title").equals("Cypress")) {
			System.out.println(js.get("courses["+i+"].price"));
			break;
		}
	}
}
}
