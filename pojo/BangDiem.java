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
	private Float diemGK, diemCK, diemKhac, diemTong;
	
	public BangDiem() {
	}
	public BangDiem(String maSV, String maMH) {
		this.maSV = maSV;
		this.maMH = maMH;
		this.diemGK = (float) 0;
		this.diemCK = (float) 0;
		this.diemKhac = (float) 0;
		this.diemTong = (float) 0;
	}
	public BangDiem(String maSV, String maMH, Float diemGK, Float diemCK, Float diemKhac, Float diemTong) {
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
	public Float getDiemGK() {
		return this.diemGK;
	}
	public Float getDiemCK() {
		return this.diemCK;
	}
	public Float getDiemKhac() {
		return this.diemKhac;
	}
	public Float getDiemTong() {
		return this.diemTong;
	}
	public void setMaSV(String maSV) {
		this.maSV = maSV;
	}
	public void setMaMH(String maMH) {
		this.maMH = maMH;
	}
	public void setDiemGK(Float diemGK) {
		this.diemGK = diemGK;
	}
	public void setDiemCK(Float diemCK) {
		this.diemCK = diemCK;
	}
	public void setDiemKhac(Float diemKhac) {
		this.diemKhac = diemKhac;
	}
	public void setDiemTong(Float diemTong) {
		this.diemTong = diemTong;
	}
}