package com.ss.spring_asynch_demo.security.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_info")
public class UserInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_name")
    private String userName;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name="email_id", unique = true, nullable = false)
    private String emailId;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name = "roles", nullable = false)
    private String roles;
}
