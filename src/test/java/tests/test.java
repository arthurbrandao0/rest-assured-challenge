package tests;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.Console;

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
    void emptyBodySuccessTest() {
        String payload = "{}";

        given().
                contentType("application/json").
                body(payload).
                when().
                post().
                then().
                statusCode(201).
                body("id", notNullValue()).
                body("createdAt", notNullValue());
    }

    @Test
    void bigNameSuccessTest() {
        String payload = "{ \"name\": \"morpheusmorpheusmorpheusmorpheusmorpheusmorpheusmorpheusmorpheusmorpheus" +
                "morpheusmorpheusmorpheusmorpheusmorpheusmorpheusmorpheusmorpheusmorpheusmorpheusmorpheusmorpheus" +
                "morpheusmorpheusmorpheusmorpheusmorpheusmorpheusmorpheusmorpheusmorpheusmorpheusmorpheusmorpheus" +
                "morpheusmorpheusmorpheusmorpheusmorpheusmorpheusmorpheusmorpheusmorpheusmorpheusmorpheusmorpheus" +
                "\", \"job\": \"leader\" }";

        given().
                contentType("application/json").
                body(payload).
                when().
                post().
                then().
                statusCode(201).
                body("id", notNullValue()).
                body("createdAt", notNullValue());
    }

    @Test
    void bigJobSuccessTest() {
        String payload = "{ \"name\": \"morpheus\", \"job\": \"leaderleaderleaderleaderleaderleaderleaderleaderleader" +
                "leaderleaderleaderleaderleaderleaderleaderleaderleaderleaderleaderleaderleaderleaderleaderleader" +
                "leaderleaderleaderleaderleaderleaderleaderleaderleaderleaderleaderleaderleaderleaderleaderleader" +
                "leaderleaderleaderleaderleaderleaderleaderleaderleaderleaderleaderleaderleaderleaderleaderleader\" }";

        given().
                contentType("application/json").
                body(payload).
                when().
                post().
                then().
                statusCode(201).
                body("id", notNullValue()).
                body("createdAt", notNullValue());
    }

    @Test
    void nameAsNumberSuccessTest() {
        int name = 1;
        String  job = "leader";

        String payload = "{ \"name\": " + name + ", \"job\": \"" + job + "\" }";
        System.out.println(payload);
        given().
                contentType("application/json").
                body(payload).
                when().
                post().
                then().
                statusCode(201).
                body("name", equalTo(name)).
                body("job", equalTo(job)).
                body("id", notNullValue()).
                body("createdAt", notNullValue());
    }

    @Test
    void jobAsNumberSuccessTest() {
        String name = "Antonio";
        int  job = 2;

        String payload = "{ \"name\": \"" + name + "\", \"job\": " + job + " }";
        System.out.println(payload);
        given().
                contentType("application/json").
                body(payload).
                when().
                post().
                then().
                statusCode(201).
                body("name", equalTo(name)).
                body("job", equalTo(job)).
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