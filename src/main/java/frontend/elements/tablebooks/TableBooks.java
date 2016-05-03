package frontend.elements.tablebooks;

import DAO.BookDAO;
import DAO.Factory;
import DAO.InterfaceDao;
import Data.Books;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.GeneratedPropertyContainer;
import com.vaadin.data.util.PropertyValueGenerator;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.ButtonRenderer;
import frontend.elements.gridbooks.BookImage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by LikeMilk on 12.04.2016.
 */
public class TableBooks extends VerticalLayout {
    private Grid grid;
    private InterfaceDao bookInterface;
    private BeanItemContainer<Books> users = new BeanItemContainer<>(Books.class);
    private GeneratedPropertyContainer gpc;
    Grid.MultiSelectionModel selection ;
    private static TableBooks instance;

    public static  TableBooks getInstance() {
        TableBooks localInstance = instance;
        if (localInstance == null) {
            synchronized (TableBooks.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new TableBooks();
                }
            }
        }
        return localInstance;
    }

    private TableBooks() {
        users = new BeanItemContainer<>(Books.class);

        Factory factory = new Factory();
        bookInterface = factory.getDAO(BookDAO.class);

        UpdateTable();

        //for(int i =0; i < 10; i++)
        //    users.addBean(new Books(""+i, ""+i, ""+i, ""+i));
        //users.addBean(new Books());
        //users.addBean(new Books());
        gpc = new GeneratedPropertyContainer(users);
        gpc.addGeneratedProperty("delete",
                new PropertyValueGenerator<String>() {

                    @Override
                    public String getValue(Item item, Object itemId,
                                           Object propertyId) {
                        return "Delete"; // The caption
                    }

                    @Override
                    public Class<String> getType() {
                        return String.class;
                    }


                });

// Create a grid bound to it
        grid = new Grid(gpc);

        grid.getColumn("delete")
                .setRenderer(new ButtonRenderer(e -> {
                    //Grid.deselect(e.getItemId());
                    grid.getSelectedRows().remove(e.getItemId());
                    Collection<Object> seletedList = grid.getSelectedRows();

                    seletedList.remove(e.getItemId());
                    grid.getContainerDataSource().removeItem(e.getItemId());
                    grid.getSelectionModel().reset();
                    for (Object el : seletedList) {
                        grid.select(el);
                    }
                }));


        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        grid.setEditorEnabled(true);

// Create a header row to hold column filters
        Grid.HeaderRow filterRow = grid.appendHeaderRow();

// Set up a filter for all columns
        for (Object pid : grid.getContainerDataSource()
                .getContainerPropertyIds()) {
            if(  grid.getColumn("delete").getPropertyId() != pid) {
                Grid.HeaderCell cell = filterRow.getCell(pid);

                // Have an input field to use for filter
                TextField filterField = new TextField();
                filterField.setColumns(8);

                // Update filter When the filter input is changed
                filterField.addTextChangeListener(change -> {
                    // Can't modify filters so need to replace
                    users.removeContainerFilters(pid);

                    // (Re)create the filter if necessary
                    if (!change.getText().isEmpty())
                        users.addContainerFilter(
                                new SimpleStringFilter(pid,
                                        change.getText(), true, false));
                });
                cell.setComponent(filterField);
                filterField.setHeight(90, Unit.PERCENTAGE);
                filterField.setWidth(80, Unit.PERCENTAGE);
            }
        }

        selection = (Grid.MultiSelectionModel) grid.getSelectionModel();
        this.addComponent(grid);
        grid.setSizeFull();
        this.setSizeFull();

        grid.setCellStyleGenerator(new Grid.CellStyleGenerator() {
            @Override
            public String getStyle(Grid.CellReference cellReference) {
                if ("delete".equals(cellReference.getPropertyId().toString()) &&
                        "mail".equals(getUI().getNavigator().getState())) return "invisible";
                return null;
            }
        });
    }

    public void UpdateTable() {
        users.removeAllItems();

        /////////////////////////////////////////
        try {
            List<Books> subList = bookInterface.getSubList(0);
            for (Books el : subList)
                users.addBean(el);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //public void addRow(Books row){
    //    users.addBean(row);
    //}

    public void deleteSelectedRows() {
        for (Object itemId: selection.getSelectedRows())
            grid.getContainerDataSource().removeItem(itemId);

        // Otherwise out of sync with container
        grid.getSelectionModel().reset();

        // Disable after deleting
    }


    public ArrayList<String> getMails() {
        ArrayList <String> emails = new ArrayList<>();
        for (Object obj: selection.getSelectedRows()) {
            Property o = grid.getContainerDataSource().getItem(obj).getItemProperty("email");
            emails.add((String) o.getValue());
        }
        return emails;
    }
}
