package com.ss.spring_asynch_demo.utils.runners;

import com.ss.spring_asynch_demo.security.entity.UserInfoEntity;
import com.ss.spring_asynch_demo.security.repo.UserInformationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserInformationFeeder implements CommandLineRunner {

    private final UserInformationRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        UserInfoEntity user = new UserInfoEntity();
        user.setUserName("User");
        user.setEmailId("user@gmail.com");
        user.setPhoneNumber("+9191919191919");
        user.setRoles("ROLE_USER");
        user.setPassword(passwordEncoder.encode("password"));

        UserInfoEntity admin = new UserInfoEntity();
        admin.setUserName("Admin");
        admin.setEmailId("admin@gmail.com");
        admin.setPhoneNumber("+9191919191919");
        admin.setRoles("ROLE_ADMIN");
        admin.setPassword(passwordEncoder.encode("password"));

        UserInfoEntity manager = new UserInfoEntity();
        manager.setUserName("Manager");
        manager.setPassword(passwordEncoder.encode("password"));
        manager.setRoles("ROLE_MANAGER");
        manager.setEmailId("manager@gmail.com");

        repository.saveAll(List.of(user, manager, admin));
    }
}
