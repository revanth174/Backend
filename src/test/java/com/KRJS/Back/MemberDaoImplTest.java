package com.KRJS.Back;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.KRJS.Back.dao.MemberDao;
import com.KRJS.Back.model.Member;

import junit.framework.TestCase;

@RunWith(JUnit4.class)
public class MemberDaoImplTest extends TestCase {

	static AnnotationConfigApplicationContext context;
	public static MemberDao dao;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.KRJS.Back");
		context.refresh();
		dao = (MemberDao) context.getBean("memberDAO");
		
	}

	@Test
	public void insertMemberTest() {

	}

	@Test
	public void getMemberTest() {
		Member m = (Member) dao.getById("M90502");
		System.out.println(m);
		assertEquals("M1335", m.getMemberId());
		// System.out.println(m);
	}

	@Test
	public void deleteMemberTest() {
		if(dao == null)
			System.out.println("null");
		Member m =dao.getById("M99482");
		assertTrue(dao.delete(m));
	}

	@Test
	public void updateMemberTest() {
		Member m = dao.getById("M70446");
		m.setName("revanth");
		
		assertTrue(dao.update(m));

	}
	
	
	@Test
	public void selectAllTest() {
		List<Member> list = dao.SelectAll();
		for(Member m : list) {
			System.out.print(m.getMemberId());
			System.out.println(m.getPayment().getRefNo());
		}
		
	}

	
	
	@Test
	public void getByApplicationNumberTest() {
		List<Member> list = dao.getByApplicationNumber("AM13735");
		for(Member m : list) {
			System.out.print(m.getMemberId());
			System.out.print(m.getDetails().getQualification());
			System.out.println(m.getPayment().getRefNo());
		}
	}
	
	
	@Test
	public void getByPhoneNumber() {
		List<Member> list = dao.getByMobileNumber(123456l);
		for(Member m :list)
			System.out.println(m.getMemberId());
	}
	
	@Test(expected = Exception.class)
	public void getByPhoneNumber_ExceptionTest() {
		List<Member> list = dao.getByMobileNumber(2525l);
		
	}
	
	@Test
	public void getByDistrict() {
		List<Member> list = dao.getByDistrict("delhi");
		for(Member m : list)
			System.out.println(m.getMemberId());
		
	}
	
	@Test
	public void getByState() {
		List<Member> list = dao.getByState("Andhra Pradesh");
		if(!list.isEmpty())
		for(Member m : list)
			System.out.println(m.getMemberId());
		else
			System.out.println("empty");
		
	}

	@Test(expected = NullPointerException.class)
	public void getByState_Exception() {
		List<Member> list = dao.getByState("Andha Pradesh");
		if(!list.isEmpty())
		for(Member m : list)
			System.out.println(m.getMemberId());
		else
			System.out.println("empty");
		
	}
	
	
	@Test
	public void getByTaluk() {
		List<Member> list = dao.getByTaluk("Andhra Pradesh");
		if(!list.isEmpty())
		for(Member m : list)
			System.out.println(m.getMemberId());
		else
			System.out.println("empty");
		
	}
	
	@Test
	public void getByAlphabeticalOrder() {
		
		List<Member> list = dao.getByAlphabet('k');
		if(!list.isEmpty())
		for(Member m : list)
			System.out.println(m.getMemberId());
		else
			System.out.println("empty");
		
	}
	
	
	@AfterClass
	public static void after() {
		context.close();
	}

}
