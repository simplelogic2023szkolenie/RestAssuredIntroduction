package users;

import base.TestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

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

}
