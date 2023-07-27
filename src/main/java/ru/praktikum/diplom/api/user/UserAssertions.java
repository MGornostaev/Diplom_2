package ru.praktikum.diplom.api.user;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static java.net.HttpURLConnection.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class UserAssertions {
    @Step
    public void createdSuccessfully(ValidatableResponse response) {
         response
                .assertThat()
                .statusCode(HTTP_CREATED)
                .body("accessToken", notNullValue())
                .body("refreshToken", notNullValue())
                .body("user", notNullValue())
                .body("success", equalTo(true));
    }

    @Step
    public String created(ValidatableResponse response) {
        return response
                .assertThat()
                .body("accessToken", notNullValue())
                .body("refreshToken", notNullValue())
                .body("user", notNullValue())
                .body("success", equalTo(true))
                .extract()
                .path("accessToken");
    }

    @Step
    public void creationConflict(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HTTP_FORBIDDEN)
                .body("success", equalTo(false))
                .body("message", equalTo("User already exists"));
    }

    @Step
    public void changedSuccessfully(ValidatableResponse response) {
         response
                .assertThat()
                .statusCode(HTTP_OK)
                .body("user", notNullValue())
                .body("success", equalTo(true));
    }

    @Step
    public void changedUnsuccessfully(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HTTP_UNAUTHORIZED)
                .body("message", equalTo("You should be authorised"))
                .body("success", equalTo(false));
    }

    @Step
    public void creationAbsenceOfData(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HTTP_FORBIDDEN)
                .body("success", equalTo(false))
                .body("message", equalTo("Email, password and name are required fields"));
    }

    @Step
    public String loggedInSuccessfully(ValidatableResponse response) {
        return response
                .assertThat()
                .statusCode(HTTP_OK)
                .body("accessToken", notNullValue())
                .body("refreshToken", notNullValue())
                .body("user", notNullValue())
                .body("success",equalTo(true))
                .extract()
                .path("accessToken");
    }

    @Step
    public void loginWithIncorrectData(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HTTP_UNAUTHORIZED)
                .body("success", equalTo(false))
                .body("message", equalTo("email or password are incorrect"));
    }
}
