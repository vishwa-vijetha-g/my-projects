package qtriptest.APITests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.devtools.v95.network.model.Request;
import org.openqa.selenium.json.Json;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.UUID;

public class testCase_API_03 {

    @Test(groups = {"API Tests"})
    public void testCaseAPI03(){

        try{
            RestAssured.baseURI = "https://content-qtripdynamic-qa-backend.azurewebsites.net";

            //Register

            String emailId = UUID.randomUUID().toString().substring(0,8) + "@email.com";
            String password = UUID.randomUUID().toString().substring(0,8);
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("email", emailId);
            jsonBody.put("password", password);
            jsonBody.put("confirmpassword", password);

            RequestSpecification registerRequest = RestAssured.given();
            registerRequest.basePath("/api/v1/register");
            registerRequest.contentType(ContentType.JSON);
            registerRequest.body(jsonBody.toString());
            Response registerResponse = registerRequest.request(Method.POST);

            //registerResponse.prettyPrint();

            registerResponse.then().assertThat().statusCode(201);
            System.out.println("The Register API should return a status code for 201 - PASSED");

            //Login
            jsonBody.remove("confirmpassword");

            RequestSpecification loginRequest = RestAssured.given();
            loginRequest.basePath("/api/v1/login");
            loginRequest.contentType(ContentType.JSON);
            loginRequest.body(jsonBody.toString());
            Response loginResponse = loginRequest.request(Method.POST);

            //loginResponse.prettyPrint();

            loginResponse.then().assertThat().statusCode(201);
            System.out.println("After successful login, status code 201 must be returned - PASSED");

            JSONObject responseJsonBody = new JSONObject(loginResponse.body().asString());

            Assert.assertTrue((Boolean) responseJsonBody.get("success"));
            System.out.println("The Response body should contain : Success = true - PASSED");

            Assert.assertNotEquals(responseJsonBody.getJSONObject("data").getString("id"), "");
            System.out.println("The Response body should contain : user ID details - PASSED");

            Assert.assertNotEquals(responseJsonBody.getJSONObject("data").getString("token"), "");
            System.out.println("The Response body should contain : token details - PASSED");

            //Test Case 03
            String token = responseJsonBody.getJSONObject("data").getString("token");
            String userId = responseJsonBody.getJSONObject("data").getString("id");
            String adventureId = "2447910730";
            JSONObject postReservationJsonBodyRequest = new JSONObject();
            postReservationJsonBodyRequest.put("userId", userId);
            postReservationJsonBodyRequest.put("name", "Crio Test");
            postReservationJsonBodyRequest.put("date", "01-01-2025");
            postReservationJsonBodyRequest.put("person", "1");
            postReservationJsonBodyRequest.put("adventure", adventureId);


            RequestSpecification postReservationRequest = RestAssured.given();
            postReservationRequest.basePath("/api/v1/reservations/new");
            postReservationRequest.header("Authorization", "Bearer " + token);
            postReservationRequest.contentType(ContentType.JSON);
            postReservationRequest.body(postReservationJsonBodyRequest.toString());
            Response postReservationResponse = postReservationRequest.request(Method.POST);

            //postReservationResponse.prettyPrint();

            postReservationResponse.then().assertThat().statusCode(200);
            System.out.println("On a successful booking, status code 200 should be returned - PASSED");
            
            RequestSpecification getReservationRequest = RestAssured.given();
            getReservationRequest.basePath("/api/v1/reservations/");
            getReservationRequest.queryParam("id", userId);
            getReservationRequest.header("Authorization", "Bearer " + token);
            getReservationRequest.contentType(ContentType.JSON);
            Response getReservationResponse = getReservationRequest.request(Method.GET);

            //getReservationResponse.prettyPrint();

            getReservationResponse.then().assertThat().statusCode(200);
            System.out.println("Perform a GET Reservations call for the user - PASSED");

            JSONArray reservationArray = new JSONArray(getReservationResponse.body().asString());
            JSONObject firstReservation = reservationArray.getJSONObject(0);
            String adventureIdFromResponse = firstReservation.getString("adventure");
            Assert.assertEquals(adventureIdFromResponse, adventureId);
            
            System.out.println("Ensure that the successful booking is listed - PASSED");


        }
        catch(Exception e){
            System.out.println("TEST CASE 03 - FAILED");
            e.printStackTrace();
        }
    }

}
