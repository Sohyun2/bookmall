package com.douzon.bookmall.test;

import java.util.List;

import com.douzon.bookmall.dao.BookDao;
import com.douzon.bookmall.vo.BookVO;

public class BookDaoTest {
	public static void main(String[] args) {
		insertTest();
	}
	
	// C
	public static void insertTest() {
		BookVO vo = new BookVO();
		vo.setBookName("미녀와야수");
		vo.setPrice(7000);
		vo.setCategoryNo(1);
				
		boolean result = new BookDao().insert(vo);
		if(result) {
			// 성공적으로 수행했을 경우..
			System.out.println("successful");
		} else {
			System.out.println("fail!");
		}
	}
	
	// R
	public static void getListTest() {
		List<BookVO> list = new BookDao().getList();
		
		for(BookVO vo : list) {
			System.out.println(vo.getBookNo() + " : " + vo.getBookName() 
					+ " : " + vo.getPrice() + " : " + vo.getCategoryNo());
			
		}
	}
}
