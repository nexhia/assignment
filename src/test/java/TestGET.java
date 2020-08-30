import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TestGET {

	@Test
	//Check that id's from /users return data in /user
	//Check that /city from user return data in /city
	void user_details_match_users_list_summaries_for_same_id() {

		RestAssured.baseURI = "http://bpdts-test-app-v2.herokuapp.com";

		//get first 10 id's
		Response response = RestAssured.get("/users");
		List <UserSummary> usersList = response.jsonPath().getList("", UserSummary.class);
		
		//the list returned from rest assured is unmodifiable, so have to create a modifiable list from it
		List<UserSummary> unlockedUsersList = new ArrayList<UserSummary>(usersList);
		
		// shuffling the list of users to then take 10 at random
		Collections.shuffle(unlockedUsersList);

		Collection<UserSummary> first10Users = unlockedUsersList.stream().limit(10).collect(Collectors.toCollection(LinkedList::new));

		//get city for first 10 id's 
		for (UserSummary userSummaryFromFirstTen : first10Users) {
			String userId = Integer.toString(userSummaryFromFirstTen.getId());

			System.out.println(String.format("Checking data integrity for user %s %s, id: %s", userSummaryFromFirstTen.getFirst_name(), userSummaryFromFirstTen.getLast_name(), userId));

			Response userDetailsResponse = RestAssured.get(String.format("/user/%s", userId)); 

			UserDetails userDetails = userDetailsResponse.jsonPath().getObject("", UserDetails.class);

			assertEquals(userSummaryFromFirstTen.getFirst_name(), userDetails.getFirst_name());
			assertEquals(userSummaryFromFirstTen.getLast_name(), userDetails.getLast_name());
			assertEquals(userSummaryFromFirstTen.getEmail(), userDetails.getEmail());
			assertEquals(userSummaryFromFirstTen.getIp_address(), userDetails.getIp_address());
			assertEquals(userSummaryFromFirstTen.getLatitude(), userDetails.getLatitude());
			assertEquals(userSummaryFromFirstTen.getLongitude(), userDetails.getLongitude());

			System.out.println("User details match.");
			System.out.println("Checking user is found in city endpoint.");

			Response usersInCityResponse = RestAssured.get(String.format("/city/%s/users", userDetails.getCity()));
			List <UserSummary> usersInCity = usersInCityResponse.jsonPath().getList("", UserSummary.class);

			assertTrue(usersInCity.stream().anyMatch(userSummaryInCity -> userSummaryFromFirstTen.equals(userSummaryInCity)));
			
			System.out.println("Found matching user in users from city list");
		}
	}
}
