package frontend.elements.components;

import com.vaadin.event.FieldEvents;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.ui.TextField;

/**
 * Created by LikeMilk on 04.05.2016.
 */
public abstract class OnEnterKeyHandler {

    final ShortcutListener enterShortCut = new ShortcutListener(
            "EnterOnTextAreaShorcut", ShortcutAction.KeyCode.ENTER, null) {
        @Override
        public void handleAction(Object sender, Object target) {
            onEnterKeyPressed();
        }
    };

    public void installOn(final TextField component)
    {
        component.addFocusListener(
                new FieldEvents.FocusListener() {

                    @Override
                    public void focus(FieldEvents.FocusEvent event
                    ) {
                        component.addShortcutListener(enterShortCut);
                    }

                }
        );

        component.addBlurListener(
                new FieldEvents.BlurListener() {

                    @Override
                    public void blur(FieldEvents.BlurEvent event
                    ) {
                        component.removeShortcutListener(enterShortCut);
                    }

                }
        );
    }

    public abstract void onEnterKeyPressed();

}