package cn.edu.bupt.emailTemplate.helper;

import cn.edu.bupt.emailTemplate.dao.EmailTemplateDo;
import cn.edu.bupt.emailTemplate.model.EmailTemplate;
import cn.edu.bupt.enums.EmailType;
import cn.edu.bupt.enums.UserStatus;
import cn.edu.bupt.user.dao.UserDo;
import cn.edu.bupt.user.model.User;
import org.springframework.stereotype.Component;

/**
 * ClassName: UserHelper
 * Description:
 * Create by xiongyu
 * Date: 2020/9/9 1:22 下午
 */

@Component
public class EmailHelper {
    public EmailTemplate convert2Model(EmailTemplateDo emailTemplateDo) {
        if (emailTemplateDo == null) return null;
        EmailTemplate emailTemplate = new EmailTemplate();
        emailTemplate.setContent(emailTemplateDo.getContent());
        emailTemplate.setFroms(emailTemplateDo.getFroms());
        emailTemplate.setId(emailTemplateDo.getId());
        emailTemplate.setPassword(emailTemplateDo.getPassword());
        emailTemplate.setSubject(emailTemplateDo.getSubject());
        emailTemplate.setSmtp(emailTemplateDo.getSmtp());
        emailTemplate.setUsername(emailTemplateDo.getUsername());
        emailTemplate.setEmailType(EmailType.getByCode(emailTemplateDo.getEmailType()));
        return emailTemplate;
    }
    public EmailTemplateDo convert2Do(EmailTemplate emailTemplate) {
        if (emailTemplate == null) return null;
        EmailTemplateDo emailTemplateDo = new EmailTemplateDo();
        emailTemplateDo.setContent(emailTemplate.getContent());
        emailTemplateDo.setFroms(emailTemplate.getFroms());
        emailTemplateDo.setId(emailTemplate.getId());
        emailTemplateDo.setPassword(emailTemplate.getPassword());
        emailTemplateDo.setSubject(emailTemplate.getSubject());
        emailTemplateDo.setSmtp(emailTemplate.getSmtp());
        emailTemplateDo.setUsername(emailTemplate.getUsername());
        emailTemplateDo.setEmailType(emailTemplate.getEmailType().getCode());
        return emailTemplateDo;
    }

}
