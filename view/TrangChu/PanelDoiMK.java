package view.TrangChu;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import QuanLySinhVien.MainClass.Main;
import dao.TaiKhoanDAO;
import pojo.TaiKhoan;
import view.dashboard.GiaoVuDashboard;
import view.dashboard.SinhVienDashboard;

/**
 * view.TrangChu
 * @Created by DELL - StudentID: 18120652
 * @Date Jun 15, 2020 - 9:29:48 PM 
 * @Description ...
 */
public class PanelDoiMK extends JPanel {
	private JPasswordField txtRePassword;
	private JPasswordField txtNewPassword;
	private JPasswordField txtPassword;

	/**
	 * Create the panel.
	 */
	public PanelDoiMK() {
		setBackground(new Color(245, 245, 245));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 245, 245));
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		
		JLabel lbMatKhau = new JLabel("Mật khẩu mới:");
		lbMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lbXacNhan = new JLabel("Nhâp lại mật khẩu:");
		lbXacNhan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtRePassword = new JPasswordField();
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(245, 245, 245));
		
		txtNewPassword = new JPasswordField();
		
		JLabel lbMatKhauHT = new JLabel("Mật khẩu hiện tại:");
		lbMatKhauHT.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtPassword = new JPasswordField();
		
		JPanel title = new JPanel();
		title.setBackground(new Color(245, 245, 245));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
				.addComponent(title, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(59)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lbMatKhau)
							.addGap(39))
						.addComponent(lbMatKhauHT)
						.addComponent(lbXacNhan))
					.addGap(22)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtPassword, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
						.addComponent(txtNewPassword, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
						.addComponent(txtRePassword, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE))
					.addGap(67))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(title, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(36)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbMatKhauHT)
						.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtNewPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbMatKhau))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtRePassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbXacNhan))
					.addGap(28)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(35, Short.MAX_VALUE))
		);
		
		JLabel titleContent = new JLabel("Đổi mật khẩu");
		titleContent.setFont(new Font("Tahoma", Font.BOLD, 24));
		title.add(titleContent);
		
		JButton btnSubmit = new JButton("Đổi mật khẩu");
		btnSubmit.setEnabled(false);
		
		JLabel error = new JLabel("");
		error.setForeground(Color.RED);
		panel.add(error);
		error.setFont(new Font("Tahoma", Font.PLAIN, 14));
		setLayout(groupLayout);
		
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (!String.copyValueOf(txtPassword.getPassword()).contentEquals(Main.getTaiKhoan().getMatKhau())) {
					error.setText("Mật khẩu đã không trùng khớp với mật khẩu hiện tại");
				}
				else {
					if (!Arrays.equals(txtNewPassword.getPassword(), txtRePassword.getPassword())) {
						error.setText("Mật khẩu mới và mật khẩu xác nhận không trùng nhau");
					}
					else {
						TaiKhoan tk = Main.getTaiKhoan();
						tk.setMatKhau(String.copyValueOf(txtNewPassword.getPassword()));
						TaiKhoanDAO.suaTaiKhoan(tk);
						JOptionPane.showMessageDialog(new JFrame(), "Đổi mật khẩu thành công", "Đổi mật khẩu thành công",JOptionPane.INFORMATION_MESSAGE);
						Main.setMainPanel(new PanelTrangChu());
					}
				}
			}
		});
		panel_1.add(btnSubmit);
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtRePassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (String.copyValueOf(txtPassword.getPassword()).contentEquals("") || String.copyValueOf(txtNewPassword.getPassword()).contentEquals("") || String.copyValueOf(txtRePassword.getPassword()).contentEquals("")) {
					btnSubmit.setEnabled(false);
				} else {
					btnSubmit.setEnabled(true);
				}
			}
		});
		
	}
}
