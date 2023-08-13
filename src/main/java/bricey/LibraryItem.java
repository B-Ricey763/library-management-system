package bricey;

import java.util.LinkedList;
import java.util.Arrays;
import java.io.Serializable;

abstract class LibraryItem implements Borrowable, Configurable, Serializable {
	private int id;
	private String name;
	private String description;
	private String borrowingMemberName;
	private LinkedList<BorrowTransaction> borrowingHistory;

	public LibraryItem(int i, String n, String d) {
		id = i;
		name = n;
		description = d;
	}

	public LibraryItem(int i, String n) {
		this(i, n, "");
	}

	public LibraryItem() {
		this(-1, "", "");
	}

	public void setId(int i) {
		id = i;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String newName) {
		name = newName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescrption(String newDesc) {
		description = newDesc;
	}

	public String getBorrowingMemberName() {
		return borrowingMemberName != null ? borrowingMemberName.trim() : null;
	}

	public LinkedList<BorrowTransaction> getHistory() {
		return borrowingHistory;
	}

	public void checkOut(String name) {
		borrowingMemberName = name;
	}

	public void returnItem() {
		borrowingMemberName = null;
	}

	public ConfigurableField<?>[] getFields() {
		return new ConfigurableField<?>[] {
			new ConfigurableField<String>(String.class, "Name"),
			// Give description a default value
			new ConfigurableField<String>(String.class, "Description", "")
		};
	}

	public void setMembersByFields(ConfigurableField<?>[] fields) {
		for (ConfigurableField<?> field : fields) {
			switch (field.getName()) {
				case "Name":
					name = field.getValue().toString();
					break;
				case "Description":
					description = field.getValue().toString();
					break;
			}
		}
	}

	@Override
	public String toString() {
		return name + 
			"\nWith Description: " + description;
	}

}
