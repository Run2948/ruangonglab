package com.borun.lab.bean;

import javax.persistence.*;

@Entity
@Table(name="student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String stuname;
    private String stunum;
    private String password;
    private Integer sign;
    private Integer signall;
    private Integer leave;
    private Integer leaveall;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public String getStunum() {
        return stunum;
    }

    public void setStunum(String stunum) {
        this.stunum = stunum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSign() {
        return sign;
    }

    public void setSign(Integer sign) {
        this.sign = sign;
    }

    public Integer getSignall() {
        return signall;
    }

    public void setSignall(Integer signall) {
        this.signall = signall;
    }

    public Integer getLeave() {
        return leave;
    }

    public void setLeave(Integer leave) {
        this.leave = leave;
    }

    public Integer getLeaveall() {
        return leaveall;
    }

    public void setLeaveall(Integer leaveall) {
        this.leaveall = leaveall;
    }
}
