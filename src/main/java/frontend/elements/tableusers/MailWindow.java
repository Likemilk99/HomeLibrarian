package frontend.elements.tableusers;

import Data.Address;
import Data.Users;
import com.vaadin.annotations.Theme;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Page;
import com.vaadin.ui.*;
import mail.Sender;
import mail.TemplateMails;

import java.util.ArrayList;
import java.util.List;

@Theme("mytheme")
public class MailWindow extends Window {
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

    private BeanItemContainer<Address> dataSource;

    public MailWindow(List <Address> list) {

        super("Mail"); // Set window caption
        center();
        // Some basic content for the window
        setHeight(465, Unit.PIXELS);
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
        dataSource = new BeanItemContainer<>(Address.class);
        table.setContainerDataSource(dataSource);
        dataSource.addAll(list);



//        table.addContainerProperty("Name", String.class, null);
  //      table.addContainerProperty("Mail", String.class, null);
        table.setHeight(100, Unit.PERCENTAGE);
        table.setWidth(100, Unit.PERCENTAGE);

        addresses = new Panel();
        addresses.setHeight(100, Unit.PERCENTAGE);
        addresses.setContent(table);

        body = new HorizontalLayout();
        body.setWidth(100, Unit.PERCENTAGE);
        body.addComponent(text);
        body.addComponent(addresses);
        body.setSpacing(true);
        //    body.setMargin(true);

        button_close = new Button("close");
        button_close.addClickListener(event -> close());

        button_send = new Button("send");
        button_send.addClickListener(event -> {
            Sender sender = new Sender();
            for(Address el : list)
                sender.send(text.getValue(),el.getEmail());
            close();
        });
        button_close.setWidth(60, Unit.PERCENTAGE);
        button_send.setWidth(60, Unit.PERCENTAGE);

        footer = new HorizontalLayout(button_send, button_close);
        footer.setWidth(100, Unit.PERCENTAGE);
        //    footer.setHeight(0, Unit.PERCENTAGE);

        footer.setSpacing(true);
        body.addComponent(text);
        body.addComponent(addresses);
        leftBody.addComponent(header);
        leftBody.addComponent(body);
        leftBody.addComponent(footer);


        int i=0;
        for (Address el : list) {

         //   table.addItem(el);
         //   i++;
        }
        content = new HorizontalLayout();

        content.addComponent(leftBody);
        //content.addComponent(addresses);
        content.setSizeFull();
        content.setSpacing(true);

        setContent(content);
        setModal(true);
        setResizable(false);
        text.setInputPrompt("Input text...");
        box_mails.addListener((Property.ValueChangeListener) event -> {
            String str = (String) box_mails.getValue();
            //    text.setValue(str);
            if (str.equals("Registration")) {
                text.setValue(TemplateMails.MAIL_REG.getMail());
            } else if (str.equals("Message")) {
                text.setValue(TemplateMails.MAIL_MESS.getMail());
            }

        });

        setClosable(false);
    }


}
