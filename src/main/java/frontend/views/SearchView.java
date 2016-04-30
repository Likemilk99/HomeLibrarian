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
        header = new HeaderLayout();
        body = new HorizontalLayout();
        hSplitBar = new HorizontalSplitPanel();
        grid = new CssLayout();
        hSplitBar.setSplitPosition(320, Unit.PIXELS);
        hSplitBar.setMaxSplitPosition(320, Unit.PIXELS);
        hSplitBar.setMinSplitPosition(20, Unit.PIXELS);
        menu = MenuFind.getInstance();

        // Styles
        mainlayout.setStyleName("v-main-body");
        body.setStyleName("bodylayout");

        // Add components
        hSplitBar.setFirstComponent(menu);
        hSplitBar.setSecondComponent(grid);

        // TODO SQL
        for (int i=0; i < 40; i++) {
            BookImage el = new BookImage(new Books(""+i, ""+i, ""+i, ""+i));
            el.setHeight(400, Unit.PIXELS);
            el.setWidth(250, Unit.PIXELS);

            grid.addComponent(el);
        }
        body.addComponent(hSplitBar);

        mainlayout.addComponent(body, "left: 0px; top: 10%;");
        mainlayout.addComponent(header, "left: 0px; top: 0px; bottom: 90%;");
        //mainlayout.addComponent(hSplitBar, "left: 0px; top: 10%;");

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
    }
}
