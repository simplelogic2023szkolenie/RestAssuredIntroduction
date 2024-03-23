package users;

import base.TestBase;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class GetUsers extends TestBase {

    @Test
    public void shouldGetAllUsers() {
        given().
                baseUri(baseUrl).
        when().
                get(users).
        then().
                statusCode(200);
    }
}
