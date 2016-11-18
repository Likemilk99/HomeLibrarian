package frontend.views;

import Data.Users;
import com.vaadin.annotations.Theme;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import frontend.elements.components.HeaderLayout;
import service.IService.IUserService;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.sql.SQLException;


@Theme("mytheme")
public class ProfileView extends CustomComponent implements View {

    public static final String NAME = "profile";

    private String usernameString;

    private AbsoluteLayout mainlayout;
    private HeaderLayout header;
    private HorizontalLayout body;

    private VerticalLayout content = new VerticalLayout();

    private Label role = new Label("Status: ");

    private TextField username = new TextField("Username");
    private TextField newusername = new TextField("New username");
    private Button apply_username = new Button("Apply");
    private Button change_username = new Button("Change");

    private TextField fname = new TextField("First name");
    private TextField newfname = new TextField("New first name");
    private Button apply_fname = new Button("Apply");
    private Button change_fname = new Button("Change");

    private TextField email = new TextField("Email");
    private TextField newemail = new TextField("New email");
    private Button apply_email = new Button("Apply");
    private Button change_email = new Button("Change");

    private TextField password = new TextField("New password");
    private TextField repassword = new TextField("Repeat password");
    private Button change_password = new Button("Change pass");
    private Button apply_password = new Button("Apply");

    private CheckBox allow_emails = new CheckBox("Allow emails from this service.");

    public ProfileView() {
        mainlayout = new AbsoluteLayout();
        body = new HorizontalLayout();

        // Styles
        mainlayout.setStyleName("v-main-body");
        body.setStyleName("settingslayout");

        // Add components
        mainlayout.addComponent(body, "left: 0px; top: 10%;");

        // USERNAME
        username.setReadOnly(true);
        newusername.setVisible(false);
        newusername.addValidator(new usernameValidator());
        apply_username.setVisible(false);
        apply_username.setEnabled(false);

        apply_username.addClickListener(e -> {
           IUserService iUserService = new IUserService();

            try {
                Users user =  iUserService.getElById(usernameString);
                user.setNickName(newusername.getValue());
                iUserService.update(user);

                username.setReadOnly(false);
                username.setValue(newusername.getValue());
                username.setReadOnly(true);

                newusername.setValue("");

                newusername.setVisible(false);
                apply_username.setVisible(false);
                apply_username.setEnabled(false);
                change_username.setVisible(true);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });

        change_username.addClickListener(e -> {
            newusername.setVisible(true);
            apply_username.setVisible(true);
            change_username.setVisible(false);
        });

        // FIRST NAME
        fname.setReadOnly(true);
        newfname.setVisible(false);
        newfname.addValidator(new emptyValidator());
        apply_fname.setVisible(false);
        apply_fname.setEnabled(false);

        apply_fname.addClickListener(e -> {
            IUserService iUserService = new IUserService();

            try {
                Users user =  iUserService.getElById(usernameString);
                user.setFname(newfname.getValue());
                iUserService.update(user);

                fname.setReadOnly(false);
                fname.setValue(newfname.getValue());
                fname.setReadOnly(true);

                newfname.setValue("");

                newfname.setVisible(false);
                apply_fname.setVisible(false);
                apply_fname.setEnabled(false);
                change_fname.setVisible(true);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        change_fname.addClickListener(e -> {
            newfname.setVisible(true);
            apply_fname.setVisible(true);
            change_fname.setVisible(false);
        });

        // EMAIL
        email.setReadOnly(true);
        newemail.setVisible(false);
        newemail.addValidator(new emailValidator());
        apply_email.setVisible(false);
        apply_email.setEnabled(false);

        apply_email.addClickListener(e -> {
            IUserService iUserService = new IUserService();

            try {
                Users user = (Users) iUserService.getElById(usernameString);
                user.setEmail(newemail.getValue());
                getSession().setAttribute("user", newemail.getValue());
                usernameString = newemail.getValue();
                iUserService.update(user);

                email.setReadOnly(false);
                email.setValue(newemail.getValue());
                email.setReadOnly(true);

                newemail.setValue("");

                newemail.setVisible(false);
                apply_email.setVisible(false);
                apply_email.setEnabled(false);
                change_email.setVisible(true);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        change_email.addClickListener(e -> {
            newemail.setVisible(true);
            apply_email.setVisible(true);
            change_email.setVisible(false);
        });

        // PASSWORD
        password.setVisible(false);
        repassword.setVisible(false);
        repassword.addValidator(new passwordValidator());
        apply_password.setVisible(false);
        apply_password.setEnabled(false);

        apply_password.addClickListener(e -> {
            IUserService iUserService = new IUserService();

            try {
                Users user = (Users) iUserService.getElById(usernameString);
                user.setPassword(repassword.getValue());
                iUserService.update(user);

                password.setValue("");
                repassword.setValue("");

                password.setVisible(false);
                repassword.setVisible(false);

                apply_password.setVisible(false);
                apply_password.setEnabled(false);

                change_password.setVisible(true);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        change_password.addClickListener(e -> {
            password.setVisible(true);
            repassword.setVisible(true);
            apply_password.setVisible(true);
            change_password.setVisible(false);
        });

        change_username.setStyleName("main-button");
        change_fname.setStyleName("main-button");
        change_email.setStyleName("main-button");
        change_password.setStyleName("main-button");

        apply_username.setStyleName("super-button");
        apply_fname.setStyleName("super-button");
        apply_email.setStyleName("super-button");
        apply_password.setStyleName("super-button");

        username.setWidth(100, Unit.PERCENTAGE);
        newusername.setWidth(100, Unit.PERCENTAGE);
        apply_username.setWidth(80, Unit.PERCENTAGE);
        change_username.setWidth(80, Unit.PERCENTAGE);

        fname.setWidth(100, Unit.PERCENTAGE);
        newfname.setWidth(100, Unit.PERCENTAGE);
        apply_fname.setWidth(80, Unit.PERCENTAGE);
        change_fname.setWidth(80, Unit.PERCENTAGE);

        email.setWidth(100, Unit.PERCENTAGE);
        newemail.setWidth(100, Unit.PERCENTAGE);
        apply_email.setWidth(80, Unit.PERCENTAGE);
        change_email.setWidth(80, Unit.PERCENTAGE);

        password.setWidth(100, Unit.PERCENTAGE);
        repassword.setWidth(100, Unit.PERCENTAGE);
        apply_password.setWidth(80, Unit.PERCENTAGE);
        change_password.setWidth(100, Unit.PERCENTAGE);

        content.addComponent(role);

        content.addComponent(username);
        content.addComponent(newusername);
        content.addComponent(apply_username);
        content.addComponent(change_username);

        content.addComponent(fname);
        content.addComponent(newfname);
        content.addComponent(apply_fname);
        content.addComponent(change_fname);

        content.addComponent(email);
        content.addComponent(newemail);
        content.addComponent(apply_email);
        //content.addComponent(change_email);

        content.addComponent(new Label("Password"));
        content.addComponent(password);
        content.addComponent(repassword);
        content.addComponent(apply_password);
        content.addComponent(change_password);

        content.addComponent(allow_emails);

        content.setComponentAlignment(username, Alignment.MIDDLE_CENTER);
        content.setComponentAlignment(newusername, Alignment.MIDDLE_CENTER);
        content.setComponentAlignment(apply_username, Alignment.MIDDLE_CENTER);
        content.setComponentAlignment(change_username, Alignment.MIDDLE_CENTER);

        content.setComponentAlignment(fname, Alignment.MIDDLE_CENTER);
        content.setComponentAlignment(newfname, Alignment.MIDDLE_CENTER);
        content.setComponentAlignment(apply_fname, Alignment.MIDDLE_CENTER);
        content.setComponentAlignment(change_fname, Alignment.MIDDLE_CENTER);

        content.setComponentAlignment(email, Alignment.MIDDLE_CENTER);
        content.setComponentAlignment(newemail, Alignment.MIDDLE_CENTER);
        content.setComponentAlignment(apply_email, Alignment.MIDDLE_CENTER);
        //content.setComponentAlignment(change_email, Alignment.MIDDLE_CENTER);

        content.setComponentAlignment(password, Alignment.MIDDLE_CENTER);
        content.setComponentAlignment(repassword, Alignment.MIDDLE_CENTER);
        content.setComponentAlignment(apply_password, Alignment.MIDDLE_CENTER);
        content.setComponentAlignment(change_password, Alignment.MIDDLE_CENTER);

        content.setComponentAlignment(allow_emails, Alignment.MIDDLE_CENTER);

        content.setWidth(320, Unit.PIXELS);
        content.setMargin(true);
        content.setSpacing(true);
        body.addComponent(content);
        body.setComponentAlignment(content, Alignment.MIDDLE_CENTER);

        //header.setSizeFull();
        body.setSizeFull();
        mainlayout.setSizeFull();

        setSizeFull();
        setCompositionRoot(mainlayout);

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // Get the user name from the session
        usernameString = String.valueOf(getSession().getAttribute("user"));
        String statusString = String.valueOf(getSession().getAttribute("status"));

        mainlayout.removeAllComponents();

        header = new HeaderLayout(statusString);
        mainlayout.addComponent(body, "left: 0px; top: 10%;");
        mainlayout.addComponent(header, "left: 0px; top: 0px; bottom: 90%;");
        role.setValue("Status: " + statusString);

        IUserService iUserService = new IUserService();
        try {
            Users user = iUserService.getElById(usernameString);

            username.setReadOnly(false);
            username.setValue(user.getNickName());
            username.setReadOnly(true);

            fname.setReadOnly(false);
            fname.setValue(user.getFname());
            fname.setReadOnly(true);

            email.setReadOnly(false);
            email.setValue(user.getEmail());
            email.setReadOnly(true);

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    private final class usernameValidator extends
            AbstractValidator<String> {

        public usernameValidator() {
            super("Username is already exist!");
        }

        @Override
        protected boolean isValidValue(String value) {
            if(value.isEmpty()) {
                apply_username.setEnabled(false);
                return false;
            }
            IUserService iUserService = new IUserService();
            try {
                if(!iUserService.isUsernameExist(newusername.getValue()))
                    apply_username.setEnabled(true);
                    return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            apply_username.setEnabled(false);
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
            apply_password.setEnabled(!repassword.isEmpty() && repassword.getValue().matches(password.getValue()));
            if(repassword.isEmpty())
                return true;

            if (repassword.getValue().matches(password.getValue())) {
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
            apply_email.setEnabled(value.isEmpty() || !isValidEmailAddress(value));
            return true;
        }

        @Override
        public Class<String> getType() {
            return String.class;
        }
    }

    private final class emptyValidator extends
            AbstractValidator<String> {
        private boolean isUsername;

        public emptyValidator() {
            super("Cannot be empty");
        }

        @Override
        protected boolean isValidValue(String value) {
            apply_fname.setEnabled(!value.isEmpty());
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
}
