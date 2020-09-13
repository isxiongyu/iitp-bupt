package cn.edu.bupt.user.service.impl;

import cn.edu.bupt.common.CommonHelper;
import cn.edu.bupt.common.MailUtil;
import cn.edu.bupt.common.model.Mail;
import cn.edu.bupt.emailTemplate.dao.repository.EmailTemplateRepository;
import cn.edu.bupt.emailTemplate.model.EmailTemplate;
import cn.edu.bupt.enums.UserStatus;
import cn.edu.bupt.exception.user.*;
import cn.edu.bupt.user.dao.repository.UserRepository;
import cn.edu.bupt.user.model.User;
import cn.edu.bupt.user.service.UserService;
import com.alibaba.druid.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.Session;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

/**
 * ClassName: UserService
 * Description:
 * Create by xiongyu
 * Date: 2020/9/10 12:27 上午
 */

@Service
public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommonHelper commonHelper;

    @Autowired
    private EmailTemplateRepository emailTemplateRepository;

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
            logger.error("系统性异常：{}", Arrays.toString(e.getStackTrace()));
            throw new SystemException();
        }
    }

    @Override
    public void sendMail(User user) throws SystemException {
        List<EmailTemplate> emailTemplates = emailTemplateRepository.queryEmailTemplate();
        if (emailTemplates == null) {
            throw new SystemException("查询邮件模板为空");
        }
        EmailTemplate emailTemplate = emailTemplates.get(0);
        String smtp = emailTemplate.getSmtp();
        String username = emailTemplate.getUsername();
        String password = emailTemplate.getPassword();
        String from = emailTemplate.getFrom();
        String subject = emailTemplate.getSubject();
        String to = user.getEmail();
        String content = emailTemplate.getContent();
        content = MessageFormat.format(content, user.getCode());
        Session session = MailUtil.creatSession(smtp, username, password);
        try {
            Mail mail = new Mail(session, from, to, subject, content);
            MailUtil.send(mail);
        } catch (MessagingException e) {
            logger.error("user = " +  user + "发送邮件错误");
            throw new SystemException("发送邮件错误");
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
            logger.error("系统性异常：{}", Arrays.toString(e.getStackTrace()));
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
            logger.error("系统性异常：{}", Arrays.toString(e.getStackTrace()));
            throw new SystemException();
        }
    }

    @Override
    public User modPassword(User user, String oldPassword, String newPassword) throws ModPasswordException, SystemException {
        if (StringUtils.equals(user.getPassword(), oldPassword)) {
            logger.info("用户User = {}, oldPassword = {}, 旧密码输入错误", user, oldPassword);
            throw new ModPasswordException("旧密码输入错误");
        }
        try {
            userRepository.updatePasswordByEmail(user.getEmail(), newPassword);
            user.setPassword(newPassword);
            return user;
        } catch (Exception e) {
            logger.error("系统性异常：{}", Arrays.toString(e.getStackTrace()));
            throw new SystemException();
        }
    }
}
