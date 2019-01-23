package com.douzon.bookmall.main;

import java.util.List;

import com.douzon.bookmall.dao.BookDao;
import com.douzon.bookmall.dao.CartDao;
import com.douzon.bookmall.dao.CategoryDao;
import com.douzon.bookmall.dao.MemberDao;
import com.douzon.bookmall.dao.RequestBookDao;
import com.douzon.bookmall.dao.RequestDao;
import com.douzon.bookmall.vo.BookVO;
import com.douzon.bookmall.vo.CartVO;
import com.douzon.bookmall.vo.CategoryVO;
import com.douzon.bookmall.vo.MemberVO;
import com.douzon.bookmall.vo.RequestBookVO;
import com.douzon.bookmall.vo.RequestVO;

public class BookMall {

	public static void main(String[] args) {
//	    // 1. 회원 리스트 – 2명
		MemberVO memberVo = new MemberVO();
//		// 1
//		memberVo.setMemberName("이소현");
//		memberVo.setMemberTel("010-1234-5678");
//		memberVo.setMemberEmail("rooooo@daum.net");
//		memberVo.setMemberPassword("******");
//		new MemberDao().insert(memberVo);
		// 2
		memberVo.setMemberName("임채형");
		memberVo.setMemberTel("010-5299-0701");
		memberVo.setMemberEmail("cogud0908@naver.com");
		memberVo.setMemberPassword("1234");
		new MemberDao().insert(memberVo);
		// 3
		memberVo.setMemberName("개발자");
		memberVo.setMemberTel("010-1004-1004");
		memberVo.setMemberEmail("helpme@naver.com");
		memberVo.setMemberPassword("4567");
		new MemberDao().insert(memberVo);
/////////////////////////////////////////////		
		// 2. 카테고리 리스트 – 3개
		CategoryVO cartegoryVo = new CategoryVO();
		// 1
		cartegoryVo.setCategoryName("소설");
		new CategoryDao().insert(cartegoryVo);
		// 2
		cartegoryVo.setCategoryName("수필");
		new CategoryDao().insert(cartegoryVo);
		// 3
		cartegoryVo.setCategoryName("컴퓨터/IT");
		new CategoryDao().insert(cartegoryVo);
		//
		cartegoryVo.setCategoryName("예술");
		new CategoryDao().insert(cartegoryVo);
///////////////////////////////////////////		
	    // 3. 상품리스트 – 3개
		BookVO bookVO = new BookVO();
		// 1
		bookVO.setBookName("이것이 자바다");
		bookVO.setPrice(20000);
		bookVO.setCategoryNo(3);
		new BookDao().insert(bookVO);
		// 2
		bookVO.setBookName("서양 미술사");
		bookVO.setPrice(13000);
		bookVO.setCategoryNo(4);
		new BookDao().insert(bookVO);
		// 3
		bookVO.setBookName("안드로이드 완전정복");
		bookVO.setPrice(40000);
		bookVO.setCategoryNo(3);
		new BookDao().insert(bookVO);
		// 4
		bookVO.setBookName("아큐정전");
		bookVO.setPrice(10000);
		bookVO.setCategoryNo(1);
		new BookDao().insert(bookVO);
///////////////////////////////////////		
//	    // 4. 카트 리스트 – 2개
//		// 출력 : book.no, book.name, count, book.price
//		// 1
		CartVO cartVo = new CartVO();
//		cartVo.setMemberNo(1);
//		cartVo.setBookNo(1);
//		cartVo.setCount(1);
//		new CartDao().insert(cartVo);
//		// 2
//		cartVo.setMemberNo(1);
//		cartVo.setBookNo(2);
//		cartVo.setCount(2);
//		new CartDao().insert(cartVo);
		// 3
		cartVo.setMemberNo(1);
		cartVo.setBookNo(2);
		cartVo.setCount(1);
		new CartDao().insert(cartVo);
		// 3
		cartVo.setMemberNo(2);
		cartVo.setBookNo(1);
		cartVo.setCount(2);
		new CartDao().insert(cartVo);
		// 3
		cartVo.setMemberNo(1);
		cartVo.setBookNo(3);
		cartVo.setCount(1);
		new CartDao().insert(cartVo);
		// 3
		cartVo.setMemberNo(2);
		cartVo.setBookNo(4);
		cartVo.setCount(2);
		new CartDao().insert(cartVo);
///////////////////////////////////////////		
//	    // 5. 주문 리스트 – 1개
//		// 1
		RequestVO requestVo = new RequestVO();
//		requestVo.setMemeberNo(1);
//		requestVo.setOdAddress("부산");
//		new RequestDao().insert(requestVo);
		// 2
		requestVo.setMemeberNo(1);
		requestVo.setOdAddress("부산시 해운대구 우동 부산산업진흥위원회 7층 격물기지 강의장");
		new RequestDao().insert(requestVo);
		// 3
		requestVo.setMemeberNo(2);
		requestVo.setOdAddress("부산시 금정구 남산동 부산외국어대학교 I412");
		new RequestDao().insert(requestVo);
///////////////////////////////////////////
	    // 6. 주문 도서 리스트 – 2개
		// 주문과 동시에 리스트 입력된다.

		// 회원정보 출력
		memberDisp();
		System.out.println("=====================================================");
		// 카테고리 출력
		categoryDisp();
		System.out.println("=====================================================");
		// 상품리스트 출력
		bookDisp();
		System.out.println("=====================================================");
		// 카트 리스트 출력
		cartDisp();
		System.out.println("=====================================================");
		// 주문 리스트 출력
		requestListDisp();
		System.out.println("=====================================================");
		// 주문 도서 리스트
		bookListDisp();
		System.out.println("=====================================================");
	}

	public static void memberDisp() {
		System.out.println("회원 정보 출력\n");
		
		List<MemberVO> list = new MemberDao().getList();
		
		for (MemberVO vo : list) {
			System.out.print(vo.getMemberNo() + ", ");
			System.out.print(vo.getMemberName() + ", ");
			System.out.print(vo.getMemberTel() + ", ");
			System.out.print(vo.getMemberEmail() + ", ");
			System.out.println(vo.getMemberPassword());
		}

	}

	public static void categoryDisp() {
		System.out.println("카테고리 리스트\n");
		
		List<CategoryVO> list = new CategoryDao().getList();

		for (CategoryVO vo : list) {
			System.out.print(vo.getCategoryNo() + " ");
			System.out.println(vo.getCategoryName());
		}
	}

	public static void bookDisp() {
		System.out.println("상품 리스트\n");
		
		List<BookVO> list = new BookDao().getList();

		for (BookVO vo : list) {
			System.out.print(vo.getBookNo() + ", ");
			System.out.print(vo.getBookName() + ", ");
			System.out.print(vo.getCategoryName() + ", ");
			System.out.println(vo.getPrice());
		}
	}

	public static void cartDisp() {
		System.out.println("카트 리스트\n");
		
		List<CartVO> list = new CartDao().getList();
		
		for(CartVO vo : list) {
			System.out.print(vo.getCartNo() + ", ");
			System.out.print(vo.getBookName() + ", ");
			System.out.print(vo.getCount() + ", ");
			System.out.println(vo.getPrice());
		}
	}
	
	public static void requestListDisp() {
		System.out.println("주문 리스트\n");
		
		List<RequestVO> list = new RequestDao().getList();
		
		for(RequestVO vo : list) {
			System.out.print(vo.getMemeberNo() + ", ");
			System.out.print(vo.getMemberName() + "/");
			System.out.print(vo.getMemberEmail() + ", ");
			System.out.print(vo.getRequstPrice() + ", ");
			System.out.println(vo.getOdAddress());
		}
	}
	
	public static void bookListDisp() {
		System.out.println("주문 도서 리스트\n");
		
		List<RequestBookVO> list = new RequestBookDao().getList();
		
		for(RequestBookVO vo : list) {
			System.out.print(vo.getBookNo() + ", ");
			System.out.print(vo.getBookName() + ", ");
			System.out.println(vo.getCount());
		}
	}
}
