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
import frontend.elements.metro.MetroBook;
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
    private VerticalLayout body;
    private MetroBook metro;

    public MainView() {
        // Layouts
        metro = MetroBook.getInstance();
        mainlayout = new AbsoluteLayout();
       // mainlayout = new VerticalLayout();
        body = new VerticalLayout();

        //header.setHeight(10, Unit.PERCENTAGE);

        //body.setStyleName("v-body");
        mainlayout.setStyleName("v-main-body");
        body.setStyleName("bodylayout");

        body.addComponent(metro);
        // Add components

       // mainlayout.addComponent(header);
       //mainlayout.addComponent(body);

        body.setSizeFull();
        mainlayout.setSizeFull();

        setSizeFull();
        setCompositionRoot(mainlayout);
        body.setComponentAlignment(metro, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // Get the user name from the session
        String username = String.valueOf(getSession().getAttribute("user"));
        String status = String.valueOf(getSession().getAttribute("status"));

        MetroBook.getInstance().updateTable(username);

        mainlayout.removeAllComponents();

        header = new HeaderLayout(status);
        mainlayout.addComponent(body, "left: 0px; top: 10%;");
        mainlayout.addComponent(header, "left: 0px; top: 0px; bottom: 90%;");
    }
}