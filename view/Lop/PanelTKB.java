package view.Lop;

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
import dao.LopDAO;
import pojo.Lop;
import pojo.MonHoc;
import pojo.SinhVien;
import java.awt.Color;

/**
 * view.Lop
 * @Created by DELL - StudentID: 18120652
 * @Date Jun 13, 2020 - 12:34:29 PM 
 * @Description ...
 */
public class PanelTKB extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public PanelTKB() {
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
				.addComponent(title, GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(59)
					.addComponent(lblLop)
					.addGap(18)
					.addComponent(classBox, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
					.addComponent(btnSubmit, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
					.addGap(58))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(423, Short.MAX_VALUE)
					.addComponent(btnQuayVe, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addComponent(sp, GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
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
					.addComponent(sp, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnQuayVe, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addGap(6))
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 m\u00F4n h\u1ECDc", "T\u00EAn m\u00F4n h\u1ECDc", "Ph\u00F2ng h\u1ECDc"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		sp.setViewportView(table);
		
		JLabel titleContent = new JLabel("Xem th\u1EDDi kh\u00F3a bi\u1EC3u");
		titleContent.setFont(new Font("Tahoma", Font.BOLD, 24));
		titleContent.setHorizontalAlignment(SwingConstants.CENTER);
		title.add(titleContent);
		setLayout(groupLayout);

		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedClass = (String) classBox.getSelectedItem();
				Lop selected = LopDAO.thongTinLop(selectedClass);
				Set<MonHoc> danhSachMH = selected.getDsMH();
				String[][] rows = new String[danhSachMH.size()][5];
				int i = 0;
				for (MonHoc mh : danhSachMH) {
					rows[i][0] = mh.getMaMH();
					rows[i][1] = mh.getTenMH();
					rows[i][2] = mh.getPhongHoc();
					i++;
				}
				
				table.setModel(new DefaultTableModel(rows,
					new String[] {
							"M\u00E3 m\u00F4n h\u1ECDc", "T\u00EAn m\u00F4n h\u1ECDc", "Ph\u00F2ng h\u1ECDc"
					}
				));
			}
		});
	}
}
