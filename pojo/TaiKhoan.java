package pojo;

/**
 * pojo
 * @Created by DELL - StudentID: 18120652
 * @Date Jun 7, 2020 - 12:21:33 PM 
 * @Description ...
 */
public class TaiKhoan {
	private String tenTK, matKhau;
	private boolean giaoVu;
	
	public TaiKhoan() {
		
	}
	public TaiKhoan(String tenTK) {
		this.tenTK = tenTK;
	}
	public TaiKhoan(String tenTK, String matKhau, boolean giaoVu) {
		this.tenTK = tenTK;
		this.matKhau = matKhau;
		this.giaoVu = giaoVu;
	}
	
	public String getTenTK() {
		return this.tenTK;
	}
	public String getMatKhau() {
		return this.matKhau;
	}
	public boolean getGiaoVu() {
		return this.giaoVu;
	}
	public void setTenTK(String tenTK) {
		this.tenTK = tenTK;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	public void setGiaoVu(boolean giaoVu) {
		this.giaoVu = giaoVu;
	}
}
