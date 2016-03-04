package frontend.elements.tableusers;

import Data.Users;
import com.google.gwt.thirdparty.guava.common.collect.Lists;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.GeneratedPropertyContainer;
import com.vaadin.data.util.PropertyValueGenerator;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.*;
import com.vaadin.ui.renderers.ButtonRenderer;

import java.util.Collection;

/**
 * Created by likemilk on 24.02.2016.
 */
public class TableUsers extends HorizontalLayout{
    Collection<Users> people;
    Grid grid;
    BeanItemContainer<Users> users = new BeanItemContainer<>(Users.class);
    GeneratedPropertyContainer gpc;
    public TableUsers() {
        super();
        this.setSizeFull();
        //init
        people = Lists.newArrayList(
                new Users("ss", "ss", "ss"),
                new Users("ss", "ss", "ss"),
                new Users("ss", "ss", "ss"));

        users.addBean(new Users("ss", "ss", "ss"));
        users.addBean(new Users("1", "ss", "ss"));
        users.addBean(new Users("2", "ss", "ss"));
        users.addBean(new Users("2", "ss", "ss"));
        users.addBean(new Users("3", "ss", "ss"));
        users.addBean(new Users("4", "ss", "ss"));

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
                .setRenderer(new ButtonRenderer(e -> // Java 8
                        grid.getContainerDataSource()
                                .removeItem(e.getItemId())));

        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        grid.setSizeFull();
        grid.setEditorEnabled(true);

// Create a header row to hold column filters
        Grid.HeaderRow filterRow = grid.appendHeaderRow();

// Set up a filter for all columns
        for (Object pid : grid.getContainerDataSource()
                .getContainerPropertyIds()) {
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
        }
        this.addComponent(grid);
    }
}
