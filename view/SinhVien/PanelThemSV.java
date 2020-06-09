package view.SinhVien;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import dao.LopDAO;
import dao.SinhVienDAO;
import pojo.Lop;
import pojo.SinhVien;
/**
 * view
 * @Created by DELL - StudentID: 18120652
 * @Date Jun 9, 2020 - 6:21:06 PM 
 * @Description ...
 */
public class PanelThemSV extends JPanel{
	private JPanel title = new JPanel();
	private JPanel form = new JPanel();
	private JPanel buttons = new JPanel();
	private JComboBox<String> classes;
	private JRadioButton male = new JRadioButton("Nam");
	private JRadioButton female = new JRadioButton("Nữ");
	private ButtonGroup group = new ButtonGroup();
	
	private List<JTextField> inputs = new ArrayList<JTextField>();
	private List<JLabel> rowHeader = new ArrayList<JLabel>();
	private List<JPanel> formItems = new ArrayList<JPanel>();
	
	private JButton btnSubmit = new JButton("Hoàn tất");
	private JButton btnCancel = new JButton("Hủy bỏ");
	
	private String sex = "Nam";
	private String classId;
	
	private boolean validateForm() {
		if (inputs.get(0).getText().isEmpty() || inputs.get(1).getText().isEmpty() || inputs.get(2).getText().isEmpty())
			return false;
		return true;
	}
	
	public PanelThemSV() {
		this.setLayout(new BorderLayout());
		
		JLabel titleContent = new JLabel("Thêm sinh viên");
		titleContent.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
		title.add(titleContent);
		title.setAlignmentX(CENTER_ALIGNMENT);
		title.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		this.add(title, BorderLayout.PAGE_START);
		
		String[] labels = {"Họ tên:", "MSSV:", "Lớp:", "Giới tính:", "CMND:"};

		for (String row : labels) {
			JLabel header = new JLabel(row);
			header.setSize(new Dimension(100, 20));
			header.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			rowHeader.add(header);
		}
		
		for (int i = 0; i < 3; i++) {
			JTextField temp = new JTextField();
			temp.setPreferredSize(new Dimension(200, 20));
			temp.setMaximumSize(new Dimension(200, 20));
			temp.getDocument().addDocumentListener(new KeyboardListener());
			inputs.add(temp);
		}
		
		List<Lop> dsLop = LopDAO.danhSachLop();
		String[] classList = new String[dsLop.size()];
		for (int i = 0; i < dsLop.size(); i++) {
			classList[i] = dsLop.get(i).getMaLop();
		}
		classes = new JComboBox<String>(classList);
		classes.setMaximumSize(new Dimension(80, 20));
		
		classId = (String) classes.getSelectedItem();
		classes.setActionCommand("class");
		classes.addActionListener(new EventListener());
		
		male.setSelected(true);
		male.setActionCommand("male");
		male.addActionListener(new EventListener());
		female.setActionCommand("female");
		female.addActionListener(new EventListener());
		group.add(male);
		group.add(female);
		
		form.setLayout(new BoxLayout(form, BoxLayout.PAGE_AXIS));
		
		for (int i = 0; i < 5; i++) {
			formItems.add(new JPanel(new FlowLayout(FlowLayout.LEFT)));
		}
		
		formItems.get(0).add(rowHeader.get(0));
		formItems.get(0).add(inputs.get(0));
		formItems.get(1).add(rowHeader.get(1));
		formItems.get(1).add(inputs.get(1));
		formItems.get(2).add(rowHeader.get(2));
		formItems.get(2).add(classes);
		formItems.get(3).add(rowHeader.get(3));
		formItems.get(3).add(male);
		formItems.get(3).add(female);
		formItems.get(4).add(rowHeader.get(4));
		formItems.get(4).add(inputs.get(2));
		
		for (JPanel temp : formItems) {
			form.add(temp);
		}
		
		this.add(form, BorderLayout.CENTER);
		
		buttons.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		btnSubmit.setActionCommand("btnSubmit");
		btnSubmit.setEnabled(false);
		btnSubmit.addActionListener(new EventListener());
		buttons.add(btnSubmit);
		
		btnCancel.setActionCommand("btnCancel");
		btnCancel.addActionListener(new EventListener());
		buttons.add(btnCancel);
		
		this.add(buttons, BorderLayout.PAGE_END);
	}
	
	class  EventListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			switch(e.getActionCommand()) {
				case "btnSubmit":
					String ten = inputs.get(0).getText();
					String mssv = inputs.get(1).getText();
					String cmnd = inputs.get(2).getText();
					Lop lop = LopDAO.thongTinLop(classId);
					SinhVien sv = new SinhVien(mssv, ten, sex, cmnd, lop, lop.getDsMH());
					System.out.println(sv.getMaSV() + ", " + sv.getLop().getMaLop() + ", " + sv.getGioiTinh());
					//SinhVienDAO.themSinhVien(sv);
					break;
				case "btnCancel":
					//dong cua so
					break;
				case "male":
					sex = "Nam";
					break;
				case "female":
					sex = "Nữ";
					break;
				case "class":
					JComboBox cb = (JComboBox) e.getSource();
					classId = (String) cb.getSelectedItem();
					break;
			}
		}
	}
	
	class KeyboardListener implements DocumentListener{
		
		@Override
		public void insertUpdate(DocumentEvent e) {
			btnSubmit.setEnabled(validateForm());
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			btnSubmit.setEnabled(validateForm());
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			btnSubmit.setEnabled(validateForm());
		}
	}
}
