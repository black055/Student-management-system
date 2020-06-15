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
import dao.SinhVienDAO;
import view.Lop.PanelLop;
import view.MonHoc.PanelMonHoc;
import view.SinhVien.PanelKetQuaHocTap;
import view.SinhVien.PanelSinhVien;
import view.TrangChu.PanelDangNhap;
import view.TrangChu.PanelTrangChu;

/**
 * view.dashboard
 * @Created by DELL - StudentID: 18120652
 * @Date Jun 15, 2020 - 7:34:05 PM 
 * @Description ...
 */
public class SinhVienDashboard extends JPanel {

	/**
	 * Create the panel.
	 */
	private void setDefaultDashboardColor() {
		for (int i = 0; i < this.getComponentCount(); i++) {
			this.getComponent(i).setBackground(Color.DARK_GRAY);
		}
	}
	
	public SinhVienDashboard() {
		this.setBackground(Color.DARK_GRAY);

		JPanel trangChuDb = new JPanel();
		trangChuDb.setLayout(new BorderLayout(0, 0));
		JLabel trangChuLb = new JLabel("Trang ch\u1EE7");
		trangChuLb.setForeground(Color.WHITE);
		trangChuLb.setIcon(new ImageIcon("src\\icon\\home-icon.png"));
		trangChuLb.setHorizontalAlignment(SwingConstants.LEFT);
		trangChuLb.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		trangChuDb.add(trangChuLb);
		
		trangChuDb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setDefaultDashboardColor();
				trangChuDb.setBackground(Color.GRAY);
				Main.setMainPanel(new PanelTrangChu());
			}
		});
		
		JPanel xemDiemDb = new JPanel();
		xemDiemDb.setLayout(new BorderLayout(0, 0));
		
		JLabel xemDiemLb = new JLabel("Kết quả học tập");
		xemDiemLb.setForeground(Color.WHITE);
		xemDiemLb.setIcon(new ImageIcon("src\\icon\\course-icon.png"));
		xemDiemLb.setHorizontalAlignment(SwingConstants.LEFT);
		xemDiemLb.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		xemDiemDb.add(xemDiemLb);
		
		xemDiemDb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setDefaultDashboardColor();
				xemDiemDb.setBackground(Color.GRAY);
				Main.setMainPanel(new PanelKetQuaHocTap(SinhVienDAO.thongTinSinhVien(Main.getTaiKhoan().getTenTK())));
			}
		});
		
		GroupLayout gl_dashboardContainer = new GroupLayout(this);
		gl_dashboardContainer.setHorizontalGroup(
			gl_dashboardContainer.createParallelGroup(Alignment.LEADING)
				.addComponent(trangChuDb, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
				.addComponent(xemDiemDb, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
		);
		gl_dashboardContainer.setVerticalGroup(
			gl_dashboardContainer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_dashboardContainer.createSequentialGroup()
					.addContainerGap()
					.addComponent(trangChuDb, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(xemDiemDb, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED))
		);
		
		trangChuDb.setBackground(Color.GRAY);
		xemDiemDb.setBackground(Color.DARK_GRAY);
		
		this.setLayout(gl_dashboardContainer);
	}
}
