package ru.praktikum.diplom.api;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import ru.praktikum.diplom.api.user.User;

public class UserChangeDataTest extends BaseTest {

    @Test
    @DisplayName("Changing of a user data with authorization should return 200 and success param with true")
    public void changeUserDataWithAuthShouldReturn200True(){
        ValidatableResponse userResponse = userClient.userCreate(user);
        String token = userCheck.created(userResponse);

        user.setPassword(RandomStringUtils.randomAlphanumeric(5,8));
        user.setEmail(RandomStringUtils.randomAlphanumeric(4,8) + "@yandex.ru");
        user.setName(RandomStringUtils.randomAlphanumeric(5,9));

        ValidatableResponse userNewResponse = userClient.userChangeDataWithAuth(user, token);

        userCheck.changedSuccessfully(userNewResponse);
    }

    @Test
    @DisplayName("Changing of a user data without authorization should return 401 and success param with false")
    public void changeUserDataWithoutAuthShouldReturn401False(){
        userClient.userCreate(user);
        User newUser = new User(user.getEmail(), user.getPassword(), user.getName());

        newUser.setPassword(RandomStringUtils.randomAlphanumeric(5,8));
        newUser.setEmail(RandomStringUtils.randomAlphanumeric(4,8) + "@yandex.ru");
        newUser.setName(RandomStringUtils.randomAlphanumeric(5,9));

        ValidatableResponse userNewResponse = userClient.userChangeDataWithoutAuth(newUser);

        userCheck.changedUnsuccessfully(userNewResponse);
    }
}
