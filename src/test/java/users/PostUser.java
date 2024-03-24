package users;

import base.TestBase;
import io.restassured.http.ContentType;
import models.user.Address;
import models.user.User;
import org.junit.jupiter.api.Test;
import provier.UserProvider;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
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
        //https://github.com/rest-assured/rest-assured/wiki/Usage#content-type-based-serialization
        //  body(user) -> zamiana mapy na json (serializacja) działa dlatego, że dodaliśmy bilioteke jackson
        // do pom.xml -> wyjaśnienie w linku wyżej
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


    @Test
    public void shouldCreateNewUserV3() {
        //https://github.com/rest-assured/rest-assured/wiki/Usage#content-type-based-serialization
        //  body(user) -> zamiana obiektu klasy User na json (serializacja) działa dlatego, że dodaliśmy bilioteke jackson
        // do pom.xml -> wyjaśnienie w linku wyżej
        Address address = Address.builder()
                .city("Lublin")
                .street("Warszawska")
                .build();

        User user = User.builder()
                .name("Mateusz Tadla")
                .email("mtadl@april.biz")
                .username("mtadla")
                .address(address)
                .build();


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

    @Test
    public void shouldCreateNewUserV5() {
        given().
                        body(UserProvider.getFullUserData()).
                        contentType(ContentType.JSON).
                        baseUri(baseUrl).
                when().
                        post(users).
                then().
                        statusCode(201);
    }

    @Test
    public void shouldCreateNewUserV6() {
        User expectedUser = UserProvider.getFullUserData();

        User reponseUser =
                given().
                                body(expectedUser).
                                contentType(ContentType.JSON).
                                baseUri(baseUrl).
                        when().
                                post(users).
                        then().
                                statusCode(201)
                                .extract()
                                .as(User.class);

        expectedUser.setId(reponseUser.getId());
        assertThat(reponseUser, equalTo(expectedUser));
    }


    @Test
    public void shouldCreateNewUserV7() {
        User expectedUser = UserProvider.getFullUserData();

        User reponseUser =
                given().
                                body(expectedUser).
                                contentType(ContentType.JSON).
                                baseUri(baseUrl).
                        when().
                                post(users).
                        then().
                                statusCode(201)
                                .extract()
                                .as(User.class);

        reponseUser.getAddress().getGeo().setLat("123123123312123");

        reponseUser.setName("qweqewqe");

        reponseUser.getCompany().setName("qwe");
        compareObjectWithoutId(reponseUser, expectedUser);
    }
}
