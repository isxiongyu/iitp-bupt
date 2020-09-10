package cn.edu.bupt.user.model;

import cn.edu.bupt.enums.UserStatus;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * ClassName: User
 * Description:
 * Create by xiongyu
 * Date: 2020/9/5 6:50 下午
 */
public class User {
    private String userId;
    @NotNull(message="用户名不能为空")
    @Size(min=3, max=10, message="用户名必须在{min}-{max}内")
    private String username;
    private String password;
    @Pattern(regexp="^[a-z0-9A-Z]+[- | a-z0-9A-Z . _]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-z]{2,}$", message="邮箱格式不正确")
    private String email;
    @Pattern(regexp="^[1][3,4,5,7,8,9]\\d{9}$", message="手机号格式不正确")
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
    @NotNull(message="请选择性别")
    private String sex;
    /**
     * 入学年份
     */
    @NotNull(message="请选择性别")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date year;
    /**
     * 公寓号
     */
    private int department;
    /**
     * 用户状态（未激活，已激活，黑名单）
     */
    private UserStatus userStatus;

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

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", star=" + star +
                ", report=" + report +
                ", userDescription='" + userDescription + '\'' +
                ", sex='" + sex + '\'' +
                ", year=" + year +
                ", department=" + department +
                ", userStatus=" + userStatus +
                '}';
    }
}
