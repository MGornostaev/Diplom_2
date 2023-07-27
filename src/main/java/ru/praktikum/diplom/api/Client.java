package ru.praktikum.diplom.api;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static ru.praktikum.diplom.api.config.AppConfig.APP_URI;

public class Client {
    protected static RequestSpecification spec() {
        return given().log().all()
                .baseUri(APP_URI);
    }
}