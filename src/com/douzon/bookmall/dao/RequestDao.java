package com.douzon.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.douzon.bookmall.vo.BookVO;
import com.douzon.bookmall.vo.CartVO;
import com.douzon.bookmall.vo.RequestVO;

public class RequestDao {


	Connection conn;
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;

	public RequestDao() {
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
	public boolean insert(RequestVO vo) {
		boolean result = false;

		try {
			// 멤버별 카트에 있는 결제 금액 구하기			
			String sql = "select member.no, sum(book.price * cart.count)" + 
					"  from	book, cart, member" + 
					"  where book.no = cart.book_no" + 
					"  and member.no = cart.member_no" + 
					"  and member.no = " +  vo.getMemeberNo();
			stmt = conn.prepareStatement(sql);

			rs = stmt.executeQuery(sql);
			
			long sum = 0;
			if(rs.next()) {
				sum = rs.getLong(2);
			}
			 
			// insert
			sql = "insert into request values (null, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, vo.getMemeberNo());
			pstmt.setLong(2, sum);
			pstmt.setString(3, vo.getOdAddress());
			
			int count = pstmt.executeUpdate();
			result = count == 1;

			// 주문 도서 리스트에 값 넣기
			// 위의 결과가 제대로 실행되지 않았다면 order_book에 들어가지 않음..
			if(result == true) {// 주문리스트에 성공적으로 입력하였다면 주문 도서 리스트 테이블에 insert
				
				result = false;
				// 주문번호, 도서번호, 수량 구하기
				long bookNo = 0;
				long requestNo = 0;
				long subCount = 0;
				String subSql = "select	cart.book_no, request.no, cart.count" + 
						"  from request, member, cart, book" + 
						"  where member.no = " + vo.getMemeberNo() +
						"  and member.no = cart.member_no" + 
						"  and member.no = request.member_no" + 
						"  and book.no = cart.book_no";				
				stmt = conn.prepareStatement(subSql);

				rs = stmt.executeQuery(subSql);
				while(rs.next()) {
					bookNo = rs.getLong(1);
					requestNo = rs.getLong(2);
					subCount = rs.getLong(3);

					// order_book에 값 입력하기
					subSql = "insert into order_book values(?, ?, ?)";

					pstmt = conn.prepareStatement(subSql);
					pstmt.setLong(1, bookNo);
					pstmt.setLong(2, requestNo);
					pstmt.setLong(3, subCount);
					
					count = pstmt.executeUpdate();
				}								
				result = count == 1;		
				// 모두 다 수행이 성공적으로 끝났으므로 true return
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			resourceTheorem();
		}

		return result;
	}	
	

	// R
	public List<RequestVO> getList() {
		List<RequestVO> list = new ArrayList<RequestVO>();
		
		try {
			String sql = "select member.no, member.name, member.email, request.price, request.od_address" + 
					"  from member, book, cart, request" + 
					"  where member.no = cart.member_no" + 
					"  and book.no = cart.book_no" + 
					"  and member.no = request.member_no" + 
					"  group by member.no";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery(sql);
			
			while(rs.next()) {
				RequestVO vo = new RequestVO();
				vo.setMemeberNo(rs.getLong(1));
				vo.setMemberName(rs.getString(2));
				vo.setMemberEmail(rs.getString(3));
				vo.setRequstPrice(rs.getLong(4));
				vo.setOdAddress(rs.getString(5));
				
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
