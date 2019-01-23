package com.douzon.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzon.bookmall.vo.BookVO;
import com.douzon.bookmall.vo.MemberVO;

public class MemberDao {

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	public MemberDao() {
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
	public boolean insert(MemberVO vo) {
		boolean result = false;

		try {
			String sql = "  insert into member"
					+ "  values(null, ?, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getMemberName());
			pstmt.setString(2, vo.getMemberTel());
			pstmt.setString(3, vo.getMemberEmail());
			pstmt.setString(4, vo.getMemberPassword());
			
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
	public List<MemberVO> getList() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		
		try {
			String sql = "select * from member";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery(sql);
			
			while(rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setMemberNo(rs.getLong(1));
				vo.setMemberName(rs.getString(2));
				vo.setMemberTel(rs.getString(3));
				vo.setMemberEmail(rs.getString(4));
				vo.setMemberPassword(rs.getString(5));
				
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
	
	// U
	public boolean update(int pk, MemberVO vo) {
		boolean result = false;

		try {
			String sql = "update member set name = ?,"
					+ " tel = ?,"
					+ " email = ?,"
					+ " password =?"
					+ " where no = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getMemberName());
			pstmt.setString(2, vo.getMemberTel());
			pstmt.setString(3, vo.getMemberEmail());
			pstmt.setString(4, vo.getMemberPassword());
			pstmt.setInt(5, pk);
			
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
	// D
	public boolean delete(String name) {
		boolean result = false;
		
		try {
			String sql = "delete from member where name = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);

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
	
}
