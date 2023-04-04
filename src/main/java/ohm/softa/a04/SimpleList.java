package ohm.softa.a04;

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
	 * Generate a new list using the given filter instance.
	 * @return a new, filtered list
	 */
	SimpleList<T> filter(SimpleFilter<T> filter);
}
