package view.dashboard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import QuanLySinhVien.MainClass.Main;
import view.Lop.PanelLop;
import view.MonHoc.PanelMonHoc;
import view.SinhVien.PanelSinhVien;
import view.TrangChu.PanelDangNhap;
import view.TrangChu.PanelTrangChu;

/**
 * view.dashboard
 * @Created by DELL - StudentID: 18120652
 * @Date Jun 15, 2020 - 7:17:02 PM 
 * @Description ...
 */
public class DangNhapDashboard extends JPanel {

	/**
	 * Create the panel.
	 */
	
	public DangNhapDashboard() {
		this.setBackground(Color.DARK_GRAY);

		JPanel trangChuDb = new JPanel();
		trangChuDb.setLayout(new BorderLayout(0, 0));
		JLabel trangChuLb = new JLabel("Trang ch\u1EE7");
		trangChuLb.setForeground(Color.WHITE);
		trangChuLb.setIcon(new ImageIcon("src\\icon\\home-icon.png"));
		trangChuLb.setHorizontalAlignment(SwingConstants.LEFT);
		trangChuLb.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		trangChuDb.add(trangChuLb);
		
		GroupLayout gl_dashboardContainer = new GroupLayout(this);
		gl_dashboardContainer.setHorizontalGroup(
			gl_dashboardContainer.createParallelGroup(Alignment.LEADING)
				.addComponent(trangChuDb, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
		);
		gl_dashboardContainer.setVerticalGroup(
			gl_dashboardContainer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_dashboardContainer.createSequentialGroup()
					.addContainerGap()
					.addComponent(trangChuDb, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED))
		);
		
		trangChuDb.setBackground(Color.GRAY);
		
		this.setLayout(gl_dashboardContainer);
	}

}
