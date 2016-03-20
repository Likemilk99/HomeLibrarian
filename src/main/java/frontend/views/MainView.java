package frontend.views;

import Data.Books;
import com.google.gwt.thirdparty.guava.common.collect.Lists;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.*;
import frontend.elements.components.HeaderLayout;
import frontend.elements.gridbooks.GridBooks;
import frontend.elements.tableusers.TableUsers;
//import org.vaadin.virkki.carousel.HorizontalCarousel;
//import org.vaadin.virkki.carousel.client.widget.gwt.ArrowKeysMode;
//import org.vaadin.virkki.carousel.client.widget.gwt.CarouselLoadMode;

import java.util.List;

/**
 * Created by Александр on 07.02.2016.
 */
@Theme("mytheme")
public class MainView extends CustomComponent implements View {

    public static final String NAME = "";

    private AbsoluteLayout mainlayout;
    private HeaderLayout header;
    private HorizontalLayout body;

    public MainView() {
        // Layouts
        mainlayout = new AbsoluteLayout();
        header = new HeaderLayout();
        body = new HorizontalLayout(new Label("body"));

        //final HorizontalCarousel carousel = new HorizontalCarousel();

        // Only react to arrow keys when focused
        //carousel.setArrowKeysMode(ArrowKeysMode.FOCUS);
        // Fetch children lazily
        //carousel.setLoadMode(CarouselLoadMode.LAZY);
        // Transition animations between the children run 500 milliseconds
        //carousel.setTransitionDuration(500);

        // Add the child Components
        //carousel.addComponent(new Button("First child"));
        //carousel.addComponent(new Label("Second child"));
        //carousel.addComponent(new TextField("Third child"));
        // Add the Carousel to a parent layout
        //body.addComponent(carousel);
        body.addComponent(new Label("body"));

        // Styles
        //header.setStyleName("v-header");
        body.setStyleName("v-body");
        mainlayout.setStyleName("v-main-body");

        // Attributions
        //header.setMargin(true);

        // Add components
        mainlayout.addComponent(header, "left: 0px; top: 0px;");
        mainlayout.addComponent(body, "left: 0px; top: 10%;");

        //header.setSizeFull();
        body.setSizeFull();
        mainlayout.setSizeFull();

        setSizeFull();
        setCompositionRoot(mainlayout);

        //конструктор грида временно
        /*Books el = new Books();
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
        list.add(el);*/

        /*
        final GridBooks Grid = new GridBooks();
        Grid.SetBookCollection(list);
        Grid.DrowGrid((int) (UI.getCurrent().getPage().getBrowserWindowWidth() - leftbody.getWidth()));
        */
       // final TableUsers Table = new TableUsers();
        //--------------------------



        // Styles
        //mainLayout.setStyleName("borders");
        //header.setStyleName("borders");
        //body.setStyleName("borders");
        //leftbody.setStyleName("newsidebar-in");

        // Sizes
        //mainLayout.setWidth(100, Unit.PERCENTAGE);
        //mainLayout.setHeightUndefined();
        //body.setWidth(100, Unit.PERCENTAGE);
        //leftbody.setWidth(20, Unit.PERCENTAGE);
        //leftbody.setWidth(306, Unit.PIXELS);
        //button_1.setWidth(102, Unit.PIXELS);
        //button_2.setWidth(102, Unit.PIXELS);
        //button_3.setWidth(102, Unit.PIXELS);

        // add companens
        /*final Image Face =  new Image(null, new ThemeResource("Images/test.jpg"));
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

        mainLayout.setHeight(100, Unit.PERCENTAGE);*/
        /*UI.getCurrent().getPage().addBrowserWindowResizeListener(new Page.BrowserWindowResizeListener() {
            @Override
            public void browserWindowResized(Page.BrowserWindowResizeEvent event) {
                //Grid.DrowGrid((int)( UI.getCurrent().getPage().getBrowserWindowWidth()-leftbody.getWidth()));
                //Table.setWidth(UI.getCurrent().getPage().getBrowserWindowWidth()-leftbody.getWidth());

            }
        });*/
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // Get the user name from the session
        String username = String.valueOf(getSession().getAttribute("user"));
        String status = String.valueOf(getSession().getAttribute("status"));
    }
}