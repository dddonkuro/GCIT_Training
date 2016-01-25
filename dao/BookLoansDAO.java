package com.gcit.training.lms.dao;
import java.sql.Connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.lms.entity.*;

public class BookLoansDAO {
	
	private static Singleton conn = Singleton.getOneTimeConnection();
	@SuppressWarnings("static-access")
	private static Connection dbConn = conn.getConnection();
	
	
	public void create(Object obj) throws SQLException {
		 if(obj instanceof Borrower){
			 String insert = "insert into tbl_borrower (name,address,phone) values (?,?,?)";
				PreparedStatement stmt = dbConn.prepareStatement(insert);
				stmt.setString(1, ((Borrower)obj).getBorrowerName());
				stmt.setString(2, ((Borrower)obj).getAddress());
				stmt.setString(3, ((Borrower)obj).getPhoneNumber());
				stmt.executeUpdate();
		 }
		 else if(obj instanceof LibraryBranch){
			 String insert = "insert into tbl_library_branch (branchName,branchAddress) values (?,?)";
				PreparedStatement stmt = dbConn.prepareStatement(insert);
				stmt.setString(1, ((LibraryBranch)obj).getBranchName());
				stmt.setString(2, ((LibraryBranch)obj).getBranchAddress());
				stmt.executeUpdate();
		 }
		 else if(obj instanceof Book){
			    String insert = "insert into tbl_book (title) values (?)";
				PreparedStatement stmt = dbConn.prepareStatement(insert);
				stmt.setString(2, ((Book)obj).getTitle());
				stmt.executeUpdate();
		 }
		
	}

	public void update(Object entity) throws SQLException {
		
		if(entity instanceof Borrower){
			String sql = "update tbl_borrower set name = ?,address =?, phone = ? where cardNo = ?";
			PreparedStatement stmt = dbConn.prepareStatement(sql);
			stmt.setString(1, ((Borrower)entity).getBorrowerName());
			stmt.setString(2,((Borrower)entity).getAddress());
			stmt.setString(3,((Borrower)entity).getPhoneNumber());
			stmt.executeUpdate();
		}
		else if( entity instanceof LibraryBranch){
			String sql = "update tbl_library_branch set branchName = ?, branchAddress =? where branchId = ?";
			PreparedStatement stmt = dbConn.prepareStatement(sql);
			stmt.setString(1, ((Borrower)entity).getBorrowerName());
			stmt.setString(2,((Borrower)entity).getAddress());
			stmt.executeUpdate();
		}
		else if( entity instanceof Book){
			String sql = "update tbl_book set title = ? where bookId = ?";
			PreparedStatement stmt = dbConn.prepareStatement(sql);
			stmt.setString(1, ((Book)entity).getTitle());
			//stmt.setInt(2,((Book)entity).g);
			stmt.executeUpdate();
		}
	}

	public void delete(Object entity) throws SQLException {
		
		if(entity instanceof Borrower){
			String sql = "delete from tbl_borrower where cardNo = ?";
			PreparedStatement stmt = dbConn.prepareStatement(sql);
			stmt.setInt(1, ((Borrower)entity).getCardNo());
			stmt.executeUpdate();
		}
		else if(entity instanceof Book){
			String sql = "delete from tbl_book where bookId = ?";
			PreparedStatement stmt = dbConn.prepareStatement(sql);
			stmt.setInt(1, ((Book)entity).getBookId());
			stmt.executeUpdate();
			}
		else if(entity instanceof LibraryBranch){
			String sql = "delete from tbl_library_branch where branchId = ?";
			PreparedStatement stmt = dbConn.prepareStatement(sql);
			stmt.setInt(1, ((LibraryBranch)entity).getBranchId());
			stmt.executeUpdate();
			}
	}

	public Borrower readOneBorrower(int Id) throws SQLException {
	
		String sql = "select * from tbl_borrower where cardNo = ?";
		PreparedStatement stmt = dbConn.prepareStatement(sql);
		stmt.setInt(1, Id);

		ResultSet rs = stmt.executeQuery(sql);

		Borrower b = new Borrower();
		if (rs.next()) {
			b.setCardNo(rs.getInt("cardNo"));
			b.setBorrowerName(rs.getString("name"));
			b.setAddress(rs.getString("address"));
			b.setPhoneNumber(rs.getString("phone"));
		}

		return b;

	}
	public LibraryBranch readOneBranch(int Id) throws SQLException {
		
		String sql = "select * from tbl_library_branch where branchId = ?";
		PreparedStatement stmt = dbConn.prepareStatement(sql);
		stmt.setInt(1, Id);

		ResultSet rs = stmt.executeQuery(sql);

		LibraryBranch b = new LibraryBranch();
		if (rs.next()) {
			b.setBranchId(rs.getInt("BranchId"));
			b.setBranchName(rs.getString("branchName"));
			b.setBranchAddress(rs.getString("address"));
			
		}

		return b;

	}
	public Book readOneBook(int Id) throws SQLException {
		
		String sql = "select * from tbl_book_loans where bookId = ?";
		PreparedStatement stmt = dbConn.prepareStatement(sql);
		stmt.setInt(1, Id);

		ResultSet rs = stmt.executeQuery(sql);

		Book b = new Book();
		if (rs.next()) {
			b.setBookId(rs.getInt("bookId"));
			b.setTitle(rs.getString("title"));
		}

		return b;

	}

	public ArrayList<BookLoans> readAllBorrowers() throws SQLException {

		String sql = "select * from tbl_book_loans where cardNo IS NOT NULL ";
		PreparedStatement stmt = dbConn.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery(sql);

		ArrayList<BookLoans> aList = new ArrayList<>();
		while(rs.next()) {
			BookLoans b = new BookLoans();
			b.setCardNo(rs.getInt("cardNo"));
			b.setBookId(rs.getInt("bookId"));
			b.setBranchId(rs.getInt("branchId"));
			b.setDateOut(rs.getString("dateOut"));
			b.setDateIn(rs.getString("dateIn"));
			b.setDueDate(rs.getString("dueDate"));
			aList.add(b);
		}

		return aList;

	}
	
	public ArrayList<BookLoans> readAllBooks() throws SQLException {

		String sql = "select * from tbl_book_loans where bookId IS NOT NULL ";
		PreparedStatement stmt = dbConn.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery(sql);

		ArrayList<BookLoans> aList = new ArrayList<BookLoans>();
		while(rs.next()) {
			BookLoans b = new BookLoans();
			b.setBookId(rs.getInt("bookId"));
			b.setTitle(rs.getString("title"));
		
			 aList.add(b);
		}

		return aList;

	}
	
	public List<Borrower> readByDateOut(String searchString) throws SQLException {

		//Connection conn = getConnection();
		
		String qString = "%" + searchString + "%";

		String sql = "select * from tbl_book_loans like ?";
		PreparedStatement stmt = dbConn.prepareStatement(sql);
		stmt.setString(1, qString);

		ResultSet rs = stmt.executeQuery(sql);

		List<Borrower> aList = new ArrayList<Borrower>();
		while(rs.next()) {
			Borrower b = new Borrower();
			b.setCardNo(rs.getInt("cardNo"));
			b.setBorrowerName(rs.getString("name"));
			
			aList.add(b);
		}

		return aList;

	}
	public List<Borrower> readByAddress(String searchString) throws SQLException {

		//Connection conn = getConnection();
		
		String qString = "%" + searchString + "%";

		String sql = "select * from tbl_borrower like ?";
		PreparedStatement stmt = dbConn.prepareStatement(sql);
		stmt.setString(1, qString);

		ResultSet rs = stmt.executeQuery(sql);

		List<Borrower> aList = new ArrayList<Borrower>();
		while(rs.next()) {
			Borrower b = new Borrower();
			b.setCardNo(rs.getInt("address"));
			b.setBorrowerName(rs.getString("address"));
			
			aList.add(b);
		}

		return aList;

	}
	
	public List<Borrower> readByPhone(String searchString) throws SQLException {

		//Connection conn = getConnection();
		
		String qString = "%" + searchString + "%";

		String sql = "select * from tbl_borrower like ?";
		PreparedStatement stmt = dbConn.prepareStatement(sql);
		stmt.setString(1, qString);

		ResultSet rs = stmt.executeQuery(sql);

		List<Borrower> aList = new ArrayList<Borrower>();
		while(rs.next()) {
			Borrower b = new Borrower();
			b.setCardNo(rs.getInt("phone"));
			b.setBorrowerName(rs.getString("phone"));
			
			aList.add(b);
		}

		return aList;

	}

}
