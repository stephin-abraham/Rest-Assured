package restAssured;

import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class DataDrivenDemo extends DataForTest{

    @Test(dataProvider = "Data_for_Post", priority = 0)
    public void postTest(String name, int age){
        baseURI = "http://localhost:3000/";
        JSONObject req = new JSONObject();
        req.put("name",name);
        req.put("position",age);

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(req.toString())
                .when()
                .post("sample")

                .then()
                .statusCode(201)
                .log().all();
    }
    @Test(dataProvider = "Data_for_Delete",priority = 1)
    public void deletePost(String userId){
        baseURI = "http://localhost:3000/";
        given()
                .when()
                .delete("sample/"+userId)
                .then()
                .statusCode(200)
                .log().all();
    }
}
