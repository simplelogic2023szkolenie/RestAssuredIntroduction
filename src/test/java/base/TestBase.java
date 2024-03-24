package base;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.recursive.comparison.RecursiveComparisonConfiguration;
import org.junit.jupiter.api.BeforeEach;

public class TestBase {

    public String baseUrl = "https://jsonplaceholder.typicode.com";
    public String posts = "/posts";
    public String users = "/users";
    @BeforeEach()
    public void setup(){
        // ta opcja włacza logi reponse i request tylko jezeli test skończył sie failem
//        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        // to poniżej włacza logi zawsze, czy był fail czy nie było
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    public void compareObjectWithoutId(Object actual, Object expected){
        RecursiveComparisonConfiguration config = new RecursiveComparisonConfiguration();

        config.ignoreFields("id");

        Assertions.assertThat(actual)
                .usingRecursiveComparison(config)
                .isEqualTo(expected);
    }
}
