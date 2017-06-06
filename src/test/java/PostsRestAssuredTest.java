import org.hamcrest.Matchers;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;

import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.core.IsCollectionContaining.hasItem;

/**
 * Created by Dąbrowscy on 11.02.2017.
 */
public class PostsRestAssuredTest {

    String PostsUrl = "http://jsonplaceholder.typicode.com/posts";

    @Test
    public void SchemaValidationPosts() {
        given()
                .contentType("application/json")
                .when()
                .get(PostsUrl)
                .then()
                .log()
                .body()
                .body(matchesJsonSchemaInClasspath("Schema/PostsSchema.json")).log();
    }

    @Test
    public void PostsCheckWithoutParam() {
        given()
                .contentType("application/json")
                .when()
                .get(PostsUrl)
                .then()
                .statusCode(200)
                .log()
                .body();
    }

    @Test
    public void PostsCheckWithIncorrectParam() {
        given()
                .contentType("application/json")
                .queryParam("id", "wrongParam")
                .when()
                .get(PostsUrl)
                .then().statusCode(200)
                .log()
                .body();
    }

    @Test
    public void PostsCheckId1Param() {
        given()
                .contentType("application/json")
                .queryParam("id", "1")
                .when()
                .get(PostsUrl)
                .then()
                .statusCode(200)
                .log()
                .body()
                .body("userId", hasItem(1),
                        "title", hasItem("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"),
                        "body", Matchers.hasItem(PostsBodyParam.Id1Body));
    }

    @Test
    public void PostsCheckUserId1Param() {
        given()
                .contentType("application/json")
                .queryParam("userId", "1")
                .get(PostsUrl)
                .then()
                .statusCode(200)
                .log()
                .body()
                .body("id", hasItems(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
                        "title", hasItems("sunt aut facere repellat provident occaecati excepturi optio reprehenderit", "qui est esse", "ea molestias quasi exercitationem repellat qui ipsa sit aut", "eum et est occaecati", "nesciunt quas odio", "dolorem eum magni eos aperiam quia", "magnam facilis autem", "dolorem dolore est ipsam", "nesciunt iure omnis dolorem tempora et accusantium", "optio molestias id quia eum"),
                        "body", Matchers.hasItems(PostsBodyParam.Id1Body,
                                PostsBodyParam.Id2Body, PostsBodyParam.Id3Body,
                                PostsBodyParam.Id4Body, PostsBodyParam.Id5Body,
                                PostsBodyParam.Id6Body, PostsBodyParam.Id7Body,
                                PostsBodyParam.Id8Body, PostsBodyParam.Id9Body,
                                PostsBodyParam.Id10Body));
    }

    @Test
    public void PostsCheckTitleParam() {
        given()
                .contentType("application/json")
                .queryParam("title", "qui qui voluptates illo iste minima")
                .get(PostsUrl)
                .then()
                .statusCode(200)
                .log()
                .body()
                .body("userId", hasItem(10),
                        "id", hasItem(94),
                        "body", Matchers.hasItems(PostsBodyParam.Id94Body));
    }

    @Test
    public void PostsCheckBodyParam() {
        given()
                .contentType("application/json")
                .queryParam("body", PostsBodyParam.Id64Body)
                .get(PostsUrl)
                .then()
                .statusCode(200)
                .log()
                .body()
                .body("userId", hasItem(7),
                        "id", hasItem(64),
                        "title", hasItem("et fugit quas eum in in aperiam quod"));
    }

}
