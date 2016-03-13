package frontend.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import frontend.elements.components.HeaderLayout;

/**
 * Created by Александр on 13.03.2016.
 */
public class ManageView extends CustomComponent implements View {

    public static final String NAME = "manage";

    AbsoluteLayout mainlayout;
    HeaderLayout header;
    HorizontalLayout body;

    public ManageView() {
        mainlayout = new AbsoluteLayout();
        header = new HeaderLayout();
        body = new HorizontalLayout();

        // Styles
        mainlayout.setStyleName("v-main-body");

        // Attributions
        header.setMargin(true);

        // Add components
        mainlayout.addComponent(header, "left: 0px; top: 0px;");
        mainlayout.addComponent(body, "left: 0px; top: 100px;");

        //header.setSizeFull();
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
