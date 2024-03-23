package posts;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;

public class GetPosts {

    // test dla pobierania wszystkich postów i srpawdzanie status 200

    @Test
    public void shouldGetAllPosts() {
        when().
                get("https://jsonplaceholder.typicode.com/posts").
        then().
                statusCode(200);
    }
}
