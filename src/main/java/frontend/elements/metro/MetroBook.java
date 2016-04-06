package frontend.elements.metro;

import Data.Books;
import com.google.gwt.dom.client.NativeEvent;
import com.vaadin.annotations.JavaScript;
import com.vaadin.event.MouseEvents;
import com.vaadin.server.Page;
import com.vaadin.ui.*;
import frontend.elements.gridbooks.BookImage;
import javafx.scene.layout.Pane;

import java.security.PrivateKey;
import java.util.ArrayList;

/**
 * Created by likemilk on 22.02.2016.
 */

@JavaScript({"vaadin://themes/mytheme/js/SmoothScroll.js"})
public class MetroBook extends AbsoluteLayout{
  //private Panel metro = new Panel();
    public MetroBook() {
        super();
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
        //TESTING
        for (int i=0; i < 50; i++) {
            list.add(new BookImage(new Books()));
        }

        for (BookImage el: list) {
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

        button_1.addClickListener(event -> {
            if(p.getScrollLeft() > 1000 )
                p.setScrollLeft(p.getScrollLeft() - 1000);
            else
                p.setScrollLeft(0);
        });

        button_2.addClickListener(event -> {
            //   if (metro.getScrollLeft() < metro.getWidth() - 40)
            p.setScrollLeft(p.getScrollLeft() + 1000);
            //Page.getCurrent().getJavaScript().execute(
            //        "smoothScroll('panelScroll', 'anchor')");
        });
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
