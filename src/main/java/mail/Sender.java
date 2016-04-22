package mail;

import com.vaadin.server.Page;
import com.vaadin.ui.Notification;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Класс для завершения пегистрации.
 * Тело письма статично, кроме кода активации.
 * В конструкторе надо ввести логин/пароль почты-отправителя.
 */

public class Sender {
    private String username;
    private String password;
    private Properties props;
    private String text = "<p>Добрый день!" +
            " <p>Вы зарегистрировались на HomeLibrarian. " +
            "  " +
            "<p>Для завершения регистрации, пожалуйста, подтвердите ваш электронный адрес." +
            " " +
            "<p>Код подтверждения: <b>";

    public Sender() {
        this("smirnovivan944", "Iround!1");
    }

    /**
     *
     * @param username логин почты-отправителя
     * @param password пароль почты-отправителя
     */
    public Sender(String username, String password) {
        this.username = username;
        this.password = password;
        props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
    }

    /**
     * send - отправка письма
     * @param code код активации
     * @param toEmail адрес получателя
     */
    public void send(String code, String toEmail){
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            //от кого
            message.setFrom(new InternetAddress(username));
            //кому
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            //тема сообщения
            message.setSubject("Notification");
            //текст
            message.setText(TemplateMails.MAIL_REG + code + "</b>", "utf-8", "html");
            //отправляем сообщение
            Transport.send(message);


        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } finally {
            new Notification("Success","Mail sended! ",
                    Notification.TYPE_WARNING_MESSAGE, true)
                    .show(Page.getCurrent());
        }
    }

    public void send(TemplateMails mail, String toEmail){
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            //от кого
            message.setFrom(new InternetAddress(username));
            //кому
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            //тема сообщения
            message.setSubject("Notification");
            //текст
            message.setText(mail.getMail(), "utf-8", "html");
            //отправляем сообщение
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }finally {
            new Notification("Success","Mails sended! ",
                    Notification.TYPE_TRAY_NOTIFICATION, true)
                    .show(Page.getCurrent());
        }
    }
}
