package frontend.elements.gridbooks;

import Data.Books;
import com.vaadin.annotations.Theme;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.FileResource;
import com.vaadin.server.StreamResource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.BaseTheme;
import frontend.elements.components.BookWin;
import org.vaadin.teemu.ratingstars.RatingStars;

import java.io.*;

/**
 * Created by likemilk on 14.02.2016.
 */
@Theme("mytheme")
public class BookImage extends VerticalLayout {
    private final Books Book;
    private RatingStars rating = new RatingStars();
    protected FileDownloader fileDownloader;
    private Button  buttonDownload = new Button("Download");
    private Embedded imageEmbedded = new Embedded();
    private Label   title = new Label("TITLE");
    private Label   author = new Label("Author");
    /**
     * НАДО ПЕРЕДАТЬ ДЛЯ РАБОТЫ С БД
     * @param path
     */
    /*public BookImage(String path) {
        // TODO SQL
        this.Book = new Books("i", "i", "i", "i");//!!!!!!!!!!!!!!!!!!!!!!

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

    }*/

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

        imageEmbedded.setSource(new FileResource(new File(Book.getImage())));

        title.setValue(Book.getTitle());
        author.setValue(Book.getAuthor());

        if(Book.getFile().isEmpty())
            buttonDownload.setEnabled(false);

        buttonDownload.setWidth("80%");
        imageEmbedded.setWidth("100%");

        title.setWidth(null);
        author.setWidth(null);

        VerticalLayout bodyLayout = new VerticalLayout(title, author, imageEmbedded);

        bodyLayout.setExpandRatio(title, 6);
        bodyLayout.setExpandRatio(author, 4);
        bodyLayout.setExpandRatio(imageEmbedded, 90);

        bodyLayout.setComponentAlignment(title, Alignment.MIDDLE_CENTER);
        bodyLayout.setComponentAlignment(author, Alignment.MIDDLE_CENTER);
        bodyLayout.setComponentAlignment(imageEmbedded, Alignment.MIDDLE_CENTER);

        buttonDownload.setStyleName("super-button");
        title.setStyleName("name-label");
        author.setStyleName("author-label");

        this.addComponent(rating);
        this.addComponent(bodyLayout);
        this.addComponent(buttonDownload);

        this.setComponentAlignment(rating, Alignment.TOP_CENTER);
        this.setComponentAlignment(bodyLayout, Alignment.MIDDLE_CENTER);
        this.setComponentAlignment(buttonDownload, Alignment.BOTTOM_CENTER);
        this.setExpandRatio(rating, 5);
        this.setExpandRatio(bodyLayout, 85);
        this.setExpandRatio(buttonDownload, 10);

        StreamResource sr = getStream();
        FileDownloader fileDownloader = new FileDownloader(sr);
        fileDownloader.extend(buttonDownload);

        bodyLayout.addLayoutClickListener(e -> {
            BookWin win = new BookWin(this.Book);
            UI.getCurrent().addWindow(win);
        });

    }

    private StreamResource getStream() {
        StreamResource.StreamSource source = new StreamResource.StreamSource() {
            public InputStream getStream() {
                File file = new File(Book.getFile());
                InputStream input = null;
                try {
                    input = new FileInputStream(file);
                } catch ( FileNotFoundException e) {
                }
                return input;

            }
        };
        StreamResource resource = new StreamResource ( source, Book.getFile());
        return resource;
    }

}
