package com.gcit.training.lms.dao;

import java.sql.Connection;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.lms.entity.Borrower;

public class BorrowerDAO {
	private static Singleton conn = Singleton.getOneTimeConnection();
	@SuppressWarnings("static-access")
	private static Connection dbConn = conn.getConnection();
	public void create(Borrower borrower) throws SQLException {
		
		String insert = "insert into tbl_borrower (name,address,phone) values (?,?,?)";
		PreparedStatement stmt = dbConn.prepareStatement(insert);
		stmt.setString(1, borrower.getBorrowerName());
		stmt.setString(2, borrower.getAddress());
		stmt.setString(3, borrower.getPhoneNumber());
		stmt.executeUpdate();
	}

	public void update(Borrower borrower) throws SQLException {
	
		String sql = "update tbl_borrower set name = ?,address = ?, phone = ? where cardNo = ?";
		PreparedStatement stmt = dbConn.prepareStatement(sql);
		stmt.setString(1, borrower.getBorrowerName());
		stmt.setString(2, borrower.getAddress());
		stmt.setString(3, borrower.getPhoneNumber());
		stmt.executeUpdate();
	}

	public void delete(Borrower borrower) throws SQLException {
		
		String sql = "delete from tbl_borrower where cardNo = ?";
		PreparedStatement stmt = dbConn.prepareStatement(sql);

		stmt.setInt(1, borrower.getCardNo());

		stmt.executeUpdate();
	}

	public Borrower readOne(int cardNo) throws SQLException {

		String sql = "select * from tbl_borrower where cardNo = ?";
		PreparedStatement stmt = dbConn.prepareStatement(sql);
		stmt.setInt(1, cardNo);

		ResultSet rs = stmt.executeQuery();

		Borrower b = new Borrower();
		if (rs.next()) {
			b.setCardNo(rs.getInt("cardNo"));
			b.setBorrowerName(rs.getString("name"));
			b.setAddress(rs.getString("address"));
			b.setPhoneNumber(rs.getString("phone"));
		}

		return b;

	}

	public ArrayList<Borrower> readAll() throws SQLException {

		String sql = "select * from tbl_borrower";
		PreparedStatement stmt = dbConn.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		ArrayList<Borrower> aList = new ArrayList<Borrower>();
		while(rs.next()) {
			Borrower b = new Borrower();
			b.setCardNo(rs.getInt("cardNo"));
			b.setBorrowerName(rs.getString("name"));
			b.setAddress(rs.getString("address"));
			b.setPhoneNumber(rs.getString("phone"));
			aList.add(b);
		}

		return aList;

	}
	
	public List<Borrower> readByName(String searchString) throws SQLException {

		//Connection conn = getConnection();
		
		String qString = "%" + searchString + "%";

		String sql = "select * from tbl_borrower name like ?";
		PreparedStatement stmt = dbConn.prepareStatement(sql);
		stmt.setString(1, qString);

		ResultSet rs = stmt.executeQuery();

		List<Borrower> aList = new ArrayList<Borrower>();
		while(rs.next()) {
			Borrower b = new Borrower();
			b.setCardNo(rs.getInt("cardNo"));
			b.setBorrowerName(rs.getString("name"));
			b.setBorrowerName(rs.getString("address"));
			b.setPhoneNumber(rs.getString("phone"));
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

		ResultSet rs = stmt.executeQuery();

		List<Borrower> aList = new ArrayList<Borrower>();
		while(rs.next()) {
			Borrower b = new Borrower();
			b.setCardNo(rs.getInt("cardNo"));
			b.setBorrowerName(rs.getString("name"));
			b.setBorrowerName(rs.getString("address"));
			b.setPhoneNumber(rs.getString("phone"));
			aList.add(b);
		}

		return aList;

	}
	
	public List<Borrower> readByPhone(String searchString) throws SQLException {

		//Connection conn = getConnection();
		
		String qString = "%"+searchString+"%";
		String sql = "select * from tbl_borrower  where phone like ?";
		PreparedStatement stmt = dbConn.prepareStatement(sql);
		stmt.setString(1, qString);
		ResultSet rs = stmt.executeQuery();

		List<Borrower> aList = new ArrayList<Borrower>();
		while(rs.next()) {
			Borrower b = new Borrower();
			b.setCardNo(rs.getInt("cardNo"));
			b.setBorrowerName(rs.getString("name"));
			b.setBorrowerName(rs.getString("address"));
			b.setPhoneNumber(rs.getString("phone"));
			aList.add(b);
		}

		return aList;

	}
}
