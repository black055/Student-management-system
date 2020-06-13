package dao;


import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import pojo.Lop;
import util.HibernateUtil;

/**
 * @Created by DELL - StudentID: 18120652
 * @Date Jun 7, 2020 - 10:54:46 AM 
 * @Description ...
 */
public class LopDAO {
	public static List<Lop> danhSachLop() {
		List<Lop> ds = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			String hql = "select lop from Lop lop";
			Query qr = session.createQuery(hql);
			ds = qr.list();
		} catch (HibernateException ex) {
			System.err.println(ex);
		} finally {
			session.close();
		}
		return ds;
	}
	
	public static Lop thongTinLop(String maLop) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Lop lop = null;
		try  {
			lop = (Lop) session.get(Lop.class, maLop);
			if (lop != null) {
				Hibernate.initialize(lop.getDsMH());
				Hibernate.initialize(lop.getDsSV());
			}
		} catch (HibernateException ex) {
			System.err.println(ex.getMessage());
		} finally {
			session.close();
		}
		return lop;
	}
	
	public static boolean themLop(Lop lop) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (LopDAO.thongTinLop(lop.getMaLop()) != null) {
			return false;
		}
		Transaction trans = null;
		try {
			trans = session.beginTransaction();
			session.save(lop);
			trans.commit();
		} catch (HibernateException ex) {
			trans.rollback();
			System.err.println(ex.getMessage());
		} finally {
			session.close();
		}
		return true;
	}
	
	public static void themHoacCapNhat(Lop lop) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = null;
		try {
			trans = session.beginTransaction();
			session.saveOrUpdate(lop);
			trans.commit();
		} catch (HibernateException ex) {
			System.err.println(ex.getMessage());
		} finally {
			session.close();
		}
	}
}
