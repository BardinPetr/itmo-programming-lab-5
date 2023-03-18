package ru.bardinpetr.itmo.lab5.client.ui.interfaces;

public interface UIInputReceiver {

    /**
     * Fill object from UI
     *
     * @param target class of object to request from user
     * @param <T>    object type
     * @return built object
     */
    default <T> T fill(Class<T> target) {
        return fill(target, null);
    }

    /**
     * Fill object from UI
     *
     * @param target        class of object to request from user
     * @param defaultObject object from which to take field default values
     * @param <T>           object type
     * @return built object
     */
    <T> T fill(Class<T> target, T defaultObject);

    /**
     * @return true if there is next line to read from stream
     */
    boolean hasNextLine();

    /**
     * read line from stream.
     *
     * @return line or null if stream closed
     */
    String nextLine();
}
