package dao;


import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import pojo.BangDiem;
import util.HibernateUtil;

/**
 * @Created by DELL - StudentID: 18120652
 * @Date Jun 7, 2020 - 12:01:13 PM 
 * @Description ...
 */
public class BangDiemDAO {
	public static BangDiem thongTinBangDiem(String maSV, String maMH) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		BangDiem bd = null;
		try {
			bd = (BangDiem) session.get(BangDiem.class, new BangDiem(maSV, maMH));
		} catch (HibernateException ex) {
			System.err.println(ex.getMessage());
		} finally {
			session.close();
		}
		return bd;
	}

	public static List<BangDiem> ketQuaHocTap(String maSV) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<BangDiem> ds = null;
		try {
			String hql = "select bd from BangDiem bd where bd.maSV = :maSV";
			Query qr = session.createQuery(hql);
			qr.setParameter("maSV", maSV);
			ds = qr.list();
		} catch (HibernateException ex) {
			System.err.println(ex.getMessage());
		} finally {
			session.close();
		}
		return ds;
	}
	
	public static List<BangDiem> ketQuaMonHoc(String maMH) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<BangDiem> ds = null;
		try {
			String hql = "select bd from BangDiem bd where bd.maMH = :maMH";
			Query qr = session.createQuery(hql);
			qr.setParameter("maMH", maMH);
			ds = qr.list();
		} catch (HibernateException ex) {
			System.err.println(ex.getMessage());
		} finally  {
			session.close();
		}
		return ds;
	}
	
	public static boolean themBangDiem(BangDiem bd) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (BangDiemDAO.thongTinBangDiem(bd.getMaSV(), bd.getMaMH()) != null) {
			return false;
		}
		Transaction trans = null;
		try {
			trans = session.beginTransaction();
			session.save(bd);
			trans.commit();
		} catch (HibernateException ex) {
			trans.rollback();
			System.err.println(ex.getMessage());
		} finally {
			session.close();
		}
		return true;
	}
	
	public static boolean suaBangDiem(BangDiem bd) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (BangDiemDAO.thongTinBangDiem(bd.getMaSV(), bd.getMaMH()) == null) {
			return false;
		}
		Transaction trans = null;
		try {
			trans = session.beginTransaction();
			session.update(bd);
			trans.commit();
		} catch (HibernateException ex) {
			trans.rollback();
			System.err.println(ex.getMessage());
		} finally {
			session.close();
		}
		return true;
	}
	
	public static boolean xoaBangDiem(BangDiem bd) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (BangDiemDAO.thongTinBangDiem(bd.getMaSV(), bd.getMaMH()) == null) {
			return false;
		}
		Transaction trans = null;
		try {
			trans = session.beginTransaction();
			session.delete(bd);
			trans.commit();
		} catch (HibernateException ex) {
			trans.rollback();
			System.err.println(ex.getMessage());
		} finally {
			session.close();
		}
		return true;
	}
}
