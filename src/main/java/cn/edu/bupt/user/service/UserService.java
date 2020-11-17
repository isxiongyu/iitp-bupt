package cn.edu.bupt.user.service;

import cn.edu.bupt.enums.EmailType;
import cn.edu.bupt.exception.user.*;
import cn.edu.bupt.goods.model.Goods;
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

    /**
     * 修改密码
     * @param email 邮箱
     * @param newPassword 新密码
     * @param confirmPassword 确认密码
     * @throws ModPasswordException
     * @throws SystemException
     */
    void modPassword(String email, String newPassword, String confirmPassword) throws ModPasswordException, SystemException;

    /**
     * 发送修改密码验证码至邮箱
     * @param email 发送验证码邮箱
     * @throws ModPasswordException
     * @throws SystemException
     */
    void sendEmailVerifyCode(String email) throws ModPasswordException, SystemException;

    /**
     * 校验验证码
     * @param email
     * @param code
     * @throws ModPasswordException
     * @throws SystemException
     */
    void verifyCode(String email, String code) throws ModPasswordException, SystemException;

    void addGoods(Goods goods) throws SystemException, AddGoodsException;
}
