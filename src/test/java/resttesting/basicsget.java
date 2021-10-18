package resttesting;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.Test;

public class basicsget {

	@Test
	public void getAddress() {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		String response=given().log().all().queryParam("key", "qaclick123").body(body.getBody())
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP")).extract().response().asString();
		
		JsonPath js = new JsonPath(response);
		String placeid=js.get("place_id");
		
		
		//Get
		
		String getresp=given().queryParam("key", "qaclick123").queryParam("place_id", placeid).when().get("/maps/api/place/get/json").then().extract().response().asString();
		
		JsonPath js1 = new JsonPath(getresp);
		System.out.println(js1.get("address"));
		
		//Put
		
		String newaddress= "Street Number 4, Test";
		
		given().queryParam("key", "qaclick123").queryParam("place_id", placeid)
		.body("{\r\n"
				+ "\"place_id\":\""+placeid+"\",\r\n"
				+ "\"address\":\""+newaddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "").when().put("/maps/api/place/update/json").then().log().all()
		.assertThat().statusCode(200);
		
		
		/*JsonPath js2 = new JsonPath(resp3);
		System.out.println(js2.get("address"));
		Assert.assertEquals(newaddress, js2.get("address"));*/
		
		//Get
		
String getrespagain=given().queryParam("key", "qaclick123").queryParam("place_id", placeid).when().get("/maps/api/place/get/json").then().extract().response().asString();
		
		JsonPath js4 = new JsonPath(getrespagain);
		System.out.println(js4.get("address"));
		
		Assert.assertEquals(newaddress, js4.get("address"));
	}
}
