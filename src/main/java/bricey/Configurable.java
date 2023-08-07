package bricey;

/**
 * Used to specify how something
 * can be added, changed, or removed
 * on the CLI
 */
interface Configurable {
	public ConfigurableField<?>[] getFields();
	public void setMembersByFields(ConfigurableField<?>[] fields);
}

