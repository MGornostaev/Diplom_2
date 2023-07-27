package ru.praktikum.diplom.api.order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static java.net.HttpURLConnection.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasSize;

public class OrderAssertions {
    @Step
    public void createdSuccessfully(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HTTP_CREATED)
                .body("name", notNullValue())
                .body("order", notNullValue())
                .body("success", equalTo(true));
    }

    @Step
    public void creationBadRequest(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HTTP_BAD_REQUEST)
                .body("message", equalTo("Ingredient ids must be provided"))
                .body("success", equalTo(false));
    }

    @Step
    public void creationInternalServerError(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HTTP_INTERNAL_ERROR)
                .body("success", equalTo(false));
    }

    @Step
    public void creationRestricted(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HTTP_UNAUTHORIZED)
                .body("success", equalTo(false));
    }

    @Step
    public void gotOrdersSuccessfully(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HTTP_OK)
                .body("orders", notNullValue())
                .body("orders", hasSize(1))
                .body("orders[0].ingredients", notNullValue())
                .body("orders[0].status", notNullValue())
                .body("orders[0].name", notNullValue())
                .body("orders[0].updatedAt", notNullValue())
                .body("orders[0].number", notNullValue())
                .body("total", notNullValue())
                .body("totalToday", notNullValue())
                .body("success", equalTo(true));
    }

    @Step
    public void gotOrdersUnauthorized(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HTTP_UNAUTHORIZED)
                .body("message", equalTo("You should be authorised"))
                .body("success", equalTo(false));
    }
}
