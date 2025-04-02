package restAssured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class reQres {
    public static String baseUrl = "https://reqres.in";
    public static String contentBody = " ";
    public static String tokenId = " ";

    @Test(priority = 0, enabled  = true)
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

    @Test(priority = 1, enabled  = true)
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
    @Test(priority = 2, enabled  = true)
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
    @Test(priority = 3, enabled  = true)
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

    @Test(priority = 4, enabled  = true)
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

    @Test(priority = 5, enabled  = true)
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

    @Test(priority = 6, enabled  = true)
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

    @Test(priority = 7, enabled  = true)
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

    @Test(priority = 8, enabled  = true)
    public void registerSuccessful(){
        String auth = "{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"pistol\"\n" +
                "}";
        Response response = RestAssured
                .given()
                .baseUri(baseUrl)
                .body(auth)
                .contentType(ContentType.JSON)

                .when()
                .post("/api/register")

                .then()
                .statusCode(200)
                .extract().response();
        tokenId = response.jsonPath().getString("token");
        System.out.println("The token is ="+tokenId);
        System.out.println("The Successful register status code is "+response.getStatusCode());
    }

    @Test(priority = 9, enabled  = true)
    public void registerUnsuccessful(){
        String auth = "{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "}";
        Response response = RestAssured
                .given()
                .baseUri(baseUrl)
                .body(auth)
                .contentType(ContentType.JSON)

                .when()
                .post("/api/register")

                .then()
                .statusCode(400)
                .extract().response();

        System.out.println("The unsuccessful register status code is "+response.getStatusCode());
    }
}
