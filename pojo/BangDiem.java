package pojo;

import java.io.Serializable;

/**
 * pojo
 * @Created by DELL - StudentID: 18120652
 * @Date Jun 7, 2020 - 11:34:43 AM 
 * @Description ...
 */
public class BangDiem implements Serializable {
	private String maSV, maMH;
	private float diemGK, diemCK, diemKhac, diemTong;
	
	public BangDiem() {
		
	}
	public BangDiem(String maSV, String maMH) {
		this.maSV = maSV;
		this.maMH = maMH;
	}
	public BangDiem(String maSV, String maMH, float diemGK, float diemCK, float diemKhac, float diemTong) {
		this.maSV = maSV;
		this.maMH = maMH;
		this.diemGK = diemGK;
		this.diemCK = diemCK;
		this.diemKhac = diemKhac;
		this.diemTong = diemTong;
	}
	public String getMaSV() {
		return this.maSV;
	}
	public String getMaMH() {
		return this.maMH;
	}
	public float getDiemGK() {
		return this.diemGK;
	}
	public float getDiemCK() {
		return this.diemCK;
	}
	public float getDiemKhac() {
		return this.diemKhac;
	}
	public float getDiemTong() {
		return this.diemTong;
	}
	public void setMaSV(String maSV) {
		this.maSV = maSV;
	}
	public void setMaMH(String maMH) {
		this.maMH = maMH;
	}
	public void setDiemGK(float diemGK) {
		this.diemGK = diemGK;
	}
	public void setDiemCK(float diemCK) {
		this.diemCK = diemCK;
	}
	public void setDiemKhac(float diemKhac) {
		this.diemKhac = diemKhac;
	}
	public void setDiemTong(float diemTong) {
		this.diemTong = diemTong;
	}
}