package bricey;

import java.time.LocalDateTime;

class BorrowTransaction {
	private Borrowable item;
	private String details;
	private String time;
	private Member member;

	public BorrowTransaction(Borrowable i, Member m, String d) {
		item = i;
		details = d;
		member = m;

		time = "";
	}

	public BorrowTransaction(Borrowable i, Member m) {
		this(i, m, "No Details");
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

	public Member getMember() {
		return member;
	}
}

