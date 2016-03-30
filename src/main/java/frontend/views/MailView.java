package frontend.views;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import frontend.elements.components.HeaderLayout;
import frontend.elements.tableusers.MenuTable;
import frontend.elements.tableusers.TableUsers;

/**
 * Created by Александр on 13.03.2016.
 */
@Theme("mytheme")
public class MailView extends CustomComponent implements View {

    public static final String NAME = "mail";

    private AbsoluteLayout mainlayout;
    private HeaderLayout header;
    private HorizontalLayout body;
    private TableUsers table;
   // private MenuMail menu;

    private HorizontalSplitPanel hSplitBar;

    private static MailView instance;

    public static  MailView getInstance() {
        MailView localInstance = instance;
        if (localInstance == null) {
            synchronized (TableUsers.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new MailView();
                }
            }
        }
        return localInstance;
    }

    private  MailView() {
        mainlayout = new AbsoluteLayout();
        header = new HeaderLayout();
        body = new HorizontalLayout();
        table = TableUsers.getInstance();
        hSplitBar = new HorizontalSplitPanel();
        hSplitBar.setSplitPosition(320, Unit.PIXELS);
        hSplitBar.setMaxSplitPosition(320, Unit.PIXELS);
        hSplitBar.setMinSplitPosition(20, Unit.PIXELS);
       // menu = MenuMail.getInstance();
        // Styles
        mainlayout.setStyleName("v-main-body");
        body.setStyleName("bodylayout");

        // Add components
       // hSplitBar.setFirstComponent(menu);
        hSplitBar.setSecondComponent(table);
        body.addComponent(hSplitBar);
        mainlayout.addComponent(header, "left: 0px; top: 0px;");
        mainlayout.addComponent(body, "left: 0px; top: 10%;");

        //header.setSizeFull();
        body.setSizeFull();
        mainlayout.setSizeFull();

        ///TESTING MENU////
       // menu.setTable(table);
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
