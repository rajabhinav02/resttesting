package resttesting;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import junit.framework.Assert;

import static io.restassured.RestAssured.*;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

public class testswagger {

	@Test
	public void testgetstore() {
		
		RestAssured.baseURI= "https://petstore.swagger.io/v2";
		
	String response=when()
			.get("/store/inventory")
			.then()
			.assertThat()
			.statusCode(200)
			.extract().response().asString();
	System.out.println(response);
	
	String rf= when().get("/store/inventory").asString();
	System.out.println("this is rf-- "+rf);
	
	
	JsonPath js = new JsonPath(response);
	System.out.println(js.getString("available"));
	
	}
	
	@Test(priority=2)
	public void postpet() {
		
		RestAssured.baseURI= "https://petstore.swagger.io/v2";
		
		JSONObject jh = new JSONObject();
		jh.put("id","10");
		jh.put("petId","20");
		jh.put("quantity","30");
		jh.put("shipDate","2021-09-15T18:42:29.920Z");
		jh.put("status","placed");
		jh.put("complete","true");
		
		String map=jh.toJSONString();
		
		String resp=given().body("{\r\n"
				+ "  \"id\": 10,\r\n"
				+ "  \"petId\": 20,\r\n"
				+ "  \"quantity\": 30,\r\n"
				+ "  \"shipDate\": \"2021-09-14T18:42:29.920Z\",\r\n"
				+ "  \"status\": \"placed\",\r\n"
				+ "  \"complete\": true\r\n"
				+ "}")
		.when()
		.post("/store/order")
		.then().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println(resp);
		//JsonPath jk = new JsonPath(res);
		
		//Assert.assertEquals(jk.getString("quantity"), "30");
	}
	
}
