package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * view
 * @Created by DELL - StudentID: 18120652
 * @Date Jun 8, 2020 - 7:36:11 PM 
 * @Description ...
 */
public class FrameController {
	private JPanel noiDungChinh;
	private String tinhNang = "TrangChu";
	
	private List<DashBoard> menuItem =  null;
	
	public FrameController(JPanel panel) {
		this.noiDungChinh = panel;
	}
	
	public JPanel getNoiDungChinh() {
		return this.noiDungChinh;
	}
	
	public void setDashboardEvent() {
		for (DashBoard db : menuItem) {
			db.getNenThe().addMouseListener(new DashboardEvent(db.getTenThe(), db.getNenThe(),  db.getNoiDung()));
		}
	}
	
	class DashboardEvent implements MouseListener{
		private JPanel temp;
		
		private String tenThe;
		private JPanel nenThe;
		private JLabel noiDung;
		
		DashboardEvent(String tenThe, JPanel nenThe, JLabel noiDung) {
			this.tenThe = tenThe;
			this.nenThe = nenThe;
			this.noiDung = noiDung;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			switch(tenThe) {
			case "TrangChu":
				//temp = new JPanelTrangChu();
				break;
			case "Lop":
				//temp = new JPanelLop();
				break;
			case "SinhVien":
				//temp = new JPanelSinhVien();
				break;
			case "MonHoc":
				//temp = new JPanelMonHoc();
				break;
			}
			noiDungChinh.removeAll();
			// set layout;
			noiDungChinh.add(temp);
			noiDungChinh.validate();
			noiDungChinh.repaint();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			//doi mau DAM hon
		}

		@Override
		public void mouseReleased(MouseEvent e) {
						
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			//doi mau DAM hon
		}

		@Override
		public void mouseExited(MouseEvent e) {
			//tra ve mau binh thuong
		}
	}
	
	public static class ButtonEvent implements ActionListener {
		private JPanel temp;

		@Override
		public void actionPerformed(ActionEvent e) {
			switch(e.getActionCommand()) {
			case "btnThemSV":
				System.out.println("them sv");
				//chuyen den panel xem sv
				break;
			case "btnXemDiem":
				System.out.println("xem diem");
				//chuyen den trang xem diem
				break;
		}
		}
	}
}