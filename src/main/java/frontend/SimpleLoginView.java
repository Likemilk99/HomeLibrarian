package frontend;

import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.annotations.*;
import com.vaadin.ui.themes.Reindeer;

/**
 * Created by Александр on 07.02.2016.
 */
@Theme("mytheme")
public class SimpleLoginView extends CustomComponent implements View,
        Button.ClickListener {
    public static final String NAME = "login";

    private final Image logo =  new Image(null, new ThemeResource("Images/logo.png"));
    private final Label label = new Label("Please login to access the application. (test@test.com/passw0rd)");
    private final TextField user;
    private final PasswordField password;

    private final Button loginButton;

    public SimpleLoginView() {
        setSizeFull();

        // Initialize logo
        logo.setWidth("350px");
        // Create the user input field
        user = new TextField("User:");
        user.setWidth("300px");
        user.setInputPrompt("Username or email");
        user.addValidator(new EmailValidator(
                "Username must be an email address"));
        user.setInvalidAllowed(false);

        // Create the password input field
        password = new PasswordField("Password:");
        password.setWidth("300px");
        password.addValidator(new PasswordValidator());
        password.setValue("");
        password.setNullRepresentation("");

        // Create login button
        loginButton = new Button("Login", this);

        // Add both to a panel
        Panel panel = new Panel();
        panel.setSizeUndefined();
        panel.setStyleName(Reindeer.LAYOUT_BLUE);

        //
        FormLayout content = new FormLayout();
        content.addStyleName("mypanelcontent");
        content.addComponent(label);
        content.setComponentAlignment(label, Alignment.MIDDLE_CENTER);
        content.addComponent(user);
        content.setComponentAlignment(user, Alignment.MIDDLE_CENTER);
        content.addComponent(password);
        content.setComponentAlignment(password, Alignment.MIDDLE_CENTER);
        content.addComponent(loginButton);
        content.setComponentAlignment(loginButton, Alignment.MIDDLE_CENTER);
        content.setSizeUndefined(); // Shrink to fit
        content.setMargin(new MarginInfo(true, true, true, false));
        //content.setMargin(true);
        panel.setContent(content);
        //fields.setCaption("Please login to access the application. (test@test.com/passw0rd)");
        //fields.setSpacing(true);
        //

        // The view root layout
        VerticalLayout viewLayout = new VerticalLayout(panel);

        viewLayout.setSizeFull();
        viewLayout.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
        //viewLayout.setStyleName(Reindeer.LAYOUT_BLUE);
        viewLayout.addStyleName("backgroundimage");

        setCompositionRoot(viewLayout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // focus the username field when user arrives to the login view
        user.focus();
    }

    // Validator for validating the passwords
        private static final class PasswordValidator extends
            AbstractValidator<String> {

        public PasswordValidator() {
            super("The password provided is not valid");
        }

        @Override
        protected boolean isValidValue(String value) {
            //
            // Password must be at least 8 characters long and contain at least
            // one number
            //
            if (value != null
                    && (value.length() < 8 || !value.matches(".*\\d.*"))) {
                return false;
            }
            return true;
        }

        @Override
        public Class<String> getType() {
            return String.class;
        }
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {

        //
        // Validate the fields using the navigator. By using validors for the
        // fields we reduce the amount of queries we have to use to the database
        // for wrongly entered passwords
        //
        if (!user.isValid() || !password.isValid()) {
            return;
        }

        String username = user.getValue();
        String password = this.password.getValue();

        //
        // Validate username and password with database here. For examples sake
        // I use a dummy username and password.
        //
        boolean isValid = username.equals("test@test.com")
                && password.equals("passw0rd");

        if (isValid) {

            // Store the current user in the service session
            getSession().setAttribute("user", username);

            // Navigate to main view
            getUI().getNavigator().navigateTo(SimpleLoginMainView.NAME);//

        } else {

            // Wrong password clear the password field and refocuses it
            this.password.setValue(null);
            this.password.focus();

        }
    }
}