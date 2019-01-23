package com.douzon.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzon.bookmall.vo.BookVO;

public class BookDao {

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	public BookDao() {
		connectionDB();
	}

	private void connectionDB() {
		try {
			// 1. JDBC Driver(MySQL) 로딩
			Class.forName("com.mysql.jdbc.Driver");

			// 2. 연결하기(Connection 객체 얻어오기)
			String url = "jdbc:mysql://127.0.0.1:3306/bookmall";
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");

			// 3. PrepareStatement 객체 생성
			pstmt = conn.prepareStatement(url);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩실패 : " + e);
		} catch (SQLException e) {
			System.out.println("error : " + e);
		}
	}

	// 자원정리
	private void resourceTheorem() {
		try {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// C
	public boolean insert(BookVO vo) {
		boolean result = false;

		try {
			String sql = "insert into book values (null, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getBookName());
			pstmt.setLong(2, vo.getPrice());
			pstmt.setLong(3, vo.getCategoryNo());

			int count = pstmt.executeUpdate();
			result = count == 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			resourceTheorem();
		}

		return result;
	}	

	// R
	public List<BookVO> getList() {
		List<BookVO> list = new ArrayList<BookVO>();
		
		try {
			String sql = "select book.no, book.name, category.name, book.price" + 
					"  from book, category" + 
					"  where book.category_no = category.no" +
					"  order by book.no asc";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery(sql);
			
			while(rs.next()) {
				BookVO vo = new BookVO();
				vo.setBookNo(rs.getLong(1));
				vo.setBookName(rs.getString(2));
				vo.setCategoryName(rs.getString(3));
				vo.setPrice(rs.getLong(4));
				
				list.add(vo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			resourceTheorem();
		}
		
		return list;
	}	
}
