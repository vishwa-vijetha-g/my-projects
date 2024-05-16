package qtriptest.APITests;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;
import java.util.UUID;



public class testCase_API_01 {
    @Test(groups = {"API Tests"})
    public void testCaseAPI01(){
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
        }
        catch(Exception e){
            System.out.println("TEST CASE 01 - FAILED");
            e.printStackTrace();
        }

    }
    
   
}
