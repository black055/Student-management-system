package view.SinhVien;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import QuanLySinhVien.MainClass.Main;
import dao.BangDiemDAO;
import dao.SinhVienDAO;
import pojo.BangDiem;
import pojo.MonHoc;
import pojo.SinhVien;
import javax.swing.JTextField;
import javax.swing.Box;
import java.awt.FlowLayout;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * view.SinhVien
 * @Created by DELL - StudentID: 18120652
 * @Date Jun 14, 2020 - 10:29:04 AM 
 * @Description ...
 */

public class PanelSuaDiem extends JPanel {
	
	private JButton btnSuaDiem = new JButton("Sửa điểm");
	private JButton btnQuayVe = new JButton("Quay về");
	
	private String studentId;
	private String courseId;
	private JTextField txtDiemTong;
	private JTextField txtDiemKhac;
	private JTextField txtDiemCK;
	private JTextField txtDiemGK;
	
	/**
	 * Create the panel.
	 */
	
	private String[] getCoursesList(String selectedStudent) {
		selectedStudent = selectedStudent.split(" - ")[0];
		Set<MonHoc> dsMH = SinhVienDAO.thongTinSinhVien(selectedStudent).getDsMH();
		String[] courseList = new String[dsMH.size() + 1];
		courseList[0] = "------------------------------------------------------------------------------";
		int index = 1;
		for (MonHoc temp : dsMH) {
			courseList[index++] = temp.getMaMH() + " - " + temp.getTenMH();
		}
		return courseList;
	}
	
	private boolean validateForm() {
		if (txtDiemGK.getText().contentEquals("") || txtDiemCK.getText().contentEquals("") || txtDiemKhac.getText().contentEquals("") || txtDiemTong.getText().contentEquals(""))
			return false;
		try {
			float diemGK = Float.parseFloat(txtDiemGK.getText());
			float diemCK = Float.parseFloat(txtDiemCK.getText());
			float diemKhac = Float.parseFloat(txtDiemKhac.getText());
			float diemTong = Float.parseFloat(txtDiemTong.getText());
			
			if (diemGK < 0 || diemGK > 10)
				return false;
			if (diemCK < 0 || diemCK > 10)
				return false;
			if (diemKhac < 0 || diemKhac > 10)
				return false;
			if (diemTong < 0 || diemTong > 10)
				return false;
		} catch (NumberFormatException ex) {
			return false;
		}
		return true;
	}
	
	public PanelSuaDiem() {
		
		JPanel title = new JPanel();
		
		JLabel titleContent = new JLabel("Sửa điểm");
		titleContent.setFont(new Font("Tahoma", Font.BOLD, 24));
		title.add(titleContent);
		
		JPanel form = new JPanel();
		
		JLabel lbSinhVien = new JLabel("SINH VI\u00CAN:");
		lbSinhVien.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		List<SinhVien> dsSV = SinhVienDAO.danhSachSinhVien();
		String[] studentList = new String[dsSV.size()];
		for (int i = 0; i < dsSV.size(); i++) {
			studentList[i] = dsSV.get(i).getMaSV() + " - " + dsSV.get(i).getTenSV();
		}
		JComboBox studentBox = new JComboBox(studentList);
		studentId = (String) studentBox.getSelectedItem();
		studentId = studentId.split(" - ")[0];
		
		JLabel lbMonHoc = new JLabel("M\u00D4N H\u1ECCC:");
		lbMonHoc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		
		String selectedStudent = (String)studentBox.getSelectedItem();
		String[] courseList = getCoursesList(selectedStudent);
		JComboBox courseBox = new JComboBox(courseList);
		courseBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (courseBox.getSelectedItem() != null) {
					String selectedItem = (String) courseBox.getSelectedItem();
					selectedItem = selectedItem.split(" - ")[0];
					if (selectedItem.contentEquals("------------------------------------------------------------------------------")) {
						btnSuaDiem.setEnabled(false);
						txtDiemGK.setText(""); txtDiemCK.setText(""); txtDiemTong.setText(""); txtDiemKhac.setText("");
						txtDiemGK.setEditable(false); txtDiemCK.setEditable(false); txtDiemKhac.setEditable(false); txtDiemTong.setEditable(false);
					} else {
						txtDiemGK.setEditable(true);
						txtDiemCK.setEditable(true);
						txtDiemKhac.setEditable(true);
						txtDiemTong.setEditable(true);
						courseId = selectedItem;
						BangDiem bd = BangDiemDAO.thongTinBangDiem(studentId, courseId);
						txtDiemGK.setText(bd.getDiemGK().toString());
						txtDiemCK.setText(bd.getDiemCK().toString());
						txtDiemKhac.setText(bd.getDiemKhac().toString());
						txtDiemTong.setText(bd.getDiemTong().toString());
					}
				}
			}
		});
		
		JPanel panel = new JPanel();
		
		JPanel input = new JPanel();
		GroupLayout gl_form = new GroupLayout(form);
		gl_form.setHorizontalGroup(
			gl_form.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_form.createSequentialGroup()
					.addGap(70)
					.addGroup(gl_form.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lbMonHoc, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbSinhVien, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE))
					.addGap(4)
					.addGroup(gl_form.createParallelGroup(Alignment.TRAILING)
						.addComponent(studentBox, Alignment.LEADING, 0, 242, Short.MAX_VALUE)
						.addComponent(courseBox, Alignment.LEADING, 0, 242, Short.MAX_VALUE))
					.addGap(75))
				.addGroup(gl_form.createSequentialGroup()
					.addGap(1)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, gl_form.createSequentialGroup()
					.addComponent(input, GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
					.addGap(1))
		);
		gl_form.setVerticalGroup(
			gl_form.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_form.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_form.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbSinhVien)
						.addComponent(studentBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_form.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbMonHoc)
						.addComponent(courseBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(input, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		input.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
		
		JPanel borderDiemGK = new JPanel();
		borderDiemGK.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u0110i\u1EC3m GK", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		input.add(borderDiemGK);
		
		txtDiemGK = new JTextField();
		txtDiemGK.setEditable(false);
		txtDiemGK.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				btnSuaDiem.setEnabled(validateForm());
			}
		});
		borderDiemGK.add(txtDiemGK);
		txtDiemGK.setColumns(10);
		
		JPanel borderDiemCK = new JPanel();
		borderDiemCK.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u0110i\u1EC3m CK", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		input.add(borderDiemCK);
		
		txtDiemCK = new JTextField();
		txtDiemCK.setEditable(false);
		txtDiemCK.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				btnSuaDiem.setEnabled(validateForm());
			}
		});
		borderDiemCK.add(txtDiemCK);
		txtDiemCK.setColumns(10);
		
		JPanel borderDiemKhac = new JPanel();
		borderDiemKhac.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u0110i\u1EC3m kh\u00E1c", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		input.add(borderDiemKhac);
		
		txtDiemKhac = new JTextField();
		txtDiemKhac.setEditable(false);
		txtDiemKhac.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				btnSuaDiem.setEnabled(validateForm());
			}
		});
		borderDiemKhac.add(txtDiemKhac);
		txtDiemKhac.setColumns(10);
		
		JPanel borderDiemTong = new JPanel();
		borderDiemTong.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u0110i\u1EC3m t\u1ED5ng", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		input.add(borderDiemTong);
		
		txtDiemTong = new JTextField();
		txtDiemTong.setEditable(false);
		txtDiemTong.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				btnSuaDiem.setEnabled(validateForm());
			}
		});
		borderDiemTong.add(txtDiemTong);
		txtDiemTong.setColumns(10);
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		JPanel btnSubmitBorder = new JPanel();
		FlowLayout flowLayout = (FlowLayout) btnSubmitBorder.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		btnSubmitBorder.setBorder(new EmptyBorder(5, 0, 5, 0));
		panel.add(btnSubmitBorder);
		btnSubmitBorder.add(btnSuaDiem);
		btnSuaDiem.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		btnSuaDiem.setEnabled(false);
		btnSuaDiem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		btnSuaDiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				float diemGK = Float.parseFloat(txtDiemGK.getText());
				float diemCK = Float.parseFloat(txtDiemCK.getText());
				float diemKhac = Float.parseFloat(txtDiemKhac.getText());
				float diemTong = Float.parseFloat(txtDiemTong.getText());
				BangDiem bd = new BangDiem(studentId, courseId, diemGK, diemCK, diemKhac, diemTong);
				if (BangDiemDAO.suaBangDiem(bd) == true) {
					String message = "Sửa bảng điểm thành công! \n";
					message += "MSSV: " + studentId + "\nMã môn học: " + courseId + "\n";
					message += " Điểm GK: " + diemGK + "\n Điểm CK: " + diemCK + "\n Điểm khác: " + diemKhac + "\n Điểm tổng: " + diemTong;
					JOptionPane.showMessageDialog(new JFrame(), message, "Tra cứu thành công!",JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "Có lỗi xảy ra khi tìm kiếm!", "Tra cứu thất bại!",JOptionPane.ERROR_MESSAGE);
				}
				
				Main.setMainPanel(new PanelSinhVien());
			}
		});
		
		JPanel btnBackBorder = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) btnBackBorder.getLayout();
		flowLayout_1.setVgap(0);
		flowLayout_1.setHgap(0);
		btnBackBorder.setBorder(new EmptyBorder(5, 0, 5, 0));
		panel.add(btnBackBorder);
		btnBackBorder.add(btnQuayVe);
		btnQuayVe.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnQuayVe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.setMainPanel(new PanelSinhVien());
			}
		});
		
		btnQuayVe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		form.setLayout(gl_form);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(form, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
						.addComponent(title, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE))
					.addGap(0))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(title, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(form, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
		studentBox.addActionListener(new EventListener(studentBox, courseBox));
		
	}
	
	private class EventListener implements ActionListener {
		
		private JComboBox sourceBox;
		private JComboBox dynamicBox;
		
		public EventListener(JComboBox sourceBox, JComboBox dynamicBox) {
			this.dynamicBox = dynamicBox;
			this.sourceBox = sourceBox;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String[] courseList = getCoursesList((String) sourceBox.getSelectedItem());
			dynamicBox.removeAllItems();
			for (String course : courseList) {
				dynamicBox.addItem(course);
			}
			dynamicBox.validate();
			btnSuaDiem.setEnabled(false);
			studentId = (String) sourceBox.getSelectedItem();
			studentId = studentId.split(" - ")[0];
		}
	}
}
