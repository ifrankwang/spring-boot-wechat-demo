package com.example.demo.dao;

import com.example.demo.model.*;
import com.example.demo.repo.UserDslRepo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Dao 示例
 */
@Repository
@Transactional
public class UserDao {
    private static final Logger log = LoggerFactory.getLogger(UserDao.class);
    private final JPAQueryFactory query;
    private final QAppUser qUser;
    private final QDevice qDevice;
    private final QUsersDevice qUsersDevice;
    private final UserDslRepo userRepo;

    public UserDao(
            EntityManager manager,
            UserDslRepo userRepo) {
        query = new JPAQueryFactory(manager);
        qUser = QAppUser.appUser;
        qDevice = QDevice.device;
        qUsersDevice = QUsersDevice.usersDevice;
        this.userRepo = userRepo;
    }

    /**
     * 保存用户信息
     *
     * @param user 用户信息
     */
    public void saveUser(AppUser user) {
        userRepo.save(user);
    }

    /**
     * 获取所有用户信息
     *
     * @return 用户信息列表
     */
    public List<AppUser> findUsers() {
        return userRepo.findAll();
    }

    /**
     * 根据用户名称模糊查询用户信息
     *
     * @param expression 关键词
     * @return 用户信息列表
     */
    public List<AppUser> findUsersNameLike(String expression) {
        return (List<AppUser>) userRepo.findAll(qUser.name.contains(expression));
    }

    /**
     * 根据用户 id 获取用户信息
     *
     * @param id 用户 id
     * @return 用户信息
     */
    public AppUser findUserById(int id) {
        return userRepo.findById(id);
    }

    /**
     * 根据用户 id 获取带有设备列表的用户信息
     *
     * @param id 用户 id
     * @return 带有设备列表的用户信息
     */
    public AppUser findUserWithDevicesById(int id) {
        AppUser user = userRepo.findById(id);

        log.info(user.toString());
        log.info(user.getDevices().toString());

        return user;
    }

    /**
     * 获取所有用户信息，并带有设备列表，
     * 此方法效率并不好，不建议这么使用，
     * 此方法仅供参考 QueryDsl 的使用
     *
     * @return 带有设备列表的用户信息列表
     */
    public List<AppUser> findUserWithDevices() {
        List<AppUser> users = query.selectFrom(qUser)
                .fetch();

        for (AppUser user : users) {
            List<Device> devices = query.selectFrom(qDevice)
                    .leftJoin(qUsersDevice)
                    .on(qDevice.id.eq(qUsersDevice.deviceId))
                    .leftJoin(qUser)
                    .on(qUser.id.eq(qUsersDevice.userId))
                    .where(qUser.id.eq(user.getId()))
                    .fetch();
            user.setDevices(devices);
        }

        return users;
    }

    /**
     * 根据用户名获取用户信息
     *
     * @param name 用户名
     * @return 用户信息
     */
    public List<AppUser> findUsersByName(String name) {
        return userRepo.findByName(name);
    }

    /**
     * 用户增加设备
     *
     * @param userId 用户 id
     * @param device 设备
     */
    public void addDeviceToUser(int userId, Device device) {
        AppUser user = userRepo.findById(userId);
        user.getDevices().add(device);
    }
}
