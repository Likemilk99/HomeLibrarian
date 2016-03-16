package frontend.elements.components;

import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.ui.*;

/**
 * Created by Лукерия on 16.03.2016.
 */
public class RegistrationWin extends Window {
    private VerticalLayout content;
    private TextField username;
    private PasswordField password;
    private PasswordField reenter;
    private Label date;
    private HorizontalLayout date_layout;
    private NativeSelect day;
    private NativeSelect month;
    private NativeSelect year;
    private TextField email;
    private Button signup;
    public RegistrationWin() {
        setCaption("Registration");

        content = new VerticalLayout();
        content.setWidth(100, Unit.PERCENTAGE);
        content.setMargin(true);
        content.setSpacing(true);
        setContent(content);

        username = new TextField("Username:");
        username.setRequired(true);
        username.setWidth(50, Unit.PERCENTAGE);
        username.setStyleName("main-edit");
        username.setResponsive(true);

        password = new PasswordField("Password:");
        password.addValidator(new PasswordReenterValidator());
        password.setRequired(true);
        password.setWidth(50, Unit.PERCENTAGE);
        password.setStyleName("main-edit");
        password.setResponsive(true);

        reenter = new PasswordField("Reenter password:");
        reenter.addValidator(new PasswordReenterValidator());
        reenter.setRequired(true);
        reenter.setWidth(50, Unit.PERCENTAGE);
        reenter.setStyleName("main-edit");
        reenter.setResponsive(true);

        date = new Label("Date of birth:");
        date.setWidth(50, Unit.PERCENTAGE);
        date.setStyleName("label");
        date.setResponsive(true);

        date_layout = new HorizontalLayout();
        day = new NativeSelect("Day: ");
        for (int i=1; i<=31; i++) {
            day.addItem(i);
            day.setItemCaption(i, Integer.toString(i));
        }
        day.setValue(1);
        day.setNullSelectionAllowed(false);
        day.setWidth(100, Unit.PERCENTAGE);
        day.setStyleName("main-select");
        day.setResponsive(true);

        month = new NativeSelect("Month: ");
        for (int i=1; i<=12; i++) {
            month.addItem(i);
            month.setItemCaption(i, Integer.toString(i));
        }
        month.setValue(1);
        month.setNullSelectionAllowed(false);
        month.setWidth(100, Unit.PERCENTAGE);
        month.setStyleName("main-select");
        month.setResponsive(true);

        year = new NativeSelect("Year: ");
        for (int i=java.util.Calendar.getInstance().get(java.util.Calendar.YEAR); i>1900; --i) {
            year.addItem(i);
            year.setItemCaption(i, Integer.toString(i));
        }
        year.setValue(java.util.Calendar.getInstance().get(java.util.Calendar.YEAR));
        year.setNullSelectionAllowed(false);
        year.setWidth(100, Unit.PERCENTAGE);
        year.setStyleName("main-select");
        year.setResponsive(true);

        date_layout.addComponent(day);
        date_layout.addComponent(month);
        date_layout.addComponent(year);
        date_layout.setSpacing(true);
        date_layout.setWidth(50, Unit.PERCENTAGE);

        email = new TextField("Email:");
        email.addValidator(new EmailValidator(
                "Username must be an email address"));
        email.setRequired(true);
        email.setWidth(50, Unit.PERCENTAGE);
        email.setStyleName("main-edit");
        email.setResponsive(true);

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

        signup = new Button("SIGNUP");
        signup.setWidth(40, Unit.PERCENTAGE);
        signup.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                if (!newuserButtonClick(username.getValue(),
                        password.getValue(),
                        day.getCaption(),
                        month.getCaption(),
                        year.getCaption(),
                        email.getValue()
                        //secret.getValue(),
                        //answer.getValue()
                )) {
                    password.setValue("");
                    reenter.setValue("");
                }
            }
        });
        signup.setStyleName("super-button");
        signup.setResponsive(true);

        // Put some components in it
        content.addComponent(username);
        content.addComponent(password);
        content.addComponent(reenter);
        content.addComponent(date);
        content.addComponent(date_layout);
        content.addComponent(email);
        //subContent.addComponent(secret);
        // subContent.addComponent(answer);
        content.addComponent(signup);

        content.setComponentAlignment(username, Alignment.MIDDLE_CENTER);
        content.setComponentAlignment(password, Alignment.MIDDLE_CENTER);
        content.setComponentAlignment(reenter, Alignment.MIDDLE_CENTER);
        content.setComponentAlignment(date, Alignment.MIDDLE_CENTER);
        content.setComponentAlignment(date_layout, Alignment.MIDDLE_CENTER);
        content.setComponentAlignment(email, Alignment.MIDDLE_CENTER);
        //subContent.setComponentAlignment(secret, Alignment.MIDDLE_CENTER);
        //subContent.setComponentAlignment(answer, Alignment.MIDDLE_CENTER);
        content.setComponentAlignment(signup, Alignment.MIDDLE_CENTER);
        // Center it in the browser window
        center();
        setWidth(100, Unit.PERCENTAGE);
        setModal(true);
        addStyleName("mainlayout");
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
            if (reenter.getValue().matches(password.getValue())) {
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

    protected boolean newuserButtonClick(String user,
                                         String pass,
                                         String day,
                                         String month,
                                         String year,
                                         String email
                                         //String secret,
                                         // String answer
    ) {
        // TODO SQL
        return false;
    }
}
