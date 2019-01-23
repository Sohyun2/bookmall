package com.douzon.bookmall.test;

import java.util.List;

import com.douzon.bookmall.dao.RequestBookDao;
import com.douzon.bookmall.vo.RequestBookVO;

public class RequestBookTest {

	public static void main(String[] args) {
		insertTest();
	}

	// C
	public static void insertTest() {
		// RequestBookVO vo = new RequestBookVO();
		long memberNo = 1; // 구하고싶은 회원 번호

		boolean result = new RequestBookDao().insert(memberNo);
		if (result) {
			// 성공적으로 수행했을 경우..
			System.out.println("successful");
		} else {
			System.out.println("fail!");
		}
	}

	// R
	public static void getListTest() {
		List<RequestBookVO> list = new RequestBookDao().getList();

		for (RequestBookVO vo : list) {
			System.out.println(vo.getBookNo() + " : " + vo.getBookName() + " : " + vo.getCount() + "개");
		}
	}
}
