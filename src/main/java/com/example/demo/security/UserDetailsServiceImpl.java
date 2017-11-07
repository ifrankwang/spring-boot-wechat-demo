package com.example.demo.security;

import com.example.demo.model.AppUser;
import com.example.demo.repo.UserDslRepo;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserDslRepo repo;

    public UserDetailsServiceImpl(UserDslRepo repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String s)
            throws UsernameNotFoundException {
        AppUser user = repo.findByUsername(s);
        if (null == user) {
            throw new UsernameNotFoundException(s);
        }
        return new User(user.getUsername(), user.getPassword(), Collections.emptyList());
    }
}
