package frontend.views;

import Data.Books;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import frontend.elements.components.HeaderLayout;
import frontend.elements.gridbooks.BookImage;
import frontend.elements.tableusers.MenuFind;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Александр on 13.03.2016.
 */
@Theme("mytheme")
public class SearchView extends CustomComponent implements View {

    public static final String NAME = "search";

    private AbsoluteLayout mainlayout;
    private HeaderLayout header;
    private HorizontalLayout body;
    private MenuFind menu;
    private CssLayout grid;
    private HorizontalSplitPanel hSplitBar;

    private static SearchView instance;

    public static  SearchView getInstance() {
        SearchView localInstance = instance;
        if (localInstance == null) {
            synchronized (SearchView.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new SearchView();
                }
            }
        }
        return localInstance;
    }

    private SearchView() {
        mainlayout = new AbsoluteLayout();
        body = new HorizontalLayout();
        hSplitBar = new HorizontalSplitPanel();
        grid = new CssLayout();
        hSplitBar.setSplitPosition(320, Unit.PIXELS);
        hSplitBar.setMaxSplitPosition(320, Unit.PIXELS);
        hSplitBar.setMinSplitPosition(20, Unit.PIXELS);
        menu = MenuFind.getInstance();
        menu.setGrid(grid);
        // Styles
        mainlayout.setStyleName("v-main-body");
        body.setStyleName("bodylayout");

        // Add components
        hSplitBar.setFirstComponent(menu);
        hSplitBar.setSecondComponent(grid);

        body.addComponent(hSplitBar);

        mainlayout.addComponent(body, "left: 0px; top: 10%;");

        //header.setSizeFull();
     //   grid.setSizeFull();
        body.setSizeFull();
        mainlayout.setSizeFull();

        setSizeFull();
        setCompositionRoot(mainlayout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // Get the user name from the session
        //String username = String.valueOf(getSession().getAttribute("user"));
        //String status = String.valueOf(getSession().getAttribute("status"));
        String status = String.valueOf(getSession().getAttribute("status"));

        mainlayout.removeAllComponents();

        header = new HeaderLayout(status);
        mainlayout.addComponent(body, "left: 0px; top: 10%;");
        mainlayout.addComponent(header, "left: 0px; top: 0px; bottom: 90%;");

        menu.updateFind();
    }

    public void setList(List<Books> list ){
        for (Books el : list) {
            BookImage book = new BookImage(el, getUI().getSession().getAttribute("user").toString());
            book.setHeight(568, Unit.PIXELS);
            book.setWidth(320, Unit.PIXELS);
            grid.addComponent(book);
        }
    }


}
