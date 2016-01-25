package com.gcit.training.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.lms.entity.*;

public class PublisherDAO {

	private static Singleton conn = Singleton.getOneTimeConnection();
	@SuppressWarnings("static-access")
	private static Connection dbConn = conn.getConnection();
	public void create(Publisher Publisher) throws SQLException {
		
		String insert = "insert into tbl_Publisher (publisherName,publisherAddress,publisherPhone) values (?,?,?)";
		PreparedStatement stmt = dbConn.prepareStatement(insert);
		stmt.setString(1, Publisher.getPublisherName());
		stmt.setString(2, Publisher.getPublisherAddress());
		stmt.setString(3, Publisher.getPublisherPhone());
		stmt.executeUpdate();
	}

	public void update(Publisher Publisher) throws SQLException {
	
		String sql = "update tbl_Publisher set publisherName = ? ,publisherAddress = ? ,publisherPhone = ? where cardNo = ?";
		PreparedStatement stmt = dbConn.prepareStatement(sql);
		stmt.setString(1, Publisher.getPublisherName());
		stmt.setString(2,Publisher.getPublisherAddress());
		stmt.setString(3,Publisher.getPublisherPhone());
		stmt.executeUpdate();
	}

	public void delete(Publisher Publisher) throws SQLException {
		
		String sql = "delete from tbl_Publisher where publisherName = ?";
		PreparedStatement stmt = dbConn.prepareStatement(sql);

		stmt.setInt(1, Publisher.getPublisherId());

		stmt.executeUpdate();
	}

	public Publisher readOne(int pubId) throws SQLException {

		String sql = "select * from tbl_Publisher where publisherId = ?";
		PreparedStatement stmt = dbConn.prepareStatement(sql);
		stmt.setInt(1, pubId);

		ResultSet rs = stmt.executeQuery(sql);

		Publisher b = new Publisher();
		if (rs.next()) {
			b.setPublisherId(rs.getInt("publisherId"));
			b.setPublisherName(rs.getString("publisherName"));
			b.setPublisherAddress(rs.getString("publisherAddress"));
			b.setPublisherPhone(rs.getString("publisherPhone"));
		}

		return b;

	}

	public ArrayList<Publisher> readAll() throws SQLException {

		String sql = "select * from tbl_Publisher";
		PreparedStatement stmt = dbConn.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery(sql);

		ArrayList<Publisher> aList = new ArrayList<Publisher>();
		while(rs.next()) {
			Publisher b = new Publisher();
			b.setPublisherId(rs.getInt("publisherId"));
			b.setPublisherName(rs.getString("publisherName"));
			b.setPublisherAddress(rs.getString("publisherAddress"));
			b.setPublisherPhone(rs.getString("publisherPhone"));
			aList.add(b);
		}

		return aList;

	}
	
	public List<Publisher> readByName(String searchString) throws SQLException {

		//Connection conn = getConnection();
		
		String qString = "%" + searchString + "%";

		String sql = "select * from tbl_Publisher where publisherName like ?";
		PreparedStatement stmt = dbConn.prepareStatement(sql);
		stmt.setString(1, qString);

		ResultSet rs = stmt.executeQuery();

		List<Publisher> aList = new ArrayList<Publisher>();
		while(rs.next()) {
			Publisher b = new Publisher();
			b.setPublisherId(rs.getInt("publisherId"));
			b.setPublisherName(rs.getString("publisherName"));
			b.setPublisherName(rs.getString("publisherAddress"));
			b.setPublisherPhone(rs.getString("publisherPhone"));
			aList.add(b);
		}

		return aList;

	}
	public List<Publisher> readByAddress(String searchString) throws SQLException {

		//Connection conn = getConnection();
		
		String qString = "%" + searchString + "%";

		String sql = "select * from tbl_publisher where publisherAddress like ?";
		PreparedStatement stmt = dbConn.prepareStatement(sql);
		stmt.setString(1, qString);

		ResultSet rs = stmt.executeQuery();

		List<Publisher> aList = new ArrayList<Publisher>();
		while(rs.next()) {
			Publisher b = new Publisher();
			b.setPublisherId(rs.getInt("publisherId"));
			b.setPublisherName(rs.getString("publisherName"));
			b.setPublisherAddress(rs.getString("publisherAddress"));
			b.setPublisherPhone(rs.getString("publisherPhone"));
			aList.add(b);
		}

		return aList;

	}
	
	public List<Publisher> readByPhone(String searchString) throws SQLException {

		//Connection conn = getConnection();
		
		String qString = "%"+searchString+"%";
		String sql = "select * from tbl_Publisher  where publisherPhone like ?";
		PreparedStatement stmt = dbConn.prepareStatement(sql);
		stmt.setString(1, qString);
		ResultSet rs = stmt.executeQuery();

		List<Publisher> aList = new ArrayList<Publisher>();
		while(rs.next()) {
			Publisher b = new Publisher();
			b.setPublisherId(rs.getInt("publisherId"));
			b.setPublisherName(rs.getString("publisherName"));
			b.setPublisherAddress(rs.getString("publisherAddress"));
			b.setPublisherPhone(rs.getString("publisherPhone"));
			aList.add(b);
		}

		return aList;

	}
}
