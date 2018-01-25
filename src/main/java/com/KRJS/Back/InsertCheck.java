package com.KRJS.Back;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.KRJS.Back.dao.MemberDao;
import com.KRJS.Back.daoimpl.MemberDaoImpl;
import com.KRJS.Back.model.Address;
import com.KRJS.Back.model.Details;
import com.KRJS.Back.model.Member;
import com.KRJS.Back.model.Payment;

public class InsertCheck {

	public static void main(String args[]) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.KRJS.Back");
		context.refresh();

		Random ran = new Random();
		String memid = "M" + Integer.toString(ran.nextInt(100000));
		String app = "A" + memid;
		Member m = new Member();
		m.setAppNo(app);
		m.setMemberId(memid);
		m.setName("veena");
		String a[] = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
		m.setFname(a[ran.nextInt(26)]);
		m.setTitle("mrs");
		int yyyy = 1996;
		int mm = 10;
		int date = 30;
		// year-month-date
		m.setDob(LocalDate.of(yyyy, mm, date));

		String city[] = { "Bangalore", "Andhra Pradesh", "chennai", "delhi", "mumbai" };
		int pincode[] = { 560068, 500068, 500001, 560075, 520054, 560012 };

		Address current = new Address();
		current.setAddress("25/10 1st main");
		current.setCity("kormangla");
		current.setState(city[new Random().nextInt(5)]);
		current.setTaluk(city[new Random().nextInt(5)]);
		current.setDistrict(city[new Random().nextInt(5)]);
		current.setPincode(pincode[new Random().nextInt(5)]);

		Details details = new Details();
		details.setOccupation("farmer");
		details.setMaritalStatus(true);
		details.setNoc(0);
		details.setVemanaVani(true);
		details.setMember(m);

		details.setQualification("degree");

		Payment payment = new Payment();

		payment.setFeePaid(BigDecimal.valueOf(1000.0));
		String ref = "ref" + Integer.toString(new Random().nextInt(10000));
		payment.setRefNo(ref);
		payment.setMop("online");
		payment.setApplicationDate(new Date());
		payment.setMember(m);

		m.setAddress(current);
		m.setDetails(details);
		m.setPayment(payment);
		// System.out.println("hello");
		
		MemberDao dao = (MemberDao) context.getBean("MemberDao");
		if(dao.insert(m))
			System.out.println("success");
		else
			System.out.println("not success");
		
		
		/*List<Member> list = dao.getByState("Andhra Pradesh");
		
		for(Member me : list)
			System.out.println(me.getMemberId());*/
		
		//Member mem = (Member)dao.getById("M8426");
		//System.out.println(mem);
		context.close();

	}

}
