package ohm.softa.a04;

import java.util.function.Function;

public interface SimpleList<T> extends Iterable<T> {
    /**
     * Add a given object to the back of the list.
     */
    void add(T o);

    /**
     * @return current size of the list
     */
    int size();

    // Keine Ahnung warum man das tun sollte, aber es geht...
    @SuppressWarnings("unchecked")
    default void addDefault(Class<T> clazz) {
        try {
            /* better solution would be to use Google Guava to get a type token
            and use this token to create a new instance
            because we didn't include a reference to Guava this is the easiest way to create new instance of T */
            this.add(clazz.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get a new SimpleList instance with all items of this list which match the given filter
     *
     * @param filter SimpleFilter instance
     * @return new SimpleList instance
     */
    default SimpleList<T> filter(SimpleFilter<T> filter) {
        SimpleList<T> result = new SimpleListImpl<>();
        for (T o : this) {
            if (filter.include(o)) {
                result.add(o);
            }
        }
        return result;
    }

    default <R> SimpleList<R> map(Function<T, R> transform) {
        SimpleList<R> newList = new SimpleListImpl<>();
        this.forEach(elem -> newList.add(transform.apply(elem)));
        return newList;
    }
}

