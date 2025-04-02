package restAssured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class test {
    public static String baseUrl = "https://simple-books-api.glitch.me";
    public static String token = " ";
    public static String orderId = " ";
    public static String resBody = " ";


    @Test(enabled = true,priority = 0)
    public void authorization(){
        String requestBody = "{\n" +
                "   \"clientName\": \"Stephy\",\n" +
                "   \"clientEmail\": \"stephy0123456d789@example.com\"\n" +
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

    @Test(enabled = true,priority = 1)
    public void createAnOrder() {
        String requestBody = "{\n" +
                "  \"bookId\": 1,\n" +
                "  \"customerName\": \"John\"\n" +
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
        System.out.print(response.getBody().asString());
        orderId =response.jsonPath().getString("orderId");
        System.out.println(orderId);
    }

    @Test(enabled = true,priority = 2)
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

    @Test(enabled = true,priority = 3)
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

    @Test(enabled = true,priority = 4)
    public void getASingleBook() {
        Response response = RestAssured
                .given()
                .baseUri(baseUrl)
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)

                .when()
                .get("/books/1")

                .then()
                .statusCode(200)
                .extract().response();
        System.out.println(response.getBody().asString());
    }


    @Test(enabled = true,priority = 5)
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


    @Test(enabled = true, priority = 6)
    public void updateAnOrder() {
        String requestBody = "{\n" +
                "  \"customerName\": \"Steph\"\n" +
                "}";
        Response response = RestAssured
                .given()
                .baseUri(baseUrl)
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .contentType(ContentType.JSON)

                .when()
                .patch("/orders/"+orderId)

                .then()
                .statusCode(204)
                .extract().response();

        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
    }

    @Test(enabled = true, priority = 7)
    public void getUpdatedOrder() {
        Response response = RestAssured
                .given()
                .baseUri(baseUrl)
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)

                .when()
                .get("/orders/"+orderId)

                .then()
                .statusCode(200)
                .extract().response();

        System.out.println("Response Status Code: " + response.getStatusCode());
       resBody=  response.getBody().asString();
       System.out.println("Response Body content is :" +resBody);
    }
    @Test(enabled = true, priority = 8)
    public void deleteAnOrder() {

        Response response = RestAssured
                .given()
                .baseUri(baseUrl)
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)

                .when()
                .delete("/orders/"+orderId)

                .then()
                .statusCode(204)
                .extract().response();

        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
    }
}

