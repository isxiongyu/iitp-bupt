package cn.edu.bupt.user.service;

import cn.edu.bupt.enums.EmailType;
import cn.edu.bupt.exception.user.*;
import cn.edu.bupt.user.model.User;

/**
 * ClassName: UserService
 * Description:
 * Create by xiongyu
 * Date: 2020/9/11 2:40 下午
 */
public interface UserService {
    /**
     * 用户注册
     * @param user
     */
    void register(User user) throws RegisterException, SystemException;

    /**
     * 注册成功发送激活邮件
     */
    void sendMail(User user, EmailType emailType) throws SystemException;
    /**
     * 用户激活
     * @param code 激活码
     */
    void active(String code) throws ActiveException, SystemException;

    /**
     * 用户登录
     * @param name 用户名/邮箱/电话
     * @param password 密码
     * @return
     * @throws LoginException
     */
    User login(String name, String password) throws LoginException, SystemException;

    User modPassword(User user, String oldPassword, String newPassword) throws ModPasswordException, SystemException;

    void sendEmailVerifyCode(String email) throws ForgetPasswordException, SystemException;
}
