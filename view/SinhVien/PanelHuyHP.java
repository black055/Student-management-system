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

/**
 * view.SinhVien
 * @Created by DELL - StudentID: 18120652
 * @Date Jun 12, 2020 - 4:04:57 PM 
 * @Description ...
 */
public class PanelHuyHP extends JPanel {
	private JButton btnHuy = new JButton("Hủy môn học");
	private JButton btnQuayVe = new JButton("Quay về");
	
	private String studentId;
	private String courseId;
	
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
	
	public PanelHuyHP() {
		
		JPanel title = new JPanel();
		
		JLabel titleContent = new JLabel("Hủy đăng ký học phần");
		titleContent.setFont(new Font("Tahoma", Font.BOLD, 24));
		title.add(titleContent);
		
		JPanel form = new JPanel();
		
		JLabel lblNewLabel = new JLabel("SINH VI\u00CAN:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		List<SinhVien> dsSV = SinhVienDAO.danhSachSinhVien();
		String[] studentList = new String[dsSV.size()];
		for (int i = 0; i < dsSV.size(); i++) {
			studentList[i] = dsSV.get(i).getMaSV() + " - " + dsSV.get(i).getTenSV();
		}
		JComboBox studentBox = new JComboBox(studentList);
		studentId = (String) studentBox.getSelectedItem();
		studentId = studentId.split(" - ")[0];
		
		JLabel lblNewLabel_1 = new JLabel("M\u00D4N H\u1ECCC:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		
		String selectedStudent = (String)studentBox.getSelectedItem();
		String[] courseList = getCoursesList(selectedStudent);
		JComboBox courseBox = new JComboBox(courseList);
		courseBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (courseBox.getSelectedItem() != null) {
					String selectedItem = (String) courseBox.getSelectedItem();
					selectedItem = selectedItem.split(" - ")[0];
					if (selectedItem.contentEquals("------------------------------------------------------------------------------")) {
						btnHuy.setEnabled(false);
					} else {
						btnHuy.setEnabled(true);
						courseId = selectedItem;
					}
				}
			}
		});
		
		JPanel panel = new JPanel();
		GroupLayout gl_form = new GroupLayout(form);
		gl_form.setHorizontalGroup(
			gl_form.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_form.createSequentialGroup()
					.addGap(60)
					.addGroup(gl_form.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_form.createParallelGroup(Alignment.LEADING)
						.addComponent(courseBox, 0, 278, Short.MAX_VALUE)
						.addComponent(studentBox, 0, 278, Short.MAX_VALUE))
					.addGap(66))
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
		);
		gl_form.setVerticalGroup(
			gl_form.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_form.createSequentialGroup()
					.addGap(49)
					.addGroup(gl_form.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(studentBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(40)
					.addGroup(gl_form.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(courseBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(22, Short.MAX_VALUE))
		);
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		JPanel btnHuyBorder = new JPanel();
		btnHuyBorder.setBorder(new EmptyBorder(5, 0, 5, 0));
		panel.add(btnHuyBorder);
		btnHuyBorder.add(btnHuy);
		btnHuy.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		btnHuy.setEnabled(false);
		btnHuy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		btnHuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean result = BangDiemDAO.xoaBangDiem(BangDiemDAO.thongTinBangDiem(studentId, courseId));
				if (result == true) {
					String message = "Hủy học phần thành công! \n";
					message += "Mã số sinh viên: " + studentId + "\n";
					message += "Mã môn học: " + courseId + "\n";
					JOptionPane.showMessageDialog(new JFrame(), message, "Hủy thành công!",JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "Có lỗi xảy ra!", "Hủy thất bại!",JOptionPane.ERROR_MESSAGE);
				}
				Main.setMainPanel(new PanelSinhVien());
			}
		});
		
		JPanel btnBackBorder = new JPanel();
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
				.addComponent(title, GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
				.addComponent(form, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(title, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(form, GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE))
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
			btnHuy.setEnabled(false);
			studentId = (String) sourceBox.getSelectedItem();
			studentId = studentId.split(" - ")[0];
		}
	}
}
