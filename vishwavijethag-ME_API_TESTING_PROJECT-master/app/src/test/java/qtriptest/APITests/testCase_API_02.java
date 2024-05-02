package qtriptest.APITests;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.UUID;
public class testCase_API_02 {

    @Test(groups = {"API Tests"})
    public void testCaseAPI02(){

        try{
            
            RestAssured.baseURI = "https://content-qtripdynamic-qa-backend.azurewebsites.net";

            //Get Cities
            RequestSpecification citiesRequest = RestAssured.given();
            citiesRequest.basePath("/api/v1/cities");
            citiesRequest.queryParam("q", "beng");
            Response citiesResponse = citiesRequest.request(Method.GET);
            //citiesResponse.prettyPrint();

            citiesResponse.then().assertThat().statusCode(200);
            System.out.println("After successful search, the status code must be 200 - PASSED");

            String responseStringBody = citiesResponse.body().asString();
            JSONArray responseJsonArray = new JSONArray(responseStringBody);
            Assert.assertEquals(responseJsonArray.length(),1);
            System.out.println("The Result should be an array of length 1 - PASSED");

            citiesResponse.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File("/home/crio-user/workspace/vishwavijethag-ME_API_TESTING_PROJECT/app/src/test/resources/schema.json")));
            System.out.println("Response Json Schema validation - PASSED");

        }
        catch(Exception e){
            System.out.println("TEST CASE 02 - FAILED");
            e.printStackTrace();
        }
    }

}
