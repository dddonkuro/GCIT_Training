package com.gcit.training.lms.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import com.gcit.training.lms.entity.LibraryBranch;
import com.gcit.training.lms.entity.*;;

public class BookCopiesDAO {
	
	private static Singleton conn = Singleton.getOneTimeConnection();
	@SuppressWarnings("static-access")
	private static Connection dbConn = conn.getConnection();
	 
	public void create(BookCopies copy) throws SQLException {
			List<Book>books = copy.getBooks();
			List<LibraryBranch> branches = copy.getBranches();
			
			for(int i  =0;i<books.size();i++){
				String insert = "insert into tbl_book_copies (bookI,bracnhId,noOfCopies) values (?,?,?)";
				Book bk = books.get(1);
				LibraryBranch branch = branches.get(i);
				PreparedStatement stmt = dbConn.prepareStatement(insert);
				stmt.setInt(1, bk.getBookId());
				stmt.setInt(2, branch.getBranchId());
				stmt.setInt(3, copy.getNumOfCopies());
				stmt.executeUpdate();
		
			}
	}

	public void update(BookCopies copy) throws SQLException {
		
			
			List<Book>books = copy.getBooks();
			List<LibraryBranch> branches = copy.getBranches();
			
			for(int i  =0;i<books.size();i++){
				String sql = "update tbl_book_copies set bookId = ?,branchId = ? where noOfCopies = ?";;
				Book bk = books.get(1);
				LibraryBranch branch = branches.get(i);
				PreparedStatement stmt = dbConn.prepareStatement(sql);
				stmt.setInt(1, bk.getBookId());
				stmt.setInt(2, branch.getBranchId());
				stmt.setInt(3, copy.getNumOfCopies());
				stmt.executeUpdate();
			}
	}

	public void delete(BookCopies copy) throws SQLException {
			List<Book>books = copy.getBooks();
			List<LibraryBranch> branches = copy.getBranches(); 
		
			for(int i =0; i< books.size();i++){
				String sql = "delete from tbl_library_branch where bookId = ?";
				Book bk = books.get(1);
				LibraryBranch branch = branches.get(i);
				PreparedStatement stmt = dbConn.prepareStatement(sql);
				stmt.setInt(1, bk.getBookId());
				stmt.executeUpdate();
			}
			
			
		
	}

	public Object readOneBook(int ObjectId) throws SQLException {
		
		String sql = "select * from tbl_book where bookId = ?";
		PreparedStatement stmt = dbConn.prepareStatement(sql);
		stmt.setInt(1, ObjectId);

		ResultSet rs = stmt.executeQuery();

		Book a = new Book();
		if (rs.next()) {
			a.setBookId(rs.getInt("bookId"));
			a.setTitle(rs.getString("title"));
		}

		return a;

	}
	
public Object readOneBranch(int ObjectId) throws SQLException {
		
		String sql = "select * from tbl_library_branch where branchId = ?";
		PreparedStatement stmt = dbConn.prepareStatement(sql);
		stmt.setInt(1, ObjectId);
		ResultSet rs = stmt.executeQuery();

		LibraryBranch a = new LibraryBranch();
		if (rs.next()) {
			a.setBranchId(rs.getInt("branchId"));
			a.setBranchName(rs.getString("branchName"));
			a.setBranchAddress(rs.getString("branchAddress"));
		}

		return a;

	}

	public List<Book> readAllBook() throws SQLException {

		 
		String sql = "select * from tbl_book_copies C,tbl_book B where C.bookId = B.bookId";
		PreparedStatement stmt = dbConn.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		List<Book> aList = new ArrayList<>();
		while(rs.next()) {
			Book a = new Book();
			a.setBookId(rs.getInt("bookId"));
			a.setTitle(rs.getString("title"));
			aList.add(a);
		}

		return aList;

	}
	
	public List<LibraryBranch> readAllBranches() throws SQLException {

		 
		String sql = "select * from tbl_book_copies C,tbl_library_branch B where C.branchId = B.branchId";
		PreparedStatement stmt = dbConn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		List<LibraryBranch> aList = new ArrayList<LibraryBranch>();
		while(rs.next()) {
			LibraryBranch a = new LibraryBranch();
			a.setBranchId(rs.getInt("branchId"));
			a.setBranchName(rs.getString("branchName"));
			a.setBranchAddress(rs.getString("branchAddress"));
			aList.add(a);
		}

		return aList;

	}
	
	public List<Book> readByBookTitle(String searchString) throws SQLException {

		String qString = "%" + searchString + "%";

		String sql = "select * from tbl_book_copies C,tbl_book B where C.title = B.title AND B.title like ?";
		PreparedStatement stmt = dbConn.prepareStatement(sql);
		stmt.setString(1, qString);

		ResultSet rs = stmt.executeQuery();

		List<Book> aList = new ArrayList<>();
		while(rs.next()) {
			Book a = new Book();
			a.setBookId(rs.getInt("bookId"));
			a.setTitle(rs.getString("title"));
			aList.add(a);
		}

		return aList;

	}
	
	public List<LibraryBranch> readByBranchName(String searchString) throws SQLException {

		//Connection conn = getConnection();
		
		String qString = "%" + searchString + "%";

		String sql = "select * from tbl_library_branch BR,tbl_book_copies BC where BR.branchId = BC.branchId AND BR.branchName like ?";
		PreparedStatement stmt = dbConn.prepareStatement(sql);
		stmt.setString(1, qString);

		ResultSet rs = stmt.executeQuery();

		List<LibraryBranch> aList = new ArrayList<>();
		while(rs.next()) {
			LibraryBranch a = new LibraryBranch();
			a.setBranchId(rs.getInt("branchId"));
			a.setBranchName(rs.getString("BranchName"));
			a.setBranchAddress(rs.getString("BranchAddress"));
			aList.add(a);
		}

		return aList;

	}
	public List<String> readByNoOfCopies(int numbOfCopies) throws SQLException {


		String sql = "select * from tbl_library_branch BR,tbl_book_copies BC where BR.branchId = BC.branchId AND BR.branchName like ?";
		PreparedStatement stmt = dbConn.prepareStatement(sql);
		stmt.setInt(1, numbOfCopies);

		ResultSet rs = stmt.executeQuery();

		List<String> aList = new ArrayList<>();
		 String cols = "BranchId: BranchName: BranchAddress: Titile: NoOfCopies";
		 aList.add(cols);
		while(rs.next()) {
			String book_copies = "";
			book_copies += rs.getInt("branchId")+" : ";
			book_copies += rs.getString("BranchName")+" : ";
			book_copies += rs.getString("BranchAddress")+ " : ";
			book_copies += rs.getString("title")+ " : ";
			aList.add(book_copies);
		}

		return aList;

	}
}
