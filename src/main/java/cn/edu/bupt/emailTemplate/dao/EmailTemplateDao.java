package cn.edu.bupt.emailTemplate.dao;

import cn.edu.bupt.emailTemplate.model.EmailTemplate;

/**
 * ClassName: EmailTemplateDao
 * Description:
 * Create by xiongyu
 * Date: 2020/9/11 4:23 下午
 */
public interface EmailTemplateDao {
    EmailTemplateDo queryEmailTemplateByType(String type);
}

