package frontend.elements.gridbooks;

import Data.Books;
import com.vaadin.ui.*;

/**
 * Created by likemilk on 14.02.2016.
 */
public class WindowInfo  extends Window {
    public WindowInfo(Books el) {
        super("Книга: " + el.getTitle());
        this.setHeight(50, Unit.PERCENTAGE);
        this.setWidth(50, Unit.PERCENTAGE);
        center();

        VerticalLayout content = new VerticalLayout();
        content.setSizeFull();
        content.addComponent(new Label("Краткая информация"));
        content.addComponent(new Label("Автор: " + el.getAuthor()));
        content.addComponent(new Label("Год: " + el.getYear()));
        content.addComponent(new Label("Название: " + el.getTitle()));
        content.setMargin(true);
        setContent(content);

        Button But_close = new Button("Close");
        But_close.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                close();
            }
        });
        content.addComponent(But_close);
        content.setComponentAlignment(But_close, Alignment.BOTTOM_LEFT);
    }
}
