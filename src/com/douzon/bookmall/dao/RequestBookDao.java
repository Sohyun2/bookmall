package com.douzon.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.douzon.bookmall.vo.RequestBookVO;

public class RequestBookDao {

	Connection conn;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;

	public RequestBookDao() {
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
			stmt = conn.createStatement();
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
	public boolean insert(long memberNo) {
		boolean result = false;

		try {
			String sql = "select book.no, request.no, sum(cart.count)" + 
					"  from	book, cart, member, request" + 
					"  where book.no = cart.book_no" + 
					"  and cart.member_no = member.no" + 
					"  and request.member_no = member.no" + 
					"  and member.no = " + memberNo +
					"  group by member.no";
			stmt = conn.prepareStatement(sql);

			rs = stmt.executeQuery(sql);
			long bookNo = 0;
			long requestNo = 0;
			long bookCount = 0;
			
			if(rs.next()) {
				bookNo = rs.getLong(1);
				requestNo = rs.getLong(2);
				bookCount = rs.getLong(3);
			}			
			
			sql = "insert into order_book values(?, ?, ?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, bookNo);
			pstmt.setLong(2, requestNo);
			pstmt.setLong(3, bookCount);
			
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
	public List<RequestBookVO> getList() {
		List<RequestBookVO> list = new ArrayList<RequestBookVO>();
		
		try {
			String sql = "select book.no, book.name, order_book.count" + 
					"  from book, member, request, order_book" + 
					"  where member.no = request.member_no" + 
					"  and request.member_no = order_book.order_no" + 
					"  and order_book.book_no = book.no";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery(sql);
			
			while(rs.next()) {
				RequestBookVO vo = new RequestBookVO();
				vo.setBookNo(rs.getLong(1));
				vo.setBookName(rs.getString(2));
				vo.setCount(rs.getLong(3));
				
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

//	// U
//	public boolean update(RequestBookVO vo) {
//		boolean result = false;
//
//		try {
//			String sql = "insert into order_book values(null, ?, ?)";
//
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setLong(1, vo.getCount());
//			pstmt.setLong(2, vo.getBookNo());
//			
//			int count = pstmt.executeUpdate();
//			result = count == 1;
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			resourceTheorem();
//		}
//
//		return result;
//	}
}
