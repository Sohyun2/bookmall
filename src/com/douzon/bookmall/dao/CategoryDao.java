package com.douzon.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzon.bookmall.vo.CategoryVO;

public class CategoryDao {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	public CategoryDao() {
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
	public boolean insert(CategoryVO vo) {
		boolean result = false;

		try {
			String sql = "insert into category values (null, ?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getCategoryName());

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
	public List<CategoryVO> getList() {
		List<CategoryVO> list = new ArrayList<CategoryVO>();
		
		
		try {
			String sql = "select * from category order by no";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery(sql);
			
			while(rs.next()) {
				CategoryVO vo = new CategoryVO();
				vo.setCategoryNo(rs.getLong(1));
				vo.setCategoryName(rs.getString(2));
				
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
	
	//U
	public boolean update(CategoryVO vo, int pk) {
		boolean result = false;
		
		try {
			String sql = "update category set name = ?"
					+ " where no = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getCategoryName());
			pstmt.setInt(2, pk);

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
			String sql = "delete from category where name = ?";

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
