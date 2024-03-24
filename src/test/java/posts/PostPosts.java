package posts;

import base.TestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostPosts extends TestBase {

    // zrób zapytanie tworzące post, i bodu uzupełnij poprzed Mape



    private String body = """
            {
                 "userId": 1,
                 "title": "customtitle",
                 "body": "quia etoitecto"
               }
            """;

    @Test
    public void shouldCreateNewPost() {
        given().
                        body(body).
                        contentType(ContentType.JSON).
                        baseUri(baseUrl).
                when().
                        post(posts).
                then().
                        statusCode(201)
                        .body("id", equalTo(101))
                        .body("title", equalTo("customtitle"));
    }


    @Test
    public void shouldCreateNewPost2() {
        Map<String, Object> post = new HashMap<>();
        post.put("userId", 12);
        post.put("title", "customtitle");
        post.put("body", "quia etoitecto");

        given().
                        body(post).
                        contentType(ContentType.JSON).
                        baseUri(baseUrl).
                when().
                        post(posts).
                then().
                        statusCode(201)
                        .body("id", equalTo(101))
                        .body("title", equalTo("customtitle"));
    }

    // stworzenie obiektu klasy Post
    // przekazanianie go w body do zapytania tworzącego nowy post

    // przy pomocy .extract().as(Post.class)
    // pobranie obiektu z reponspona
    // porównanie tych dwóch obiektów


}
