package frontend.elements.tableusers;

import Data.Users;
import com.vaadin.server.LegacyApplication;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.*;
import com.vaadin.ui.Button;
import com.vaadin.ui.Window;
import frontend.elements.components.RegistrationWin;
import mail.Sender;

import java.awt.*;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * Created by likemilk on 16.03.2016.
 */
public class MenuTable extends VerticalLayout {
    private VerticalLayout menu;
    private Button button_delete;
    private Button button_block;
    private Button button_add;
    private TableUsers tableinstance;
    private static MenuTable instance;
    private Sender sender;

    public static  MenuTable getInstance() {
        MenuTable localInstance = instance;
        if (localInstance == null) {
            synchronized (TableUsers.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new MenuTable();
                }
            }
        }
        return localInstance;
    }

    public MenuTable() {
        menu = new VerticalLayout();
        sender = new Sender();
        menu.setWidth(100, Unit.PERCENTAGE);
        menu.setMargin(true);
        menu.setSpacing(true);
        button_delete = new Button("Delete", event -> {
            tableinstance.deleteSelectedRows();
        });
        button_delete.setWidth(200, Unit.PIXELS);

        button_add = new Button("Add", event -> {
            RegistrationWin subWindow = new RegistrationWin();
            // Open it in the UI
            getUI().addWindow(subWindow);
            subWindow.addListener(new Window.CloseListener() {
                // inline close-listener
                public void windowClose(Window.CloseEvent e) {
                    tableinstance.addRow(subWindow.getUser());
                }
            });
        });

        button_add.setWidth(200, Unit.PIXELS);

        button_block = new Button("Block", event -> {

        });
        button_block.setWidth(200, Unit.PIXELS);

        menu.addComponent(button_add);
        menu.setComponentAlignment(button_add, Alignment.TOP_CENTER);
        menu.addComponent(button_block);
        menu.setComponentAlignment(button_block, Alignment.TOP_CENTER);
        menu.addComponent(button_delete);
        menu.setComponentAlignment(button_delete, Alignment.TOP_CENTER);


        this.addComponent(menu);
        this.setComponentAlignment(menu, Alignment.TOP_CENTER);

        button_add.setStyleName("super-button");
        button_block.setStyleName("menu-button");
        button_delete.setStyleName("menu-button");
        this.setStyleName("menulayout");
        this.setSizeFull();
    }

    public void setTable(TableUsers tableinstance) {
        this.tableinstance = tableinstance;
    }


}
