package ru.praktikum.diplom.api;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import ru.praktikum.diplom.api.order.Order;

public class OrderGetTest extends BaseTest {

    Order order = orderGenerator.random();

    @Test
    @DisplayName("Getting orders of user with authorization should return 200 and success param with true")
    public void getUserOrdersWithAuthShouldReturn200True(){
        ValidatableResponse userResponse = userClient.userCreate(user);
        String token = userCheck.created(userResponse);
        orderClient.orderCreateWithAuth(order, token);

        ValidatableResponse orderResponse = orderClient.getUserOrdersWithAuth(token);

        orderCheck.gotOrdersSuccessfully(orderResponse);
    }

    @Test
    @DisplayName("Getting orders of user without authorization should return 401 and success param with false")
    public void getUserOrdersWithoutAuthShouldReturn401False(){
        orderClient.orderCreateWithoutAuth(order);

        ValidatableResponse orderResponse = orderClient.getUserOrdersWithoutAuth();

        orderCheck.gotOrdersUnauthorized(orderResponse);
    }
}
