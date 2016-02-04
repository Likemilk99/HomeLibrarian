package frontend;

import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.vaadin.annotations.*;


import javax.servlet.annotation.WebServlet;
//import java.awt.*;

/**
 * Created by likemilk on 29.12.2015.
 */
@Theme("mytheme")
@Widgetset("newtest.MyAppWidgetset")
public class MainUI extends UI{
    Layout HL1 = new HightLayout();
    Layout TB = new TableBooks();

    Button button_1 = new Button("Add");
    Button button_2 = new Button("Delete");
    Button button_3 = new Button("Search");

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        CreateWindow();
    }

    void CreateWindow(){

        HorizontalLayout test= new HorizontalLayout();
        test.addComponent(new Label("HI Hi HI"));
        test.setHeight("70%");
        test.setStyleName("borders");

        HorizontalLayout test1 = new HorizontalLayout(button_1, button_2, button_3);

        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setStyleName("borders");
        mainLayout.addComponent(HL1);
        mainLayout.addComponent(TB);
        mainLayout.addComponent(new Label("tetstststs"));
        setContent(mainLayout);

    }
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MainUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }

    void CeateWindow_1() {
        HorizontalLayout MainWin= new HorizontalLayout();

        VerticalLayout HeaderLayout = new VerticalLayout();
        MenuBar barmenu = new MenuBar();
        barmenu.addItem("test_1",null,null);
        barmenu.addItem("test_2",null,null);

        MainWin.addComponent(barmenu) ;
    }
}
