package cn.edu.bupt.emailTemplate.dao;

import cn.edu.bupt.emailTemplate.model.EmailTemplate;

import java.util.List;

/**
 * ClassName: EmailTemplateDao
 * Description:
 * Create by xiongyu
 * Date: 2020/9/11 4:23 下午
 */
public interface EmailTemplateDao {
    List<EmailTemplate> queryEmailTemplate();
}
