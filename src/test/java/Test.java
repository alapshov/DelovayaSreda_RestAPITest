import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import static org.hamcrest.core.IsEqual.equalTo;

public class Test extends RestAssured {
    @BeforeClass
    public void setUp(){
        baseURI= "https://reqres.in";
    }
    @org.testng.annotations.Test
    @Parameters({"statusCode","id"})
    public void getTest(int statusCode, int id){

        when()
                .get("/api/users/"+id)
                .then()
                .statusCode(statusCode)
                .assertThat()
                .body("data.id", equalTo(id));
    }
    @org.testng.annotations.Test
    @Parameters({"statusCode", "jsonRquest"})
    public void createTest(int statusCode, String jsonRquest){
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(jsonRquest);
        when()
                 .post("/api/users")
                .then()
                .statusCode(statusCode);
    }
    @org.testng.annotations.Test
    @Parameters({"statusCode","jsonRquest"})
    public void updateTest(int statusCode, String jsonRquest){
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(jsonRquest);
        when()
                .put("/api/users")
                .then()
                .statusCode(statusCode);
    }
    @org.testng.annotations.Test
    @Parameters({"statusCode","id"})
    public void deleteTest(int statusCode, int id){
        when()
                .delete("/api/users/"+id)
                .then()
                .statusCode(statusCode);
    }

    @org.testng.annotations.Test
    @Parameters({"statusCode","id"})
    public void notFoundTest(int statusCode, int id){
        when()
                .get("/api/users/"+id)
                .then()
                .statusCode(statusCode);
    }


}
