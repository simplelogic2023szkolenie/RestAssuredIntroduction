package users;

import base.TestBase;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetUsers extends TestBase {

    @Test
    public void shouldGetAllUsers() {
        given().
                        baseUri(baseUrl).
                when().
                        get(users).
                then().
                        statusCode(200)
                        .body("", hasSize(10));
    }

    @Test
    public void getFirstUser() {
        given().
                        baseUri(baseUrl).
                when().
                        get(users + "/1").
                then().
                         statusCode(200).
                         body("id", equalTo(1)).
                         body("username", equalTo("Bret")).
                         body("address.street", equalTo("Kulas Light"));
    }

    @Test
    public void getFirstUserV2() {
        given().
                        baseUri(baseUrl).
                        pathParams("id", 1).
                when().
                        get(users + "/{id}").
                then().
                        statusCode(200).
                        time(lessThan(1200L));
    }

    @Test
    public void getUsersWithName() {
        given().
                        baseUri(baseUrl).
                        queryParam("name", "Leanne Graham").
                        queryParam("username", "Bret").
                when().
                        get(users).
                then().
                        statusCode(200).
                        time(lessThan(1200L));
    }

    @Test
    public void getUsersWithNameV2() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("name", "Leanne Graham");
        queryParams.put("username", "Bret");

        given().
                        baseUri(baseUrl).
                        queryParams(queryParams).
                when().
                        get(users).
                then().
                        statusCode(200).
                        time(lessThan(1200L))
                .body("[0].username", equalTo("Bret"));
    }
}
