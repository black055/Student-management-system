package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pojo.TaiKhoan;
import util.HibernateUtil;

/**
 * dao
 * @Created by DELL - StudentID: 18120652
 * @Date Jun 7, 2020 - 12:28:21 PM 
 * @Description ...
 */
public class TaiKhoanDAO {
	public static TaiKhoan thongTinTaiKhoan(String tenTK) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		TaiKhoan tk = null;
		try {
			tk = (TaiKhoan) session.get(TaiKhoan.class, tenTK);
		} catch (HibernateException ex) {
			System.err.println(ex.getMessage());
		} finally {
			session.close();
		}
		return tk;
	}
	public static boolean suaTaiKhoan(TaiKhoan tk) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (TaiKhoanDAO.thongTinTaiKhoan(tk.getTenTK()) == null) {
			return false;
		}
		Transaction trans = null;
		try {
			trans = session.beginTransaction();
			session.save(tk);
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
