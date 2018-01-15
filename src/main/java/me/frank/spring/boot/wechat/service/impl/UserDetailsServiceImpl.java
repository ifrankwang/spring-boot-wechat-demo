package me.frank.spring.boot.wechat.service.impl;

import me.frank.spring.boot.wechat.entity.AppUser;
import me.frank.spring.boot.wechat.repo.UserRepo;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepo repo;

    public UserDetailsServiceImpl(UserRepo repo) {
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
