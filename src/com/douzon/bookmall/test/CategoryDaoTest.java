package com.douzon.bookmall.test;

import java.util.List;

import com.douzon.bookmall.dao.CategoryDao;
import com.douzon.bookmall.vo.CategoryVO;

public class CategoryDaoTest {
	public static void main(String[] args) {
		updateTest();
	}
	
	// C
	public static void insertTest() {
		CategoryVO vo = new CategoryVO();
		vo.setCategoryName("컴퓨터/IT");
		
		boolean result = new CategoryDao().insert(vo);
		if(result) {
			// 성공적으로 수행했을 경우..
			System.out.println("successful");
		} else {
			System.out.println("fail!");
		}
	}
	
	// R
	public static void getListTest() {
		List<CategoryVO> list = new CategoryDao().getList();
		
		for(CategoryVO vo : list) {
			System.out.println(vo.getCategoryNo() + " : " + vo.getCategoryName());
			
		}
	}
	
	// U
	public static void updateTest() {
		int pk = 3; // no가 3인 name을 변경
		
		CategoryVO vo = new CategoryVO();
		vo.setCategoryName("컴퓨터");		
		
		boolean result = new CategoryDao().update(vo, pk);
		if(result) {
			// 성공적으로 수행했을 경우..
			System.out.println("successful");
		} else {
			System.out.println("fail!");
		}
	}
	
	//D
	public static void deleteTest() {
		boolean result = new CategoryDao().delete("소설");
		if(result) {
			// 성공적으로 수행했을 경우..
			System.out.println("successful");
		} else {
			System.out.println("fail!");
		}
	}
}
