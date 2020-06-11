package dao;


import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pojo.MonHoc;
import util.HibernateUtil;

/**
 * @Created by DELL - StudentID: 18120652
 * @Date Jun 7, 2020 - 11:09:42 AM 
 * @Description ...
 */
public class MonHocDAO {
	
	public static MonHoc thongTinMonHoc(String maMH) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		MonHoc mh = null;
		try {
			mh = (MonHoc) session.get(MonHoc.class, maMH);
			if (mh != null) {
				Hibernate.initialize(mh.getDsSV());
			}
		} catch (HibernateException ex ) {
			System.err.println(ex.getMessage());
		} finally {
			session.close();
		}
		return mh;
	}
	
	public static boolean themMonHoc(MonHoc mh) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (MonHocDAO.thongTinMonHoc(mh.getMaMH()) != null) {
			return false;
		}
		Transaction trans = null;
		try {
			trans = session.beginTransaction();
			session.save(mh);
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
