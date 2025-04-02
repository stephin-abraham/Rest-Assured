package restAssured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class reQres {
    public static String baseUrl = "https://reqres.in";
    public static String contentBody = " ";

    @Test(priority = 0)
    public void getUsers(){
       Response res =  RestAssured
               .given()
               .baseUri(baseUrl)
               .contentType(ContentType.JSON)

               .when()
               .get("/api/users?page=2")

               .then()
               .statusCode(200)
               .extract().response();

        contentBody = res.getBody().asString();
        System.out.println("The content body = "+contentBody);
    }

    @Test(priority = 1)
    public void getSingleUser(){

        Response response = RestAssured
                .given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)

                .when()
                .get("/api/users/2")

                .then()
                .statusCode(200)
                .extract().response();

        System.out.println("The content body of single user is = "+ response.getBody().asString());
    }
    @Test(priority = 2)
    public void getListOfResource(){

        Response response = RestAssured
                .given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)

                .when()
                .get("/api/unknown")

                .then()
                .statusCode(200)
                .extract().response();

        System.out.println("The list of resource is = "+ response.getBody().asString());
    }
    @Test(priority = 3)
    public void getSingleResource(){

        Response response = RestAssured
                .given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)

                .when()
                .get("/api/unknown/2")

                .then()
                .statusCode(200)
                .extract().response();

        System.out.println("The content body of single user is = "+ response.getBody().asString());
    }

    @Test(priority = 4)
    public void createAUser(){
        String bodyContent = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";
        Response response = RestAssured
                .given()
                .baseUri(baseUrl)
                .body(bodyContent)
                .contentType(ContentType.JSON)

                .when()
                .post("/api/users")

                .then()
                .statusCode(201)
                .extract().response();

        System.out.println("The content body of a user after creating is = "+ response.getBody().asString());
    }

    @Test(priority = 5)
    public void updateCreatedUser(){
        String bodyContent = "{\n" +
                "    \"name\": \"Stephin\",\n" +
                "    \"profession\": \"leader\"\n" +
                "}";
        Response response = RestAssured
                .given()
                .baseUri(baseUrl)
                .body(bodyContent)
                .contentType(ContentType.JSON)

                .when()
                .put("api/users/2")

                .then()
                .statusCode(200)
                .extract().response();

        System.out.println("The content body of a user after updating = "+ response.getBody().asString());
    }

    @Test(priority = 6)
    public void partialUpdateCreatedUser(){
        String bodyContent = "{\n" +
                "    \"name\": \"Stephin\",\n" +
                "    \"profession\": \"basketball\"\n" +
                "}";
        Response response = RestAssured
                .given()
                .baseUri(baseUrl)
                .body(bodyContent)
                .contentType(ContentType.JSON)

                .when()
                .patch("api/users/2")

                .then()
                .statusCode(200)
                .extract().response();

        System.out.println("The content body of a user after partial update = "+ response.getBody().asString());
    }

    @Test(priority = 7)
    public void deleteCreatedUser(){

        Response response = RestAssured
                .given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)

                .when()
                .delete("api/users/2")

                .then()
                .statusCode(204)
                .extract().response();
        System.out.println(response.getStatusCode());
    }
}
