package frontend.views;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import frontend.elements.components.HeaderLayout;
import frontend.elements.gridbooks.HightLayout;
import frontend.elements.tableusers.MenuTable;
import frontend.elements.tableusers.TableUsers;

/**
 * Created by Александр on 13.03.2016.
 */
@Theme("mytheme")
public class ManageUsersView extends CustomComponent implements View {

    public static final String NAME = "manageusers";

    private AbsoluteLayout mainlayout;
    private HeaderLayout header;
    private HorizontalLayout body;

    private TableUsers table;
    private VerticalLayout tablecontent = new VerticalLayout();
 //   private HorizontalLayout buttons = new HorizontalLayout();
 //   private Button back = new Button("<");
 //   private Button forward = new Button(">");

    private MenuTable menu;
    private HorizontalSplitPanel hSplitBar;

    private static ManageUsersView instance;

    public static  ManageUsersView getInstance() {
        ManageUsersView localInstance = instance;
        if (localInstance == null) {
            synchronized (TableUsers.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ManageUsersView();
                }
            }
        }
        return localInstance;
    }

    private ManageUsersView() {
        mainlayout = new AbsoluteLayout();
        header = new HeaderLayout();

        body = new HorizontalLayout();
        table = TableUsers.getInstance();
        table.setSizeFull();

      //  buttons.addComponent(back);
      //  buttons.addComponent(forward);

      //  tablecontent.addComponent(buttons);
        tablecontent.addComponent(table);
        tablecontent.setSizeFull();
      //  tablecontent.setExpandRatio(buttons, 5);
        tablecontent.setExpandRatio(table, 95);

        hSplitBar = new HorizontalSplitPanel();
        hSplitBar.setSplitPosition(320, Unit.PIXELS);
        hSplitBar.setMaxSplitPosition(320, Unit.PIXELS);
        hSplitBar.setMinSplitPosition(20, Unit.PIXELS);
        menu = MenuTable.getInstance();
        // Styles
        mainlayout.setStyleName("v-main-body");
        body.setStyleName("bodylayout");

        // Add components
        hSplitBar.setFirstComponent(menu);
        hSplitBar.setSecondComponent(tablecontent);
        body.addComponent(hSplitBar);

        mainlayout.addComponent(body, "left: 0px; top: 10%;");
        mainlayout.addComponent(header, "left: 0px; top: 0px; bottom: 90%;");

        //header.setSizeFull();
        body.setSizeFull();
        mainlayout.setSizeFull();

        ///TESTING MENU////
        menu.setTable(table);
        ///////////////////

       /* back.addClickListener(e -> {

        });

        forward.addClickListener(e -> {

        });*/

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
