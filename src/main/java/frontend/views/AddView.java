package frontend.views;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import frontend.elements.components.HeaderLayout;

/**
 * Created by Александр on 13.03.2016.
 */
@Theme("mytheme")
public class AddView extends CustomComponent implements View {

    public static final String NAME = "add";

    AbsoluteLayout mainlayout;
    HeaderLayout header;
    HorizontalLayout body;

    public AddView() {
        mainlayout = new AbsoluteLayout();
        body = new HorizontalLayout();

        // Styles
        mainlayout.setStyleName("v-main-body");
        body.setStyleName("bodylayout");
        // Add components
        mainlayout.addComponent(body, "left: 0px; top: 10%;");

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
        String status = String.valueOf(getSession().getAttribute("status"));

        mainlayout.removeAllComponents();

        header = new HeaderLayout(status);
        mainlayout.addComponent(body, "left: 0px; top: 10%;");
        mainlayout.addComponent(header, "left: 0px; top: 0px; bottom: 90%;");
    }
}
