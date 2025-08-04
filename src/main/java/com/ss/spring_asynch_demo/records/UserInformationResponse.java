package com.ss.spring_asynch_demo.records;

public record UserInformationResponse(
    Long userId,
    String firstName,
    String lastName,
    String email,
    String phoneNumber
) {
    public UserInformationResponse(Long userId, String firstName, String lastName, String email, String phoneNumber) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    public UserInformationResponse() {

        this(null, null, null, null, null);
    }
}