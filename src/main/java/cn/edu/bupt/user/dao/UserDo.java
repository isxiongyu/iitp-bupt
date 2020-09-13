package cn.edu.bupt.user.dao;

import cn.edu.bupt.enums.UserStatus;

import java.util.Date;

/**
 * ClassName: UserDo
 * Description:
 * Create by xiongyu
 * Date: 2020/9/9 1:17 下午
 */
public class UserDo {
    private String userId;
    private String username;
    private String password;
    private String email;
    private String phone;
    /**
     * 点赞次数
     */
    private int star;
    /**
     * 举报次数
     */
    private int report;
    private String userDescription;
    private String sex;
    /**
     * 入学年份
     */
    private Date year;
    /**
     * 公寓号
     */
    private int department;

    /**
     * 用户状态（未激活，已激活，黑名单）
     */
    private String userStatus;

    /**
     * 激活码
     */
    private String code;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int getReport() {
        return report;
    }

    public void setReport(int report) {
        this.report = report;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
