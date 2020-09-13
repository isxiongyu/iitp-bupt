package cn.edu.bupt.common.model;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * ClassName: Mail
 * Description:
 * Create by xiongyu
 * Date: 2020/9/11 4:53 下午
 */
public class Mail {
    private MimeMultipart list;
    private MimeMessage message;

    public Mail(Session session, String from, String to, String subject, String content) throws MessagingException {
        this.message = new MimeMessage(session);
        this.list = new MimeMultipart();
        MimeBodyPart part = new MimeBodyPart();
        part.setContent(content, "text/html;charset=utf-8");
        this.list.addBodyPart(part);
        this.message.setFrom(new InternetAddress(from));
        this.message.setSubject(subject, "utf-8");
        this.message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
    }

    public MimeMessage getMessage() {
        return this.message;
    }

    public MimeMultipart getList() {
        return this.list;
    }
}
