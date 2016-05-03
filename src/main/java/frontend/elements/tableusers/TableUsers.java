package frontend.elements.tableusers;

import Data.Address;
import Data.Users;

import com.vaadin.annotations.Theme;
import com.vaadin.client.widget.grid.CellReference;
import com.vaadin.client.widget.grid.CellStyleGenerator;
import com.vaadin.data.Item;

import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.GeneratedPropertyContainer;
import com.vaadin.data.util.PropertyValueGenerator;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.server.Page;
import com.vaadin.ui.*;
import com.vaadin.ui.renderers.ButtonRenderer;

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

        users = new BeanItemContainer<>(Users.class);
           for(int i =0; i < 10; i++)
               users.addBean(new Users("ss",""+i, "ss", "iround2@yandex.ru"));
       //     users.addBean(new Users("ss","", "ss", "likemilk99@gmail.com"));
      //  users.addBean(new Users("ss","", "ss", "azure49@ya.ru"));
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
                filterField.addTextChangeListener(event -> {
                            new Notification("addTextChangeListener Filter \n","Value: " + event.getText() ,
                                    Notification.TYPE_WARNING_MESSAGE, true)
                                    .show(Page.getCurrent());
                        }
                );
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

    public void addRow(Users row){
        users.addBean(row);
    }

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

    public List<Address> getSRows() {
       Collection <Object> list = grid.getSelectedRows();
        List <Address> subList = new LinkedList<>();
        for (Object el : list) {
            subList.add(users.getItem(el).getBean());
        }
        return subList;
    }
}
