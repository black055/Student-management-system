package view.SinhVien;

import java.awt.Color;
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
import dao.LopDAO;
import dao.MonHocDAO;
import pojo.BangDiem;
import pojo.Lop;
import pojo.MonHoc;
import pojo.SinhVien;
import view.Lop.PanelLop;

/**
 * view.SinhVien
 * @Created by DELL - StudentID: 18120652
 * @Date Jun 15, 2020 - 7:41:53 PM 
 * @Description ...
 */
public class PanelKetQuaHocTap extends JPanel {
	public PanelKetQuaHocTap(SinhVien sv) {
			setBackground(new Color(245, 245, 245));
		
		JPanel title = new JPanel();
		title.setBackground(new Color(245, 245, 245));
		
		List<Lop> dsLop = LopDAO.danhSachLop();
		String[] classList = new String[dsLop.size()];
		int index = 0;
		for (Lop lop : dsLop)  {
			classList[index++] = lop.getMaLop();
		}
		
		JScrollPane sp = new JScrollPane();
		
		JButton btnQuayVe = new JButton("Quay v\u1EC1");
		btnQuayVe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.setMainPanel(new PanelLop());
			}
		});
		btnQuayVe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lbName = new JLabel("Họ tên: " + sv.getTenSV());
		lbName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNewLabel = new JLabel("MSSV: " + sv.getMaSV());
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNewLabel_1 = new JLabel("L\u1EDBp: " + sv.getLop().getMaLop());
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(506, Short.MAX_VALUE)
					.addComponent(btnQuayVe, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(18)
					.addComponent(lbName)
					.addGap(164)
					.addComponent(lblNewLabel_1)
					.addContainerGap(34, Short.MAX_VALUE))
				.addComponent(sp, GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
				.addComponent(title, GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(title, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_1)
						.addComponent(lbName))
					.addGap(13)
					.addComponent(sp, GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnQuayVe, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addGap(6))
		);
		
		List<BangDiem> bangDiem = BangDiemDAO.ketQuaHocTap(sv.getMaSV());
		String[][] rows = new String[bangDiem.size()][7];
		int i = 0;
		for (BangDiem bd : bangDiem) {
			rows[i][0] = bd.getMaMH();
			MonHoc mh = MonHocDAO.thongTinMonHoc(bd.getMaMH());
			rows[i][1] = mh.getTenMH();
			rows[i][2] = String.valueOf(bd.getDiemGK());
			rows[i][3] = String.valueOf(bd.getDiemCK());
			rows[i][4] = String.valueOf(bd.getDiemKhac());
			rows[i][5] = String.valueOf(bd.getDiemTong());
			rows[i][6] = (bd.getDiemTong() >= 5) ? "ĐẬU" : "RỚT";
			i++;
		}
		
		JTable table = new JTable();
		table.setModel(new DefaultTableModel(rows,
			new String[] {
				"M\u00E3 m\u00F4n h\u1ECDc", "T\u00EAn m\u00F4n h\u1ECDc", "\u0110i\u1EC3m GK", "\u0110i\u1EC3m CK", "\u0110i\u1EC3m kh\u00E1c", "\u0110i\u1EC3m t\u1ED5ng", "K\u1EBFt qu\u1EA3"
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
		
		JLabel titleContent = new JLabel("K\u1EBFt qu\u1EA3 h\u1ECDc t\u1EADp");
		titleContent.setFont(new Font("Tahoma", Font.BOLD, 24));
		titleContent.setHorizontalAlignment(SwingConstants.CENTER);
		title.add(titleContent);
		setLayout(groupLayout);
	}
}
