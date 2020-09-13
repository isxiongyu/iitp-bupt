package cn.edu.bupt.user.service;

import cn.edu.bupt.exception.ActiveException;
import cn.edu.bupt.exception.LoginException;
import cn.edu.bupt.exception.RegisterException;
import cn.edu.bupt.exception.SystemException;
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
    void register(User user) throws RegisterException;

    /**
     * 注册成功发送激活邮件
     */
    void sendMail(User user) throws SystemException;
    /**
     * 用户激活
     * @param code 激活码
     */
    void active(String code) throws ActiveException;

    void login(String name, String password) throws LoginException;

}
