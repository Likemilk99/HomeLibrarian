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
public class MenuFind extends VerticalLayout {
    private VerticalLayout menu;

    private TableUsers tableinstance;
    private static MenuFind instance;
    private Sender sender;

    public static MenuFind getInstance() {
        MenuFind localInstance = instance;
        if (localInstance == null) {
            synchronized (TableUsers.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new MenuFind();
                }
            }
        }
        return localInstance;
    }

    public MenuFind() {
        menu = new VerticalLayout();
        sender = new Sender();
        menu.setWidth(100, Sizeable.Unit.PERCENTAGE);
        menu.setMargin(true);
        menu.setSpacing(true);

        this.addComponent(menu);
        this.setComponentAlignment(menu, Alignment.TOP_CENTER);

        this.setStyleName("menulayout");
        this.setSizeFull();
    }

    public void setTable(TableUsers tableinstance) {
        this.tableinstance = tableinstance;
    }
}
