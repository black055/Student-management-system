/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.util.Set;

/**
 *
 * @author DELL
 */
public class SinhVien {
    private String maSV, tenSV, gioiTinh, cmnd;
    private Lop lop;
    private Set<MonHoc> dsMH;
    /*--------- Contructor ---------*/
    public SinhVien() {
    }
    public SinhVien(String maSV) {
        this.maSV = maSV;
    }
    public SinhVien(String maSV, String tenSV, String gioiTinh, String cmnd, Lop lop, Set<MonHoc> dsMH) {
        this.maSV = maSV;
        this.tenSV = tenSV;
        this.gioiTinh = gioiTinh;
        this.cmnd = cmnd;
        this.lop = lop;
        this.dsMH = dsMH;
    }
    
    /*--------- Getter/Setter ---------*/
    public String getMaSV() {
        return this.maSV;
    }
    public String getTenSV() {
        return this.tenSV;
    }
    public String getGioiTinh() {
        return this.gioiTinh;
    }
    public String getCmnd() {
        return this.cmnd;
    }
    public Lop getLop() {
        return this.lop;
    }
    public Set<MonHoc> getDsMH(){
    	return this.dsMH;
    }
    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }
    public void setTenSV(String tenSV) {
        this.tenSV = tenSV;
    }
    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }
    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }
    public void setLop(Lop lop) {
        this.lop = lop;
    }
    public void setDsMH(Set<MonHoc> dsMH) {
    	this.dsMH = dsMH;
    }
}
