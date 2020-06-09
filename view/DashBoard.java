package view;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * view
 * @Created by DELL - StudentID: 18120652
 * @Date Jun 8, 2020 - 7:42:25 PM 
 * @Description ...
 */
public class DashBoard {
	private String tenThe;
	private JPanel nenThe;
	private JLabel noiDung;
	
	DashBoard(String tenThe, JPanel nenThe, JLabel noiDung){
		this.noiDung = noiDung;
		this.nenThe = nenThe;
		this.tenThe = tenThe;
	}
	public void setTenThe(String tenThe) {
		this.tenThe = tenThe;
	}
	public void setNenThe(JPanel nenThe) {
		this.nenThe = nenThe;
	}
	public void setNoiDung(JLabel noiDung) {
		this.noiDung = noiDung;
	}
	public String getTenThe() {
		return this.tenThe;
	}
	public JLabel getNoiDung() {
		return this.noiDung;
	}
	public JPanel getNenThe() {
		return this.nenThe;
	}
}
