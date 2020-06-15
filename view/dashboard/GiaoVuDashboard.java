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
 * @Date Jun 15, 2020 - 7:02:26 PM 
 * @Description ...
 */
public class GiaoVuDashboard extends JPanel {

	/**
	 * Create the panel.
	 */
	private void setDefaultDashboardColor() {
		for (int i = 0; i < this.getComponentCount(); i++) {
			this.getComponent(i).setBackground(Color.DARK_GRAY);
		}
	}
	
	public GiaoVuDashboard() {
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
		
		JPanel sinhVienDb = new JPanel();
		sinhVienDb.setLayout(new BorderLayout(0, 0));
		
		JLabel sinhVienLb = new JLabel("Qu\u1EA3n l\u00FD sinh vi\u00EAn");
		sinhVienLb.setForeground(Color.WHITE);
		sinhVienLb.setIcon(new ImageIcon("src\\icon\\student-icon.png"));
		sinhVienLb.setHorizontalAlignment(SwingConstants.LEFT);
		sinhVienLb.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		sinhVienDb.add(sinhVienLb);
		
		sinhVienDb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setDefaultDashboardColor();
				sinhVienDb.setBackground(Color.GRAY);
				Main.setMainPanel(new PanelSinhVien());
			}
		});
		
		JPanel lopDb = new JPanel();
		lopDb.setLayout(new BorderLayout(0, 0));
		
		JLabel lopLb = new JLabel("Qu\u1EA3n l\u00FD l\u1EDBp");
		lopLb.setForeground(Color.WHITE);
		lopLb.setIcon(new ImageIcon("src\\icon\\class-icon.png"));
		lopLb.setHorizontalAlignment(SwingConstants.LEFT);
		lopLb.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lopDb.add(lopLb);
		
		lopDb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setDefaultDashboardColor();
				lopDb.setBackground(Color.GRAY);
				Main.setMainPanel(new PanelLop());
			}
		});
		
		JPanel monHocDb = new JPanel();
		monHocDb.setLayout(new BorderLayout(0, 0));
		
		JLabel monHocLb = new JLabel("Qu\u1EA3n l\u00FD m\u00F4n h\u1ECDc");
		monHocLb.setForeground(Color.WHITE);
		monHocLb.setIcon(new ImageIcon("src\\icon\\course-icon.png"));
		monHocLb.setHorizontalAlignment(SwingConstants.LEFT);
		monHocLb.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		monHocDb.add(monHocLb);
		
		monHocDb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setDefaultDashboardColor();
				monHocDb.setBackground(Color.GRAY);
				Main.setMainPanel(new PanelMonHoc());
			}
		});
		
		GroupLayout gl_dashboardContainer = new GroupLayout(this);
		gl_dashboardContainer.setHorizontalGroup(
			gl_dashboardContainer.createParallelGroup(Alignment.LEADING)
				.addComponent(trangChuDb, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
				.addComponent(sinhVienDb, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
				.addComponent(lopDb, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
				.addComponent(monHocDb, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
		);
		gl_dashboardContainer.setVerticalGroup(
			gl_dashboardContainer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_dashboardContainer.createSequentialGroup()
					.addContainerGap()
					.addComponent(trangChuDb, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(sinhVienDb, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lopDb, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(monHocDb, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(86, Short.MAX_VALUE))
		);
		
		trangChuDb.setBackground(Color.GRAY);
		sinhVienDb.setBackground(Color.DARK_GRAY);
		monHocDb.setBackground(Color.DARK_GRAY);
		lopDb.setBackground(Color.DARK_GRAY);
		
		this.setLayout(gl_dashboardContainer);
	}

}
