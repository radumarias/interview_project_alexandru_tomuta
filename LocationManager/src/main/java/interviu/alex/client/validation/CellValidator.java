package interviu.alex.client.validation;

/**
 * Created by alexa on 7/14/2017.
 */
public interface CellValidator<K> {
    void validate(K k) throws CellValidationException;
}
