package requests;




import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.restassured.http.ContentType;
import model.*;
import org.junit.jupiter.api.Assertions;


import static io.restassured.RestAssured.given;

public class BaseRequest {

    public PostAnswer basePost(int code, PostRequest postRequest) {
        return given()
                .when()
                .contentType(ContentType.JSON)
                .body(postRequest)
                .post("/api/register")
                .then()
                .log().all()
                .statusCode(code)
                .extract()
                .body()
                .jsonPath()
                .getObject("", PostAnswer.class);
    }

    public GetAnswer baseGet(int code, int id){
        return given()
                .when()
                .get("/api/users/"+id)
                .then()
                .log().all()
                .statusCode(code)
                .extract()
                .body()
                .jsonPath()
                .getObject("", GetAnswer.class);
    }

    public void baseDelete(int code, int id){
        given()
                .when()
                .delete("/api/users/"+id)
                .then()
                .log().all()
                .statusCode(code);
    }

    public PutAnswer basePut(int code, int id, PutRequest putRequest){
        return given()
                .log().all()
                .when()
                .contentType(ContentType.JSON)
                .body(putRequest)
                .put("/api/users/"+id)
                .then()
                .log().all()
                .statusCode(code)
                .extract()
                .body()
                .jsonPath()
                .getObject("", PutAnswer.class);
    }

    public void checkRegisterFailure(PostAnswer postAnswer){
        assert postAnswer.equals(new PostAnswer(null,null,"Missing password"));
    }

    public void checkNonexistentUser(GetAnswer getAnswer){
        assert getAnswer.equals(new GetAnswer());

    }

    public void checkUpdateNonexistentUser(PutAnswer putAnswer){
        assert putAnswer.equals(new PutAnswer());
    }

    public void checkRegisterSuccess(PostAnswer postAnswer){
        Assertions.assertFalse(postAnswer.NotCompleteRegister());
    }

    public void checkExistentUser(GetAnswer getAnswer, int id){
        Assertions.assertEquals(getAnswer.getData().getId(), id);
        Assertions.assertFalse(getAnswer.isIncomplete());
    }

    public void checkUpdateExistentUser(PutAnswer putAnswer,PutRequest putRequest){
        Assertions.assertEquals(putAnswer.getName(), putRequest.getName());
        Assertions.assertEquals(putAnswer.getJob(), putRequest.getJob());
//        Assertions.assertEquals(baseGet(200,id).getData().getFirst_name(), putRequest.getName());
    }


}
