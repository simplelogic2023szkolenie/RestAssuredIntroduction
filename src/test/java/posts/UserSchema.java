package posts;

import base.TestBase;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class UserSchema extends TestBase {
    @Test
    public void validateGetUserSchema() {
        given().
                baseUri(baseUrl).
                pathParams("userId", 1).
        when().
                get(users + "/{userId}").
        then().
                statusCode(200).
                time(lessThan(1200L)).
                body(JsonSchemaValidator.matchesJsonSchema(new File("src/main/resources/user_schema.json")));
    }
}
