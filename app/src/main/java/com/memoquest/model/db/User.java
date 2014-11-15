package com.memoquest.model.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.Date;

/**
 * Created by franck on 30/10/2014.
 */

@Table(name = "MQ_USER")
public class User extends Model {


    @Column(name = "ROW_ID")
    private Integer serverId;



    @Column(name = "LAST_NAME")
    private Integer lastName;

    @Column(name = "FIRST_NAME")
    private Integer firstName;


    @Column(name = "ROLE")
    private Integer role;

    @Column(name = "EMAIL")
    private Integer email;

    @Column(name = "LOGIN")
    private Integer login;

    @Column(name = "PASSWORD")
    private Integer password;

    @Column(name = "ACTIVE")
    private Integer active;


    @Column(name = "CREATED_BY")
    //  public int createUser;
    private Long createUser;

    @Column(name = "CREATED")
    private Date createTime;

    @Column(name = "UPDATED_BY")
    //   public int updateUser;
    private Long updateUser;

    @Column(name = "UPDATED")
    private Date updateTime;

    public Integer getServerId() {
        return serverId;
    }

    public void setServerId(Integer serverId) {
        this.serverId = serverId;
    }

    public Integer getLastName() {
        return lastName;
    }

    public void setLastName(Integer lastName) {
        this.lastName = lastName;
    }

    public Integer getFirstName() {
        return firstName;
    }

    public void setFirstName(Integer firstName) {
        this.firstName = firstName;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getEmail() {
        return email;
    }

    public void setEmail(Integer email) {
        this.email = email;
    }

    public Integer getLogin() {
        return login;
    }

    public void setLogin(Integer login) {
        this.login = login;
    }

    public Integer getPassword() {
        return password;
    }

    public void setPassword(Integer password) {
        this.password = password;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "serverId=" + serverId +
                ", lastName=" + lastName +
                ", firstName=" + firstName +
                ", role=" + role +
                ", email=" + email +
                ", login=" + login +
                ", password=" + password +
                ", active=" + active +
                ", createUser=" + createUser +
                ", createTime=" + createTime +
                ", updateUser=" + updateUser +
                ", updateTime=" + updateTime +
                '}';
    }
}
