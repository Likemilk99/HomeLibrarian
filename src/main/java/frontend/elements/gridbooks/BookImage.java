package frontend.elements.gridbooks;

import Data.Books;
import Data.Rating;
import com.vaadin.annotations.Theme;
import com.vaadin.server.*;
import com.vaadin.ui.*;
import frontend.elements.components.BookWin;
import org.vaadin.teemu.ratingstars.RatingStars;
import service.IService.IRaitingService;

import java.io.*;
import java.sql.SQLException;

/**
 * Created by likemilk on 14.02.2016.
 */
@Theme("mytheme")
public class BookImage extends VerticalLayout {
    private final Books Book;
    private RatingStars rating = new RatingStars();
    private RatingStars rating_my = new RatingStars();
    private HorizontalLayout rating_layout = new HorizontalLayout();
    protected FileDownloader fileDownloader;
    private Button  buttonDownload = new Button("Download");
    private Embedded imageEmbedded = new Embedded();
    private Label   title = new Label("TITLE");
    private Label   author = new Label("Author");

    /**
     *
     * @param el
     */
    public BookImage(Books el, String user) {
        super();
        this.Book = el;

        this.setStyleName("cells");
        this.setHeight("250px");
        this.setWidth("200px");

        rating.setAnimated(true);
        rating.setCaption(null);
        rating.setMaxValue(5);
        rating.setStyleName("rating");
        rating.setReadOnly(true);

        rating_my.setAnimated(true);
        rating_my.setCaption(null);
        rating_my.setMaxValue(5);
        rating_my.setStyleName("rating_my");

        IRaitingService iRaitingService = new IRaitingService();
        try {
            double rate = iRaitingService.getRaiting(el.getId());
            rating.setReadOnly(false);
            rating.setValue(rate);
            rating.setReadOnly(true);
            double myrate = iRaitingService.getRaiting(user,el.getId());
            rating_my.setValue(myrate);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        rating_my.addValueChangeListener(e -> {
            try {
                Rating rat = iRaitingService.getUser(getUI().getSession().getAttribute("user").toString(),el.getId());

                rat.setRaiting(rating_my.getValue());

                iRaitingService.update(rat);

                double rate = iRaitingService.getRaiting(el.getId());
                rating.setReadOnly(false);
                rating.setValue(rate);
                rating.setReadOnly(true);

                new Notification(String.valueOf(rate),
                        Notification.Type.TRAY_NOTIFICATION)
                        .show(Page.getCurrent());
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });

        rating_layout.addComponent(rating);
        rating_layout.addComponent(rating_my);
        rating_layout.setComponentAlignment(rating, Alignment.MIDDLE_LEFT);
        rating_layout.setComponentAlignment(rating_my, Alignment.MIDDLE_LEFT);
        rating_layout.setStyleName("ratinglayout");

        imageEmbedded.setSource(new FileResource(new File(Book.getImage())));

        title.setValue(Book.getTitle());
        author.setValue(Book.getAuthor());

        if(Book.getFile().isEmpty())
            buttonDownload.setEnabled(false);

        buttonDownload.setWidth("80%");
        imageEmbedded.setWidth("100%");
        imageEmbedded.setHeight("100%");

        title.setWidth(null);
        author.setWidth(null);

        VerticalLayout bodyLayout = new VerticalLayout(title, author, imageEmbedded);

        bodyLayout.setExpandRatio(title, 12);
        bodyLayout.setExpandRatio(author, 8);
        bodyLayout.setExpandRatio(imageEmbedded, 80);
        bodyLayout.setSizeFull();
        bodyLayout.setComponentAlignment(title, Alignment.MIDDLE_CENTER);
        bodyLayout.setComponentAlignment(author, Alignment.MIDDLE_CENTER);
        bodyLayout.setComponentAlignment(imageEmbedded, Alignment.MIDDLE_CENTER);

        buttonDownload.setStyleName("super-button");
        title.setStyleName("name-label");
        author.setStyleName("author-label");

        this.addComponent(rating_layout);
        this.addComponent(bodyLayout);
        this.addComponent(buttonDownload);

        this.setComponentAlignment(rating_layout, Alignment.TOP_CENTER);
        this.setComponentAlignment(bodyLayout, Alignment.TOP_CENTER);
        this.setComponentAlignment(buttonDownload, Alignment.BOTTOM_CENTER);
        this.setExpandRatio(rating_layout, 5);
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
