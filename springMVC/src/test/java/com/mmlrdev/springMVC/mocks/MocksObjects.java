package com.mmlrdev.springMVC.mocks;

import com.mmlrdev.springMVC.models.User;

public class MocksObjects {

    public static User getUserMock() {
        return User.builder()
                .id(1L)
                .name("Mock Obj Test")
                .build();
    }
}
