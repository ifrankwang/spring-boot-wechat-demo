package me.frank.spring.boot.wechat.service.impl;

import me.frank.spring.boot.wechat.entity.AppUser;
import me.frank.spring.boot.wechat.repo.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static me.frank.spring.boot.wechat.exception.ServiceException.INVALID_USER;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final Logger LOG = LoggerFactory.getLogger(getClass());
    private final UserRepo repo;

    public UserDetailsServiceImpl(UserRepo repo) {
        this.repo = repo;
    }

    @Override
    public AppUser loadUserByUsername(String username) throws UsernameNotFoundException {
        LOG.info("\n根据用户名{}获取用户数据...", username);

        AppUser user = repo.findByUsername(username);

        if (null == user) {
            LOG.warn("\n无法根据用户名{}获取到用户数据！", username);
            throw INVALID_USER;
        }

        LOG.info("\n成功获取到用户信息：{}", user);
        return user;
    }
}
