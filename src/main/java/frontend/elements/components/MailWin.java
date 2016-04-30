package frontend.elements.components;

import com.vaadin.annotations.Theme;
import com.vaadin.data.Property;
import com.vaadin.ui.*;

@Theme("mytheme")
public class MailWin extends Window {
    private Table table;
    private Button button_close;
    private Button button_send;
    private TextArea text;
    private ComboBox box_mails;
    private HorizontalLayout content;
    private VerticalLayout leftBody;
    private Panel addresses;
    private HorizontalLayout header;
    private HorizontalLayout body;
    private HorizontalLayout footer;

    public MailWin() {
        super("Mail"); // Set window caption
        center();

        // Some basic content for the window
        setHeight(450, Unit.PIXELS);
        setWidth(800, Unit.PIXELS);
        setCaption("Send mail");
        leftBody = new VerticalLayout();

        box_mails = new ComboBox(null);
        box_mails.setWidth(100, Unit.PERCENTAGE);
        Object itemId = box_mails.addItem();
        box_mails.setItemCaption(itemId, "custom");
        box_mails.addItem("Registration");
        box_mails.addItem("Message");
        box_mails.setValue(itemId);
        box_mails.setNullSelectionAllowed(false);
        // leftBody.addComponent(box_mails);
        leftBody.setWidth(100, Unit.PERCENTAGE);
        //    leftBody.setMargin(true);
        leftBody.setSpacing(true);

        text = new TextArea();
        text.setHeight(300, Unit.PIXELS);
        text.setWidth(100, Unit.PERCENTAGE);
        //  leftBody.addComponent(text);

        header = new HorizontalLayout();
        header.setWidth(100, Unit.PERCENTAGE);
        header.addComponent(box_mails);
        header.addComponent(new Label("Send to:"));
        header.setSpacing(true);


        table = new Table();
        table.addContainerProperty("Name", String.class, null);
        table.addContainerProperty("Mail", String.class, null);
        table.setHeight(100, Unit.PERCENTAGE);
        table.setWidth(100, Unit.PERCENTAGE);

        addresses = new Panel();
        addresses.setHeight(100, Unit.PERCENTAGE);
        addresses.setContent(table);
        Object user[][] = {{
                "tettttttttttttttst", "test"
        }
        };

        body = new HorizontalLayout();
        body.setWidth(100, Unit.PERCENTAGE);
        body.addComponent(text);
        body.addComponent(addresses);
        body.setSpacing(true);
        //    body.setMargin(true);

        button_close = new Button("close");
        button_close.addClickListener(event -> {
            close();
        });

        button_send = new Button("send");
        button_close.setWidth(60, Unit.PERCENTAGE);
        button_send.setWidth(60, Unit.PERCENTAGE);

        footer = new HorizontalLayout(button_send, button_close);
        footer.setWidth(100, Unit.PERCENTAGE);
        //    footer.setHeight(0, Unit.PERCENTAGE);
        //  footer.setStyleName("v-header");


        //   footer.setMargin(true);
        footer.setSpacing(true);
        body.addComponent(text);
        body.addComponent(addresses);
        leftBody.addComponent(header);
        leftBody.addComponent(body);
        leftBody.addComponent(footer);


        for (int i = 0; i < 30; i++) {
            table.addItem(new Object[]{"Canopus", "test"}, i);
        }
        content = new HorizontalLayout();

        content.addComponent(leftBody);
        //content.addComponent(addresses);
        content.setSizeFull();
        content.setSpacing(true);

        content.setStyleName("sebdmaillayout");
        setContent(content);
        setModal(true);
        setResizable(false);
        text.setInputPrompt("Input text...");
        box_mails.addListener((Property.ValueChangeListener) event -> {
            String str = (String) box_mails.getValue();
            //    text.setValue(str);
            if (str.equals("Registration")) {
                text.setValue("koko");
            } else if (str.equals("Message")) {
                text.setValue("lololo");
            }

        });

        setClosable(false);

        // Trivial logic for closing the sub-window


    }
}