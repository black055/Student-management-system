package view.SinhVien;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.SinhVienDAO;
import pojo.SinhVien;
import view.FrameController;

/**
 * view
 * @Created by DELL - StudentID: 18120652
 * @Date Jun 8, 2020 - 8:26:01 PM 
 * @Description ...
 */

public class PanelSinhVien extends JPanel{
	private JPanel title = new JPanel();
	private JTable danhSach = new JTable();
	private JPanel buttons = new JPanel();
	
	public PanelSinhVien() {
		this.setLayout(new BorderLayout());
		JLabel titleContent = new JLabel("Quản lý sinh viên");
		titleContent.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
		title.add(titleContent);
		title.setAlignmentX(CENTER_ALIGNMENT);
		title.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		this.add(title, BorderLayout.PAGE_START);
		
		List<SinhVien> danhSachSV = SinhVienDAO.danhSachSinhVien();
		String[] tenCot = {"MSSV", "Họ tên", "Lớp", "Giới tính", "CMND"};
		DefaultTableModel model = new DefaultTableModel();
		for (String temp : tenCot) {
			model.addColumn(temp);
		}
		for (SinhVien sv : danhSachSV) {
			model.addRow(new Object[] {sv.getMaSV(), sv.getTenSV(), sv.getLop().getMaLop(), sv.getGioiTinh(), sv.getCmnd()});
		}
		danhSach.setModel(model);
		danhSach.setEnabled(false);
		JScrollPane sp = new JScrollPane(danhSach);
		sp.setBorder(BorderFactory.createTitledBorder("Danh sách sinh viên"));
		this.add(sp, BorderLayout.CENTER);
		
		buttons.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		JButton btnThemSV = new JButton("Thêm sinh viên");
		btnThemSV.setActionCommand("btnThemSV");
		btnThemSV.addActionListener(new FrameController.ButtonEvent());
		buttons.add(btnThemSV);
		
		JButton btnXemDiem = new JButton("Xem điểm");
		btnXemDiem.setActionCommand("btnXemDiem");
		btnXemDiem.addActionListener(new FrameController.ButtonEvent());
		buttons.add(btnXemDiem);
		
		this.add(buttons, BorderLayout.PAGE_END);
	}
}
