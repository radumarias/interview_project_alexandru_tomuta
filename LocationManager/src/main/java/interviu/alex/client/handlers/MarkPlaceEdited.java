package interviu.alex.client.handlers;

import interviu.alex.client.components.AlertPopup;
import interviu.alex.shared.model.googleapi.Place;

/**
 * Created by alexa on 7/15/2017.
 */
public class MarkPlaceEdited implements OnUpdateHandler<Place, String> {
    @Override
    public void handleUpdate(Place place, String value) {
        if(place != null){
            place.setUserEdited(true);
            new AlertPopup("Marked ["+place.getName()+"] as user edited!").show();
        }
        else{
            new AlertPopup("Failed to mark place as modified!").show();
        }
    }
}
