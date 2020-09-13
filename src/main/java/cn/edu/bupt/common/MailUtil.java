package cn.edu.bupt.common;

import cn.edu.bupt.common.model.Mail;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

/**
 * ClassName: MailUtil
 * Description:
 * Create by xiongyu
 * Date: 2020/9/11 4:47 下午
 */
public class MailUtil {
    public static Session creatSession(String smtp, final String username, final String password) {
        Properties props = new Properties();
        props.setProperty("mail.host", smtp);
        props.setProperty("mail.smtp.auth", "true");
        Authenticator authenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };
        Session session = Session.getInstance(props, authenticator);
        return session;
    }

    public static void send(Mail mail) throws MessagingException {
        MimeMultipart list = mail.getList();
        MimeMessage message = mail.getMessage();
        message.setContent(list);
        Transport.send(message);
    }
}
