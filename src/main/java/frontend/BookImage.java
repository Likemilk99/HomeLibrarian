package frontend;

import Data.Books;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.*;

/**
 * Created by likemilk on 14.02.2016.
 */
public class BookImage extends VerticalLayout {
    private final Books Book;
    private Button button_1 = new Button("rate it !");
    private Button  button_2 = new Button("Upload");
    private Button  button_3 = new Button("Info");
    private Image bookimage;// = new Image(null, new ThemeResource("images/test.jpg"));
    private HorizontalLayout footer = new HorizontalLayout(button_2, button_3);

    /**
     * НАДО ПЕРЕДАТЬ ДЛЯ РАБОТЫ С БД
     * @param path
     */
    BookImage(String path) {
        bookimage = new Image(null, new ThemeResource("images/test.jpg"));
        this.Book = new Books();//!!!!!!!!!!!!!!!!!!!!!!
        this.setStyleName("cells");
        this.setHeight("250px");
        this.setWidth("200px");

        footer.setWidth("100%");
        button_2.setWidth("100%");
        button_3.setWidth("100%");

        bookimage.setHeight("100px");
        bookimage.setWidth("100px");

        this.addComponent(button_1);
        this.addComponent(bookimage);
        this.addComponent(footer);

        this.setComponentAlignment(button_1, Alignment.TOP_CENTER);
        this.setComponentAlignment(bookimage, Alignment.MIDDLE_CENTER);
        this.setComponentAlignment(footer, Alignment.BOTTOM_CENTER);

    }

    /**
     * НАДО ПЕРЕДАТЬ ДЛЯ РАБОТЫ С БД
     * @param el
     */
    public BookImage(Books el) {
        super();
        this.Book = el;
        bookimage =  new Image(null, new ThemeResource("Images/test.jpg"));

        this.setStyleName("cells");
        this.setHeight("250px");
        this.setWidth("200px");

        footer.setWidth("100%");
        button_2.setWidth("100%");
        button_3.setWidth("100%");

        bookimage.setHeight("100px");
        bookimage.setWidth("100px");

        this.addComponent(button_1);
        this.addComponent(bookimage);
        this.addComponent(footer);

        this.setComponentAlignment(button_1, Alignment.TOP_CENTER);
        this.setComponentAlignment(bookimage, Alignment.MIDDLE_CENTER);
        this.setComponentAlignment(footer, Alignment.BOTTOM_CENTER);

        button_3.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                WindowInfo sub = new WindowInfo(Book);
                UI.getCurrent().addWindow(sub);


            }
        });

    }

}
