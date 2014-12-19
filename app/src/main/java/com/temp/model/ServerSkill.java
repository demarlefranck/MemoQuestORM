package com.temp.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.Date;

/**
 * Created by franck on 30/10/2014.
 *
 *
 *  INSERT INTO `SERVER_MQ_SKILL` (`ROW_ID`, `GRADE`, `SUBJECT`, `CATEGORY`, `SKILL`, `CREATED`, `CREATED_BY`, `UPDATED`, `UPDATED_BY`) VALUES
 *
 */
@Table(name = "SERVER_MQ_SKILL")
public class ServerSkill  extends Model {

    @Column(name = "ROW_ID")
    private Integer serverId;

    @Column(name = "GRADE")
    private String grade;

    @Column(name = "SUBJECT")
    private String subject;

    @Column(name = "CATEGORY")
    private String category;

    @Column(name = "SKILL")
    private String skill;

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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
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
        return "Skill{" +
                "serverId=" + serverId +
                ", grade='" + grade + '\'' +
                ", subject='" + subject + '\'' +
                ", category='" + category + '\'' +
                ", skill='" + skill + '\'' +
                ", createUser=" + createUser +
                ", createTime=" + createTime +
                ", updateUser=" + updateUser +
                ", updateTime=" + updateTime +
                '}';
    }
}
