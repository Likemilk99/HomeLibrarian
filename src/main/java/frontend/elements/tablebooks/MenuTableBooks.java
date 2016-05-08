package frontend.elements.tablebooks;

import DAO.BookDAO;
import DAO.Factory;
import DAO.InterfaceDao;
import com.vaadin.ui.*;
import frontend.elements.components.BookWin;
import mail.Sender;

import java.sql.SQLException;

/**
 * Created by Александр on 25.04.2016.
 */
public class MenuTableBooks extends VerticalLayout {
    private VerticalLayout menu;
    private Button button_delete;
    private Button button_block;
    private Button button_add;
    private TableBooks tableinstance;
    private static MenuTableBooks instance;
    private Sender sender;

    public static  MenuTableBooks getInstance() {
        MenuTableBooks localInstance = instance;
        if (localInstance == null) {
            synchronized (TableBooks.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new MenuTableBooks();
                }
            }
        }
        return localInstance;
    }

    public MenuTableBooks() {
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
            BookWin subWindow = new BookWin();
            // Open it in the UI
            getUI().addWindow(subWindow);
            subWindow.addListener((Window.CloseEvent e) -> {
                //tableinstance.addRow(subWindow.getBook());

                //Factory factory = new Factory();
                //InterfaceDao bookInterface = factory.getDAO(BookDAO.class);
                //try {
                    //bookInterface.addEl(subWindow.getBook());
                //} catch(SQLException exp) {

                //}

            });
        });

        button_add.setWidth(200, Unit.PIXELS);

        //button_block = new Button("Block", event -> {
//
        //});
        //button_block.setWidth(200, Unit.PIXELS);

        menu.addComponent(button_add);
        menu.setComponentAlignment(button_add, Alignment.TOP_CENTER);
        //menu.addComponent(button_block);
        //menu.setComponentAlignment(button_block, Alignment.TOP_CENTER);
        menu.addComponent(button_delete);
        menu.setComponentAlignment(button_delete, Alignment.TOP_CENTER);

        this.addComponent(menu);
        this.setComponentAlignment(menu, Alignment.TOP_CENTER);

        button_add.setStyleName("super-button");

        this.setStyleName("menulayout");
        this.setSizeFull();
    }

    public void setTable(TableBooks tableinstance) {
        this.tableinstance = tableinstance;
    }

}
