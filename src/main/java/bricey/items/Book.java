package bricey;

class Book extends LibraryItem {
	private String author;
	private int publishYear;

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String newAuthor) {
		author = newAuthor;
	}

	public int getPublishYear() {
		return publishYear;
	}

	public void setPublishYear(int year) {
		publishYear = year;
	}

	@Override
	public ConfigurableField<?>[] getFields() {
		return Utils.concatArrays(
			super.getFields(),
			new ConfigurableField<?>[] {
				new ConfigurableField<String>(String.class, "Author"),
				new ConfigurableField<Integer>(Integer.class, "PublishYear", "Year of Publishing", null)
			}
		);
	}

	@Override
	public void setMembersByFields(ConfigurableField<?>[] fields) {
		// See, this code was duplicated from LibraryItem.
		// This entire system should be refactored to use 
		// reflection but Java's so... special... that it
		// probably requires less boilerplate just to do this
		for (ConfigurableField<?> field : fields) {
			switch (field.getName()) {
				case "Author":
					author = field.getValue().toString();
					break;
				case "PublishYear":
					// Ya know, I thought by using generics
					// I could get around casting like this.
					// Guess not!
					publishYear = (int) field.getValue();
					break;
			}
		}
		// Pass it up the line. Woohoo inheritance!
		super.setMembersByFields(fields);
	}

	@Override
	public String toString() {
		return super.toString() 
			+ "\nWritten by: " + author
			+ "\nin: " + publishYear;
	}
}
