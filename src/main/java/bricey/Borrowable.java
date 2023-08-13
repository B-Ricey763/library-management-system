package bricey;

import java.util.LinkedList;

/*
 * Implements borrowing functionality
 * to check out and return items
 * as well as seeing the past borrows
 */
public interface Borrowable {
    public void checkOut(String member);
    public void returnItem();
    public LinkedList<BorrowTransaction> getHistory();
}
