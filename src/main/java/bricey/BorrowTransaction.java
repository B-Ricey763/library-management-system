package bricey;

import java.time.LocalDateTime;

class BorrowTransaction {
	private Borrowable item;
	private String details;
	private String time;
	private String memberName;

	public BorrowTransaction(Borrowable i, String m, String d) {
		item = i;
		details = d;
		memberName = m;

		time = "";
	}

	public Borrowable getItem() {
		return item;
	}

	public String getDetails() {
		return details;
	}

	public String  getTime() {
		return time;
	}

	public String getMember() {
		return memberName;
	}
}

