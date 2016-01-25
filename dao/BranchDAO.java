package com.gcit.training.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.lms.entity.LibraryBranch;

public class BranchDAO {
	private static Singleton conn = Singleton.getOneTimeConnection();
	@SuppressWarnings("static-access")
	private static Connection dbConn = conn.getConnection();
	
	
	public void create(LibraryBranch obj) throws SQLException {
		
			  String insert = "insert into tbl_library_branch (branchName,branchAddress) values (?,?)";
				PreparedStatement stmt = dbConn.prepareStatement(insert);
				stmt.setString(1, obj.getBranchName());
				stmt.setString(2, obj.getBranchAddress());
				stmt.executeUpdate();
	
		
	}

	public void update(LibraryBranch obj) throws SQLException {
	
		String sql = "update tbl_library_branch set branchName = ?,branchAddress =? where branchId = ?";
		PreparedStatement stmt = dbConn.prepareStatement(sql);
		stmt.setString(1, obj.getBranchName());
		stmt.setString(2,obj.getBranchAddress());
		stmt.executeUpdate();
	}

	public void delete(LibraryBranch branch) throws SQLException {
		
		String sql = "delete from tbl_library_branch where branchId = ?";
		PreparedStatement stmt = dbConn.prepareStatement(sql);
		stmt.setInt(1, branch.getBranchId());
		stmt.executeUpdate();
	}

	public LibraryBranch readOne(int branchId) throws SQLException {

		String sql = "select * from tbl_library_branch where branchId = ?";
		PreparedStatement stmt = dbConn.prepareStatement(sql);
		stmt.setInt(1, branchId);
		ResultSet rs = stmt.executeQuery(sql);

		LibraryBranch b = new LibraryBranch();
		
		if (rs.next()) {
			b.setBranchId(rs.getInt("branchId"));
			b.setBranchName(rs.getString("branchName"));
			b.setBranchAddress(rs.getString("branchAddress"));
		}

		return b;

	}

	public ArrayList<LibraryBranch> readAll() throws SQLException {

		String sql = "select * from tbl_library_branch";
		PreparedStatement stmt = dbConn.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery(sql);

		ArrayList<LibraryBranch> aList = new ArrayList<LibraryBranch>();
		while(rs.next()) {
			LibraryBranch b = new LibraryBranch();
			b.setBranchId(rs.getInt("branchId"));
			b.setBranchName(rs.getString("branchName"));
			b.setBranchAddress(rs.getString("branchAddress"));
			aList.add(b);
		}

		return aList;

	}
	
	public List<LibraryBranch> readByName(String searchString) throws SQLException {

		//Connection conn = getConnection();
		
		String qString = "%" + searchString + "%";

		String sql = "select * from tbl_library_branch where branchName like ?";
		PreparedStatement stmt = dbConn.prepareStatement(sql);
		stmt.setString(1, qString);

		ResultSet rs = stmt.executeQuery();

		List<LibraryBranch> aList = new ArrayList<LibraryBranch>();
		while(rs.next()) {
			LibraryBranch b = new LibraryBranch();
			b.setBranchId(rs.getInt("branchId"));
			b.setBranchName(rs.getString("branchName"));
			b.setBranchName(rs.getString("branchAddress"));
			aList.add(b);
		}

		return aList;

	}
	public List<LibraryBranch> readByAddress(String searchString) throws SQLException {

		//Connection conn = getConnection();
		
		String qString = "%" + searchString + "%";

		String sql = "select * from tbl_library_branch where branchAddress like ?";
		PreparedStatement stmt = dbConn.prepareStatement(sql);
		stmt.setString(1, qString);

		ResultSet rs = stmt.executeQuery();

		List<LibraryBranch> aList = new ArrayList<LibraryBranch>();
		while(rs.next()) {
			LibraryBranch b = new LibraryBranch();
			b.setBranchId(rs.getInt("branchId"));
			b.setBranchName(rs.getString("branchName"));
			b.setBranchAddress(rs.getString("branchAddress"));
			aList.add(b);
		}

		return aList;

	}
	

}
