package com.gcit.training.lms.entity;
import java.util.*;
public class BookGenre {
	
	private int genreId,bookId; //book id, genre id
	private Book book;//book
	private List<Genre> genre;
	public BookGenre(){
		book = new Book();
		genre = new ArrayList<>();
	}
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public List<Genre> getGenre() {
		return genre;
	}

	public void setGenre(List<Genre> genre) {
		this.genre = genre;
	}
	public int getGenreId(){
		return genreId;
	}

	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	
}
