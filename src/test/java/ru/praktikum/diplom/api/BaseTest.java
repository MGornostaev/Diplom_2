package ru.praktikum.diplom.api;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import ru.praktikum.diplom.api.order.OrderAssertions;
import ru.praktikum.diplom.api.order.OrderClient;
import ru.praktikum.diplom.api.order.OrderGenerator;
import ru.praktikum.diplom.api.user.*;

public class BaseTest {
    protected final OrderGenerator orderGenerator = new OrderGenerator();

    protected final OrderClient orderClient = new OrderClient();

    protected final OrderAssertions orderCheck = new OrderAssertions();

    protected final UserGenerator userGenerator = new UserGenerator();

    protected final UserClient userClient = new UserClient();

    protected final UserAssertions userCheck = new UserAssertions();

    User user = userGenerator.random();

    @After
    public void clean() {
        Credentials credentials = Credentials.from(user);
        try {
            ValidatableResponse loginResponse = userClient.userLogin(credentials);
            String token = userCheck.loggedInSuccessfully(loginResponse);
            userClient.userDelete(token);
        } catch (AssertionError error) {
            System.out.println(error.getMessage());
        }
    }
}
