package ru.praktikum.diplom.api.user;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import ru.praktikum.diplom.api.Client;

import static ru.praktikum.diplom.api.config.AppConfig.AUTH;
import static ru.praktikum.diplom.api.config.AppConfig.USER_API;

public class UserClient extends Client {
    @Step
    public ValidatableResponse userCreate(User user) {
        return spec()
                .contentType(ContentType.JSON)
                .body(user)
                .post( AUTH + "/register")
                .then().log().all();
    }

    @Step
    public ValidatableResponse userLogin(Credentials credentials) {
        return spec()
                .contentType(ContentType.JSON)
                .body(credentials)
                .post( AUTH + "/login")
                .then().log().all();
    }

    @Step
    public ValidatableResponse userChangeDataWithAuth(User user, String token) {
        return spec()
                .header("Authorization", token)
                .contentType(ContentType.JSON)
                .body(user)
                .patch( AUTH + USER_API)
                .then().log().all();
    }

    @Step
    public ValidatableResponse userChangeDataWithoutAuth(User user) {
        return spec()
                .contentType(ContentType.JSON)
                .body(user)
                .patch( AUTH + USER_API)
                .then().log().all();
    }



    @Step
    public void userDelete(String token) {
         spec()
                .header("Authorization", token)
                .delete(AUTH + USER_API)
                .then().log().all();
    }
}
