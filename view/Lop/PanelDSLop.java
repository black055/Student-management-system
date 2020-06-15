package view.Lop;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.util.List;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import QuanLySinhVien.MainClass.Main;
import dao.LopDAO;
import dao.SinhVienDAO;
import pojo.Lop;
import pojo.SinhVien;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

/**
 * view.Lop
 * @Created by DELL - StudentID: 18120652
 * @Date Jun 13, 2020 - 12:06:58 PM 
 * @Description ...
 */
public class PanelDSLop extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public PanelDSLop() {
		setBackground(new Color(245, 245, 245));
		
		JPanel title = new JPanel();
		title.setBackground(new Color(245, 245, 245));
		
		JLabel lblLop = new JLabel("L\u1EDAP:");
		lblLop.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		List<Lop> dsLop = LopDAO.danhSachLop();
		String[] classList = new String[dsLop.size()];
		int index = 0;
		for (Lop lop : dsLop)  {
			classList[index++] = lop.getMaLop();
		}
		JComboBox classBox = new JComboBox(classList);
		
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
				.addComponent(title, GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(59)
					.addComponent(lblLop)
					.addGap(18)
					.addComponent(classBox, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
					.addComponent(btnSubmit, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
					.addGap(58))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(374, Short.MAX_VALUE)
					.addComponent(btnQuayVe, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addComponent(sp, GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(title, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addGap(13)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(classBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLop)
						.addComponent(btnSubmit))
					.addGap(11)
					.addComponent(sp, GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
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
				String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		sp.setViewportView(table);
		
		JLabel titleContent = new JLabel("Xem danh s\u00E1ch l\u1EDBp");
		titleContent.setFont(new Font("Tahoma", Font.BOLD, 24));
		titleContent.setHorizontalAlignment(SwingConstants.CENTER);
		title.add(titleContent);
		setLayout(groupLayout);

		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedClass = (String) classBox.getSelectedItem();
				Lop selected = LopDAO.thongTinLop(selectedClass);
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
