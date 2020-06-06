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
public class MonHoc {
    private String tenMH, maMH, phongHoc;
    private Lop lop;
    
    private Set<SinhVien> dsSV;
    /*--------- Contructor ---------*/
    public MonHoc() {
    }
    public MonHoc(String maMH) {
        this.maMH = maMH;
    }
    public MonHoc(String maMH, String tenMH, String phongHoc, Lop lop, Set<SinhVien> dsSV) {
        this.maMH = maMH;
        this.tenMH = tenMH;
        this.phongHoc = phongHoc;
        this.lop = lop;
        this.dsSV = dsSV;
    }
    
    /*--------- Getter/Setter ---------*/
    public String getMaMH() {
        return this.maMH;
    }
    public String getTenMH() {
        return this.tenMH;
    }
    public String getPhongHoc() {
        return this.phongHoc;
    }
    public Lop getLop() {
        return this.lop;
    }
    public Set<SinhVien> getDsSV() {
    	return this.dsSV;
    }
    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }
    public void setTenMH(String tenMH) {
        this.tenMH = tenMH;
    }
    public void setPhongHoc(String phongHoc) {
        this.phongHoc = phongHoc;
    }
    public void setLop(Lop lop) {
        this.lop = lop;
    }
    public void setDsSV(Set<SinhVien> dsSV) {
    	this.dsSV = dsSV;
    }
}