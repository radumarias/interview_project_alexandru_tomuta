package interviu.alex.client.handlers;

/**
 * Triggered when a user makes a change
 * Created by alexa on 7/15/2017.
 */
public interface OnUpdateHandler<T, V> {
    void handleUpdate(T object, V value);
}
