package bricey;

/**
 * Used in conjuntion with Configurable
 * to provide a way to interact with items via CLI.
 * When no default value is provided, it is assumed
 * the field is required. 
 */
class ConfigurableField<T> {
	// We have to use this weird 
	// type token thing since Java
	// doesn't let you use reflection to get
	// types of generics at runtime :(
	private final Class<T> type;
	private String name;
	private String prompt;
	private T defaultValue;
	private T value;

	public ConfigurableField(Class<T> t, String n, String p, T dv) {
		type = t;
		name = n;
		defaultValue = dv;
		prompt = p;
	}

	public ConfigurableField(Class<T> t, String n, T dv) {
		// Name is assumed to be same as prompt
		this(t, n, n, dv);
	}

	public ConfigurableField(Class<T> t, String n) {
		// Name is assumed to be same as prompt
		// and it's assumed to be required
		this(t, n, n, null);
	}

	public String getName() {
		return name;
	}

	public String getPrompt() {
		return prompt;
	}

	public T getDefaultValue() {
		return defaultValue;
	}

	public void setValue(String input) {
		// This is so messed up I don't even want to talk about it
		// Until of course I have to add more types, MANUALLY, 
		// to this potentially infinite if chain!!
		// In the name of backwards compatability, java's
		// type erasure sucks and generics are poop. UGH!
		if (type == Integer.class) {
			value = type.cast(Integer.parseInt(input));
		} else if (type == String.class) {
			value = type.cast(input);
		}
	}

	public T getValue() {
		return value;
	}

}
