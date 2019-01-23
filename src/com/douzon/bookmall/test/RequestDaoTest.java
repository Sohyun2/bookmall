package com.douzon.bookmall.test;

import java.util.List;

import com.douzon.bookmall.dao.RequestDao;
import com.douzon.bookmall.vo.RequestVO;

public class RequestDaoTest {

	public static void main(String[] args) {
		insertTest();
	}
	// C
	public static void insertTest() {
		RequestVO vo = new RequestVO();
		vo.setMemeberNo(2);
		vo.setOdAddress("부산시 해운대구");
		
		boolean result = new RequestDao().insert(vo);
		if(result) {
			// 성공적으로 수행했을 경우..
			System.out.println("successful");
		} else {
			System.out.println("fail!");
		}
	}
	
	// R
	public static void getListTest() {
		List<RequestVO> list = new RequestDao().getList();
		
		for(RequestVO vo : list) {
			System.out.println( vo.getMemeberNo()
					+ " : " + vo.getMemberName()
					+ " : " + vo.getMemberEmail()
					+ " : " + vo.getRequstPrice()
					+ " : " + vo.getOdAddress());
		}
	}
}
