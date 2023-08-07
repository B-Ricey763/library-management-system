package bricey;

import java.util.LinkedList;
import java.util.Arrays;
import java.io.Serializable;

abstract class LibraryItem implements Borrowable, Configurable, Serializable {
	private int id;
	private String name;
	private String description;
	private Member borrowingMember;
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

	public Member getCurrentBorrowingMember() {
		return borrowingMember;
	}

	public LinkedList<BorrowTransaction> getHistory() {
		return borrowingHistory;
	}

	public void checkOut(Member member) {
		borrowingMember = member;
	}

	public void returnItem(Member member) {
		// TOOD: implement
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
