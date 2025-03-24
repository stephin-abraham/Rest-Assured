package restAssured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class test {
    public static String baseUrl = "https://simple-books-api.glitch.me";
    public static String token = " ";

    @Test(priority = 0)
    public void authorization(){
        String requestBody = "{\n" +
                "   \"clientName\": \"Stephy1\",\n" +
                "   \"clientEmail\": \"stephy1@example.com\"\n" +
                "}";
       Response response = RestAssured
               .given()
               .baseUri(baseUrl)
               .body(requestBody)
               .contentType(ContentType.JSON)

               .when()
               .post("/api-clients/")

               .then()
               .statusCode(201)
               .extract().response();
        token= response.jsonPath().getString("accessToken");
        System.out.println(token);
    }

    @Test(priority = 1)
    public void createAnOrder() {
        String requestBody = "{\n" +
                "  \"bookId\": 101,\n" +
                "  \"customerName\": \"Jhonny11\"\n" +
                "}";
        Response response = RestAssured
                .given()
                .baseUri(baseUrl)
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .contentType(ContentType.JSON)

                .when()
                .post("/orders/")

                .then()
                .statusCode(201)
                .extract().response();

//        token= response.jsonPath().getString("accessToken");
        System.out.println(response.getBody().asString());
    }

    @Test(priority = 2)
    public void getStatus() {
        Response response = RestAssured
                .given()
                .baseUri(baseUrl)
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)

                .when()
                .get("/status/")

                .then()
                .statusCode(200)
                .extract().response();
        System.out.println(response.getBody().asString());
    }

    @Test(priority = 3)
    public void getListOfBooks() {
        Response response = RestAssured
                .given()
                .baseUri(baseUrl)
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)

                .when()
                .get("/books/")

                .then()
                .statusCode(200)
                .extract().response();
        System.out.println(response.getBody().asString());

    }

//    @Test(priority = 4)
//    public void getASingleBook() {
//        Response response = RestAssured
//                .given()
//                .baseUri(baseUrl)
//                .header("Authorization", "Bearer " + token)
//                .contentType(ContentType.JSON)
//
//                .when()
//                .get("/books/")
//
//                .then()
//                .statusCode(200)
//                .extract().response();
//        System.out.println(response.getBody().asString());
//    }


    @Test(priority = 4)
    public void getAllOrders() {
        Response response = RestAssured
                .given()
                .baseUri(baseUrl)
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)

                .when()
                .get("/orders/")

                .then()
                .statusCode(200)
                .extract().response();
        System.out.println(response.getBody().asString());
    }
}

