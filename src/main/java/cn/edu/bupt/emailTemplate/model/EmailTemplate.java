package cn.edu.bupt.emailTemplate.model;

import cn.edu.bupt.enums.EmailType;

/**
 * ClassName: EmailTemplate
 * Description:
 * Create by xiongyu
 * Date: 2020/9/11 2:51 下午
 */
public class EmailTemplate {
    private int id;
    private String smtp;
    private String username;
    private String password;
    private String froms;
    private String subject;
    private String content;
    private EmailType emailType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSmtp() {
        return smtp;
    }

    public void setSmtp(String smtp) {
        this.smtp = smtp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFroms() {
        return froms;
    }

    public void setFroms(String froms) {
        this.froms = froms;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public EmailType getEmailType() {
        return emailType;
    }

    public void setEmailType(EmailType emailType) {
        this.emailType = emailType;
    }
}
