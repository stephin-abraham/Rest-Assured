package restAssured;

import io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class LocalApiTesting {
    @Test(priority = 0)
    public void getTest(){
        baseURI = "http://localhost:3000/";
        given()
                .param("name","Stephin")
                .get("users")
                .then()
                .statusCode(200)
                .log().all();
    }
    @Test(priority = 1)
    public void postTest(){
        baseURI = "http://localhost:3000/";
        JSONObject req = new JSONObject();
        req.put("name","Lebron");
        req.put("position","SG");

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(req.toString())
                .when()
                .post("users")

                .then()
                .statusCode(201)
                .log().all();
    }
    @Test(priority = 2)
    public void patchTest(){
        baseURI = "http://localhost:3000/";
        JSONObject req = new JSONObject();
        req.put("name","Lebron");
        req.put("position","SF");

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(req.toString())
                .when()
                .put("users/3d2a")

                .then()
                .statusCode(200)
                .log().all();
    }
    @Test(priority = 3)
    public void deleteTest(){
        baseURI = "http://localhost:3000/";

        given()
                .when()
                .delete("users/4a96")

                .then()
                .statusCode(200)
                .log().all();
    }

}
