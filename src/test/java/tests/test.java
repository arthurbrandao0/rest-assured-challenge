package tests;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;


class PostTest {

    @BeforeAll
    static void urlInfo() {
        baseURI = "https://reqres.in";
        basePath = "/api/users";
    }

    @Test
    void successTest() {
        String payload = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";

        given().
            contentType("application/json").
            body(payload).
        when().
            post().
        then().
            statusCode(201).
            body("name", equalTo("morpheus")).
            body("job", equalTo("leader")).
            body("id", notNullValue()).
            body("createdAt", notNullValue());
    }

    @Test
    void invalidPayloadTest() {
        String payload = "{ \"a\" }";

        given().
            contentType("application/json").
            body(payload).
        when().
            post().
        then().
            statusCode(400);
    }
}