package com.taotao.sso.pojo;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;

/**
 * 用户表
 *
 * @author wcyong
 *
 * @date 2019-12-11
 */
public class TbUser extends com.taotao.pojo.TbUser {
    @Null
    private Long id;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空！")
    private String username;

    /**
     * 密码，加密存储
     */
    @NotBlank(message = "密码不能为空！")
    private String password;

    /**
     * 注册手机号
     */
    @NotBlank(message = "手机号不能为空！")
    private String phone;

    /**
     * 注册邮箱
     */
    @Email(message = "Email格式不正确！")
    private String email;

    private Date created;

    private Date updated;

    /**
     * 收货地址存json字符串
     */
    private String address;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    @Override
    public String getPhone() {
        return phone;
    }

    @Override
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    @Override
    public Date getCreated() {
        return created;
    }

    @Override
    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public Date getUpdated() {
        return updated;
    }

    @Override
    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }
}