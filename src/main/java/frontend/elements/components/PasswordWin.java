package frontend.elements.components;

import DAO.Factory;
import DAO.InterfaceDao;
import DAO.UserDAO;
import Data.PasswordGenerator;
import Data.Users;
import com.vaadin.server.Page;
import com.vaadin.ui.*;
import mail.Sender;
import mail.TemplateMails;

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
        field_mail.setWidth(100, Unit.PERCENTAGE);
        field_mail.setResponsive(true);

        field_pass = new PasswordField("New password:");
        field_pass.setWidth(100, Unit.PERCENTAGE);
        field_pass.setResponsive(true);

        field_newPass = new PasswordField("Reenter new Password:");
        field_newPass.setWidth(100, Unit.PERCENTAGE);
        field_newPass.setResponsive(true);

        field_code = new TextField("Code:");
        field_code.setWidth(100, Unit.PERCENTAGE);
        field_code.setResponsive(true);

        but_send = new Button("Send code");
        but_change = new Button("Apply");

        menu = new HorizontalLayout(but_send, but_change);
       // menu.setMargin(true);
        content = new VerticalLayout( field_mail,
                                        field_pass,
                                        field_newPass,
                                        field_code,
                                        menu);

        content.setComponentAlignment(field_mail, Alignment.MIDDLE_CENTER);
        content.setComponentAlignment(field_pass, Alignment.MIDDLE_CENTER);
        content.setComponentAlignment(field_newPass, Alignment.MIDDLE_CENTER);
        content.setComponentAlignment(field_code, Alignment.MIDDLE_CENTER);
        content.setComponentAlignment(menu, Alignment.MIDDLE_CENTER);


        center();
        setDraggable(false);
        setModal(true);
        setResizable(false);


        content.setStyleName("loginlayout");
        content.setWidth(320, Unit.PIXELS);
        content.setMargin(true);
        setContent(content);

        but_send.addClickListener(e -> {
            if (!field_mail.getValue().equals("")) {
                Factory F = new Factory();
                InterfaceDao dao = F.getDAO(UserDAO.class);
                Users user = new Users();
                try {
                    user = (Users) dao.getElById(field_mail.getValue());

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                Sender sender = new Sender();
                str_code = PasswordGenerator.generate(0, 20);
                sender.send(TemplateMails.MAIL_FORGOT, str_code, user.getEmail());
                sended = true;
            } else {
                new Notification("Error", "Incorrect parameters!",
                        Notification.TYPE_TRAY_NOTIFICATION, true)
                        .show(Page.getCurrent());
            }
        });

        but_change.addClickListener(e -> {
            if(sended == true && field_pass.getValue().equals(field_newPass.getValue()) && field_code.getValue().equals(str_code)) {
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
}
