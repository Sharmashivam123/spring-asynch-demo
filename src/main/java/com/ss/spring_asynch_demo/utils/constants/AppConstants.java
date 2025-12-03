package com.ss.spring_asynch_demo.utils.constants;

public class AppConstants {
    public static final String USER_ID = "userId";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String EMAIL = "email";
    public static final String PHONE_NUMBER = "phoneNumber";

    public static final String USER_INFO_URI = "/users/{userId}";
    public static final String ORDERS_BY_USER_ID_URI = "/orders/user/{userId}";

    private AppConstants() {

    }
}
