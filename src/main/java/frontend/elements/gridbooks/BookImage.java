package frontend.elements.gridbooks;

import Data.Books;
import com.vaadin.annotations.Theme;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.BaseTheme;
import org.vaadin.teemu.ratingstars.RatingStars;

/**
 * Created by likemilk on 14.02.2016.
 */
@Theme("mytheme")
public class BookImage extends VerticalLayout {
    private final Books Book;
    private Button button_1 = new Button("rate it !");
    private RatingStars rating = new RatingStars();
    private Button  button_2 = new Button("Download");
    private Button  button_3 = new Button();
    private Label   name = new Label("NAME");
    private Label   author = new Label("Author");
    /**
     * НАДО ПЕРЕДАТЬ ДЛЯ РАБОТЫ С БД
     * @param path
     */
    BookImage(String path) {
        this.Book = new Books();//!!!!!!!!!!!!!!!!!!!!!!

        this.setStyleName("cells");
        this.setHeight("250px");
        this.setWidth("200px");

        button_2.setWidth("80%");

        button_3.setStyleName(BaseTheme.BUTTON_LINK);
        button_3.setIcon(new ThemeResource("Images/logo.png"));

        button_3.setWidth("100%");
        button_3.setHeight("100%");

        this.addComponent(rating);
        this.addComponent(name);
        this.addComponent(author);
        this.addComponent(button_3);
        this.addComponent(button_2);

        this.setComponentAlignment(rating, Alignment.TOP_CENTER);
        this.setComponentAlignment(button_3, Alignment.MIDDLE_CENTER);
        this.setComponentAlignment(button_2, Alignment.BOTTOM_CENTER);

    }

    /**
     * НАДО ПЕРЕДАТЬ ДЛЯ РАБОТЫ С БД
     * @param el
     */
    public BookImage(Books el) {
        super();
        this.Book = el;

        this.setStyleName("cells");
        this.setHeight("250px");
        this.setWidth("200px");

        rating.setAnimated(true);
        rating.setCaption(null);
        rating.setMaxValue(5);

        button_2.setWidth("80%");
        button_3.setWidth("100%");
        name.setWidth(null);
        author.setWidth(null);

        VerticalLayout top = new VerticalLayout(rating, name, author);
        top.setComponentAlignment(rating, Alignment.TOP_CENTER);
        top.setComponentAlignment(name, Alignment.MIDDLE_CENTER);
        top.setComponentAlignment(author, Alignment.BOTTOM_CENTER);

        button_3.setStyleName(BaseTheme.BUTTON_LINK);
        button_2.setStyleName("super-button");
        name.setStyleName("name-label");
        author.setStyleName("author-label");

        ThemeResource res = new ThemeResource("Images/logo_icon.png");
        button_3.setIcon(res);

        this.addComponent(top);
        this.addComponent(button_3);
        this.addComponent(button_2);

        this.setComponentAlignment(top, Alignment.TOP_CENTER);
        this.setComponentAlignment(button_3, Alignment.MIDDLE_CENTER);
        this.setComponentAlignment(button_2, Alignment.BOTTOM_CENTER);

        button_3.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                WindowInfo sub = new WindowInfo(Book);
                UI.getCurrent().addWindow(sub);


            }
        });

    }

}
