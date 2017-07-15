package interviu.alex.client.validation;

import com.google.gwt.core.client.GWT;

/**
 * Created by alexa on 7/14/2017.
 */
public class FloatNumberFormatValidator implements CellValidator<String> {

    @Override
    public void validate(String value) throws CellValidationException {
        try{
            Float.parseFloat(value);
        }
        catch (NumberFormatException nfe){
            GWT.log("Failed float parsing validation for string ["+value+"]");
            throw new CellValidationException("Not a float number");
        }
    }
}
