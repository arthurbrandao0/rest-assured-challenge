package tests;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.Console;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;


class PostTest {

    private static final String BASE_URL = "https://reqres.in";
    private static final String BASE_PATH = "/api/users";

    @BeforeAll
    static void urlInfo() {
        baseURI = BASE_URL;
        basePath = BASE_PATH;
    }

    @Test
    void successTest() {
        String name = "morpheus";
        String  job = "leader";

        String payload = "{ \"name\": \"" + name + "\", \"job\": \"" + job + "\" }";

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
        String name = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut" +
                " labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " +
                "nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit " +
                "esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in " +
                "culpa qui officia deserunt mollit anim id est laborum.";
        String  job = "leader";

        String payload = "{ \"name\": \"" + name + "\", \"job\": \"" + job + "\" }";

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
        String  name = "Arthur";
        String job = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut" +
                " labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " +
                "nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit " +
                "esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in " +
                "culpa qui officia deserunt mollit anim id est laborum.";

        String payload = "{ \"name\": \"" + name + "\", \"job\": \"" + job + "\" }";

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