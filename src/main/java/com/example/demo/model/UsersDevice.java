package com.example.demo.model;

import javax.persistence.*;

/**
 * 用户与设备关系实体类
 */
@Entity(name = "user_device")
public class UsersDevice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "device_id")
    private int deviceId;

    protected UsersDevice() {
    }

    public UsersDevice(int userId, int deviceId) {
        this.userId = userId;
        this.deviceId = deviceId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String toString() {
        return "UsersDevice{" +
                "id=" + id +
                ", userId=" + userId +
                ", deviceId=" + deviceId +
                '}';
    }
}
