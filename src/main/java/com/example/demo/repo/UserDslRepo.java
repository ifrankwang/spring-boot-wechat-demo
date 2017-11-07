package com.example.demo.repo;

import com.example.demo.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

/**
 * 对User表进行CRUD操作的Repo，
 * 会自动根据方法名生成查询sql，
 * 不需要implement
 */
public interface UserDslRepo extends JpaRepository<AppUser, Integer>, QuerydslPredicateExecutor<AppUser> {

    /**
     * 根据名称获取用户列表
     *
     * @param name 用户名称
     * @return 用户列表
     */
    List<AppUser> findByName(String name);

    /**
     * 根据id获取用户信息
     *
     * @param id 用户id
     * @return 用户信息
     */
    AppUser findById(int id);

    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    AppUser findByUsername(String username);
}
