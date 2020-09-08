package cn.edu.bupt.user.model;

import cn.edu.bupt.enums.UserStatus;

import java.util.Date;

/**
 * ClassName: User
 * Description:
 * Create by xiongyu
 * Date: 2020/9/5 6:50 下午
 */
public class User {
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
    private UserStatus userStatus;
}
