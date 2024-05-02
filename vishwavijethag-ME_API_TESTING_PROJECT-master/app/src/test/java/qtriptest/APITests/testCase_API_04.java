package qtriptest.APITests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.UUID;

public class testCase_API_04 {

    @Test(groups = {"API Tests"})
    public void testCaseAPI04(){

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

            RequestSpecification reRegisterRequest = RestAssured.given();
            reRegisterRequest.basePath("/api/v1/register");
            reRegisterRequest.contentType(ContentType.JSON);
            reRegisterRequest.body(jsonBody.toString());
            Response reRegisterResponse = registerRequest.request(Method.POST);

            reRegisterResponse.prettyPrint();

            reRegisterResponse.then().assertThat().statusCode(400);
            System.out.println("Ensure that the second registration fails with status code 400 - PASSED");

            String reRegisterResponseString = reRegisterResponse.body().asString();
            Assert.assertTrue(reRegisterResponseString.contains("\"message\": \"Email already exists\""));
            System.out.println("Ensure that \"message\": \"Email already exists\" is part of the response - PASSED");


        }
        catch(Exception e){
            System.out.println("TEST CASE 04 - FAILED");
            e.printStackTrace();
        }

    }
}

  

