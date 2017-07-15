package interviu.alex.client.components;

import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.user.cellview.client.CellTable;
import interviu.alex.client.handlers.OnUpdateHandler;
import interviu.alex.client.validation.CellValidationException;
import interviu.alex.client.validation.CellValidator;
import interviu.alex.shared.model.googleapi.Place;

import java.util.List;

/**
 * Created by alexa on 7/14/2017.
 */
public class CustomEditTextCell<T> extends EditTextCell {

    private CellTable<T> table;
    private List<CellValidator<String>> validators;
    private List<OnUpdateHandler<T, String>> handlers;

    public CustomEditTextCell(CellTable<T> table, List<CellValidator<String>> validators, List<OnUpdateHandler<T, String>> updateHandlers){
        this.table = table;
        this.validators = validators;
        this.handlers = updateHandlers;
    }

    @Override
    public void onBrowserEvent(Context context, Element parent, String value, NativeEvent event, ValueUpdater<String> valueUpdater) {
        super.onBrowserEvent(context, parent, value, event, valueToUpdate -> {
            try{
                for(CellValidator<String> validator : validators){
                    validator.validate(valueToUpdate);
                }
                for(OnUpdateHandler<T, String> handler : handlers){
                    handler.handleUpdate((T) context.getKey(), valueToUpdate);
                }
            }
            catch (CellValidationException cve){
                new AlertPopup("Value ["+valueToUpdate+"] is not a number").show();
                this.clearViewData(context.getKey());
                table.redraw();
            }
        });
    }

}
