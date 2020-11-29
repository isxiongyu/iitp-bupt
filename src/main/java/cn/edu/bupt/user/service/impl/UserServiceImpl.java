package cn.edu.bupt.user.service.impl;

import cn.edu.bupt.common.CommonHelper;
import cn.edu.bupt.enums.EmailType;
import cn.edu.bupt.enums.UserStatus;
import cn.edu.bupt.exception.user.*;
import cn.edu.bupt.goods.dao.repository.GoodsRepository;
import cn.edu.bupt.goods.model.Goods;
import cn.edu.bupt.user.dao.repository.UserRepository;
import cn.edu.bupt.user.model.User;
import cn.edu.bupt.user.service.UserService;
import com.alibaba.druid.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ClassName: UserService
 * Description:
 * Create by xiongyu
 * Date: 2020/9/10 12:27 上午
 */

@Service
public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private ExecutorService executor = Executors.newFixedThreadPool(6);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private CommonHelper commonHelper;

    /**
     * 用户注册service
     * @param user 用户
     * @throws RegisterException
     */
    @Override
    public void register(User user) throws RegisterException, SystemException {
        user.setUserId(commonHelper.getUUID());
        user.setUserStatus(UserStatus.UN_ACTIVE);
        try {
            User findUser = userRepository.queryBy3Unique(user.getUsername(), user.getPhone(), user.getEmail());
            if (findUser != null) {
                logger.info("user = " +  user + "  用户名已存在，或者邮箱、手机号已被注册");
                throw new RegisterException("用户名已存在，或者邮箱、手机号已被注册");
            }
            int res = userRepository.insertUser(user);
            if (res != 1) {
                logger.info("user = " +  user + "  注册失败");
                throw new RegisterException("注册失败");
            }
        } catch (RegisterException re) {
            throw re;
        } catch (Exception e) {
            logger.error("系统性异常: ", e);
            throw new SystemException();
        }
    }

    @Override
    public void active(String code) throws ActiveException, SystemException {
        try {
            User user = userRepository.queryByCode(code);
            if (user == null) {
                logger.info("code = " +  code + "激活码无效");
                throw new ActiveException("激活码无效");
            }
            if (user.getUserStatus() != UserStatus.UN_ACTIVE) {
                logger.info("code = " +  code + "用户已激活或者用户被冻结");
                throw new ActiveException("用户已激活或者用户被冻结");
            }
            userRepository.updateStatusByCode(code, "active");
        } catch (ActiveException ae) {
            throw ae;
        } catch (Exception e) {
            logger.error("系统性异常: ", e);
            throw new SystemException();
        }
    }

    @Override
    public User login(String name, String password) throws LoginException, SystemException {
        try {
            User user = userRepository.queryByUnique(name);
            if (user == null) {
                logger.info("用户名name = {} 不存在", name);
                throw new LoginException("用户名不存在");
            }
            if (!user.getPassword().equals(password)) {
                logger.info("用户名name = {}, 密码password = {}, 密码错误", name, password);
                throw new LoginException("密码错误");
            }
            return user;
        } catch (LoginException e1) {
            throw e1;
        } catch (Exception e) {
            logger.error("系统性异常: ", e);
            throw new SystemException();
        }
    }

    @Override
    public void modPassword(String email, String newPassword, String confirmPassword) throws ModPasswordException, SystemException {
        try {
            if (newPassword == null || confirmPassword == null) {
                throw new ModPasswordException("请输入新密码或者确认密码");
            }
            if (!StringUtils.equals(newPassword, confirmPassword)) {
                throw new ModPasswordException("两次输入的密码不一致");
            }
            userRepository.updatePasswordByEmail(email, newPassword);
        } catch (ModPasswordException mpe) {
            throw mpe;
        } catch (Exception e) {
            logger.error("系统性异常: ", e);
            throw new SystemException();
        }
    }

    @Override
    public void sendEmailVerifyCode(String email) throws ModPasswordException, SystemException {
        try {
            User user = userRepository.queryByEmail(email);
            if (user == null) {
                logger.info("输入邮箱" + email +"不存在");
                throw new ModPasswordException("输入邮箱不存在");
            }
            user.setCode(commonHelper.getUUID().substring(0, 6));
            userRepository.updateUser(user);
            executor.execute(() -> {
                try {
                    commonHelper.sendMail(user, EmailType.FORGET_PASSWORD);
                } catch (SystemException e) {
                    logger.error("发送验证码邮件失败, 失败信息：" + e.getMessage());
                }
            });
        } catch (ModPasswordException mpe) {
            throw mpe;
        } catch (Exception e) {
            logger.error("系统性异常: ", e);
            throw new SystemException();
        }
    }

    @Override
    public void verifyCode(String email, String code) throws ModPasswordException, SystemException {
        try {
            User user = userRepository.queryByEmail(email);
            if (user == null) {
                throw new ModPasswordException("邮箱不存在");
            }
            if (!StringUtils.equals(user.getCode(), code)) {
                throw new ModPasswordException("验证码错误");
            }
            if (user.getCode() == null) {
                throw new ModPasswordException("请先获取验证码");
            }
            user.setCode(null);
            userRepository.updateUser(user);
        } catch (ModPasswordException mpe) {
            throw mpe;
        } catch (Exception e) {
            logger.error("系统性异常: ", e);
            throw new SystemException();
        }
    }

    @Override
    public void addGoods(Goods goods) throws SystemException, AddGoodsException {
        try {
            if (goods == null) {
                throw new AddGoodsException("添加的书籍为null");
            }
            goodsRepository.insertGoods(goods);
        } catch (AddGoodsException age) {
            throw age;
        } catch (Exception e) {
            logger.error("系统性异常: ", e);
            throw new SystemException();
        }
    }
}
