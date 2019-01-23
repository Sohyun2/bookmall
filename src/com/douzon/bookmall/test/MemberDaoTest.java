package com.douzon.bookmall.test;

import java.util.List;

import com.douzon.bookmall.dao.BookDao;
import com.douzon.bookmall.dao.MemberDao;
import com.douzon.bookmall.vo.MemberVO;

public class MemberDaoTest {	
	public static void main(String[] args) {
		insertTest();
	}
	// C
	public static void insertTest() {
		MemberVO vo = new MemberVO();
		vo.setMemberName("홍길동");
		vo.setMemberTel("010-000-0000");
		vo.setMemberEmail("abcd@naver.com");
		vo.setMemberPassword("1234");
		
		boolean result = new MemberDao().insert(vo);
		if(result) {
			// 성공적으로 수행했을 경우..
			System.out.println("successful");
		} else {
			System.out.println("fail!");
		}
	}

	// R
	public static void getListTest() {
		List<MemberVO> list = new MemberDao().getList();
		
		for(MemberVO vo : list) {
			System.out.println(vo.getMemberNo() 
					+ " : " + vo.getMemberName() 
					+ " : " + vo.getMemberTel() 
					+ " : " + vo.getMemberEmail() 
					+ " : " + vo.getMemberPassword());
		}
	}
	
	//U
	public static void updateTest() {
		int pk = 5;
		MemberVO vo = new MemberVO();
		vo.setMemberName("고길동");
		vo.setMemberTel("010-567-9874");
		vo.setMemberEmail("qwensjfynbc@naver.com");
		vo.setMemberPassword("456433");
		
		boolean result = new MemberDao().update(pk, vo);
		if(result) {
			// 성공적으로 수행했을 경우..
			System.out.println("successful");
		} else {
			System.out.println("fail!");
		}
	}
	
	//D
	public static void deleteTest() {
		String memberName = "홍길동";

		boolean result = new MemberDao().delete(memberName);
		if(result) {
			// 성공적으로 수행했을 경우..
			System.out.println("successful");
		} else {
			System.out.println("fail!");
		}
	}
}
