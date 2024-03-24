package users;

import base.TestBase;
import io.restassured.http.ContentType;
import models.user.Address;
import models.user.User;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PutPatchUser extends TestBase {

    @Test
    public void shouldPutUser() {
        Map<String, Object> user = new HashMap<>();
        user.put("name", "Mateusz Tadla");
        user.put("username", "mtadla");
        user.put("email", "mtadl@april.biz");

        given().
                        body(user).
                        contentType(ContentType.JSON).
                        pathParams("id", 1).
                        baseUri(baseUrl).
                when().
                        put(users+"/{id}").
                then().
                        statusCode(200);
    }

    @Test
    public void shouldPatchUser() {
        Map<String, Object> user = new HashMap<>();
        user.put("name", "Mateusz Tadla");
        user.put("username", "mtadla");
        user.put("email", "mtadl@april.biz");

        given().
                        body(user).
                        contentType(ContentType.JSON).
                        pathParams("id", 1).
                        baseUri(baseUrl).
                when().
                        patch(users+"/{id}").
                then().
                        statusCode(200);
    }
}
