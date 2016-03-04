package frontend.views;

import Data.Books;
import com.google.gwt.thirdparty.guava.common.collect.Lists;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.*;
import frontend.elements.gridbooks.GridBooks;

import java.util.List;

/**
 * Created by Александр on 07.02.2016.
 */
public class MainView extends CustomComponent implements View {

    public static final String NAME = "";

    Label text = new Label();

    Button logout = new Button("Logout", new Button.ClickListener() {

        @Override
        public void buttonClick(Button.ClickEvent event) {

            // "Logout" the user
            getSession().setAttribute("user", null);

            // Refresh this view, should redirect to login view
            getUI().getNavigator().navigateTo(NAME);
        }
    });

    Button button_1 = new Button("Add");
    Button button_2 = new Button("Menu");
    Button button_3 = new Button("Search");

    public MainView() {
        // And show the username
        //text.setValue("Hello " + username);
        VerticalLayout mainLayout = new VerticalLayout();
        setCompositionRoot(mainLayout);
        //setContent(mainLayout);
        final HorizontalLayout body = new HorizontalLayout();
        final VerticalLayout leftbody = new VerticalLayout();
        HorizontalLayout midbody = new HorizontalLayout();
        HorizontalLayout header = new HorizontalLayout(button_1, button_2, button_3, logout);

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

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // Get the user name from the session
        String username = String.valueOf(getSession().getAttribute("user"));
        String status = String.valueOf(getSession().getAttribute("status"));
    }
}