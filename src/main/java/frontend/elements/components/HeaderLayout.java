package frontend.elements.components;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import frontend.views.*;

/**
 * Created by Александр on 13.03.2016.
 */
public class HeaderLayout extends HorizontalLayout {
    private Button button_main;
    private Button button_profile;
    private Button button_search;

    private Button button_add;
    private Button button_mail;

    private Button button_manage;

    private Button button_logout;

    private HorizontalLayout header_basic;
    private HorizontalLayout header_pro;
    private HorizontalLayout header_logout;

    // TODO user rights to add elements to navigation panel
    public HeaderLayout() {
        button_main = new Button("Main", new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                // Refresh this view, should redirect
                getUI().getNavigator().navigateTo(MainView.NAME);
            }
        });
        button_profile = new Button("Profile", new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                // Refresh this view, should redirect
                getUI().getNavigator().navigateTo(ProfileView.NAME);
            }
        });
        button_search = new Button("Search", new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                //  should redirect
                getUI().getNavigator().navigateTo(SearchView.NAME);
            }
        });
        button_add = new Button("Add", new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                // should redirect
                getUI().getNavigator().navigateTo(AddView.NAME);
            }
        });
        button_mail = new Button("Mail", new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                //  should redirect
                getUI().getNavigator().navigateTo(MailView.NAME);
            }
        });
        button_manage = new Button("Manage", new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                //  should redirect
                getUI().getNavigator().navigateTo(ManageView.NAME);
            }
        });
        button_logout = new Button("Logout", new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {

                // "Logout" the user
                getSession().setAttribute("user", null);

                // Refresh this view, should redirect to login view
                getUI().getNavigator().navigateTo(SimpleLoginView.NAME);
            }
        });

        header_basic = new HorizontalLayout();
        header_pro = new HorizontalLayout();
        header_logout = new HorizontalLayout(button_logout);

        header_basic.addComponent(button_main);
        header_basic.addComponent(button_profile);
        header_basic.addComponent(button_search);

        // TODO
        header_pro.addComponent(button_add);
        header_pro.addComponent(button_mail);
        header_pro.addComponent(button_manage);

        header_basic.setSpacing(true);
        header_pro.setSpacing(true);
        header_logout.setSpacing(true);

        addComponent(header_basic);
        addComponent(header_pro);
        addComponent(header_logout);
        setComponentAlignment(header_basic, Alignment.MIDDLE_LEFT);
        setComponentAlignment(header_pro, Alignment.MIDDLE_CENTER);
        setComponentAlignment(header_logout, Alignment.MIDDLE_RIGHT);
        setWidth(100, Unit.PERCENTAGE);
        //setSizeFull();
    }
}
