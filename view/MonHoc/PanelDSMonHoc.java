package view.MonHoc;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Set;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import QuanLySinhVien.MainClass.Main;
import dao.BangDiemDAO;
import dao.MonHocDAO;
import dao.SinhVienDAO;
import pojo.BangDiem;
import pojo.MonHoc;
import pojo.SinhVien;
import view.Lop.PanelLop;

/**
 * view.MonHoc
 * @Created by DELL - StudentID: 18120652
 * @Date Jun 14, 2020 - 11:17:23 PM 
 * @Description ...
 */
public class PanelDSMonHoc extends JPanel {
	private JTable table;
	/**
	 * Create the panel.
	 */
	public PanelDSMonHoc() {
		
		JPanel title = new JPanel();
		
		JLabel lbMonHoc = new JLabel("M\u00D4N H\u1ECCC:");
		lbMonHoc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		List<MonHoc> dsMH = MonHocDAO.danhSachMonHoc();
		String[] courseList = new String[dsMH.size()];
		int index = 0;
		for (MonHoc mh : dsMH)  {
			courseList[index++] = mh.getMaMH() + " - " + mh.getTenMH();
		}
		JComboBox courseBox = new JComboBox(courseList);
		
		JButton btnSubmit = new JButton("Xem danh s\u00E1ch");
		
		JScrollPane sp = new JScrollPane();
		
		JButton btnQuayVe = new JButton("Quay v\u1EC1");
		btnQuayVe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.setMainPanel(new PanelLop());
			}
		});
		btnQuayVe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(title, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lbMonHoc)
					.addGap(18)
					.addComponent(courseBox, 0, 223, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(btnSubmit)
					.addContainerGap())
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(342, Short.MAX_VALUE)
					.addComponent(btnQuayVe, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addComponent(sp, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(title, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addGap(13)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(courseBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSubmit)
						.addComponent(lbMonHoc))
					.addGap(11)
					.addComponent(sp, GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnQuayVe)
					.addGap(10))
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"MSSV", "H\u1ECD t\u00EAn", "Gi\u1EDBi t\u00EDnh", "CMND"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		sp.setViewportView(table);
		
		JLabel titleContent = new JLabel("Xem bảng điểm");
		titleContent.setFont(new Font("Tahoma", Font.BOLD, 24));
		titleContent.setHorizontalAlignment(SwingConstants.CENTER);
		title.add(titleContent);
		setLayout(groupLayout);

		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedCourse = (String) courseBox.getSelectedItem();
				selectedCourse = selectedCourse.split(" - ")[0];
				MonHoc selected = MonHocDAO.thongTinMonHoc(selectedCourse);
				Set<SinhVien> danhSachSV = selected.getDsSV();
				String[][] rows = new String[danhSachSV.size()][5];
				int i = 0;
				for (SinhVien sv : danhSachSV) {
					rows[i][0] = sv.getMaSV();
					rows[i][1] =sv.getTenSV();
					rows[i][2] = sv.getGioiTinh();
					rows[i][3] = sv.getCmnd();
					i++;
				}
				
				table.setModel(new DefaultTableModel(rows,
					new String[] {
							"MSSV", "H\u1ECD t\u00EAn", "Gi\u1EDBi t\u00EDnh", "CMND"
					}
				));
			}
		});
	}
}
