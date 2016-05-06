package frontend.elements.tablebooks;

import DAO.BookDAO;
import DAO.Factory;
import DAO.InterfaceDao;
import Data.Books;
import Data.ConstParam;
import Data.Users;
import com.vaadin.annotations.Theme;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.GeneratedPropertyContainer;
import com.vaadin.data.util.PropertyValueGenerator;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.*;
import com.vaadin.ui.renderers.ButtonRenderer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by LikeMilk on 12.04.2016.
 */
@Theme("mytheme")
public class TableBooks extends VerticalLayout {
    private Grid grid;
    private InterfaceDao bookInterface;
    private BeanItemContainer<Books> books = new BeanItemContainer<>(Books.class);
    private GeneratedPropertyContainer gpc;
    Grid.MultiSelectionModel selection ;
    private static TableBooks instance;

    private int position;

    private Label lablePages;
    private Button back = new Button("<");
    private Button forward = new Button(">");
    private HorizontalLayout buttons = new HorizontalLayout();

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
        lablePages = new Label();
        position = 0;
        Factory factory = new Factory();
        bookInterface = factory.getDAO(BookDAO.class);
        books = new BeanItemContainer<>(Books.class);

        buttons.addComponent(back);

        back.addClickListener(a -> {
            position = position - ConstParam.TABLE_PAGE_VALUE;
            if (position < 0)
                position = 0;
            try {

                final List<Books> subList = bookInterface.getSubList(position, ConstParam.TABLE_PAGE_VALUE);
                books.removeAllItems();
                books.addAll(subList);
                lablePages.setValue(position + "-" + (position + subList.size()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //  } else
            //       position = ConstParam.TABLE_PAGE_VALUE;
        });

        buttons.addComponent(forward);
        buttons.addComponent(lablePages);
        forward.addClickListener(a -> {

            long tableCount = 0;

            try {
                tableCount = bookInterface.getCount();

            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (tableCount > position) {
                position = position + ConstParam.TABLE_PAGE_VALUE;

                try {
                    final List<Books> subList = bookInterface.getSubList(position, ConstParam.TABLE_PAGE_VALUE);
                    books.removeAllItems();
                    books.addAll(subList);
                    lablePages.setValue(position + "-" + (position + subList.size()));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        this.addComponent(buttons);
        updateTable();

        gpc = new GeneratedPropertyContainer(books);
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
                    try {
                        bookInterface.deleteEl(books.getItem(e.getItemId()).getBean());
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    updateTable();
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
                    books.removeContainerFilters(pid);
                    books.removeAllItems();
                    updateTable();

                    // (Re)create the filter if necessary
                    if (!change.getText().isEmpty()) {
                        lablePages.setValue("Search all pages");
                        books.removeAllItems();
                        try {
                            books.addAll(bookInterface.getAllEls());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        books.addContainerFilter(
                                new SimpleStringFilter(pid,
                                        change.getText(), true, false));
                    }
                });
                cell.setComponent(filterField);
                filterField.setHeight(90, Unit.PERCENTAGE);
                filterField.setWidth(80, Unit.PERCENTAGE);
            }
        }

        selection = (Grid.MultiSelectionModel) grid.getSelectionModel();
        this.addComponent(grid);
        this.setExpandRatio(buttons, 5);
        this.setExpandRatio(grid, 95);
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

        grid.getEditorFieldGroup().addCommitHandler(new FieldGroup.CommitHandler() {
            @Override
            public void preCommit(FieldGroup.CommitEvent commitEvent) throws FieldGroup.CommitException {

                try {
                    bookInterface.updateEl(books.getItem(grid.getEditedItemId()).getBean());
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void postCommit(FieldGroup.CommitEvent commitEvent) throws FieldGroup.CommitException {
                //Trololo
            }
        });
    }

    public void UpdateTable() {
        books.removeAllItems();

        /////////////////////////////////////////
        try {
            List<Books> subList = bookInterface.getSubList(0, ConstParam.TABLE_PAGE_VALUE);
            for (Books el : subList)
                books.addBean(el);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

  /*  public void addRow(Books row){
        books.addBean(row);
        try {
            bookInterface.addEl(row);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    public void deleteSelectedRows() {
        for (Object itemId: selection.getSelectedRows()) {
            try {
                bookInterface.deleteEl(books.getItem(itemId).getBean());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            grid.getContainerDataSource().removeItem(itemId);
        }
        // Otherwise out of sync with container
        // grid.getSelectionModel().reset();
        updateTable();
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

    public void updateTable() {
        try {
            final List<Books> subList = bookInterface.getSubList(position, ConstParam.TABLE_PAGE_VALUE);
            books.removeAllItems();
            books.addAll(subList);
            lablePages.setValue(position +"-" + (position + subList.size()));
            //  position = position + ConstParam.TABLE_PAGE_VALUE;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

