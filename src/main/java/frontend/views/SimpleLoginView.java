package frontend.views;

import DAO.UserDAO;
import Data.Users;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.*;
import com.vaadin.annotations.*;
import com.vaadin.ui.themes.ValoTheme;
import frontend.elements.components.RegistrationWin;

import java.sql.SQLException;

/**
 * Created by Александр on 07.02.2016.
 */
@Theme("mytheme")
public class SimpleLoginView extends CustomComponent implements View,
        Button.ClickListener {
    public static final String NAME = "login";

    //private final Image logo =  new Image(null, new ThemeResource("Images/logo.png"));

    private final Button loginButton;
    private final Button signupButton;
    private final Button forgotButton;

    private final TextField user_field;
    private final PasswordField password_field;

    private final Label testlabel;
    public SimpleLoginView() {
        setSizeFull();
        setResponsive(true);
        // Initialize logo
        ThemeResource resource = new ThemeResource("Images/logo.png");
        Embedded logo = new Embedded(null, resource);
        logo.setSizeFull();
        //logo.setWidth(100, Unit.PERCENTAGE);
        //logo.setHeight(100, Unit.PERCENTAGE);
        //logo.setHeight(UI.getCurrent().getPage().getBrowserWindowHeight()/4, Unit.PIXELS);
        VerticalLayout logo_layout = new VerticalLayout();
        logo_layout.addComponent(logo);
        logo_layout.setComponentAlignment(logo, Alignment.MIDDLE_CENTER);
        logo_layout.addStyleName("loginlayout");
        logo_layout.setWidth(320, Unit.PIXELS);
        //logo_layout.setHeight(100, Unit.PERCENTAGE);
        //logo_layout.setSizeFull();
        //logo_layout.setExpandRatio(logo, 1.0f);
        // Initialize lebels
        testlabel = new Label("(test@test.com/passw0rd)");
        testlabel.setWidth(null);
        testlabel.setHeight(100, Unit.PERCENTAGE);
        testlabel.addStyleName("errorlabel");
        Label introlabel = new Label("Welcome to HomeLibrarian");
        introlabel.setWidth(null);
        introlabel.setHeight(100, Unit.PERCENTAGE);
        introlabel.addStyleName("introlabel");
        introlabel.setResponsive(true);

        Label passwordlabel = new Label("Password:");
        passwordlabel.setWidth(null);
        passwordlabel.setHeight(100, Unit.PERCENTAGE);
        passwordlabel.setResponsive(true);

        Label loginlabel = new Label("Username or email: ");
        loginlabel.setSizeFull();
        loginlabel.setResponsive(true);

        // Password forgot link
        forgotButton = new Button("forgot your password?");
        forgotButton.addClickListener(e ->
                forgotButtonClick()
        );
        forgotButton.setWidth(null);
        forgotButton.setHeight(100, Unit.PERCENTAGE);
        forgotButton.setStyleName(ValoTheme.BUTTON_LINK);
        forgotButton.setResponsive(true);
        //passwordlink.setStyleName("buttonlink");

        // Password layout
        HorizontalLayout password_layout_components = new HorizontalLayout();
        password_layout_components.setWidth(null);
        password_layout_components.setSpacing(true);
        password_layout_components.addComponent(passwordlabel);
        password_layout_components.setComponentAlignment(passwordlabel, Alignment.MIDDLE_LEFT);
        password_layout_components.addComponent(forgotButton);
        password_layout_components.setComponentAlignment(forgotButton, Alignment.MIDDLE_LEFT);

        HorizontalLayout password_layout = new HorizontalLayout(password_layout_components);
        password_layout.setSizeFull();
        // Create the user input field
        user_field = new TextField();
        user_field.setSizeFull();
        user_field.addValidator(new EmailValidator(
                "Username must be an email address"));
        user_field.setValue("iround0@yandex.ru");
     //   user_field.setInvalidAllowed(false);
        user_field.setResponsive(true);

        // Create the password input field
        password_field = new PasswordField();
        password_field.setSizeFull();
        password_field.addValidator(new PasswordValidator());
        password_field.setValue("7154255");
        password_field.setNullRepresentation("");
        password_field.setResponsive(true);

        // Create login button
        loginButton = new Button("LOGIN");
        loginButton.setSizeFull();
        loginButton.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                loginButtonClick();
            }
        });
        loginButton.setStyleName("main-button");
        loginButton.setResponsive(true);

        // Create signup button
        signupButton = new Button("SIGNUP", this);
        signupButton.setSizeFull();
        signupButton.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                signupButtonClick();
            }
        });
        signupButton.addStyleName("super-button");
        signupButton.setResponsive(true);

        // Add both to a panel
        //Panel panel = new Panel();
        //panel.setSizeFull();
        //panel.setStyleName(Reindeer.LAYOUT_BLUE);

        HorizontalLayout buttons_layout = new HorizontalLayout();
        buttons_layout.setSizeFull();
        buttons_layout.setSpacing(true);
        buttons_layout.addComponent(loginButton);
        buttons_layout.addComponent(signupButton);
        //buttonslayot.setComponentAlignment(loginButton, Alignment.MIDDLE_CENTER);
        //
        VerticalLayout content = new VerticalLayout();
        content.addStyleName("loginlayout");
        //content.addComponent(logo_layout);
        //content.setComponentAlignment(logo_layout, Alignment.MIDDLE_CENTER);
        //content.setExpandRatio(logo_layout, 2.5f);
        //content.addComponent(introlabel);
        //content.setComponentAlignment(introlabel, Alignment.MIDDLE_CENTER);
        //content.setExpandRatio(introlabel, 1.5f);
        content.addComponent(testlabel);
        content.setComponentAlignment(testlabel, Alignment.MIDDLE_CENTER);
        //content.setExpandRatio(testlabel, 0.5f);
        content.addComponent(loginlabel);
        content.setComponentAlignment(loginlabel, Alignment.MIDDLE_CENTER);
        //content.setExpandRatio(loginlabel, 0.5f);
        content.addComponent(user_field);
        content.setComponentAlignment(user_field, Alignment.MIDDLE_CENTER);
        //content.setExpandRatio(user_field, 0.5f);
        content.addComponent(password_layout);
        content.setComponentAlignment(password_layout, Alignment.MIDDLE_CENTER);
        //content.setExpandRatio(password_layout, 0.5f);
        content.addComponent(password_field);
        content.setComponentAlignment(password_field, Alignment.MIDDLE_CENTER);
        //content.setExpandRatio(password_field, 0.5f);
        content.addComponent(buttons_layout);
        content.setComponentAlignment(buttons_layout, Alignment.MIDDLE_CENTER);
        //content.setExpandRatio(buttons_layout, 0.5f);
        //content.setMargin(new MarginInfo(true, true, true, false));
        content.setWidth(25, Unit.PERCENTAGE);
        content.setHeight(100, Unit.PERCENTAGE);
        content.setSpacing(true);
        content.setResponsive(true);

        // The view root layout
        VerticalLayout viewLayout = new VerticalLayout();

        viewLayout.setSizeUndefined();
        //viewLayout.setSizeFull();
        viewLayout.setResponsive(true);
        viewLayout.addComponent(logo_layout);
        viewLayout.addComponent(introlabel);
        viewLayout.addComponent(content);
        viewLayout.setComponentAlignment(logo_layout, Alignment.MIDDLE_CENTER);
        viewLayout.setComponentAlignment(introlabel, Alignment.MIDDLE_CENTER);
        viewLayout.setComponentAlignment(content, Alignment.MIDDLE_CENTER);
        //viewLayout.setSpacing(true);
        //viewLayout.setExpandRatio(logo_layout, 3.0f);
        //viewLayout.setExpandRatio(introlabel, 2.0f);
        //viewLayout.setExpandRatio(content, 4.0f);
        //viewLayout.setStyleName(Reindeer.LAYOUT_BLUE);

        VerticalLayout panel = new VerticalLayout(viewLayout);
        panel.setComponentAlignment(viewLayout, Alignment.TOP_CENTER);
        //panel.setStyleName("mainlayout");
        panel.setSizeFull();
        setCompositionRoot(panel);
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

        UserDAO InUser = new UserDAO();
        Users user = new Users("LikeMilk","Ivan", "7154255", "iround@yandex.ru");
        try {
         user = InUser.getElById(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        boolean isValid;
        //isValid = username.equals("test@test.com")
        //            && password.equals("passw0rd");
        try {
            isValid = user.getEmail().equals(username)
                    && user.getPassword().equals(password);
        }
        catch(NullPointerException e) {
            isValid = false;
        }


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
        Window subWindow = new RegistrationWin();
        // Open it in the UI
        getUI().addWindow(subWindow);
    }

    protected void forgotButtonClick() {
        //Window subWindow = new Window("Try to remember");
        // Open it in the UI
        //getUI().addWindow(subWindow);
    }
}