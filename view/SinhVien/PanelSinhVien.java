package view.SinhVien;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.List;

import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import QuanLySinhVien.MainClass.Main;
import dao.SinhVienDAO;
import pojo.SinhVien;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;

import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.CardLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 * view.SinhVien
 * @Created by DELL - StudentID: 18120652
 * @Date Jun 10, 2020 - 3:11:26 PM 
 * @Description ...
 */
public class PanelSinhVien extends JPanel {
	private JTable danhSach = new JTable();

	/**
	 * Create the panel.
	 */
	public PanelSinhVien() {
		
		JPanel title = new JPanel();
		title.setBackground(Color.CYAN);
		title.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel titleContent = new JLabel("Qu\u1EA3n l\u00FD sinh vi\u00EAn");
		titleContent.setForeground(Color.BLACK);
		titleContent.setFont(new Font("Tahoma", Font.BOLD, 24));
		title.add(titleContent);
		
		List<SinhVien> danhSachSV = SinhVienDAO.danhSachSinhVien();
		String[][] rows = new String[danhSachSV.size()][5];
		int i = 0;
		for (SinhVien sv : danhSachSV) {
			rows[i][0] = sv.getMaSV();
			rows[i][1] =sv.getTenSV();
			rows[i][2] = sv.getLop().getMaLop();
			rows[i][3] = sv.getGioiTinh();
			rows[i][4] = sv.getCmnd();
			i++;
		}
		
		JPanel center = new JPanel();
		center.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Danh s\u00E1ch sinh vi\u00EAn", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		danhSach.setBorder(null);
		danhSach.setFont(new Font("Tahoma", Font.PLAIN, 14));
		danhSach.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		danhSach.setModel(new DefaultTableModel(rows, new String[] {
				"MSSV", "H\u1ECD t\u00EAn", "L\u1EDBp", "Gi\u1EDBi t\u00EDnh", "CMND"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		JScrollPane sp = new JScrollPane(danhSach);
		
		JPanel buttons = new JPanel();
		
		JButton btnThemSV = new JButton("Thêm sinh viên");
		btnThemSV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Main.setMainPanel(new PanelThemSV());
			}
		});
		btnThemSV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnXemDiem = new JButton("Xem điểm");
		btnXemDiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.setMainPanel(new PanelXemDiemSV());
			}
		});
		btnXemDiem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(title, GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addComponent(center, GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttons, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(title, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(buttons, GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
						.addComponent(center, GroupLayout.PREFERRED_SIZE, 441, Short.MAX_VALUE)))
		);
		GroupLayout gl_center = new GroupLayout(center);
		gl_center.setHorizontalGroup(
			gl_center.createParallelGroup(Alignment.LEADING)
				.addComponent(sp, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
		);
		gl_center.setVerticalGroup(
			gl_center.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_center.createSequentialGroup()
					.addGap(6)
					.addComponent(sp, GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE))
		);
		center.setLayout(gl_center);
		
		JButton btnDKHP = new JButton("Đăng kí học phần");
		btnDKHP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.setMainPanel(new PanelDKHP());
			}
		});
		btnDKHP.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnHuyHP = new JButton("Hủy học phần");
		btnHuyHP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.setMainPanel(new PanelHuyHP());
			}
		});
		btnHuyHP.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnNewButton = new JButton("Sửa điểm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.setMainPanel(new PanelSuaDiem());
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout gl_buttons = new GroupLayout(buttons);
		gl_buttons.setHorizontalGroup(
			gl_buttons.createParallelGroup(Alignment.LEADING)
				.addComponent(btnThemSV, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
				.addComponent(btnXemDiem, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
				.addComponent(btnNewButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
				.addComponent(btnDKHP, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
				.addComponent(btnHuyHP, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
		);
		gl_buttons.setVerticalGroup(
			gl_buttons.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_buttons.createSequentialGroup()
					.addGap(39)
					.addComponent(btnThemSV, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnXemDiem, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnDKHP, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnHuyHP, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addGap(166))
		);
		buttons.setLayout(gl_buttons);
		setLayout(groupLayout);

	}
}
