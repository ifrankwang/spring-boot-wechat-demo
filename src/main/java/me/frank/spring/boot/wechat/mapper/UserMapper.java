package me.frank.spring.boot.wechat.mapper;

import me.frank.spring.boot.wechat.entity.AppUser;

import java.util.List;

/**
 * MyBatis 框架下用来操作 User 表数据的类，
 * 不需要 implement，
 * 相对应的 *Mapper.xml 文件中需要有对应的 id
 */
public interface UserMapper {

    /**
     * 获取所有的用户信息
     *
     * @return 用户信息列表
     */
    List<AppUser> findAll();

}
