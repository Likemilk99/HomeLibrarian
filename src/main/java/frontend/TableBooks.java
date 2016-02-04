package frontend;

import com.vaadin.ui.*;


/**
 * Created by likemilk on 30.12.2015.
 */
public class TableBooks extends HorizontalLayout {
    Table Books;
    TextArea Desc;
  // Layout LLayout;

    public TableBooks() {
        Books = new  Table ();
        Books.addContainerProperty("Name", String.class, null);
        Books.addContainerProperty("Author", String.class, null);
        Books.addContainerProperty("Year", String.class, null);
        Books.addContainerProperty("Resource", String.class, null);
        Books.setColumnWidth("Name", 100);


        Books.setWidth("100%");
        Books.setHeight("600px");


        Desc = new TextArea();

        Desc.setWidth("100%");
        Desc.setHeight("600px");
        Desc.setReadOnly(true);

        setWidth("100%");
        setHeight("100%");
        setStyleName("borders");


     //   FileResource resource = new FileResource( new File("F:\\PROJECTS\\JAVA\\newtest\\src\\main\\resources\\Images\\test.jpg"));
     //   Image Im= new Image("",resource);
    //    Im.setWidth("50px");
     //   Im.setHeight("50px");


      //  LLayout = new VerticalLayout();
      //  LLayout.addComponent(Im);
       // LLayout.addComponent(Desc);

        addComponent(Books);
        addComponent(Desc);
        //addComponent(Desc);
    }
}
