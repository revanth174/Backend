package com.KRJS.Back.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;



@Entity
public class Details {

	

	@Id
	@GeneratedValue(generator = "gen")
	@GenericGenerator(name = "gen", strategy = "foreign", parameters = {
			@Parameter(name = "property", value = "member") })
	@Column(name = "mem_id")
	private String memberid;

	public String getMemberid() {
		return memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public Boolean getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(Boolean maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public int getNoc() {
		return noc;
	}

	public void setNoc(int noc) {
		this.noc = noc;
	}

	public Boolean getVemanaVani() {
		return vemanaVani;
	}

	public void setVemanaVani(Boolean vemanaVani) {
		this.vemanaVani = vemanaVani;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getGmail() {
		return gmail;
	}

	public void setGmail(String gmail) {
		this.gmail = gmail;
	}

	@OneToOne
	@PrimaryKeyJoinColumn
	private Member member = new Member();

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	
	
	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public int getWardNo() {
		return wardNo;
	}

	public void setWardNo(int wardNo) {
		this.wardNo = wardNo;
	}
	

	@Override
	public String toString() {
		return "Details [memberid=" + memberid + ", member=" + member + ", qualification=" + qualification
				+ ", occupation=" + occupation + ", maritalStatus=" + maritalStatus + ", noc=" + noc + ", vemanaVani="
				+ vemanaVani + ", phone=" + phone + ", gmail=" + gmail + ", ward=" + ward + ", wardNo=" + wardNo + "]";
	}

	@Column(name = "qualification")
	private String qualification;

	@Column(name = "occupation")
	private String occupation;

	@Column(name = "maritalstatus")
	private Boolean maritalStatus;

	@Column(name = "noOfChildrens")
	private int noc;

	@Column(name = "vemanaVani")
	private Boolean vemanaVani;

	@Column(name = "phone")
	private long phone;

	@Column(name = "gmail")
	private String gmail;
	
	String ward;
	int wardNo;



}
