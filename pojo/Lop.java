/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author DELL
 */
public class Lop {
    private String maLop;
    private Set<SinhVien> dsSV;
    private Set<MonHoc> dsMH;
    /*--------- Contructor ---------*/
    public Lop() {
    }
    public Lop(String maLop) {
        this.maLop = maLop;
        this.dsSV = new HashSet<SinhVien>();
        this.dsMH = new HashSet<MonHoc>();
    }
    public Lop(String maLop, Set<SinhVien> dsSV, Set<MonHoc> dsMH) {
        this.maLop = maLop;
        this.dsSV = dsSV;
        this.dsMH = dsMH;
    }
    
    /*--------- Getter/Setter ---------*/
    public String getMaLop() {
        return this.maLop;
    }
    public Set<SinhVien> getDsSV() {
        return this.dsSV;
    }
    public Set<MonHoc> getDsMH() {
        return this.dsMH;
    }
    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }
    public void setDsSV(Set<SinhVien> dsSV) {
        this.dsSV = dsSV;
    }
    public void setDsMH(Set<MonHoc> dsMH) {
        this.dsMH = dsMH;
    }
}
