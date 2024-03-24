package posts;

import base.TestBase;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetPosts extends TestBase {

    @Test
    public void shouldGetAllPosts() {
        given().
                baseUri(baseUrl).
        when().
                get(posts).
        then().
                statusCode(200).
                body("", hasSize(100)).extract().response();
    }

    @Test
    public void getFirstPost() {
        given().
                baseUri(baseUrl).
        when().
                get(posts + "/1").
        then().
                statusCode(200).
                body("id", equalTo(1)).
                body("title", equalTo("sunt aut facere repellat provident occaecati excepturi optio reprehenderit")).
                body("body", equalTo("quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"));
    }

    @Test
    public void getFirstPostV2() {
        given().
                baseUri(baseUrl).
                pathParams("id", 1).
        when().
                get(posts + "/{id}").
        then().
                statusCode(200).
                time(lessThan(1200L));
    }

    // zrób 4 testy
    // 1- zapytanie po POSTY dla userID =1 z użyciem queryParam + stringi
    // 2- zapytanie po POSTY dla userID =1 oraz id=2 z użyciem queryParam + stringi
    // 3- zapytanie po POSTY dla userID =1 z użyciem queryParams + mapa
    // 4- zapytanie po POSTY dla userID =1 oraz id=2z użyciem queryParams + mapa

}
