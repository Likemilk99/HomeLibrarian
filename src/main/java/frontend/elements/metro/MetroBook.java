package frontend.elements.metro;

import DAO.BookDAO;
import DAO.Factory;
import DAO.InterfaceDao;
import Data.Books;
import Data.ConstParam;
import com.google.gwt.dom.client.NativeEvent;
import com.vaadin.annotations.JavaScript;
import com.vaadin.event.MouseEvents;
import com.vaadin.server.Page;
import com.vaadin.ui.*;
import frontend.elements.gridbooks.BookImage;
import javafx.scene.layout.Pane;

import java.awt.print.Book;
import java.security.PrivateKey;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by likemilk on 22.02.2016.
 */

@JavaScript({"vaadin://themes/mytheme/js/SmoothScroll_17.js"})
public class MetroBook extends AbsoluteLayout{
    private InterfaceDao bookInterface;
    private int position;
    private int oldScroll = -1;
    private HorizontalLayout hl = new HorizontalLayout();
    private ArrayList<BookImage> list = new ArrayList<BookImage>();
    private static int width;

    private static MetroBook instance;

    public static  MetroBook getInstance() {
        MetroBook localInstance = instance;
        if (localInstance == null) {
            synchronized (MetroBook.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new MetroBook();
                }
            }
        }
        return localInstance;
    }

    private MetroBook() {
        super();
        position = 0;
        Factory factory = new Factory();
        bookInterface = factory.getDAO(BookDAO.class);

        setStyleName("metrolayout");

        Button button_1 = new Button("<");
        Button button_2 = new Button(">");

        button_1.setStyleName("scroll-button");
        button_2.setStyleName("scroll-button");

        Panel p = new Panel();

        p.setStyleName("metro-panel");
        p.setId("panelScroll");
        hl.setId("anchor");

        //updateTable();
        //////////////////////////////////////
        hl.setSpacing(true);
        hl.setMargin(true);
        hl.setHeight(100, Unit.PERCENTAGE);

        p.setContent(hl);
        p.setHeight(100, Unit.PERCENTAGE);
        p.setWidth(100, Unit.PERCENTAGE);
       // p.setSizeFull();

        button_1.setHeight(100, Unit.PERCENTAGE);
        button_2.setHeight(100, Unit.PERCENTAGE);

        button_1.setWidth(100, Unit.PERCENTAGE);
        button_2.setWidth(100, Unit.PERCENTAGE);

        addComponent(p, "left: 10%; right: 10%;");
        addComponent(button_1, "left: 0%; right: 90%;");
        addComponent(button_2, "left: 90%; right: 0%;");

        setSizeFull();

        //setExpandRatio(p, 20);
        //setExpandRatio(button_1, 1);
        //setExpandRatio(button_2, 1);
        //width = UI.getCurrent().getWidth();

        button_1.addClickListener(lambda ->
                    Page.getCurrent().getJavaScript().execute(
                            "smoothScroll('panelScroll', 'left')")
        );

        button_2.addClickListener(lambda -> {
                    if(oldScroll == p.getScrollLeft()) {
                        try {
                            final List<Books> subList = bookInterface.getSubList(position, ConstParam.METRO_PAGE_VALUE);
                            for (Books el : subList)
                                list.add(new BookImage(el, getUI().getSession().getAttribute("user").toString()));
                            for (BookImage el : list) {
                                hl.addComponent(el);
                                el.setHeight(568, Unit.PIXELS);
                                el.setWidth(320, Unit.PIXELS);
                                hl.setComponentAlignment(el, Alignment.MIDDLE_CENTER);
                            }
                            list.clear();

                        } catch (SQLException e) {
                            e.printStackTrace();
                        } finally {
                            position = position + ConstParam.METRO_PAGE_VALUE;
                        }
                    }
                    oldScroll = p.getScrollLeft();
                    Page.getCurrent().getJavaScript().execute(
                            "smoothScroll('panelScroll', 'right')");
                }
        );
    }

    public void updateTable(String user) {
        try {
            hl.removeAllComponents();

            position = 0;
            final List<Books> subList = bookInterface.getSubList(position, ConstParam.METRO_PAGE_VALUE);

            for (Books el : subList)
                list.add(new BookImage(el, user));

            for (BookImage el : list) {
                hl.addComponent(el);
                el.setHeight(568, Unit.PIXELS);
                el.setWidth(320, Unit.PIXELS);
                hl.setComponentAlignment(el, Alignment.MIDDLE_CENTER);
            }
            list.clear();

            position = position + ConstParam.METRO_PAGE_VALUE;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    class PanelClick extends Panel implements MouseEvents.ClickListener {

        public PanelClick(){
            addListener((MouseEvents.ClickListener)this);
        }

        public void click(MouseEvents.ClickEvent event) {
            if(MouseEvents.ClickEvent.BUTTON_LEFT == event.getButton()){
                System.out.println("Right mouse button clicked [" );

            }
        }
    }

}
