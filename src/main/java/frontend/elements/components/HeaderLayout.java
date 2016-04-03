package frontend.elements.components;

import com.vaadin.annotations.Theme;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
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

   // private Button button_add;
 //   private Button button_mail;

    private Button button_manage;

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
       /* button_add = new Button("Add", event -> {
            // should redirect
            getUI().getNavigator().navigateTo(AddView.NAME);
        });*/
       /* button_mail = new Button("Mail", event -> {
            //  should redirect
            getUI().getNavigator().navigateTo(MailView.NAME);
        });*/
        button_manage = new Button("Manage", event -> {
            //  should redirect
            getUI().getNavigator().navigateTo(ManageView.NAME);
        });
        button_logout = new Button("Logout", event -> {

            // "Logout" the user
            getSession().setAttribute("user", null);

            // Refresh this view, should redirect to login view
            getUI().getNavigator().navigateTo(SimpleLoginView.NAME);
        });

        //button_main.setIcon( new ThemeResource( "Images/logo_icon.png" ) );
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
        // TODO
       // header_pro.addComponent(button_add);
       // header_pro.addComponent(button_mail);
        header_pro.addComponent(button_manage);

      //  header_pro.setComponentAlignment(button_add, Alignment.MIDDLE_CENTER);
      //  header_pro.setComponentAlignment(button_mail, Alignment.MIDDLE_CENTER);
        header_pro.setComponentAlignment(button_manage, Alignment.MIDDLE_CENTER);

        //header_basic.setSpacing(true);
        //header_pro.setSpacing(true);
        //header_logout.setSpacing(true);

        addComponent(header_basic);
        addComponent(header_pro);
        addComponent(header_logout);
        setComponentAlignment(header_basic, Alignment.MIDDLE_LEFT);
        setComponentAlignment(header_pro, Alignment.MIDDLE_CENTER);
        setComponentAlignment(header_logout, Alignment.MIDDLE_RIGHT);
        setWidth(100, Unit.PERCENTAGE);

        header_basic.setHeight(100, Unit.PERCENTAGE);
        header_pro.setHeight(100, Unit.PERCENTAGE);
        header_logout.setHeight(100, Unit.PERCENTAGE);

        button_main.setHeight(100, Unit.PERCENTAGE);
        button_profile.setHeight(100, Unit.PERCENTAGE);
        button_search.setHeight(100, Unit.PERCENTAGE);
    //    button_add.setHeight(100, Unit.PERCENTAGE);
     //   button_mail.setHeight(100, Unit.PERCENTAGE);
        button_manage.setHeight(100, Unit.PERCENTAGE);
        button_logout.setHeight(100, Unit.PERCENTAGE);

        button_main.setStyleName("headerlayout");
        button_profile.setStyleName("headerlayout");
        button_search.setStyleName("headerlayout");
        //button_add.setStyleName("navigation-button");
        //button_mail.setStyleName("navigation-button");
        button_manage.setStyleName("headerlayout");
        button_logout.setStyleName("headerlayout");

       setStyleName("headerlayout");
      //  setMargin(true);
      //  setSpacing(true);
        setWidth(100, Unit.PERCENTAGE);
        setHeight(10, Unit.PERCENTAGE);
        //setSizeFull();
    }
}
