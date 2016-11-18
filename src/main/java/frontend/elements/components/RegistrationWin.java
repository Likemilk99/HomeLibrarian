package frontend.elements.components;

import Data.Users;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.server.Page;
import com.vaadin.ui.*;
import service.IService.IUserService;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.sql.SQLException;

/**
 * Created by Лукерия on 16.03.2016.
 */
public class RegistrationWin extends Window {
    private VerticalLayout contentLayout;
    private TextField usernameField;
    private TextField fnameField;
    private PasswordField passwordField;
    private PasswordField reenterField;
    private Label dateLabel;
    private HorizontalLayout date_layout;
    private NativeSelect daySelect;
    private NativeSelect monthSelect;
    private NativeSelect yearSelect;
    private TextField emailField;
    private Button signupButton;

    private Users user;
    public RegistrationWin() {
        setCaption("Registration");

        //setSizeFull();
        contentLayout = new VerticalLayout();
        //contentLayout.setSizeFull();
        contentLayout.setWidth(60, Unit.PERCENTAGE);
        contentLayout.setMargin(true);
        contentLayout.setSpacing(true);
        contentLayout.setStyleName("loginlayout");
        setContent(contentLayout);

        usernameField = new TextField("Username:");
        usernameField.addValidator(new usernameValidator());
        usernameField.setRequired(true);
        usernameField.setWidth(100, Unit.PERCENTAGE);

        fnameField = new TextField("First name:");
        fnameField.setWidth(100, Unit.PERCENTAGE);

        passwordField = new PasswordField("Password:");
        passwordField.addValidator(new emptyValidator());
        passwordField.setRequired(true);
        passwordField.setWidth(100, Unit.PERCENTAGE);
        passwordField.setResponsive(true);

        reenterField = new PasswordField("Reenter password:");
        reenterField.addValidator(new passwordValidator());
        reenterField.setRequired(true);
        reenterField.setWidth(100, Unit.PERCENTAGE);
        reenterField.setResponsive(true);

        dateLabel = new Label("Date of birth:");
        dateLabel.setWidth(100, Unit.PERCENTAGE);
        dateLabel.setResponsive(true);

        date_layout = new HorizontalLayout();
        daySelect = new NativeSelect("Day: ");
        for (int i=1; i<=31; i++) {
            daySelect.addItem(i);
            daySelect.setItemCaption(i, Integer.toString(i));
        }
        daySelect.setValue(1);
        daySelect.setNullSelectionAllowed(false);
        daySelect.setWidth(100, Unit.PERCENTAGE);
        daySelect.setResponsive(true);

        monthSelect = new NativeSelect("Month: ");
        for (int i=1; i<=12; i++) {
            monthSelect.addItem(i);
            monthSelect.setItemCaption(i, Integer.toString(i));
        }
        monthSelect.setValue(1);
        monthSelect.setNullSelectionAllowed(false);
        monthSelect.setWidth(100, Unit.PERCENTAGE);
        monthSelect.setResponsive(true);

        yearSelect = new NativeSelect("Year: ");
        for (int i=java.util.Calendar.getInstance().get(java.util.Calendar.YEAR); i>1900; --i) {
            yearSelect.addItem(i);
            yearSelect.setItemCaption(i, Integer.toString(i));
        }
        yearSelect.setValue(java.util.Calendar.getInstance().get(java.util.Calendar.YEAR));
        yearSelect.setNullSelectionAllowed(false);
        yearSelect.setWidth(100, Unit.PERCENTAGE);
        yearSelect.setResponsive(true);

        date_layout.addComponent(daySelect);
        date_layout.addComponent(monthSelect);
        date_layout.addComponent(yearSelect);
        date_layout.setSpacing(true);
        date_layout.setWidth(100, Unit.PERCENTAGE);

        emailField = new TextField("Email:");
        emailField.addValidator(new emailValidator());
        emailField.setRequired(true);
        emailField.setWidth(100, Unit.PERCENTAGE);
        emailField.setResponsive(true);

        /*final TextField secret = new TextField("Secret question:");
        secret.setRequired(true);
        secret.setWidth(50, Unit.PERCENTAGE);
        secret.setStyleName("textfield");
        secret.setResponsive(true);

        final TextField answer = new TextField("Answer:");
        answer.setRequired(true);
        answer.setWidth(50, Unit.PERCENTAGE);
        answer.setStyleName("textfield");
        answer.setResponsive(true);*/

        signupButton = new Button("SIGNUP");
        signupButton.setWidth(80, Unit.PERCENTAGE);
        signupButton.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                if (!newUserButtonClick()) {
                    passwordField.setValue("");
                    reenterField.setValue("");
                }
            }
        });
        signupButton.setStyleName("super-button");
        signupButton.setResponsive(true);

        // Put some components in it
        contentLayout.addComponent(usernameField);
        contentLayout.addComponent(fnameField);
        contentLayout.addComponent(passwordField);
        contentLayout.addComponent(reenterField);
        contentLayout.addComponent(dateLabel);
        contentLayout.addComponent(date_layout);
        contentLayout.addComponent(emailField);
        //subContent.addComponent(secret);
        // subContent.addComponent(answer);
        contentLayout.addComponent(signupButton);

        contentLayout.setComponentAlignment(usernameField, Alignment.MIDDLE_CENTER);
        contentLayout.setComponentAlignment(fnameField, Alignment.MIDDLE_CENTER);
        contentLayout.setComponentAlignment(passwordField, Alignment.MIDDLE_CENTER);
        contentLayout.setComponentAlignment(reenterField, Alignment.MIDDLE_CENTER);
        contentLayout.setComponentAlignment(dateLabel, Alignment.MIDDLE_CENTER);
        contentLayout.setComponentAlignment(date_layout, Alignment.MIDDLE_CENTER);
        contentLayout.setComponentAlignment(emailField, Alignment.MIDDLE_CENTER);
        //subContent.setComponentAlignment(secret, Alignment.MIDDLE_CENTER);
        //subContent.setComponentAlignment(answer, Alignment.MIDDLE_CENTER);
        contentLayout.setComponentAlignment(signupButton, Alignment.MIDDLE_CENTER);
        // Center it in the browser window
        center();
        setDraggable(false);
        setResizable(false);
        setWidth(320, Unit.PIXELS);
        setModal(true);
    }


    private final class usernameValidator extends
            AbstractValidator<String> {

        public usernameValidator() {
            super("Username is already exist!");
        }

        @Override
        protected boolean isValidValue(String value) {
            if(value.isEmpty())
                return false;

            IUserService iUserService = new IUserService();
            try {
                if(!iUserService.isUsernameExist(usernameField.getValue()))
                    return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        public Class<String> getType() {
            return String.class;
        }
    }

    private final class passwordValidator extends
            AbstractValidator<String> {

        public passwordValidator() {
            super("Password does not match the confirm password!");
        }

        @Override
        protected boolean isValidValue(String value) {
            if(reenterField.isEmpty())
                return false;

            if (reenterField.getValue().matches(passwordField.getValue())) {
                return true;
            }
            return false;
        }

        @Override
        public Class<String> getType() {
            return String.class;
        }
    }

    private final class emailValidator extends
            AbstractValidator<String> {

        public emailValidator() {
            super("Must be an email address");
        }

        @Override
        protected boolean isValidValue(String value) {
            if(value.isEmpty() || !isValidEmailAddress(value))
                return false;

            return true;
        }

        @Override
        public Class<String> getType() {
            return String.class;
        }
    }

    private final class emptyValidator extends
            AbstractValidator<String> {

        public emptyValidator() {
            super("Cannot be empty");
        }

        @Override
        protected boolean isValidValue(String value) {
            if(value.isEmpty())
                return false;

            return true;
        }

        @Override
        public Class<String> getType() {
            return String.class;
        }
    }

    private static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    public Users getUser() {
        return user;
    }

    private boolean newUserButtonClick() {
        String username = usernameField.getValue();
        String fname = fnameField.getValue();
        String pass = passwordField.getValue();
        String day = daySelect.getItemCaption(daySelect.getValue());
        String month = monthSelect.getItemCaption(monthSelect.getValue());
        String year = yearSelect.getItemCaption(yearSelect.getValue());
        String email = emailField.getValue();
        //secret.getValue(),
        //answer.getValue()
        if(!emailField.isValid() || !reenterField.isValid() || !usernameField.isValid()) {
            new Notification("Необходимо заполнить все требуемые поля.",
                    Notification.Type.WARNING_MESSAGE)
                    .show(Page.getCurrent());
            return false;
        }

        user  = new Users(username, fname, pass, email);
        IUserService iUserService = new IUserService();
        try {
            iUserService.insert(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.close();
        return false;
    }
}
