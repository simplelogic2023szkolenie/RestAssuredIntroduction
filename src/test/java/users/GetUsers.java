package users;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;

public class GetUsers {

    @Test
    public void shouldGetAllUsers() {
        when().
                get("https://jsonplaceholder.typicode.com/users").
        then().
                statusCode(200);
    }
}
