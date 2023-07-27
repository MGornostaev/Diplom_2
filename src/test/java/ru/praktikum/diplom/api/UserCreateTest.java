package ru.praktikum.diplom.api;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

public class UserCreateTest extends BaseTest {
    @Test
    @DisplayName("Creation of a user with unique valid data should return 201 and success param with true")
    public void createUserWithNewValidDataShouldReturn201True() {
        ValidatableResponse userResponse = userClient.userCreate(user);

        userCheck.createdSuccessfully(userResponse);
    }

    @Test
    @DisplayName("Creation of a user with non-unique data should return 403 and success param with false")
    public void createUserWithExistentDataShouldReturn403False() {
        userClient.userCreate(user);
        ValidatableResponse userResponse = userClient.userCreate(user);

        userCheck.creationConflict(userResponse);
    }

    @Test
    @DisplayName("Creation of a user without email should return 403 and success param with false")
    public void createUserWithoutEmailShouldReturn403False() {
        user.setEmail(null);
        ValidatableResponse userResponse = userClient.userCreate(user);

        userCheck.creationAbsenceOfData(userResponse);
    }

    @Test
    @DisplayName("Creation of a user without password should return 403 and success param with false")
    public void createUserWithoutPasswordShouldReturn403False() {
        user.setPassword(null);
        ValidatableResponse userResponse = userClient.userCreate(user);

        userCheck.creationAbsenceOfData(userResponse);
    }

    @Test
    @DisplayName("Creation of a user without name should return 403 and success param with false")
    public void createUserWithoutNameShouldReturn403False() {
        user.setName(null);
        ValidatableResponse userResponse = userClient.userCreate(user);

        userCheck.creationAbsenceOfData(userResponse);
    }
}
