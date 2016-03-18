package frontend.elements.metro;

import com.google.gwt.dom.client.NativeEvent;
import com.vaadin.annotations.JavaScript;
import com.vaadin.event.MouseEvents;
import com.vaadin.server.Page;
import com.vaadin.ui.*;
import javafx.scene.layout.Pane;

import java.security.PrivateKey;

/**
 * Created by likemilk on 22.02.2016.
 */

public class MetroBook extends HorizontalLayout{
  private Panel metro = new Panel();
    public MetroBook() {
        super();
        metro.setWidth(700, Unit.PIXELS);
        metro.setHeight(250, Unit.PIXELS);
        metro.setStyleName("borders");

        HorizontalLayout hl = new HorizontalLayout();
        hl.setHeight(100, Unit.PERCENTAGE);
        hl.setWidthUndefined();
        hl.setSpacing(true);

        hl.addComponent(new Label("test"));
        hl.addComponent(new Label("test"));
        hl.addComponent(new Label("test"));
        hl.addComponent(new Label("test"));
        hl.addComponent(new Label("test"));
        hl.addComponent(new Label("test"));
        hl.addComponent(new Label("test"));
        hl.addComponent(new Label("test"));
        hl.addComponent(new Label("test"));
        hl.addComponent(new Label("test"));
        hl.addComponent(new Label("test"));
        hl.addComponent(new Label("test"));
        hl.addComponent(new Label("test"));
        hl.addComponent(new Label("test"));
        hl.addComponent(new Label("test"));
        hl.addComponent(new Label("test"));
        hl.addComponent(new Label("test"));
        hl.addComponent(new Label("test"));
        hl.addComponent(new Label("test"));
        hl.addComponent(new Label("test"));
        hl.addComponent(new Label("test"));
        hl.addComponent(new Label("test"));
        hl.addComponent(new Label("test"));
        hl.addComponent(new Label("test"));
        hl.addComponent(new Label("test"));
        hl.addComponent(new Label("test"));
        hl.addComponent(new Label("test"));
        hl.addComponent(new Label("test"));
        hl.addComponent(new Label("test"));
        hl.addComponent(new Label("test"));
        hl.addComponent(new Label("test"));
        hl.addComponent(new Label("test"));
        hl.addComponent(new Label("test"));
        hl.addComponent(new Label("test"));
        hl.addComponent(new Label("test"));
        hl.addComponent(new Label("test"));
        hl.addComponent(new Label("test"));
        hl.addComponent(new Label("test"));
        hl.addComponent(new Label("test"));
        hl.addComponent(new Label("test"));
        hl.addComponent(new Label("test"));
        hl.addComponent(new Label("test"));

        FormLayout content = new FormLayout();
        content.addComponent(hl);

        Button button_1 = new Button("Left");

        metro.addClickListener(new PanelClick() {
          /*  public void buttonClick(MouseEvents.ClickEvent event) {
                //  if(metro.getScrollLeft() > 40 )
                metro.setScrollLeft(metro.getScrollLeft()-100);
                metro.setStyleName("ease-in-out");
            }*/
        });

        Button button_2 = new Button("Right");
        button_1.setHeight(250, Unit.PIXELS);
        button_2.setHeight(250, Unit.PIXELS);
        button_1.addClickListener(new Button.ClickListener() {
          public void buttonClick(Button.ClickEvent event) {
        //  if(metro.getScrollLeft() > 40 )
            metro.setScrollLeft(metro.getScrollLeft()-100);
       //     metro.setStyleName("ease-in-out");
         }
         });

        button_2.addClickListener(new Button.ClickListener() {
          public void buttonClick(Button.ClickEvent event) {
           //  if(metro.getScrollLeft() < metro.getWidth()-40)
           metro.setScrollLeft(metro.getScrollLeft() + 100);
        //   metro.setStyleName("ease-in-out");
          }
         });


        content.setSizeUndefined(); // Shrink to fit
        content.setMargin(true);
          //this.getContent().setSizeUndefined();
        metro.setContent(content);
        this.addComponent(button_1);
        this.addComponent(metro);
        this.addComponent(button_2);

   //  content.setStyleName("v-scrollable");
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
