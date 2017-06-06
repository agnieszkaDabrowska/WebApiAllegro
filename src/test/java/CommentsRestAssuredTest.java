import org.hamcrest.Matchers;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.hasItems;

/**
 * Created by Dąbrowscy on 13.02.2017.
 */
public class CommentsRestAssuredTest {

    String CommentsUrl = "http://jsonplaceholder.typicode.com/posts/1/comments";

    @Test
    public void CommentsRestAssuredTest() {
        given()
                .contentType("application/json")
                .when()
                .get(CommentsUrl)
                .then()
                .statusCode(200)
                .log()
                .body()
                .body(matchesJsonSchemaInClasspath("Schema/CommentsSchema.json"))
                .log();
    }

    @Test
    public void CommentsCheckWithoutParam() {
        given()
                .contentType("application/json")
                .when()
                .get(CommentsUrl)
                .then()
                .statusCode(200)
                .log()
                .body();
    }

    @Test
    public void CommentsCheckWitIncorrectParam() {
        given()
                .contentType("application/json")
                .queryParam("name", "123456")
                .when()
                .get(CommentsUrl)
                .then()
                .statusCode(200)
                .log()
                .body();
    }

    @Test
    public void CommentsCheckPostId() {
        given()
                .contentType("application/json")
                .queryParam("postId", "1")
                .relaxedHTTPSValidation().when().get(CommentsUrl)
                .then()
                .statusCode(200)
                .log()
                .body().
                body("id", hasItems(1, 2, 3, 4, 5));
    }

    @Test
    public void CommentsCheckId() {
        given()
                .contentType("application/json")
                .queryParam("id", "3")
                .when()
                .get(CommentsUrl)
                .then()
                .statusCode(200)
                .log()
                .body()
                .body("postId", hasItems(1),
                        "name", Matchers.hasItem(CommentsParams.Id3Name),
                        "email", Matchers.hasItem(CommentsParams.Id3Email),
                        "body", Matchers.hasItem(CommentsParams.Id3Body));
    }

    @Test
    public void CommentsCheckName() {
        given()
                .contentType("application/json")
                .queryParam("name", CommentsParams.Id4Name)
                .when()
                .get(CommentsUrl)
                .then()
                .statusCode(200)
                .log()
                .body()
                .body("postId", hasItems(1),
                        "id", hasItems(4),
                        "email", Matchers.hasItem(CommentsParams.Id4Email),
                        "body", Matchers.hasItem(CommentsParams.Id4Body));
    }

    @Test
    public void CommentsCheckEmail() {
        given()
                .contentType("application/json")
                .queryParam("email", CommentsParams.Id5Email)
                .when()
                .get(CommentsUrl)
                .then()
                .statusCode(200)
                .log()
                .body()
                .body("postId", hasItems(1),
                        "id", hasItems(5),
                        "name", Matchers.hasItem(CommentsParams.Id5Name),
                        "body", Matchers.hasItem(CommentsParams.Id5Body));
    }

    @Test
    public void CommentsCheckBody() {
        given()
                .contentType("application/json")
                .queryParam("body", CommentsParams.Id2Body)
                .when()
                .get(CommentsUrl)
                .then()
                .statusCode(200)
                .log()
                .body()
                .body("postId", hasItems(1),
                        "id", hasItems(2),
                        "name", Matchers.hasItem(CommentsParams.Id2Name),
                        "email", Matchers.hasItem(CommentsParams.Id2Email));
    }
}

