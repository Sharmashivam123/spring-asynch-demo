package com.ss.spring_asynch_demo.security.repo;

import com.ss.spring_asynch_demo.security.entity.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInformationRepository extends JpaRepository<UserInfoEntity, Long> {

    Optional<UserInfoEntity> findByEmailId(String emailId);
}
