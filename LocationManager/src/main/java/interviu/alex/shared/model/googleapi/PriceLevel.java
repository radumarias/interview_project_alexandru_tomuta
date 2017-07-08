package interviu.alex.shared.model.googleapi;

import java.util.HashMap;
import java.util.Map;

/**
 * Translating google price level int values to their representing string value
 * Created by alexa on 7/8/2017.
 */
public class PriceLevel {

    private final static Map<Integer, String> priceLevelMapping = new HashMap<>();
    static {
        priceLevelMapping.put(0,"Free");
        priceLevelMapping.put(1,"Inexpensive");
        priceLevelMapping.put(2,"Moderate");
        priceLevelMapping.put(3,"Expensive");
        priceLevelMapping.put(4,"Very Expensive");
    }

    public String of(int priceLevel){
        if(priceLevel > 5 || priceLevel < -1) {
            throw new IllegalArgumentException("Price level out of range!");
        }

        return priceLevelMapping.get(priceLevel);
    }
}
