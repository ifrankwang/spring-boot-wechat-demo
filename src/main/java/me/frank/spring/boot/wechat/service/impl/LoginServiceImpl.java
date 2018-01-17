package me.frank.spring.boot.wechat.service.impl;

import me.frank.spring.boot.wechat.entity.AppUser;
import me.frank.spring.boot.wechat.exception.ServiceException;
import me.frank.spring.boot.wechat.repo.UserRepo;
import me.frank.spring.boot.wechat.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static me.frank.spring.boot.wechat.exception.ServiceException.USER_UNBIND;

@Service
@Transactional
public class LoginServiceImpl implements ILoginService {
    private final UserRepo userRepo;

    @Autowired
    public LoginServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public AppUser findUserByOpenId(String openId) throws ServiceException {
        final AppUser USER = userRepo.findByOpenId(openId);

        if (null == USER) {
            throw USER_UNBIND;
        }

        return USER;
    }

    @Override
    public void saveUserInfo(AppUser user) {
        userRepo.save(user);
    }
}
