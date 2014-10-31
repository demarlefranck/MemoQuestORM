package com.memoquest.model.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.Date;
import java.util.List;

/**
 * Created by franck on 30/10/2014.
 */
@Table(name = "MQ_QUIZ")
public class Quiz extends Model {


    // `ROW_ID`, `NAME`,  `SKILL_ID`


    @Column(name = "ROW_ID")
    private Integer serverId;

    @Column(name = "NAME")
    private String name;
/*
    @Column(name = "SKILL_ID", onDelete = Column.ForeignKeyAction.CASCADE)
    private Skill skill;
*/

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<QuizContent> getQuizContents() {

        return getMany(QuizContent.class, "QUIZ_FK");
    }


    @Override
    public String toString() {
        return "Quiz{" +
                "name='" + name + '\'' +
                //      ", skill=" + skill +
                ", quizContents=" + getQuizContents().toString() +
                ", createUser=" + createUser +
                ", createTime=" + createTime +
                ", updateUser=" + updateUser +
                ", updateTime=" + updateTime +
                '}';
    }
}
