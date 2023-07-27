package ru.praktikum.diplom.api;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.diplom.api.user.Credentials;

public class UserLoginTest extends BaseTest{
    @Before
    public void createUser() {
        userClient.userCreate(user);
    }

    @Test
    @DisplayName("Login with valid data should return 200 and success param with true")
    public void userLoginWithValidDataShouldReturn200True() {
        Credentials credentials = Credentials.from(user);
        ValidatableResponse loginResponse = userClient.userLogin(credentials);

        userCheck.loggedInSuccessfully(loginResponse);
    }

    @Test
    @DisplayName("Login with nonexistent data should return 401 and success param with false")
    public void userLoginWithNonexistentDataShouldReturn401False() {
        Credentials credentials = Credentials.from(user);
        credentials.setPassword(RandomStringUtils.randomAlphanumeric(5,8));
        credentials.setEmail(RandomStringUtils.randomAlphanumeric(4,8) + "@yandex.ru");
        ValidatableResponse loginResponse = userClient.userLogin(credentials);

        userCheck.loginWithIncorrectData(loginResponse);
    }
}
