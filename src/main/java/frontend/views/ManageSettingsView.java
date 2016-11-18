package frontend.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import frontend.elements.components.HeaderLayout;

/**
 * Created by Александр on 25.04.2016.
 */
public class ManageSettingsView extends CustomComponent implements View {
    public static final String NAME = "managesettings";

    AbsoluteLayout mainlayout;
    HeaderLayout header;
    HorizontalLayout body;

    private static ManageSettingsView instance;

    public static  ManageSettingsView getInstance() {
        ManageSettingsView localInstance = instance;
        if (localInstance == null) {
            localInstance = instance;
            if (localInstance == null) {
                instance = localInstance = new ManageSettingsView();
            }
        }
        return localInstance;
    }

    private ManageSettingsView() {
        mainlayout = new AbsoluteLayout();

        body = new HorizontalLayout();
        // Styles
        mainlayout.setStyleName("v-main-body");
        body.setStyleName("settingslayout");

        // Add components
        mainlayout.addComponent(body, "left: 0px; top: 10%;");

        //header.setSizeFull();
        body.setSizeFull();
        mainlayout.setSizeFull();

        ///////////////////

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
    }
}
