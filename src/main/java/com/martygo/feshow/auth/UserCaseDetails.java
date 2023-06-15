package com.martygo.feshow.auth;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.martygo.feshow.domain.UserCase;

public class UserCaseDetails implements UserDetails {
    private String email;
    private String password;
    private List<GrantedAuthority> authorities;

    public UserCaseDetails(UserCase userCase) {
        this.email = userCase.getEmail();
        this.password = userCase.getPassword();
        this.authorities = Arrays.stream(userCase.getRoles().split(",")).map(
                SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
