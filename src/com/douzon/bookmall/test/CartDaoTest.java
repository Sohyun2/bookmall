package com.douzon.bookmall.test;

import java.util.List;

import com.douzon.bookmall.dao.CartDao;
import com.douzon.bookmall.vo.CartVO;

public class CartDaoTest {

	public static void main(String[] args) {
		insertTest();
	}
	
	// C
	public static void insertTest() {
		CartVO vo = new CartVO();
		vo.setMemberNo(1);
		vo.setBookNo(3);
		vo.setCount(1);
				
		boolean result = new CartDao().insert(vo);
		if(result) {
			// 성공적으로 수행했을 경우..
			System.out.println("successful");
		} else {
			System.out.println("fail!");
		}
	}

	// R
	public static void getListTest() {
		List<CartVO> list = new CartDao().getList();
		
		for(CartVO vo : list) {
			System.out.println(vo.getMemberNo() 
					+ " : " + vo.getBookName() 
					+ " : " + vo.getCount()
					+ " : " + vo.getPrice());			
		}
		
		
	}
}
