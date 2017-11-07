package com.example.demo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 设备实体类，结构与数据库Device表一致
 */
@Entity
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_device",
            joinColumns = @JoinColumn(name = "device_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<AppUser> users = new ArrayList<>();

    public Device() {
    }

    public Device(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AppUser> getUsers() {
        return users;
    }

    public void setUsers(List<AppUser> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
