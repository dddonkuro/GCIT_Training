package com.gcit.training.lms.entity;
import java.util.ArrayList;
import java.util.List;

public class BookLoans {
	private int bookId,cardNo,branchId;
	private String title,dateOut,dueDate,dateIn;
	private List<LibraryBranch> branches;
	private List<Book> books;
	private List<Borrower> borrowers;
	
	
	
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getCardNo() {
		return cardNo;
	}
	public void setCardNo(int cardNo) {
		this.cardNo = cardNo;
	}
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDateOut() {
		return dateOut;
	}
	public void setDateOut(String dateOut) {
		this.dateOut = dateOut;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getDateIn() {
		return dateIn;
	}
	public void setDateIn(String dateIn) {
		this.dateIn = dateIn;
	}
	public List<LibraryBranch> getBranches() {
		return branches;
	}
	public void setBranches(List<LibraryBranch> branches) {
		this.branches = branches;
	}
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	public List<Borrower> getBorrowers() {
		return borrowers;
	}
	public void setBorrowers(List<Borrower> borrowers) {
		this.borrowers = borrowers;
	}

	

}
