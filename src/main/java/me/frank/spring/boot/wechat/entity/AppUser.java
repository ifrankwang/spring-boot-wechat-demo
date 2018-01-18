package me.frank.spring.boot.wechat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * 用户实体类，结构与数据库User表一致
 */
@Entity(name = "user")
public class AppUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;
    @Column
    @JsonIgnore
    private String name;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    @JsonIgnore
    private String gender;
    @Column(name = "open_id")
    @JsonIgnore
    private String openId;
    @Transient
    @JsonIgnore
    private List<GrantedAuthority> authorities;

    public AppUser() {
    }

    public AppUser(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }

    public long getId() {
        return id;
    }

    public AppUser setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AppUser setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public AppUser setUsername(String username) {
        this.username = username;
        return this;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public AppUser setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public AppUser setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getOpenId() {
        return openId;
    }

    public AppUser setOpenId(String openId) {
        this.openId = openId;
        return this;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public AppUser setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
        return this;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
