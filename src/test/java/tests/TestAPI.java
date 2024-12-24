package tests;


import io.restassured.RestAssured;
import model.PostRequest;
import model.PutRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import requests.BaseRequest;

public class TestAPI {

    private static BaseRequest baseRequest = new BaseRequest();


    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://reqres.in";

    }

    @Test
    @DisplayName("Регистрация пользователя без пароля")
    public void registerFailure() {
        PostRequest postRequest = new PostRequest("eve.holt@reqres.in", "");
        baseRequest.checkRegisterFailure(baseRequest.basePost(400, postRequest));
    }

    @Test
    @DisplayName("Информация о несуществующем пользователе")
    public void getUserFailure() {
        int id = 0;
        baseRequest.checkNonexistentUser(baseRequest.baseGet(404, id));
    }

    @Test
    @DisplayName("Удаление несуществующего пользователя")
    public void deleteUserFailure() {
        int id = 0;
        baseRequest.baseDelete(404, id);
    }

    @Test
    @DisplayName("Обновление несуществующего пользователя")
    public void updateUserFailure() {
        int id = 0;
        PutRequest putRequest = new PutRequest("morpheus", "zion resident");
        baseRequest.checkUpdateNonexistentUser(baseRequest.basePut(404, id, putRequest));
    }

    @Test
    @DisplayName("Регистрация с корректными данными")
    public void registerSuccess() {
        PostRequest postRequest = new PostRequest("eve.holt@reqres.in", "pistol");
        baseRequest.checkRegisterSuccess(baseRequest.basePost(200, postRequest));
    }

    @Test
    @DisplayName("Информация о существующем пользователе")
    public void getUserSuccess() {
        int id = 2;
        baseRequest.checkExistentUser(baseRequest.baseGet(200, id), id);
    }

    @Test
    @DisplayName("Удаление существующего пользователя")
    public void deleteUserSuccess(){
        int id =2;
        baseRequest.baseDelete(204, id);
        baseRequest.checkNonexistentUser(baseRequest.baseGet(404, id));
    }

    @Test
    @DisplayName("Обновление существующего пользователя")
    public void updateUserSuccess(){
        int id =2;
        PutRequest putRequest = new PutRequest("morpheus", "zion resident");
        baseRequest.checkUpdateExistentUser(baseRequest.basePut(200,id,putRequest),putRequest);

    }



}
