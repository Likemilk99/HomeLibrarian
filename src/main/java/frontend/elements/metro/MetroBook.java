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
    private static int width;

    public MetroBook() {
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
        HorizontalLayout hl = new HorizontalLayout();
        p.setStyleName("metro-panel");
        p.setId("panelScroll");
        hl.setId("anchor");
        /////////////////////////////////////////
        ArrayList<BookImage> list = new ArrayList<>();

        try {
            List<Books> subList = bookInterface.getSubList(position, ConstParam.METRO_PAGE_VALUE);
            for (Books el : subList)
                list.add(new BookImage(el));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            position = position + ConstParam.METRO_PAGE_VALUE;
        }

        //TESTING
        for (int i = 0; i < 50; i++) {
            //  list.add(new BookImage(new Books()));
        }

        for (BookImage el : list) {
            hl.addComponent(el);
            el.setHeight(568, Unit.PIXELS);
            el.setWidth(320, Unit.PIXELS);
            hl.setComponentAlignment(el, Alignment.MIDDLE_CENTER);
        }

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

        addComponent(p, "left: 0px; top: 0%;");
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
                                list.add(new BookImage(el));
                            for (BookImage el : list) {
                                hl.addComponent(el);
                                el.setHeight(568, Unit.PIXELS);
                                el.setWidth(320, Unit.PIXELS);
                                hl.setComponentAlignment(el, Alignment.MIDDLE_CENTER);
                            }

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


        /*button_2.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                //TODO change condition

                new Notification("Values",String.valueOf(p.getScrollLeft()),
                        Notification.TYPE_WARNING_MESSAGE, true)
                        .show(Page.getCurrent());
                if(p.getScrollLeft() + 100 <= UI.getCurrent().getWidth()) {
                    p.setScrollLeft(p.getScrollLeft() + 100);
                } else {

                }
            }

        });*/
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
