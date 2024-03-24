package users;

import base.TestBase;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DeleteUser extends TestBase {

    @Test
    public void deleteFirstUser() {
        given().
                baseUri(baseUrl).
        when().
                delete(users + "/1").
        then().
                 statusCode(200)
                .body(equalTo("{}"));
    }
}
