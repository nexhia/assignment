import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;


public class EndPoints {
	
	@Test
	void statusCheck() {
		
		RequestSpecification spec = new RequestSpecBuilder().setBaseUri("http://bpdts-test-app-v2.herokuapp.com").build();
		
		given().spec(spec).when().get("/users").then().statusCode(200);
		given().spec(spec).when().get("/instructions").then().statusCode(200);
		given().spec(spec).when().get("/city/Armamar/users").then().statusCode(200);
		given().spec(spec).when().get("/user/10").then().statusCode(200);
		given().spec(spec).when().get("/user/null").then().statusCode(404);
		
	}

}
