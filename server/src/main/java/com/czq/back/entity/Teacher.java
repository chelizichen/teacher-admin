package com.czq.back.entity;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Teacher")
// 教师表
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private Long id;

    // 姓名
    @Column(name = "name", nullable = false)
    private String name;

    // 邮箱
    @Column(name = "email", nullable = false)
    private String email;

    // 电话号码
    @Column(name = "phone")
    private String phone;

    // 学科、主要任职
    @Column(name = "subject")
    private String subject;

    // 几年经验
    @Column(name = "experience")
    private Integer experience;

    // 认证 证书类
    @Column(name = "certification")
    private String certification;

    // 密码
    @Column(name = "password")
    private String password;

    // 状态 0 1
    @Column(name = "status")
    private String status;


    // 1 教师
    // 2 管理员
    @Column(name = "permission")
    private String permission;

    @Column(name="title")
    private Integer titleId;

    @ManyToOne
    @JoinColumn(name = "title",insertable = false,updatable = false)
    private Title title;

    public Integer getTitleId() {
        return titleId;
    }

    public void setTitleId(Integer titleId) {
        this.titleId = titleId;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Constructors, getters, and setters
}

