package com.ss.spring_asynch_demo.security.service;

import com.ss.spring_asynch_demo.security.entity.UserDetailsInformation;
import com.ss.spring_asynch_demo.security.repo.UserInformationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsInformationService implements UserDetailsService {

    private final UserInformationRepository userInformationRepository;

    @Override
    public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
        return userInformationRepository.findByEmailId(emailId)
                .map(UserDetailsInformation::new)
                .orElseThrow(() -> new UsernameNotFoundException("Email Id does not exists."));
    }
}
