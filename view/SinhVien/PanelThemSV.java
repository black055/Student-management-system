package view.SinhVien;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;

import QuanLySinhVien.MainClass.Main;
import dao.BangDiemDAO;
import dao.LopDAO;
import dao.SinhVienDAO;
import dao.TaiKhoanDAO;
import pojo.*;

import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.DropMode;

/**
 * view.SinhVien
 * @Created by DELL - StudentID: 18120652
 * @Date Jun 10, 2020 - 7:54:27 PM 
 * @Description ...
 */
public class PanelThemSV extends JPanel {
	private JTextField txtHoTen;
	private JTextField txtCMND;
	private JTextField txtMSSV;

	/**
	 * Create the panel.
	 */
	
	private boolean validateForm() {
		if (txtHoTen.getText().equals("") || txtCMND.getText().equals("") || txtMSSV.getText().equals(""))
			return false;
		return true;
	}
	
	public PanelThemSV() {
		setBackground(new Color(245, 245, 245));
		
		JPanel title = new JPanel();
		title.setBackground(new Color(245, 245, 245));
		
		JLabel titleContent = new JLabel("Th\u00EAm sinh vi\u00EAn");
		titleContent.setBackground(Color.CYAN);
		titleContent.setFont(new Font("Tahoma", Font.BOLD, 24));
		title.add(titleContent);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 245, 245));
		
		JLabel lbHoTen = new JLabel("H\u1ECC T\u00CAN:");
		lbHoTen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtHoTen = new JTextField();
		txtHoTen.setColumns(10);
		
		JLabel lbMSSV = new JLabel("MSSV:");
		lbMSSV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtMSSV = new JTextField();
		txtMSSV.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("GI\u1EDAI T\u00CDNH:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		ButtonGroup buttons = new ButtonGroup();
		
		JRadioButton maleRadio = new JRadioButton("Nam");
		maleRadio.setBackground(new Color(245, 245, 245));
		maleRadio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		maleRadio.setSelected(true);
		buttons.add(maleRadio);
		
		JRadioButton femaleRadio = new JRadioButton("N\u1EEF");
		femaleRadio.setBackground(new Color(245, 245, 245));
		femaleRadio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		buttons.add(femaleRadio);
		
		JLabel lbCMND = new JLabel("CMND:");
		lbCMND.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtCMND = new JTextField();
		txtCMND.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("L\u1EDAP:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		List<Lop> dsLop = LopDAO.danhSachLop();
		String[] classList = new String[dsLop.size()];
		for (int i = 0; i < dsLop.size(); i++) {
			classList[i] = dsLop.get(i).getMaLop();
		}
		JComboBox classBox = new JComboBox(classList);
		
		JButton btnThemSV = new JButton("Th\u00EAm sinh vi\u00EAn");
		btnThemSV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String ten = txtHoTen.getText();
				String mssv = txtMSSV.getText();
				String cmnd = txtCMND.getText();
				Lop lop = LopDAO.thongTinLop((String) classBox.getSelectedItem());
				String gioiTinh = (maleRadio.isSelected()) ? "Nam" : "Nữ";
				SinhVien sv = new SinhVien(mssv, ten, gioiTinh, cmnd, lop, lop.getDsMH());
				
				boolean result = SinhVienDAO.themSinhVien(sv);
				
				if (result == true) {
					
					for (MonHoc temp : lop.getDsMH()) {
						BangDiemDAO.suaBangDiem(new BangDiem(mssv, temp.getMaMH()));
					}
					
					TaiKhoanDAO.themTaiKhoan(new TaiKhoan(mssv, mssv, false));
					
					String message = "Thêm sinh viên thành công: \n";
					message += "MSSV: " + mssv + "\n";
					message += "Họ tên: " + ten + "\n";
					message += "Lớp: " + lop.getMaLop() + "\n";
					message += "Giới tính: " + gioiTinh + "\n";
					message += "CMND: " + cmnd + "\n";
					JOptionPane.showMessageDialog(new JFrame(), message, "Thêm thành công!",JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "Vui lòng kiểm tra lại thông tin đã nhập!\n Mã số sinh viên có thể đã tồn tại", "Thêm thất bại!",JOptionPane.ERROR_MESSAGE);
				}
				Main.setMainPanel(new PanelSinhVien());
			}
		});
		btnThemSV.setEnabled(false);
		btnThemSV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnDatLai = new JButton("\u0110\u1EB7t l\u1EA1i");
		btnDatLai.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnThemSV.setEnabled(false);
				txtHoTen.setText("");
				txtCMND.setText("");
				txtMSSV.setText("");
			}
		});
		btnDatLai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnQuayVe = new JButton("Quay v\u1EC1");
		btnQuayVe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Main.setMainPanel(new PanelSinhVien());
			}
		});
		btnQuayVe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(title, GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(title, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE))
		);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(44)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lbHoTen)
							.addGap(5)
							.addComponent(txtHoTen, GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lbMSSV, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addComponent(txtMSSV, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
							.addGap(35)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addComponent(maleRadio, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addComponent(femaleRadio, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lbCMND, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addComponent(txtCMND, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
							.addGap(35)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addComponent(classBox, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnThemSV, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
							.addGap(50)
							.addComponent(btnDatLai, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
							.addGap(60)
							.addComponent(btnQuayVe, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)))
					.addGap(46))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(42)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lbHoTen, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtHoTen, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addGap(51)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lbMSSV, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtMSSV, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(maleRadio)
						.addComponent(femaleRadio))
					.addGap(59)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lbCMND, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtCMND, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(classBox, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addGap(74)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnThemSV, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDatLai, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnQuayVe, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
		);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);
		
		txtHoTen.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (txtHoTen.getText().length() >= 30) {
					try {
						txtHoTen.setText(txtHoTen.getText(0, 30));
					} catch (BadLocationException e1) {
						e1.printStackTrace();
					}
				}
				btnThemSV.setEnabled(validateForm());
			}
		});
		
		txtCMND.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (txtCMND.getText().length() >= 12) {
					try {
						txtCMND.setText(txtCMND.getText(0, 12));
					} catch (BadLocationException e1) {
						e1.printStackTrace();
					}
				}
				btnThemSV.setEnabled(validateForm());
			}
		});
		
		txtMSSV.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (txtMSSV.getText().length() >= 8) {
					try {
						txtMSSV.setText(txtMSSV.getText(0, 8));
					} catch (BadLocationException e1) {
						e1.printStackTrace();
					}
				}
				btnThemSV.setEnabled(validateForm());
			}
		});
		
	}
}
