package cn.edu.bupt.emailTemplate.dao.repository;

import cn.edu.bupt.emailTemplate.dao.EmailTemplateDao;
import cn.edu.bupt.emailTemplate.helper.EmailHelper;
import cn.edu.bupt.emailTemplate.model.EmailTemplate;
import cn.edu.bupt.enums.EmailType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ClassName: EmailTemplateRepository
 * Description:
 * Create by xiongyu
 * Date: 2020/9/11 4:29 下午
 */

@Component
public class EmailTemplateRepository {

    @Autowired
    private EmailTemplateDao emailTemplateDao;

    @Autowired
    private EmailHelper emailHelper;

    public EmailTemplate queryEmailTemplateByType(EmailType emailType) {
        return emailHelper.convert2Model(emailTemplateDao.queryEmailTemplateByType(emailType.getCode()));
    }
}
