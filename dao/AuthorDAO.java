package com.gcit.training.lms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.lms.entity.Author;

public class AuthorDAO {

	private static Singleton conn = Singleton.getOneTimeConnection();
	@SuppressWarnings("static-access")
	private static Connection dbConn = conn.getConnection();
	public void create(Author a) throws SQLException {
		//Connection conn = getConnection();
		String insert = "insert into tbl_author (authorName) values (?)";
		PreparedStatement stmt = dbConn.prepareStatement(insert);

		stmt.setString(1, a.getAuthorName());
		stmt.executeUpdate();
	}

	public void update(Author a) throws SQLException {
		//Connection conn = getConnection();
		String sql = "update tbl_author set authorName = ? where authorId = ?";
		PreparedStatement stmt = dbConn.prepareStatement(sql);

		stmt.setString(1, a.getAuthorName());
		stmt.setInt(2, a.getAuthorId());

		stmt.executeUpdate();
	}

	public void delete(Author a) throws SQLException {
		//Connection conn = getConnection();
		String sql = "delete from tbl_author where authorId = ?";
		PreparedStatement stmt = dbConn.prepareStatement(sql);

		stmt.setInt(1, a.getAuthorId());

		stmt.executeUpdate();
	}

	public Author readOne(int authorId) throws SQLException {

		//Connection conn = getConnection();

		String sql = "select * from tbl_author where authorId = ?";
		PreparedStatement stmt = dbConn.prepareStatement(sql);
		stmt.setInt(1, authorId);

		ResultSet rs = stmt.executeQuery(sql);

		Author a = new Author();
		if (rs.next()) {
			a.setAuthorId(rs.getInt("authorId"));
			a.setAuthorName(rs.getString("authorName"));
		}

		return a;

	}

	public List<Author> readAll() throws SQLException {

		//Connection conn = getConnection();

		String sql = "select * from tbl_author";
		PreparedStatement stmt = dbConn.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery(sql);

		List<Author> aList = new ArrayList<Author>();
		while(rs.next()) {
			Author a = new Author();
			a.setAuthorId(rs.getInt("authorId"));
			a.setAuthorName(rs.getString("authorName"));
			
			aList.add(a);
		}

		return aList;

	}
	
	public List<Author> readByName(String searchString) throws SQLException {

		//Connection conn = getConnection();
		
		String qString = "%" + searchString + "%";

		String sql = "select * from tbl_author like ?";
		PreparedStatement stmt = dbConn.prepareStatement(sql);
		stmt.setString(1, qString);

		ResultSet rs = stmt.executeQuery(sql);

		List<Author> aList = new ArrayList<Author>();
		while(rs.next()) {
			Author a = new Author();
			a.setAuthorId(rs.getInt("authorId"));
			a.setAuthorName(rs.getString("authorName"));
			
			aList.add(a);
		}

		return aList;

	}
}
