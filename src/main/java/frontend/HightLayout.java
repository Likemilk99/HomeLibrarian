package frontend;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;

/**
 * Created by likemilk on 30.12.2015.
 */
public class HightLayout extends HorizontalLayout{
    Button button_1 = new Button("Add");
    Button button_2 = new Button("Delete");
    Button button_3 = new Button("Search");
    Button button_4 = new Button("LogIn");

    public HightLayout() {
        setDefaultComponentAlignment(Alignment.TOP_LEFT);
        addComponent(button_1);
        addComponent(button_2);
        addComponent(button_3);
        addComponent(button_4);

       // Configuration

       // setHeight("100%");
        //setWidth("50%");
        setStyleName("borders");
    }
}
