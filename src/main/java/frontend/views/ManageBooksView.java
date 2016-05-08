package frontend.views;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.*;
import frontend.elements.components.HeaderLayout;
import frontend.elements.tablebooks.MenuTableBooks;
import frontend.elements.tablebooks.TableBooks;
import frontend.elements.tableusers.MenuTable;

/**
 * Created by Александр on 25.04.2016.
 */
@Theme("mytheme")
public class ManageBooksView extends CustomComponent implements View {
    public static final String NAME = "managebooks";

    private AbsoluteLayout mainlayout;
    private HeaderLayout header;
    private HorizontalLayout body;

    private TableBooks table;

    private VerticalLayout tablecontent = new VerticalLayout();

    private MenuTableBooks menu;
    private HorizontalSplitPanel hSplitBar;

    private static ManageBooksView instance;

    public static  ManageBooksView getInstance() {
        ManageBooksView localInstance = instance;
        if (localInstance == null) {
            synchronized (TableBooks.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ManageBooksView();
                }
            }
        }
        return localInstance;
    }

    private ManageBooksView() {
        mainlayout = new AbsoluteLayout();

        body = new HorizontalLayout();
        table = TableBooks.getInstance();

        tablecontent.addComponent(table);
        tablecontent.setSizeFull();
        tablecontent.setExpandRatio(table, 95);

        hSplitBar = new HorizontalSplitPanel();
        hSplitBar.setSplitPosition(320, Sizeable.Unit.PIXELS);
        hSplitBar.setMaxSplitPosition(320, Sizeable.Unit.PIXELS);
        hSplitBar.setMinSplitPosition(20, Sizeable.Unit.PIXELS);
        menu = MenuTableBooks.getInstance();
        // Styles
        mainlayout.setStyleName("v-main-body");
        //body.setStyleName("bodylayout");

        // Add components
        hSplitBar.setFirstComponent(menu);
        hSplitBar.setSecondComponent(tablecontent);
        body.addComponent(hSplitBar);

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
        String status = String.valueOf(getSession().getAttribute("status"));

        mainlayout.removeAllComponents();

        header = new HeaderLayout(status);
        mainlayout.addComponent(body, "left: 0px; top: 10%;");
        mainlayout.addComponent(header, "left: 0px; top: 0px; bottom: 90%;");
    }
}
