package users;

import base.TestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostUser extends TestBase {

    private String body = """
            {
                    "name": "Mateusz Tadla",
                    "username": "mtadla",
                    "email": "mtadl@april.biz",
                    "address": {
                        "street": "Kulas Light",
                        "suite": "Apt. 556",
                        "city": "Lublin",
                        "zipcode": "92998-3874",
                        "geo": {
                            "lat": "-37.3159",
                            "lng": "81.1496"
                        }
                    },
                    "phone": "1-770-736-8031 x56442",
                    "website": "hildegard.org",
                    "company": {
                        "name": "Romaguera-Crona",
                        "catchPhrase": "Multi-layered client-server neural-net",
                        "bs": "harness real-time e-markets"
                    }
            }
            """;


    @Test
    public void shouldCreateNewUser() {
        given().
                        body(body).
                        contentType(ContentType.JSON).
                        baseUri(baseUrl).
                when().
                        post(users).
                then().
                        statusCode(201)
                        .body("id", equalTo(11))
                        .body("address.city", equalTo("Lublin"));
    }

    @Test
    public void shouldCreateNewUserV2() {
        Map<String, Object> address = new HashMap<>();
        address.put("street", "Warszawska");
        address.put("city", "Lublin");


        Map<String, Object> user = new HashMap<>();
        user.put("name", "Mateusz Tadla");
        user.put("username", "mtadla");
        user.put("email", "mtadl@april.biz");
        user.put("address", address);


        given().
                body(user).
                contentType(ContentType.JSON).
                baseUri(baseUrl).
        when().
                post(users).
        then().
                statusCode(201).
                body("id", equalTo(11)).
                body("username", equalTo("mtadla")).
                body("address.city", equalTo("Lublin"));
    }

}
