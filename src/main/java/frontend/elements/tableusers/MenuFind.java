package frontend.elements.tableusers;

import DAO.BookDAO;
import DAO.Factory;
import DAO.InterfaceDao;
import Data.Books;
import com.vaadin.server.Page;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.*;
import frontend.elements.components.RegistrationWin;
import frontend.elements.gridbooks.BookImage;
import frontend.elements.tablebooks.TableBooks;
import frontend.views.SearchView;
import mail.Sender;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by likemilk on 16.03.2016.
 */
public class MenuFind extends VerticalLayout {
    private VerticalLayout menu;

    private static MenuFind instance;
    private Sender sender;

    private TextField name;
    private TextField author;
    private Label rating_label;
    private NativeSelect rating_min;
    private NativeSelect rating_max;
    private HorizontalLayout rating_layout;
    private NativeSelect year;
    private CssLayout grid;

    //BD
    private  InterfaceDao BooksDAO;

    public static MenuFind getInstance() {
        MenuFind localInstance = instance;
        if (localInstance == null) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new MenuFind();
                }
        }
        return localInstance;
    }

    public MenuFind() {
        menu = new VerticalLayout();
       // sender = new Sender();


        Factory F= new Factory();
        BooksDAO = F.getDAO(BookDAO.class );

        menu.setWidth(100, Sizeable.Unit.PERCENTAGE);
        menu.setMargin(true);
        menu.setSpacing(true);

        name = new TextField("Title");
        name.setWidth(200, Sizeable.Unit.PIXELS);

        author = new TextField("Author");
        author.setWidth(200, Sizeable.Unit.PIXELS);

        year = new NativeSelect("Year");
        for (int i=java.util.Calendar.getInstance().get(java.util.Calendar.YEAR); i>1900; --i) {
            year.addItem(i);
            year.setItemCaption(i, Integer.toString(i));
        }
        //year.setValue(java.util.Calendar.getInstance().get(java.util.Calendar.YEAR));
        year.setNullSelectionAllowed(true);
        year.setWidth(200, Sizeable.Unit.PIXELS);

        rating_label = new Label("Rating");

        rating_min = new NativeSelect();
        for (int i=5; i>0; --i) {
            rating_min.addItem(i);
            rating_min.setItemCaption(i, Integer.toString(i));
        }
        rating_min.setValue(1);
        rating_min.setNullSelectionAllowed(true);
        rating_min.setWidth(100, Unit.PERCENTAGE);

        rating_max = new NativeSelect();
        for (int i=5; i>0; --i) {
            rating_max.addItem(i);
            rating_max.setItemCaption(i, Integer.toString(i));
        }
        rating_max.setValue(5);
        rating_max.setNullSelectionAllowed(true);
        rating_max.setWidth(100, Unit.PERCENTAGE);

        rating_layout = new HorizontalLayout(rating_min, rating_max);
        rating_layout.setSpacing(true);
        rating_layout.setWidth(200, Sizeable.Unit.PIXELS);

        menu.addComponent(name);
        menu.addComponent(author);
        menu.addComponent(year);
        menu.addComponent(rating_label);
        menu.addComponent(rating_layout);

        this.addComponent(menu);
        this.setComponentAlignment(menu, Alignment.TOP_CENTER);

        this.setStyleName("menulayout");
        this.setSizeFull();


        name.addTextChangeListener(event -> {
            List<Books> list = null;
            try {
                if(year.getValue() == null)
                    list = BooksDAO.GetSqlRequst(author.getValue(), name.getValue(), "");
                else
                    list = BooksDAO.GetSqlRequst(author.getValue(), name.getValue(), year.getValue().toString());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("list.size() = " + list.size());
            setList(list);
        });

        author.addTextChangeListener(event -> {
            List<Books> list = null;
            try {
                if(year.getValue() == null)
                    list = BooksDAO.GetSqlRequst(author.getValue(), name.getValue(), "");
                else
                    list = BooksDAO.GetSqlRequst(author.getValue(), name.getValue(), year.getValue().toString());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("list.size() = " + list.size());
            setList(list);
        });

        year.addValueChangeListener(event -> {
            List<Books> list = null;
            try {
                if(year.getValue() == null)
                    list = BooksDAO.GetSqlRequst(author.getValue(), name.getValue(), "");
                else
                    list = BooksDAO.GetSqlRequst(author.getValue(), name.getValue(), year.getValue().toString());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("list.size() = " + list.size());
            setList(list);
        });
    }

    public void setGrid(CssLayout grid){
      this.grid = grid;
    }

    public void setList(List<Books> list ){
        grid.removeAllComponents();
        for (Books el : list) {
            BookImage book = new BookImage(el);
            book.setHeight(400, Unit.PIXELS);
            book.setWidth(250, Unit.PIXELS);
            grid.addComponent(book);
        }
    }
}
