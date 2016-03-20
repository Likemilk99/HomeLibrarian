package frontend.elements.tableusers;

import com.vaadin.server.Sizeable;
import com.vaadin.ui.*;
import frontend.elements.components.RegistrationWin;
import mail.Sender;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;

/**
 * Created by likemilk on 16.03.2016.
 */
public class MenuMail extends VerticalLayout {
    private VerticalLayout menu;
    private Button button_send;
    private ComboBox box_mails;
    private TableUsers tableinstance;
    private static MenuMail instance;
    private Sender sender;

    public static MenuMail getInstance() {
        MenuMail localInstance = instance;
        if (localInstance == null) {
            synchronized (TableUsers.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new MenuMail();
                }
            }
        }
        return localInstance;
    }

    public MenuMail() {
        menu = new VerticalLayout();
        sender = new Sender();
        menu.setWidth(100, Sizeable.Unit.PERCENTAGE);
        menu.setMargin(true);
        menu.setSpacing(true);

        button_send = new Button("Send", event -> {
            ArrayList<String> mails = tableinstance.getMails();
            for (String obj : mails)
                sender.send(new BigInteger(130, new SecureRandom()).toString(32), obj);

        });
        button_send.setWidth(200, Sizeable.Unit.PIXELS);

        box_mails = new ComboBox(null);
        Object itemId = box_mails.addItem();
        box_mails.setItemCaption(itemId, "custom");
        box_mails.addItem("one");
        box_mails.addItem("two");
        box_mails.addItem("three");
        box_mails.setValue(itemId);
        box_mails.setNullSelectionAllowed(false);

        menu.addComponent(box_mails);
        menu.setComponentAlignment(box_mails, Alignment.TOP_CENTER);
        menu.addComponent(button_send);
        menu.setComponentAlignment(button_send, Alignment.TOP_CENTER);

        this.addComponent(menu);
        this.setComponentAlignment(menu, Alignment.TOP_CENTER);

        button_send.setStyleName("super-button");
        this.setStyleName("menulayout");
        this.setSizeFull();
    }

    public void setTable(TableUsers tableinstance) {
        this.tableinstance = tableinstance;
    }
}
