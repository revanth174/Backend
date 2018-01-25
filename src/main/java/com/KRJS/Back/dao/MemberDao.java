package com.KRJS.Back.dao;

import java.util.List;

import com.KRJS.Back.model.Member;

public interface MemberDao {
	boolean insert(Member m);
	Member getById(final String memid);
	boolean delete(Member m);
	boolean update(Member m);
	List<Member> SelectAll();
	List<Member> getByApplicationNumber(String appno);
	List<Member> getByMobileNumber(Long phone);
	List<Member> getByDistrict(String dis);
	List<Member> getByState(String state);
	List<Member> getByTaluk(String taluk);
	List<Member> getByAlphabet(char a);

}
