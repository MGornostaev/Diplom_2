package ru.praktikum.diplom.api.user;

import org.apache.commons.lang3.RandomStringUtils;

public class UserGenerator {
    public User generic() {
        return new User("mishka011@yandex.ru","212221","sash11");
    }

    public User random() {
        return new User(RandomStringUtils.randomAlphanumeric(4,8) + "@yandex.ru"
                ,RandomStringUtils.randomAlphanumeric(6,8)
                ,RandomStringUtils.randomAlphanumeric(5,8));
    }
}
