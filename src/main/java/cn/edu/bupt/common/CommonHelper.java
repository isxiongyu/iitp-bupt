package cn.edu.bupt.common;

import cn.edu.bupt.common.model.Mail;
import cn.edu.bupt.emailTemplate.dao.repository.EmailTemplateRepository;
import cn.edu.bupt.emailTemplate.model.EmailTemplate;
import cn.edu.bupt.enums.EmailType;
import cn.edu.bupt.exception.user.SystemException;
import cn.edu.bupt.user.model.User;
import com.alibaba.fastjson.JSON;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.Session;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * ClassName: CommonHelper
 * Description:
 * Create by xiongyu
 * Date: 2020/9/10 1:08 上午
 */

@Component
public class CommonHelper {

    Logger logger = LoggerFactory.getLogger(CommonHelper.class);

    @Autowired
    private EmailTemplateRepository emailTemplateRepository;

    public String getUUID() {
        return UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
    }

    /**
     * 用于将参数map转换成对应的bean
     * @param map 参数map
     * @param clazz 参数类型class对象
     * @param <T> 参数类型
     * @return
     * @throws SystemException
     */
    public <T> T toBean(Map<String, ?> map, Class<T> clazz) throws SystemException {
        try {
            T bean = clazz.newInstance();
            ConvertUtils.register(new DateLocaleConverter(), Date.class);
            BeanUtils.populate(bean, map);
            return bean;
        } catch (Exception e) {
            logger.error("注册属性填充错误: " + JSON.toJSONString(map));
            throw new SystemException("属性填充错误");
        }
    }

    public void sendMail(User user, EmailType emailType) throws SystemException {
        try {
            EmailTemplate emailTemplate = emailTemplateRepository.queryEmailTemplateByType(emailType);
            if (emailTemplate == null) {
                logger.error("emailType = {}, 查询邮件模板为空", emailType.toString());
                throw new SystemException("查询邮件模板为空");
            }
            String smtp = emailTemplate.getSmtp();
            String username = emailTemplate.getUsername();
            String password = emailTemplate.getPassword();
            String from = emailTemplate.getFroms();
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
                throw new SystemException("发送邮件错误 e = " + Arrays.toString(e.getStackTrace()));
            }
        } catch (SystemException se) {
            throw se;
        } catch (Exception e) {
            logger.error("系统异常，user = {}, emailType = {}", user.toString(), emailType.toString());
            throw new SystemException();
        }
    }

}
