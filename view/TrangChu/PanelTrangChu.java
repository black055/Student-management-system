package view.TrangChu;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import QuanLySinhVien.MainClass.Main;
import view.dashboard.DangNhapDashboard;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * view.TrangChu
 * @Created by DELL - StudentID: 18120652
 * @Date Jun 15, 2020 - 5:44:12 PM 
 * @Description ...
 */
public class PanelTrangChu extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelTrangChu() {
		
		JLabel lbXinChao = new JLabel("Xin ch\u00E0o");
		lbXinChao.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lbUsername = new JLabel(Main.getTaiKhoan().getTenTK());
		lbUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbUsername.setForeground(Color.RED);
		
		JButton btnChangePassword = new JButton("\u0110\u1ED5i m\u1EADt kh\u1EA9u");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.setMainPanel(new PanelDoiMK());
			}
		});
		btnChangePassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnLogout = new JButton("\u0110\u0103ng xu\u1EA5t");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.setTaiKhoan(null);
				Main.setDashboard(new DangNhapDashboard());
				Main.setMainPanel(new PanelDangNhap());
			}
		});
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		String chucVu = (Main.getTaiKhoan().getGiaoVu() == true) ?  "Chức vụ: GIÁO VỤ" : "Chức vụ: SINH VIÊN";
		JLabel lblNewLabel_3 = new JLabel(chucVu);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(161)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnLogout, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
						.addComponent(btnChangePassword, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(174))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(lblNewLabel_3))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lbXinChao)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbUsername)))
					.addContainerGap(283, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbXinChao)
						.addComponent(lbUsername))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addGap(41)
					.addComponent(btnChangePassword, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
					.addGap(37)
					.addComponent(btnLogout, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(104))
		);
		setLayout(groupLayout);

	}
}
