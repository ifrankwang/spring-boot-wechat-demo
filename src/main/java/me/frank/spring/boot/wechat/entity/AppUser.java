package me.frank.spring.boot.wechat.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 用户实体类，结构与数据库User表一致
 */
@Data
@Entity(name = "user")
public class AppUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    private String name;
    private String username;
    private String password;
    private String gender;
    private String openId;
    @Transient
    private List<GrantedAuthority> authorities;

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
