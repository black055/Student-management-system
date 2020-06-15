package view.TrangChu;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Component;
import javax.swing.Box;
import javax.swing.LayoutStyle.ComponentPlacement;

import QuanLySinhVien.MainClass.Main;
import dao.TaiKhoanDAO;
import pojo.TaiKhoan;
import view.dashboard.GiaoVuDashboard;
import view.dashboard.SinhVienDashboard;

import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * view.TrangChu
 * @Created by DELL - StudentID: 18120652
 * @Date Jun 15, 2020 - 12:47:45 PM 
 * @Description ...
 */
public class PanelDangNhap extends JPanel {
	private JTextField txtUsername;
	private JPasswordField txtPassword;

	/**
	 * Create the panel.
	 */
	public PanelDangNhap() {
		setBackground(new Color(245, 245, 245));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 245, 245));
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		
		JLabel lblNewLabel = new JLabel("T\u00E0i kho\u1EA3n:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNewLabel_1 = new JLabel("M\u1EADt kh\u1EA9u:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		
		txtPassword = new JPasswordField();
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(245, 245, 245));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(76)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel))
						.addComponent(lblNewLabel_1))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtUsername, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
						.addComponent(txtPassword, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE))
					.addGap(67))
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(102)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_1)
						.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(156, Short.MAX_VALUE))
		);
		
		JButton btnSubmit = new JButton("\u0110\u0103ng nh\u1EADp");
		btnSubmit.setEnabled(false);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TaiKhoan input = TaiKhoanDAO.thongTinTaiKhoan(txtUsername.getText());
				if (input == null) {
					JOptionPane.showMessageDialog(new JFrame(), "Tên đăng nhập không tồn tại", "Đăng nhập thất bại",JOptionPane.ERROR_MESSAGE);
				} else {
					if (input.getMatKhau().contentEquals(new String(txtPassword.getPassword())))
					{
						JOptionPane.showMessageDialog(new JFrame(), "Chào mừng bạn đến với chương trình quản lý sinh viên", "Đăng nhập thành công",JOptionPane.INFORMATION_MESSAGE);
						Main.setTaiKhoan(input);
						if (input.getGiaoVu() == true) {
							Main.setDashboard(new GiaoVuDashboard());
						} else {
							Main.setDashboard(new SinhVienDashboard());
						}
						Main.setMainPanel(new PanelTrangChu());
					} else {
						JOptionPane.showMessageDialog(new JFrame(), "Mật khẩu không trùng khớp", "Đăng nhập thất bại",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		panel_1.add(btnSubmit);
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lbMessage = new JLabel("Vui l\u00F2ng \u0111\u0103ng nh\u1EADp \u0111\u1EC3 s\u1EED d\u1EE5ng ch\u01B0\u01A1ng tr\u00ECnh");
		panel.add(lbMessage);
		lbMessage.setFont(new Font("Tahoma", Font.BOLD, 14));
		setLayout(groupLayout);

		txtUsername.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (txtUsername.getText().contentEquals("") || txtPassword.getText().contentEquals("")) {
					btnSubmit.setEnabled(false);
				} else {
					btnSubmit.setEnabled(true);
				}
			}
		});
		
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (txtUsername.getText().contentEquals("") || txtPassword.getText().contentEquals("")) {
					btnSubmit.setEnabled(false);
				} else {
					btnSubmit.setEnabled(true);
				}
			}
		});
		
	}
}
