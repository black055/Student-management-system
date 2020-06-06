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
public class Lop {
    private String tenLop;
    private int maLop;
    private Set<SinhVien> dsSV;
    private Set<MonHoc> dsMH;
    /*--------- Contructor ---------*/
    public Lop() {
    }
    public Lop(int maLop) {
        this.maLop = maLop;
    }
    public Lop(int maLop, String tenLop,Set<SinhVien> dsSV, Set<MonHoc> dsMH) {
        this.maLop = maLop;
        this.tenLop = tenLop;
        this.dsSV = dsSV;
        this.dsMH = dsMH;
    }
    
    /*--------- Getter/Setter ---------*/
    public int getMaLop() {
        return this.maLop;
    }
    public String getTenLop() {
        return this.tenLop;
    }
    public Set<SinhVien> getDsSV() {
        return this.dsSV;
    }
    public Set<MonHoc> getDsMH() {
        return this.dsMH;
    }
    public void setMaLop(int maLop) {
        this.maLop = maLop;
    }
    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }
    public void setDsSV(Set<SinhVien> dsSV) {
        this.dsSV = dsSV;
    }
    public void setDsMH(Set<MonHoc> dsMH) {
        this.dsMH = dsMH;
    }
}
