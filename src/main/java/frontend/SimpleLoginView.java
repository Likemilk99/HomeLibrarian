package frontend;

import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Responsive;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.annotations.*;
import com.vaadin.ui.Calendar;
import com.vaadin.ui.themes.Reindeer;
import com.vaadin.ui.themes.ValoTheme;

import java.util.*;

/**
 * Created by Александр on 07.02.2016.
 */
@Theme("mytheme")
public class SimpleLoginView extends CustomComponent implements View,
        Button.ClickListener {
    public static final String NAME = "login";

    private final Image logo =  new Image(null, new ThemeResource("Images/logo.png"));

    private final Button loginButton;
    private final Button signupButton;

    private final TextField user_field;
    private final PasswordField password_field;

    private PasswordField password_reg;
    private PasswordField reenter_reg;

    private final Label testlabel;
    public SimpleLoginView() {
        setSizeFull();
        setResponsive(true);
        // Initialize logo
        logo.setWidth(12, Unit.PERCENTAGE);

        // Initialize lebels
        testlabel = new Label("(test@test.com/passw0rd)");
        testlabel.setWidth(null);
        testlabel.setStyleName("errorlabel");
        Label introlabel = new Label("Welcome to HomeLibrarian");
        introlabel.setWidth(null);
        introlabel.setStyleName("introlabel");
        Responsive.makeResponsive(introlabel);

        Label passwordlabel = new Label("Password:");
        passwordlabel.setWidth(null);
        passwordlabel.setStyleName("label");
        passwordlabel.setResponsive(true);

        Label loginlabel = new Label("Username or email: ");
        loginlabel.setWidth(25, Unit.PERCENTAGE);
        loginlabel.setStyleName("label");
        loginlabel.setResponsive(true);

        // Password forgot link
        Button passwordlink = new Button("forgot your password?");
        passwordlink.setWidth(null);
        passwordlink.setStyleName(ValoTheme.BUTTON_LINK);
        passwordlink.setResponsive(true);

        //passwordlink.setStyleName("buttonlink");
        // Password layout
        HorizontalLayout password_layout_components = new HorizontalLayout();
        password_layout_components.setWidth(null);
        password_layout_components.setSpacing(true);
        password_layout_components.addComponent(passwordlabel);
        password_layout_components.setComponentAlignment(passwordlabel, Alignment.MIDDLE_LEFT);
        password_layout_components.addComponent(passwordlink);
        password_layout_components.setComponentAlignment(passwordlink, Alignment.MIDDLE_LEFT);

        HorizontalLayout password_layout = new HorizontalLayout(password_layout_components);
        password_layout.setWidth(25, Unit.PERCENTAGE);
        // Create the user input field
        user_field = new TextField();
        user_field.setWidth(25, Unit.PERCENTAGE);
        user_field.addValidator(new EmailValidator(
                "Username must be an email address"));
        user_field.setValue("test@test.com");
        user_field.setInvalidAllowed(false);
        user_field.setStyleName("textfield");
        user_field.setResponsive(true);

        // Create the password input field
        password_field = new PasswordField();
        password_field.setWidth(25, Unit.PERCENTAGE);
        password_field.addValidator(new PasswordValidator());
        password_field.setValue("passw0rd");
        password_field.setNullRepresentation("");
        password_field.setStyleName("textfield");
        password_field.setResponsive(true);

        // Create login button
        loginButton = new Button("login", this);
        loginButton.setWidth(100, Unit.PERCENTAGE);
        loginButton.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                loginButtonClick();
            }
        });
        loginButton.setStyleName("button");
        loginButton.setResponsive(true);

        // Create signup button
        signupButton = new Button("signup", this);
        signupButton.setWidth(100, Unit.PERCENTAGE);
        signupButton.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                signupButtonClick();
            }
        });
        signupButton.setStyleName("extrabutton");
        signupButton.setResponsive(true);

        // Add both to a panel
        //Panel panel = new Panel();
        //panel.setSizeFull();
        //panel.setStyleName(Reindeer.LAYOUT_BLUE);

        HorizontalLayout buttons_layout = new HorizontalLayout();
        buttons_layout.setWidth(25, Unit.PERCENTAGE);
        buttons_layout.setSpacing(true);
        buttons_layout.addComponent(loginButton);
        buttons_layout.addComponent(signupButton);
        //buttonslayot.setComponentAlignment(loginButton, Alignment.MIDDLE_CENTER);
        //
        VerticalLayout content = new VerticalLayout();
        content.addStyleName("borders");
        content.addComponent(logo);
        content.setComponentAlignment(logo, Alignment.MIDDLE_CENTER);
        content.addComponent(introlabel);
        content.setComponentAlignment(introlabel, Alignment.MIDDLE_CENTER);
        content.addComponent(testlabel);
        content.setComponentAlignment(testlabel, Alignment.MIDDLE_CENTER);
        content.addComponent(loginlabel);
        content.setComponentAlignment(loginlabel, Alignment.MIDDLE_CENTER);
        content.addComponent(user_field);
        content.setComponentAlignment(user_field, Alignment.MIDDLE_CENTER);
        content.addComponent(password_layout);
        content.setComponentAlignment(password_layout, Alignment.MIDDLE_CENTER);
        content.addComponent(password_field);
        content.setComponentAlignment(password_field, Alignment.MIDDLE_CENTER);
        content.addComponent(buttons_layout);
        content.setComponentAlignment(buttons_layout, Alignment.MIDDLE_CENTER);
        content.setMargin(new MarginInfo(true, true, true, false));
        content.setWidth(100, Unit.PERCENTAGE);
        content.setSpacing(true);
        Responsive.makeResponsive(content);
        //content.setHeight(50, Unit.PERCENTAGE);
        //content.setMargin(true);
        //fields.setCaption("Please login to access the application. (test@test.com/passw0rd)");
        //fields.setSpacing(true);
        //

        // The view root layout
        VerticalLayout viewLayout = new VerticalLayout(content);

        //viewLayout.setWidth(100, Unit.PERCENTAGE);
        //viewLayout.setHeight(100, Unit.PERCENTAGE);
        viewLayout.setSizeFull();
        Responsive.makeResponsive(viewLayout);
        viewLayout.setComponentAlignment(content, Alignment.MIDDLE_CENTER);
        //viewLayout.setStyleName(Reindeer.LAYOUT_BLUE);

        setCompositionRoot(viewLayout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // focus the username field when user arrives to the login view
        user_field.focus();
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
            if (value != null) {
                    //&& (value.length() < 5 || !value.matches(".*\\d.*")))
                return true;
            }
            return false;
        }

        @Override
        public Class<String> getType() {
            return String.class;
        }
    }

    private final class PasswordReenterValidator extends
            AbstractValidator<String> {

        public PasswordReenterValidator() {
            super("Password does not match the confirm password");
        }

        @Override
        protected boolean isValidValue(String value) {
            //
            // Password must be at least 8 characters long and contain at least
            // one number
            //
            if (value.matches(password_reg.getValue())) {
                //&& (value.length() < 5 || !value.matches(".*\\d.*")))
                return true;
            }
            return false;
        }

        @Override
        public Class<String> getType() {
            return String.class;
        }
    }
    @Override
    public void buttonClick(Button.ClickEvent event){

    }

    protected void loginButtonClick() {
        // Validate the fields using the navigator. By using validors for the
        // fields we reduce the amount of queries we have to use to the database
        // for wrongly entered passwords
        //
        if (!user_field.isValid() || !password_field.isValid()) {
            return;
        }

        String username = user_field.getValue();
        String password = password_field.getValue();

        //
        // Validate username and password with database here. For examples sake
        // I use a dummy username and password.
        //

        // TODO SQL
        boolean isValid = username.equals("test@test.com")
                && password.equals("passw0rd");
        String status = "admin";
        if (isValid) {

            // Store the current user in the service session
            getSession().setAttribute("user", username);
            getSession().setAttribute("status", status);
            // Navigate to main view
            getUI().getNavigator().navigateTo(MainView.NAME);//

        } else {

            // Wrong password clear the password field and refocuses it
            password_field.setValue(null);
            password_field.focus();
            testlabel.setValue("username or password is invalid (test@test.com/passw0rd)");
        }
    }

    protected void signupButtonClick() {
        Window subWindow = new Window("Registration");
        VerticalLayout subContent = new VerticalLayout();
        subContent.setWidth(100, Unit.PERCENTAGE);
        subContent.setMargin(true);
        subContent.setSpacing(true);
        subWindow.setContent(subContent);

        final TextField username = new TextField("Username:");
        username.setRequired(true);
        username.setWidth(50, Unit.PERCENTAGE);
        username.setStyleName("textfield");
        username.setResponsive(true);

        password_reg = new PasswordField("Password:");
        password_reg.setRequired(true);
        password_reg.setWidth(50, Unit.PERCENTAGE);
        password_reg.setStyleName("textfield");
        password_reg.setResponsive(true);

        reenter_reg = new PasswordField("Reenter password:");
        reenter_reg.addValidator(new PasswordReenterValidator());
        reenter_reg.setRequired(true);
        reenter_reg.setWidth(50, Unit.PERCENTAGE);
        reenter_reg.setStyleName("textfield");
        reenter_reg.setResponsive(true);

        Label date = new Label("Date of birth:");
        date.setWidth(50, Unit.PERCENTAGE);
        date.setStyleName("label");
        date.setResponsive(true);
        HorizontalLayout dateOfBirthLayout = new HorizontalLayout();

        final NativeSelect day = new NativeSelect("Day: ");
        for (int i=1; i<=31; i++) {
            day.addItem(i);
            day.setItemCaption(i, Integer.toString(i));
        }
        day.setValue(1);
        day.setNullSelectionAllowed(false);
        day.setWidth(100, Unit.PERCENTAGE);
        day.setStyleName("textfield");
        day.setResponsive(true);

        final NativeSelect month = new NativeSelect("Month: ");
        for (int i=1; i<=12; i++) {
            month.addItem(i);
            month.setItemCaption(i, Integer.toString(i));
        }
        month.setValue(1);
        month.setNullSelectionAllowed(false);
        month.setWidth(100, Unit.PERCENTAGE);
        month.setStyleName("textfield");
        month.setResponsive(true);

        final NativeSelect year = new NativeSelect("Year: ");
        for (int i=java.util.Calendar.getInstance().get(java.util.Calendar.YEAR); i>1900; --i) {
            year.addItem(i);
            year.setItemCaption(i, Integer.toString(i));
        }
        year.setValue(java.util.Calendar.getInstance().get(java.util.Calendar.YEAR));
        year.setNullSelectionAllowed(false);
        year.setWidth(100, Unit.PERCENTAGE);
        year.setStyleName("textfield");
        year.setResponsive(true);

        dateOfBirthLayout.addComponent(day);
        dateOfBirthLayout.addComponent(month);
        dateOfBirthLayout.addComponent(year);
        dateOfBirthLayout.setSpacing(true);
        dateOfBirthLayout.setWidth(50, Unit.PERCENTAGE);

        final TextField email = new TextField("Email:");
        email.addValidator(new EmailValidator(
                "Username must be an email address"));
        email.setRequired(true);
        email.setWidth(50, Unit.PERCENTAGE);
        email.setStyleName("textfield");
        email.setResponsive(true);

        final TextField secret = new TextField("Secret question:");
        secret.setRequired(true);
        secret.setWidth(50, Unit.PERCENTAGE);
        secret.setStyleName("textfield");
        secret.setResponsive(true);

        final TextField answer = new TextField("Answer:");
        answer.setRequired(true);
        answer.setWidth(50, Unit.PERCENTAGE);
        answer.setStyleName("textfield");
        answer.setResponsive(true);

        Button newuserButton = new Button("signup", this);
        newuserButton.setWidth(40, Unit.PERCENTAGE);
        newuserButton.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                if (!newuserButtonClick(username.getValue(),
                        password_reg.getValue(),
                        day.getCaption(),
                        month.getCaption(),
                        year.getCaption(),
                        email.getValue(),
                        secret.getValue(),
                        answer.getValue())) {
                    password_reg.setValue("");
                    reenter_reg.setValue("");
                }
            }
        });
        newuserButton.setStyleName("extrabutton");
        newuserButton.setResponsive(true);

        // Put some components in it
        subContent.addComponent(username);
        subContent.addComponent(password_reg);
        subContent.addComponent(reenter_reg);
        subContent.addComponent(date);
        subContent.addComponent(dateOfBirthLayout);
        subContent.addComponent(email);
        subContent.addComponent(secret);
        subContent.addComponent(answer);
        subContent.addComponent(newuserButton);

        subContent.setComponentAlignment(username, Alignment.MIDDLE_CENTER);
        subContent.setComponentAlignment(password_reg, Alignment.MIDDLE_CENTER);
        subContent.setComponentAlignment(reenter_reg, Alignment.MIDDLE_CENTER);
        subContent.setComponentAlignment(date, Alignment.MIDDLE_CENTER);
        subContent.setComponentAlignment(dateOfBirthLayout, Alignment.MIDDLE_CENTER);
        subContent.setComponentAlignment(email, Alignment.MIDDLE_CENTER);
        subContent.setComponentAlignment(secret, Alignment.MIDDLE_CENTER);
        subContent.setComponentAlignment(answer, Alignment.MIDDLE_CENTER);
        subContent.setComponentAlignment(newuserButton, Alignment.MIDDLE_CENTER);
        // Center it in the browser window
        subWindow.center();
        subWindow.setWidth(50, Unit.PERCENTAGE);
        subWindow.setModal(true);
        // Open it in the UI
        getUI().addWindow(subWindow);
    }

    protected boolean newuserButtonClick(String user,
                                      String pass,
                                      String day,
                                      String month,
                                      String year,
                                      String email,
                                      String secret,
                                      String answer) {
        // TODO SQL
        return false;
    }
}