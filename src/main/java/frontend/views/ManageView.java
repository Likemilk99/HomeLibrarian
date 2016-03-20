package frontend.views;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import frontend.elements.components.HeaderLayout;
import frontend.elements.tableusers.MenuMail;
import frontend.elements.tableusers.MenuTable;
import frontend.elements.tableusers.TableUsers;

/**
 * Created by Александр on 13.03.2016.
 */
@Theme("mytheme")
public class ManageView extends CustomComponent implements View {

    public static final String NAME = "manage";

    AbsoluteLayout mainlayout;
    HeaderLayout header;
    HorizontalLayout body;

    private TableUsers table;
    MenuTable menu;
    private HorizontalSplitPanel hSplitBar;

    private static ManageView instance;

    public static  ManageView getInstance() {
        ManageView localInstance = instance;
        if (localInstance == null) {
            synchronized (TableUsers.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ManageView();
                }
            }
        }
        return localInstance;
    }

    private ManageView() {
        mainlayout = new AbsoluteLayout();
        header = new HeaderLayout();
        body = new HorizontalLayout();
        table = TableUsers.getInstance();
        hSplitBar = new HorizontalSplitPanel();
        hSplitBar.setSplitPosition(320, Unit.PIXELS);
        hSplitBar.setMaxSplitPosition(320, Unit.PIXELS);
        hSplitBar.setMinSplitPosition(20, Unit.PIXELS);
        menu = MenuTable.getInstance();
        // Styles
        mainlayout.setStyleName("v-main-body");

        // Add components
        hSplitBar.setFirstComponent(menu);
        hSplitBar.setSecondComponent(table);
        body.addComponent(hSplitBar);
        mainlayout.addComponent(header, "left: 0px; top: 0px;");
        mainlayout.addComponent(body, "left: 0px; top: 10%;");

        //header.setSizeFull();
        body.setSizeFull();
        mainlayout.setSizeFull();

        ///TESTING MENU////
        menu.setTable(table);
        ///////////////////

        setSizeFull();
        setCompositionRoot(mainlayout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // Get the user name from the session
        //String username = String.valueOf(getSession().getAttribute("user"));
        //String status = String.valueOf(getSession().getAttribute("status"));
    }
}
