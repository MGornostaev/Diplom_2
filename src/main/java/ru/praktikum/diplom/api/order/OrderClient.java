package ru.praktikum.diplom.api.order;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import ru.praktikum.diplom.api.Client;

import static ru.praktikum.diplom.api.config.AppConfig.ORDER_API;

public class OrderClient extends Client {
    @Step
    public ValidatableResponse orderCreateWithAuth(Order order, String token) {
        return spec()
                .header("Authorization", token)
                .contentType(ContentType.JSON)
                .body(order)
                .post(ORDER_API)
                .then().log().all();
    }

    @Step
    public ValidatableResponse orderCreateWithoutAuth(Order order) {
        return spec()
                .contentType(ContentType.JSON)
                .body(order)
                .post(ORDER_API)
                .then().log().all();
    }

    @Step
    public ValidatableResponse getUserOrdersWithAuth(String token) {
        return spec()
                .header("Authorization", token)
                .get(ORDER_API)
                .then().log().all();
    }

    @Step
    public ValidatableResponse getUserOrdersWithoutAuth() {
        return spec()
                .get(ORDER_API)
                .then().log().all();
    }
}
