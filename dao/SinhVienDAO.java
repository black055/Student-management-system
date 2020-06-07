package dao;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pojo.SinhVien;
import util.HibernateUtil;

/**
 * @Created by DELL - StudentID: 18120652
 * @Date Jun 7, 2020 - 10:34:03 AM 
 * @Description ...
 */
public class SinhVienDAO {
	public static SinhVien thongTinSinhVien(String maSV) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		SinhVien sv = null;
		try {
			sv = (SinhVien) session.get(SinhVien.class, maSV);
			Hibernate.initialize(sv.getDsMH());
		} catch (HibernateException ex) {
			System.err.println(ex.getMessage());
		} finally {
			session.close();
		}
		return sv;
	}
	
	public static boolean themSinhVien(SinhVien sv) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (SinhVienDAO.thongTinSinhVien(sv.getMaSV()) != null) {
			return false;
		}
		Transaction trans = null;
		try {
			trans = session.beginTransaction();
			session.save(sv);
			trans.commit();
		} catch(HibernateException ex) {
			trans.rollback();
			System.err.println(ex.getMessage());
		} finally {
			session.close();
		}
		return true;
	}
	
	public static boolean suaSinhVien(SinhVien sv) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (SinhVienDAO.thongTinSinhVien(sv.getMaSV()) == null) {
			return false;
		}
		Transaction trans = null;
		try {
			trans = session.beginTransaction();
			session.update(sv);
			trans.commit();
		} catch(HibernateException ex) {
			trans.rollback();
			System.err.println(ex.getMessage());
		} finally {
			session.close();
		}
		return true;
	}
}
