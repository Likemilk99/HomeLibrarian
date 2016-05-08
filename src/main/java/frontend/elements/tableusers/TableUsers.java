package frontend.elements.tableusers;

import DAO.BookDAO;
import DAO.Factory;
import DAO.InterfaceDao;
import DAO.UserDAO;
import Data.Address;
import Data.Books;
import Data.ConstParam;
import Data.Users;

import com.vaadin.annotations.Theme;
import com.vaadin.client.widget.grid.CellReference;
import com.vaadin.client.widget.grid.CellStyleGenerator;
import com.vaadin.data.Item;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.GeneratedPropertyContainer;
import com.vaadin.data.util.PropertyValueGenerator;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.server.Page;
import com.vaadin.ui.*;
import com.vaadin.ui.renderers.ButtonRenderer;
import frontend.elements.gridbooks.BookImage;
import javassist.CtMethod;

import java.sql.SQLException;
import java.util.*;

/**
 * Created by likemilk on 24.02.2016.
 */
@Theme("mytheme")
public class TableUsers extends VerticalLayout {
    private Grid grid;
    private BeanItemContainer<Users> users = new BeanItemContainer<>(Users.class);
    private GeneratedPropertyContainer gpc;
    Grid.MultiSelectionModel selection ;
    private static TableUsers instance;
    private ArrayList<Users> listUsers ;

    private InterfaceDao userInterface;
    private int position;

    private Label lablePages;
    private Button back = new Button("<");
    private Button forward = new Button(">");
    private HorizontalLayout buttons = new HorizontalLayout();

    public static  TableUsers getInstance() {
        TableUsers localInstance = instance;
        if (localInstance == null) {
            synchronized (TableUsers.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new TableUsers();
                }
            }
        }
        return localInstance;
    }

    private TableUsers() {
        lablePages = new Label();
        position = 0;
        Factory factory = new Factory();
        userInterface = factory.getDAO(UserDAO.class);
        users = new BeanItemContainer<>(Users.class);

        buttons.addComponent(back);

        back.setEnabled(false);
        back.addClickListener(a -> {
            position = position - ConstParam.TABLE_PAGE_VALUE;
            if (position <= 0) {
                position = 0;
                back.setEnabled(false);
            }
            try {
                final List<Users> subList = userInterface.getSubList(position, ConstParam.TABLE_PAGE_VALUE);

                users.removeAllItems();
                users.addAll(subList);
                lablePages.setValue(position + "-" + (position + subList.size()));
                forward.setEnabled(true);
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
                tableCount = userInterface.getCount();

            } catch (SQLException e) {
                e.printStackTrace();
            }

            if(tableCount > position) {
                position = position + ConstParam.TABLE_PAGE_VALUE;

                try {
                    final List<Users> subList = userInterface.getSubList(position, ConstParam.TABLE_PAGE_VALUE);

                    users.removeAllItems();
                    users.addAll(subList);
                    lablePages.setValue(position + "-" + (position + subList.size()));
                    back.setEnabled(true);

                    if(position + subList.size() == userInterface.getCount())
                        forward.setEnabled(false);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        this.addComponent(buttons);
        updateTable();

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
                    try {
                        userInterface.deleteEl(users.getItem(e.getItemId()).getBean());
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
                    users.removeContainerFilters(pid);
                    users.removeAllItems();
                    updateTable();

                    // (Re)create the filter if necessary
                    if (!change.getText().isEmpty()) {
                        lablePages.setValue("Search all pages");
                        users.removeAllItems();
                        try {
                            users.addAll(userInterface.getAllEls());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        users.addContainerFilter(
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
            }

            @Override
            public void postCommit(FieldGroup.CommitEvent commitEvent) throws FieldGroup.CommitException {
                try {
                    userInterface.updateEl(users.getItem(grid.getEditedItemId()).getBean());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void addRow(Users row){
        users.addBean(row);
        try {
            userInterface.addEl(row);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSelectedRows() {
        for (Object itemId: selection.getSelectedRows()) {
            try {
                userInterface.deleteEl(users.getItem(itemId).getBean());
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

    public List<Address> getSRows() {
       Collection <Object> list = grid.getSelectedRows();
        List <Address> subList = new LinkedList<>();
        for (Object el : list) {
            subList.add(users.getItem(el).getBean());
        }
        return subList;
    }

    public void updateTable() {
        try {
            final List<Users> subList = userInterface.getSubList(position, ConstParam.TABLE_PAGE_VALUE);
            users.removeAllItems();
            users.addAll(subList);
            lablePages.setValue(position +"-" + (position + subList.size()));

            if(subList.size() <  ConstParam.TABLE_PAGE_VALUE)
                forward.setEnabled(false);
            //  position = position + ConstParam.TABLE_PAGE_VALUE;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
