package bricey;

class Member {
	private String name;

	public Member(String n) {
		name = n;
	}

	public void borrow(Borrowable item) {
		item.checkOut(this);
	}
}
