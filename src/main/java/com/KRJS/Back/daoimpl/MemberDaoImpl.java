package com.KRJS.Back.daoimpl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.KRJS.Back.dao.MemberDao;
import com.KRJS.Back.model.Member;

@Repository("MemberDao")
@Transactional
public class MemberDaoImpl implements MemberDao {

	@Autowired
	SessionFactory sessionFactory;

	MemberDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public boolean insert(Member m) {
		System.out.println("heelo welcome to member insert");
		try {
			sessionFactory.getCurrentSession().persist(m);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public Member getById(String memid) {

		return sessionFactory.getCurrentSession().get(Member.class, memid);
	}

	public boolean delete(Member m) {
		System.out.println("delete method");
		try {
			sessionFactory.getCurrentSession().delete(m);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean update(Member m) {
		System.out.println("delete method");
		try {
			sessionFactory.getCurrentSession().update(m);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Member> SelectAll() {

		@SuppressWarnings("unchecked")
		Query<Member> query = sessionFactory.getCurrentSession().createQuery("from Member");
		return query.getResultList();

	}

	@SuppressWarnings("unchecked")
	public List<Member> getByApplicationNumber(String appno) {

		String appQuery = "from Member where appNo = :appno";
		Query<Member> query = sessionFactory.getCurrentSession().createQuery(appQuery);
		query.setParameter("appno", appno);
		return query.getResultList();
	}

	public List<Member> getByMobileNumber(Long phone) {
		// sessionFactory.getCurrentSession().createQuery("from Details where phone:
		// phone ").setParameter("phone", phone);
		String hql = "from Member mem LEFT JOIN FETCH mem.details where mem.details.phone = :phone";
		Query<Member> q = sessionFactory.getCurrentSession().createQuery(hql, Member.class);
		q.setParameter("phone", phone);

		return q.list();
	}

	public List<Member> getByDistrict(String dis) {

		String hql = "from Member mem left join fetch mem.address where mem.address.district = :dis";
		Query<Member> q = sessionFactory.getCurrentSession().createQuery(hql,Member.class);
		q.setParameter("dis", dis);
		return q.list();
	}

	public List<Member> getByState(String state) {
		String hql = "from Member mem left join fetch mem.address where mem.address.state = :state";
		Query<Member> q = sessionFactory.getCurrentSession().createQuery(hql,Member.class);
		q.setParameter("state", state);
		return q.list();
	}

	public List<Member> getByTaluk(String taluk) {
		String hql = "from Member mem left join fetch mem.address where mem.address.taluk = :taluk";
		Query<Member> q = sessionFactory.getCurrentSession().createQuery(hql,Member.class);
		q.setParameter("taluk", taluk);
		return q.list();
		
	}

	public List<Member> getByAlphabet(char a) {
		/*CriteriaBuilder c = sessionFactory.getCriteriaBuilder();
		CriteriaQuery<Member> cq = c.createQuery(Member.class);
		Root<Member> root = cq.from(Member.class);*/
	
		String hql = "from Member m where m.fname like :s";
		Query<Member> q = sessionFactory.getCurrentSession().createQuery(hql,Member.class);
		q.setParameter("s", Character.toString(a)+"%");
		return q.list();
	}

}
