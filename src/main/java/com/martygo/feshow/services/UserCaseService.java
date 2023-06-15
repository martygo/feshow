package com.martygo.feshow.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.martygo.feshow.auth.UserCaseDetails;
import com.martygo.feshow.domain.UserCase;
import com.martygo.feshow.repository.UserCaseRepository;

@Component
@Service
public class UserCaseService implements UserDetailsService {

    @Autowired
    private UserCaseRepository userCaseRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserCase> userCase = userCaseRepository.findByEmail(email);

        return userCase.map(UserCaseDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user" + email + "not found"));
    }

    public UserCase create(UserCase user) {
        return userCaseRepository.save(user);
    }

    public boolean existsByEmail(String email) {
        return userCaseRepository.existsByEmail(email);
    }

}
