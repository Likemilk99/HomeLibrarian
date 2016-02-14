package frontend;

import Data.Books;
import com.google.gwt.thirdparty.guava.common.collect.Lists;
import com.vaadin.ui.Grid;
import com.vaadin.ui.GridLayout;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.List;

/**
 * Created by likemilk on 06.01.2016.
 */
public class GridBooks extends GridLayout {
    private Collection<BookImage> elements = Lists.newArrayList();
    private int width = 0; // only for grid layout
    private int n = 0;

    GridBooks() {
        super(5,5);
        this.setSpacing(true);
        this.setMargin(true);
        this.setStyleName("borders");
        this.setWidth(100, Unit.PERCENTAGE);

    }

    GridBooks(List<Books> list, String style, int width) {
        super(5,5);
        this.width = width;
        this.n = width/263;
        this.setSpacing(true);
        this.setMargin(true);
        this.setStyleName(style.toString());
        this.setWidth(100, Unit.PERCENTAGE);

        for(Books el : list)
            elements.add(new BookImage(el));
    }

    public void SetBookCollection(List<Books> list) {
        elements.clear();
        for(Books el : list)
            elements.add(new BookImage(el));
    }


    public int GetGrigWidth() {
        return width;
    }

    public void DrowGrid(){
        for(BookImage el : elements)
            this.addComponent(el);

    }

    public void DrowGrid(double width){
        // double newn = width/210;
        if( n+1 <= width/263)
        {

            n = new BigDecimal(width/263.0).setScale(0, RoundingMode.DOWN).intValue();//doubleValue();
            this.removeAllComponents();
            this.setColumns(n);
            for(BookImage el : elements)
                this.addComponent(el);

        } else if (n > width/263 && n > 1 )
        {
            n = new BigDecimal(width/263.0).setScale(0, RoundingMode.DOWN).intValue();
            this.removeAllComponents();
            if ( n >= 1) {
                this.setColumns(n);
                for (BookImage el : elements)
                    this.addComponent(el);
            }
        }
    }

}
