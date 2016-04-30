package frontend.elements.components;

import com.vaadin.annotations.Theme;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.*;
import frontend.views.*;
import org.vaadin.teemu.VaadinIcons;

/**
 * Created by Александр on 13.03.2016.
 */
@Theme("mytheme")
public class HeaderLayout extends HorizontalLayout {
    // Main
    private Button button_main;
    private Button button_profile;
    private Button button_search;

    private Button button_add;
 //   private Button button_mail;

    private Button button_manage;
    private MenuBar menu_manage;
    private final MenuBar.Command menuCommand_users = new MenuBar.Command() {
        @Override
        public void menuSelected(final MenuBar.MenuItem selectedItem) {
            getUI().getNavigator().navigateTo(ManageUsersView.NAME);
        }
    };

    private final MenuBar.Command menuCommand_books = new MenuBar.Command() {
        @Override
        public void menuSelected(final MenuBar.MenuItem selectedItem) {
            getUI().getNavigator().navigateTo(ManageBooksView.NAME);
        }
    };

    private final MenuBar.Command menuCommand_settings = new MenuBar.Command() {
        @Override
        public void menuSelected(final MenuBar.MenuItem selectedItem) {
            getUI().getNavigator().navigateTo(ManageSettingsView.NAME);
        }
    };

    private Button button_logout;

    private HorizontalLayout header_basic;
    private HorizontalLayout header_pro;
    private HorizontalLayout header_logout;

    // Narrow

    // Mobile

    // TODO user rights to add elements to navigation panel
    public HeaderLayout() {
        button_main = new Button("Main", event -> {
            // Refresh this view, should redirect
            getUI().getNavigator().navigateTo(MainView.NAME);
        });
        button_profile = new Button("Profile", event -> {
            // Refresh this view, should redirect
            getUI().getNavigator().navigateTo(ProfileView.NAME);
        });
        button_search = new Button("Search", event -> {
            //  should redirect
            getUI().getNavigator().navigateTo(SearchView.NAME);
        });
       button_add = new Button("Add", event -> {
            // should redirect
           BookWin subWindow = new BookWin();
           // Open it in the UI
           getUI().addWindow(subWindow);
        });
       /* button_mail = new Button("Mail", event -> {
            //  should redirect
            getUI().getNavigator().navigateTo(MailView.NAME);
        });*/
        button_logout = new Button("Logout", event -> {

            // "Logout" the user
            getSession().setAttribute("user", null);

            // Refresh this view, should redirect to login view
            getUI().getNavigator().navigateTo(SimpleLoginView.NAME);
        });
        menu_manage = new MenuBar();
        MenuBar.MenuItem child = menu_manage.addItem("Manage", null);
        child.addItem("Users", menuCommand_users);
        child.addItem("Content", menuCommand_books);
        child.addItem("Settings", menuCommand_settings);

        //button_main.setIcon( new ThemeResource( "Images/Logo_icon.ico" ) );
        //button_profile.setIcon(new ThemeResource( "Images/vaadin-icons-png/user.png" ));

        header_basic = new HorizontalLayout();
        header_pro = new HorizontalLayout();
        header_logout = new HorizontalLayout(button_logout);

        header_logout.setComponentAlignment(button_logout, Alignment.MIDDLE_CENTER);

        header_basic.addComponent(button_main);
        header_basic.addComponent(button_profile);
        header_basic.addComponent(button_search);

        header_basic.setComponentAlignment(button_main, Alignment.MIDDLE_CENTER);
        header_basic.setComponentAlignment(button_profile, Alignment.MIDDLE_CENTER);
        header_basic.setComponentAlignment(button_search, Alignment.MIDDLE_CENTER);

        // TODO on user status add or not
        header_pro.addComponent(button_add);
       // header_pro.addComponent(button_mail);
        header_pro.addComponent(menu_manage);

        header_pro.setComponentAlignment(button_add, Alignment.MIDDLE_CENTER);
      //  header_pro.setComponentAlignment(button_mail, Alignment.MIDDLE_CENTER);
        header_pro.setComponentAlignment(menu_manage, Alignment.MIDDLE_CENTER);

        //header_basic.setSpacing(true);
        //header_pro.setSpacing(true);
        //header_logout.setSpacing(true);

        addComponent(header_basic);
        addComponent(header_pro);
        addComponent(header_logout);
        setComponentAlignment(header_basic, Alignment.MIDDLE_LEFT);
        setComponentAlignment(header_pro, Alignment.MIDDLE_CENTER);
        setComponentAlignment(header_logout, Alignment.MIDDLE_RIGHT);

        header_basic.setHeight(100, Unit.PERCENTAGE);
        header_pro.setHeight(100, Unit.PERCENTAGE);
        header_logout.setHeight(100, Unit.PERCENTAGE);

        button_main.setHeight(100, Unit.PERCENTAGE);
        button_profile.setHeight(100, Unit.PERCENTAGE);
        button_search.setHeight(100, Unit.PERCENTAGE);
        button_add.setHeight(100, Unit.PERCENTAGE);
     //   button_mail.setHeight(100, Unit.PERCENTAGE);
        menu_manage.setHeight(100, Unit.PERCENTAGE);
        button_logout.setHeight(100, Unit.PERCENTAGE);

        button_main.setWidth(140, Unit.PIXELS);
        button_profile.setWidth(140, Unit.PIXELS);
        button_search.setWidth(140, Unit.PIXELS);
        button_add.setWidth(140, Unit.PIXELS);
        menu_manage.setWidth(185, Unit.PIXELS);
        button_logout.setWidth(140, Unit.PIXELS);

        setStyleName("cssmenu");
      //  setMargin(true);
      //  setSpacing(true);
        setSizeFull();
    }
}
