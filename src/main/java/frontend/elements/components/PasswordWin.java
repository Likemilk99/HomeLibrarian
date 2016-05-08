package frontend.elements.components;

import DAO.Factory;
import DAO.InterfaceDao;
import DAO.UserDAO;
import Data.PasswordGenerator;
import Data.Users;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.server.Page;
import com.vaadin.ui.*;
import mail.Sender;
import mail.TemplateMails;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.sql.SQLException;

/**
 * Created by likemilk on 07.05.2016.
 */
public class PasswordWin extends Window {
    private TextField field_mail;
    private TextField field_code;
    private PasswordField field_pass;
    private PasswordField field_newPass;

    private Button but_send;
    private Button but_checkcode;
    private Button but_change;

    private VerticalLayout content;
    private HorizontalLayout menu;

    private boolean sended;
    private String str_code;

    public PasswordWin() {
        sended = false;
        str_code = "fykufkyufkuyvhjuih78tugyhvjyfu6dtfgiokjvht6t7uighj";
        setCaption("Forgot password");
      //  setWidth(410, Unit.PIXELS);
        //setHeight(600, Unit.PIXELS);

        field_mail = new TextField("Mail:");
        field_mail.addValidator(new emailValidator());
        field_mail.setWidth(100, Unit.PERCENTAGE);
        field_mail.setResponsive(true);

        field_pass = new PasswordField("New password:");
        field_pass.setWidth(100, Unit.PERCENTAGE);
        field_pass.setResponsive(true);
        field_pass.setVisible(false);

        field_newPass = new PasswordField("Reenter new Password:");
        field_newPass.addValidator(new passwordValidator());
        field_newPass.setWidth(100, Unit.PERCENTAGE);
        field_newPass.setResponsive(true);
        field_newPass.setVisible(false);

        field_code = new TextField("Code:");
        field_code.addValidator(new emptyValidator());
        field_code.setWidth(100, Unit.PERCENTAGE);
        field_code.setResponsive(true);
        field_code.setVisible(false);

        but_send = new Button("Send Code");
        but_checkcode = new Button("Check Code");
        but_change = new Button("Apply");

        but_send.setStyleName("main-button");
        but_checkcode.setStyleName("super-button");
        but_change.setStyleName("super-button");
        but_checkcode.setVisible(false);
        but_change.setVisible(false);
        but_send.setEnabled(false);
        but_checkcode.setEnabled(false);
        but_change.setEnabled(false);

        content = new VerticalLayout( field_mail,
                                        but_send,
                                        field_code,
                                        but_checkcode,
                                        field_pass,
                                        field_newPass,
                                        but_change);

        content.setComponentAlignment(field_mail, Alignment.MIDDLE_CENTER);
        content.setComponentAlignment(field_pass, Alignment.MIDDLE_CENTER);
        content.setComponentAlignment(field_newPass, Alignment.MIDDLE_CENTER);
        content.setComponentAlignment(field_code, Alignment.MIDDLE_CENTER);
        content.setComponentAlignment(but_checkcode, Alignment.MIDDLE_CENTER);
        content.setComponentAlignment(but_change, Alignment.MIDDLE_CENTER);

        center();
        setDraggable(false);
        setModal(true);
        setResizable(false);

        content.setStyleName("loginlayout");
        content.setWidth(320, Unit.PIXELS);
        content.setMargin(true);
        content.setSpacing(true);
        setContent(content);

        but_send.addClickListener(e -> {
            if (field_mail.isValid()) {
                Factory F = new Factory();
                InterfaceDao dao = F.getDAO(UserDAO.class);
                Users user = new Users();
                try {
                    user = (Users) dao.getElById(field_mail.getValue());

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                Sender sender = new Sender();
                str_code = PasswordGenerator.generate(10, 20);
                sender.send(TemplateMails.MAIL_FORGOT, str_code, user.getEmail());
                sended = true;
                but_change.setEnabled(true);
                but_send.setCaption("Send Again");
                field_code.setVisible(true);
                but_checkcode.setVisible(true);
            } else {
                new Notification("Error", "Incorrect parameters!",
                        Notification.TYPE_TRAY_NOTIFICATION, true)
                        .show(Page.getCurrent());
            }
        });

        but_checkcode.addClickListener(e -> {
            if(field_code.getValue().equals(str_code)) {
                field_mail.setVisible(false);
                but_send.setVisible(false);
                field_code.setVisible(false);
                but_checkcode.setVisible(false);
                field_pass.setVisible(true);
                field_newPass.setVisible(true);
                but_change.setVisible(true);
            }
        });

        but_change.addClickListener(e -> {
            if(field_pass.getValue().equals(field_newPass.getValue())) {
                Factory F = new Factory();
                InterfaceDao dao = F.getDAO(UserDAO.class);
                Users user = new Users();
                try {
                    user = (Users) dao.getElById(field_mail.getValue());
                    user.setPassword(field_pass.getValue());
                    dao.updateEl(user);
                    new Notification("Success","Password changed!",
                            Notification.TYPE_TRAY_NOTIFICATION, true)
                            .show(Page.getCurrent());

                    this.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                sended = false;
            }
            else {
                new Notification("Error","Incorrect parameters!",
                        Notification.TYPE_TRAY_NOTIFICATION, true)
                        .show(Page.getCurrent());
            }
        });
    }

    private final class passwordValidator extends
            AbstractValidator<String> {

        public passwordValidator() {
            super("Password does not match the confirm password!");
        }

        @Override
        protected boolean isValidValue(String value) {
            if(field_newPass.isEmpty()) {
                but_change.setEnabled(false);
                return false;
            }

            if (field_pass.getValue().matches(field_newPass.getValue())) {
                but_change.setEnabled(true);
                return true;
            }
            but_change.setEnabled(false);
            return false;
        }

        @Override
        public Class<String> getType() {
            return String.class;
        }
    }

    private final class emptyValidator extends
            AbstractValidator<String> {

        public emptyValidator() {
            super("Cannot be empty");
        }

        @Override
        protected boolean isValidValue(String value) {
            if(value.isEmpty()) {
                but_checkcode.setEnabled(false);
                return false;
            }
            but_checkcode.setEnabled(true);
            return true;
        }

        @Override
        public Class<String> getType() {
            return String.class;
        }
    }

    private final class emailValidator extends
            AbstractValidator<String> {

        public emailValidator() {
            super("Must be an email address");
        }

        @Override
        protected boolean isValidValue(String value) {
            if(value.isEmpty()) {
                but_send.setEnabled(false);
                return true;
            }

            if(!isValidEmailAddress(value)) {
                but_send.setEnabled(false);
                return false;
            }

            but_send.setEnabled(true);
            return true;
        }

        @Override
        public Class<String> getType() {
            return String.class;
        }
    }

    private static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
}
