package posts;

import base.TestBase;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

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


    @Test
    public void getPostsWithIdV1() {
        given().
                baseUri(baseUrl).
                queryParam("userId", 1).
        when().
                get(posts).
        then().
                statusCode(200).
                time(lessThan(1200L));
    }

    @Test
    public void getPostsWithIdV2() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("userId", "1");

        given().
                        baseUri(baseUrl).
                        queryParams(queryParams).
                when().
                        get(posts).
                then().
                        statusCode(200).
                        time(lessThan(1200L))
                .body("[0].userId", equalTo(1))
                        .body("", hasSize(10));
    }

    @Test
    public void getPostsWithIdV3() {
        given().
                        baseUri(baseUrl).
                        queryParam("userId", "1").
                        queryParam("id", "2").
                when().
                        get(posts).
                then().
                        statusCode(200).
                        time(lessThan(1200L));
    }

    @Test
    public void getPostsWithIdV4() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("userId", "1");
        queryParams.put("id", "2");

        given().
                baseUri(baseUrl).
                queryParams(queryParams).
        when().
                get(posts).
        then().
                statusCode(200).
                time(lessThan(1200L)).
                body("[0].userId", equalTo(1)).
                body("", hasSize(2));
    }


}
