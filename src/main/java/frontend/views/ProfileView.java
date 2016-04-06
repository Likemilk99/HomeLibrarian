package frontend.views;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import frontend.elements.components.HeaderLayout;

/**
 * Created by Александр on 13.03.2016.
 */
@Theme("mytheme")
public class ProfileView extends CustomComponent implements View {

    public static final String NAME = "profile";

    AbsoluteLayout mainlayout;
    HeaderLayout header;
    HorizontalLayout body;

    public ProfileView() {
        mainlayout = new AbsoluteLayout();
        header = new HeaderLayout();
        body = new HorizontalLayout();

        // Styles
        mainlayout.setStyleName("v-main-body");
        body.setStyleName("bodylayout");

        // Add components
        mainlayout.addComponent(body, "left: 0px; top: 10%;");
        mainlayout.addComponent(header, "left: 0px; top: 0px; bottom: 90%;");

        VerticalLayout content = new VerticalLayout();
        TextField username = new TextField("Username");
        Button change_username = new Button("Change username");
        TextField email = new TextField("Email");
        Button change_email = new Button("Change email");
        Button change_password = new Button("Change password");
        CheckBox allow_emails = new CheckBox("Allow emails from this service.");

        change_username.setStyleName("main-button");
        change_email.setStyleName("main-button");
        change_password.setStyleName("main-button");

        content.addComponent(username);
        content.addComponent(change_username);
        content.addComponent(email);
        content.addComponent(change_email);
        content.addComponent(change_password);
        content.addComponent(allow_emails);

        content.setMargin(true);
        content.setSpacing(true);
        body.addComponent(content);

        //header.setSizeFull();
        body.setSizeFull();
        mainlayout.setSizeFull();

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
