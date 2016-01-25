package com.gcit.training.lms.entity;
import java.util.*;
public class BookCopies {
	private List<LibraryBranch> branches;
	private List<Book> books;
	private int numOfCopies;
	
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
	public int getNumOfCopies() {
		return numOfCopies;
	}
	public void setNumOfCopies(int numOfCopies) {
		this.numOfCopies = numOfCopies;
	}
}
