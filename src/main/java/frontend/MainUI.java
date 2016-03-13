package frontend;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.vaadin.annotations.*;
import frontend.views.*;


import javax.servlet.annotation.WebServlet;


/**
 * Created by likemilk on 29.12.2015.
 */
@Theme("mytheme")
@Widgetset("newtest.MyAppWidgetset")
public class MainUI extends UI{

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setResponsive(true);
        //
        // Create a new instance of the navigator. The navigator will attach
        // itself automatically to this view.
        //
        new Navigator(this, this);

        //
        // The initial log view where the user can login to the application
        //
        getNavigator().addView(SimpleLoginView.NAME, SimpleLoginView.class);//

        //
        // Add other views
        //
        /*getNavigator().addView(MainView.NAME, MainView.class);
        getNavigator().addView(AddView.NAME, AddView.class);
        getNavigator().addView(MailView.NAME, MailView.class);
        getNavigator().addView(ManageView.NAME, ManageView.class);
        getNavigator().addView(ProfileView.NAME, ProfileView.class);
        getNavigator().addView(SearchView.NAME, SearchView.class);*/

        getNavigator().addView(MainView.NAME, new MainView());
        getNavigator().addView(AddView.NAME, new AddView());
        getNavigator().addView(MailView.NAME, new MailView());
        getNavigator().addView(ManageView.NAME, new ManageView());
        getNavigator().addView(ProfileView.NAME, new ProfileView());
        getNavigator().addView(SearchView.NAME, new SearchView());

        //
        // We use a view change handler to ensure the user is always redirected
        // to the login view if the user is not logged in.
        //
        getNavigator().addViewChangeListener(new ViewChangeListener() {

            @Override
            public boolean beforeViewChange(ViewChangeEvent event) {

                // Check if a user has logged in
                boolean isLoggedIn = getSession().getAttribute("user") != null && getSession().getAttribute("status") != null;
                boolean isLoginView = event.getNewView() instanceof SimpleLoginView;

                if (!isLoggedIn && !isLoginView) {
                    // Redirect to login view always if a user has not yet
                    // logged in
                    getNavigator().navigateTo(SimpleLoginView.NAME);
                    return false;

                } else if (isLoggedIn && isLoginView) {
                    // If someone tries to access to login view while logged in,
                    // then cancel
                    return false;
                }

                return true;
            }

            @Override
            public void afterViewChange(ViewChangeEvent event) {

            }
        });
    }

   @WebServlet(urlPatterns = "/*")//, name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MainUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
