package ru.praktikum.diplom.api;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import ru.praktikum.diplom.api.order.Order;

public class OrderCreateTest extends BaseTest {
    @Test
    @DisplayName("Creation of an order with authorization and ingredients should return 201 and success param with true")
    public void createOrderWithIngredientsAndAuthShouldReturn201True() {
        Order order = orderGenerator.random();
        ValidatableResponse userResponse = userClient.userCreate(user);
        String token = userCheck.created(userResponse);

        ValidatableResponse orderResponse = orderClient.orderCreateWithAuth(order, token);

        orderCheck.createdSuccessfully(orderResponse);
    }

    @Test
    @DisplayName("Creation of an order without ingredients should return 400 and success param with false")
    public void createOrderWithoutIngredientsShouldReturn400False() {
        Order order = orderGenerator.empty();
        ValidatableResponse userResponse = userClient.userCreate(user);
        String token = userCheck.created(userResponse);

        ValidatableResponse orderResponse = orderClient.orderCreateWithAuth(order, token);

        orderCheck.creationBadRequest(orderResponse);
    }

    @Test
    @DisplayName("Creation of an order with invalid ingredients should return 500")
    public void createOrderWithInvalidIngredientsShouldReturn500() {
        Order order = orderGenerator.invalid();
        ValidatableResponse userResponse = userClient.userCreate(user);
        String token = userCheck.created(userResponse);

        ValidatableResponse orderResponse = orderClient.orderCreateWithAuth(order, token);

        orderCheck.creationInternalServerError(orderResponse);
    }

    @Test
    @DisplayName("Creation of an order without authorization should return 401 and success param with false")
    public void createOrderWithoutAuthShouldReturn401False() {
        Order order = orderGenerator.random();
        ValidatableResponse orderResponse = orderClient.orderCreateWithoutAuth(order);

        orderCheck.creationRestricted(orderResponse);
    }
}
