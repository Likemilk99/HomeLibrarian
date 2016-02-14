package frontend;

import Data.Books;
import com.google.gwt.thirdparty.guava.common.collect.Lists;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.vaadin.annotations.*;


import javax.servlet.annotation.WebServlet;
import java.util.List;
//import java.awt.*;

/**
 * Created by likemilk on 29.12.2015.
 */
@Title("login")
@Theme("mytheme")
@Widgetset("newtest.MyAppWidgetset")
public class MainUI extends UI{
    Layout HL1 = new HightLayout();
    Layout TB = new TableBooks();

    Button button_1 = new Button("Add");
    Button button_2 = new Button("Menu");
    Button button_3 = new Button("Search");

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        CreateWindow_2();

/*
        //
        // Create a new instance of the navigator. The navigator will attach
        // itself automatically to this view.
        //
        new Navigator(this, this);

        //
        // The initial log view where the user can login to the application
        //
        getNavigator().addView(SimpleLoginView.NAME, SimpleLoginView.class);//

        //
        // Add the main view of the application
        //
        getNavigator().addView(SimpleLoginMainView.NAME,
                SimpleLoginMainView.class);

        //
        // We use a view change handler to ensure the user is always redirected
        // to the login view if the user is not logged in.
        //
        getNavigator().addViewChangeListener(new ViewChangeListener() {

            @Override
            public boolean beforeViewChange(ViewChangeEvent event) {

                // Check if a user has logged in
                boolean isLoggedIn = getSession().getAttribute("user") != null;
                boolean isLoginView = event.getNewView() instanceof SimpleLoginView;

                if (!isLoggedIn && !isLoginView) {
                    // Redirect to login view always if a user has not yet
                    // logged in
                    getNavigator().navigateTo(SimpleLoginView.NAME);
                    return false;

                } else if (isLoggedIn && isLoginView) {
                    // If someone tries to access to login view while logged in,
                    // then cancel
                    return false;
                }

                return true;
            }

            @Override
            public void afterViewChange(ViewChangeEvent event) {

            }
        });*/
    }


    void CreateWindow_2(){

        VerticalLayout mainLayout = new VerticalLayout();
        setContent(mainLayout);
        final HorizontalLayout body = new HorizontalLayout();
        final VerticalLayout leftbody = new VerticalLayout();
        HorizontalLayout midbody = new HorizontalLayout();
        HorizontalLayout header = new HorizontalLayout(button_1, button_2, button_3);

        //конструктор грида временно
        Books el = new Books();
        el.Books("test","test", "test", "test");
        List<Books> list = Lists.newArrayList();
        list.add(el);
        list.add(el);
        list.add(el);
        list.add(el);
        list.add(el);
        list.add(el);
        list.add(el);
        list.add(el);
        list.add(el);
        list.add(el);
        list.add(el);

        final GridBooks Grid = new GridBooks();
        Grid.SetBookCollection(list);
        Grid.DrowGrid((int) (UI.getCurrent().getPage().getBrowserWindowWidth() - leftbody.getWidth()));
        //--------------------------

        // Attributions
        mainLayout.setSpacing(false);
        mainLayout.setMargin(false);
        leftbody.setSpacing(true);
        // leftbody.setMargin(true);

        // Styles
        mainLayout.setStyleName("borders");
        header.setStyleName("borders");
        body.setStyleName("borders");
        leftbody.setStyleName("newsidebar-in");



        // Sizes
        mainLayout.setWidth(100, Unit.PERCENTAGE);
        mainLayout.setHeightUndefined();
        body.setWidth(100, Unit.PERCENTAGE);
        leftbody.setWidth(306, Unit.PIXELS);
        button_1.setWidth(102, Unit.PIXELS);
        button_2.setWidth(102, Unit.PIXELS);
        button_3.setWidth(102, Unit.PIXELS);

        // add companens
        final Image Face =  new Image(null, new ThemeResource("Images/test.jpg"));
        final Label L1 = new Label("My name");
        final Button BarButton_1 = new Button("Information");
        final Button BarButton_2 = new Button("Add Book");
        final Button BarButton_3 = new Button("Delete Book");
        BarButton_1.setStyleName("tooltip-out");
        L1.setStyleName("tooltip-out");
        Face.setStyleName("circular-out");
        leftbody.addComponent(Face);
        leftbody.addComponent(L1);
        leftbody.addComponent(BarButton_1);
        leftbody.addComponent(BarButton_2);
        leftbody.addComponent(BarButton_3);
        leftbody.setComponentAlignment(Face, Alignment.TOP_CENTER);
        leftbody.setComponentAlignment(L1, Alignment.TOP_CENTER);
        leftbody.setComponentAlignment(BarButton_1, Alignment.TOP_CENTER);
        leftbody.setComponentAlignment(BarButton_2, Alignment.TOP_CENTER);
        leftbody.setComponentAlignment(BarButton_3, Alignment.TOP_CENTER);

        midbody.addComponent(leftbody);
        midbody.addComponent(Grid);
        body.addComponent(midbody);
        mainLayout.addComponent(header);
        mainLayout.addComponent(body);
        // mainLayout.setComponentAlignment(body, Alignment.TOP_LEFT);
        mainLayout.setImmediate(true);

        UI.getCurrent().getPage().addBrowserWindowResizeListener(new Page.BrowserWindowResizeListener() {
            @Override
            public void browserWindowResized(Page.BrowserWindowResizeEvent event) {
                Grid.DrowGrid((int)( UI.getCurrent().getPage().getBrowserWindowWidth()-leftbody.getWidth()));

            }
        });

        button_2.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {

                if (leftbody.getStyleName().contains("newsidebar-in")) {
                    leftbody.removeStyleName("newsidebar-in");
                    leftbody.setStyleName("newsidebar-out");
                    L1.removeStyleName("tooltip-out");
                    L1.setStyleName("tooltip-in");
                    BarButton_1.removeStyleName("tooltip-out");
                    BarButton_1.setStyleName("tooltip-in");
                    BarButton_2.removeStyleName("tooltip-out");
                    BarButton_2.setStyleName("tooltip-in");
                    BarButton_3.removeStyleName("tooltip-out");
                    BarButton_3.setStyleName("tooltip-in");
                    Face.removeStyleName("circular-out");
                    Face.setStyleName("circular-in");
                    leftbody.setWidth(0, Unit.PIXELS);
                    Grid.DrowGrid((int) (UI.getCurrent().getPage().getBrowserWindowWidth() ));
                    return;
                }

                leftbody.removeStyleName("newsidebar-out");
                leftbody.setStyleName("newsidebar-in");
                L1.removeStyleName("tooltip-in");
                L1.setStyleName("tooltip-out");
                BarButton_1.removeStyleName("tooltip-in");
                BarButton_1.setStyleName("tooltip-out");
                BarButton_2.removeStyleName("tooltip-in");
                BarButton_2.setStyleName("tooltip-out");
                BarButton_3.removeStyleName("tooltip-in");
                BarButton_3.setStyleName("tooltip-out");
                Face.removeStyleName("circular-in");
                Face.setStyleName("circular-out");
                leftbody.setWidth(306, Unit.PIXELS);
                Grid.DrowGrid((int) (UI.getCurrent().getPage().getBrowserWindowWidth() -leftbody.getWidth() ));
            }
        });

        button_3.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                UI.getCurrent().getPage().getBrowserWindowHeight();
                event.getButton().setCaption(String.valueOf(UI.getCurrent().getPage().getBrowserWindowWidth()));
            }
        });

    }

   @WebServlet(urlPatterns = "/*")//, name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MainUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
