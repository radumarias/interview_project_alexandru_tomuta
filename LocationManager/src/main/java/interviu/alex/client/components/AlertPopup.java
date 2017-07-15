package interviu.alex.client.components;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;

/**
 * Created by alexa on 7/13/2017.
 */
public class AlertPopup extends PopupPanel {

    public AlertPopup(String text){
        super(true);
        setWidget(new Label(text));
        setSize("250", "130");
    }
}
