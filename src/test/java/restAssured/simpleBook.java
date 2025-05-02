package restAssured;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class simpleBook {
    public static String baseUrl = "https://simple-books-api.glitch.me";
    public static String token = " ";
    public static String orderId = " ";
    public static String updatedBody = " ";

    @Test(priority = 0)
    public void auth(){
        String bodyCont = "{\n" +
                "   \"clientName\": \"123steph\",\n" +
                "   \"clientEmail\": \"65123456Steph1234@gamil.com\"\n" +
                "}";
      Response res = given()
              .baseUri(baseUrl)
              .body(bodyCont)
              .contentType(ContentType.JSON)

              .when()
              .post("/api-clients/")

              .then()
              .statusCode(201)
              .extract().response();

      token = res.jsonPath().getString("accessToken");
      System.out.println(token);
    }

    @Test(priority = 1)
    public void createAnOrder(){
        String orderBody = "{\n" +
                "  \"bookId\": 1,\n" +
                "  \"customerName\": \"steph\"\n" +
                "}";
        Response res = given().
                baseUri(baseUrl)
                .body(orderBody)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer "+token)

                .when()
                .post("/orders")

                .then()
                .statusCode(201)
                .extract().response();

        orderId = res.jsonPath().getString("orderId");
        System.out.println("the OrderId for the product is "+orderId);
    }
    @Test(priority = 2)
    public void getOrder(){
        given().
                baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer "+token)

                .when()
                .get("/orders/" +orderId)

                .then()
                .statusCode(200)
                .extract().response();
    }
    @Test(priority = 3)
    public void updateOrder(){
        String updateBody = "{\n" +
                "    \"customerName\": \"jam\"\n" +
                "}";
        Response res = given()
                .baseUri(baseUrl)
                .body(updateBody)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer "+token)

                .when()
                .patch("/orders/" +orderId)

                .then()
                .statusCode(204)
                .extract().response();
        System.out.println("Response Status Code: " + res.getStatusCode());
        System.out.println("The updated content is "+updatedBody);
    }

    @Test(priority = 4)
    public void getAllOrders(){
        Response res = given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer "+token)
                .when()
                .get("/orders")

                .then()
                .statusCode(200)
                .extract().response();

        System.out.println(res.body().asString());
    }
}
