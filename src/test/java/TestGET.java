import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertSame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.mapper.ObjectMapper;
import io.restassured.response.Response;

public class TestGET {

	@Test
	//Assuming that 1000 users should be returned in /users validate response 
	//Check that id's from /users return data in /user
	//Check that /city from user return data in /city
	void user_details_match_users_list_summaries_for_same_id() {

		RestAssured.baseURI = "http://bpdts-test-app-v2.herokuapp.com";

		//get first 10 id's
		Response response = RestAssured.get("/users");
		List <UserSummary> usersList = response.jsonPath().getList("", UserSummary.class);

		Collection<UserSummary> first10Users = usersList.stream().limit(10).collect(Collectors.toCollection(LinkedList::new));

		//get city for first 10 id's 
		for (UserSummary userSummary : first10Users) {
			String userId = Integer.toString(userSummary.getId());

			Response userDetailsResponse = RestAssured.get(String.format("/user/%s", userId)); 
			
			UserDetails userDetails = userDetailsResponse.jsonPath().getObject("", UserDetails.class);

			assertEquals(userSummary.getFirst_name(), userDetails.getFirst_name());
			assertEquals(userSummary.getLast_name(), userDetails.getLast_name());
			assertEquals(userSummary.getEmail(), userDetails.getEmail());
			assertEquals(userSummary.getIp_address(), userDetails.getIp_address());
			assertEquals(userSummary.getLatitude(), userDetails.getLatitude());
			assertEquals(userSummary.getLongitude(), userDetails.getLongitude());
		}

	}
}

