package frontend.views;

import Data.Books;
import com.google.gwt.thirdparty.guava.common.collect.Lists;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.*;
import frontend.elements.gridbooks.GridBooks;
import frontend.elements.tableusers.TableUsers;

import java.util.List;

/**
 * Created by Александр on 07.02.2016.
 */
public class MainView extends CustomComponent implements View {

    public static final String NAME = "";

    Label text = new Label();

    Button button_logout = new Button("Logout", new Button.ClickListener() {

        @Override
        public void buttonClick(Button.ClickEvent event) {

            // "Logout" the user
            getSession().setAttribute("user", null);

            // Refresh this view, should redirect to login view
            getUI().getNavigator().navigateTo(NAME);
        }
    });

    Button button_main = new Button("Main");
    Button button_profile = new Button("Profile");
    Button button_search = new Button("Search");

    Button button_add = new Button("Add");
    Button button_mail = new Button("Mail");

    Button button_manage = new Button("Manage");

    public MainView() {
        // And show the username
        //text.setValue("Hello " + username);
        VerticalLayout mainLayout = new VerticalLayout();
        setCompositionRoot(mainLayout);
        //setContent(mainLayout);
        final HorizontalLayout body = new HorizontalLayout();
        body.setSizeFull();
        final VerticalLayout leftbody = new VerticalLayout();
        leftbody.setHeight(100, Unit.PERCENTAGE);
        //leftbody.setWidth(20, Unit.PERCENTAGE);
        HorizontalLayout midbody = new HorizontalLayout();
        midbody.setHeight(100, Unit.PERCENTAGE);
        HorizontalLayout header_basic = new HorizontalLayout(button_main, button_profile, button_search);
        // TODO проверка прав пользователя
        HorizontalLayout header_pro = new HorizontalLayout();
        header_pro.addComponent(button_add);
        header_pro.addComponent(button_mail);
        header_pro.addComponent(button_manage);

        HorizontalLayout header_logout = new HorizontalLayout(button_logout);

        header_basic.setSpacing(true);
        header_pro.setSpacing(true);
        header_logout.setSpacing(true);

        HorizontalLayout header = new HorizontalLayout(header_basic, header_pro, header_logout);
        header.setComponentAlignment(header_basic, Alignment.MIDDLE_LEFT);
        header.setComponentAlignment(header_pro, Alignment.MIDDLE_CENTER);
        header.setComponentAlignment(header_logout, Alignment.MIDDLE_RIGHT);
        header.setWidth(100, Unit.PERCENTAGE);
        header.setSizeFull();
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

        /*
        final GridBooks Grid = new GridBooks();
        Grid.SetBookCollection(list);
        Grid.DrowGrid((int) (UI.getCurrent().getPage().getBrowserWindowWidth() - leftbody.getWidth()));
        */
        final TableUsers Table = new TableUsers();
        //--------------------------

        // Attributions
        mainLayout.setSpacing(true);
        //mainLayout.setMargin(false);
        leftbody.setSpacing(true);
        // leftbody.setMargin(true);

        // Styles
        //mainLayout.setStyleName("borders");
        //header.setStyleName("borders");
        //body.setStyleName("borders");
        leftbody.setStyleName("newsidebar-in");

        // Sizes
        //mainLayout.setWidth(100, Unit.PERCENTAGE);
        //mainLayout.setHeightUndefined();
        body.setWidth(100, Unit.PERCENTAGE);
        //leftbody.setWidth(20, Unit.PERCENTAGE);
        //leftbody.setWidth(306, Unit.PIXELS);
        //button_1.setWidth(102, Unit.PIXELS);
        //button_2.setWidth(102, Unit.PIXELS);
        //button_3.setWidth(102, Unit.PIXELS);

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

        Table.setHeight(100, Unit.PERCENTAGE);
        midbody.addComponent(leftbody);
        midbody.addComponent(Table);
        body.addComponent(midbody);
        mainLayout.addComponent(header);
        mainLayout.addComponent(body);
        //mainLayout.setExpandRatio(body, 1.0f);
        //mainLayout.setExpandRatio(header, 0.1f);
        // mainLayout.setComponentAlignment(body, Alignment.TOP_LEFT);
        mainLayout.setImmediate(true);

        mainLayout.setHeight(100, Unit.PERCENTAGE);
        UI.getCurrent().getPage().addBrowserWindowResizeListener(new Page.BrowserWindowResizeListener() {
            @Override
            public void browserWindowResized(Page.BrowserWindowResizeEvent event) {
                //Grid.DrowGrid((int)( UI.getCurrent().getPage().getBrowserWindowWidth()-leftbody.getWidth()));
                //Table.setWidth(UI.getCurrent().getPage().getBrowserWindowWidth()-leftbody.getWidth());

            }
        });

        button_profile.addClickListener(new Button.ClickListener() {
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
                    //Grid.DrowGrid((int) (UI.getCurrent().getPage().getBrowserWindowWidth() ));
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
                //Grid.DrowGrid((int) (UI.getCurrent().getPage().getBrowserWindowWidth() -leftbody.getWidth() ));
            }
        });

        button_search.addClickListener(new Button.ClickListener() {
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