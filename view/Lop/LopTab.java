package view.Lop;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;

import QuanLySinhVien.MainClass.Main;
import dao.LopDAO;
import dao.SinhVienDAO;
import pojo.Lop;
import pojo.MonHoc;
import pojo.SinhVien;

import javax.swing.JButton;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * view.Lop
 * @Created by DELL - StudentID: 18120652
 * @Date Jun 12, 2020 - 7:15:54 PM 
 * @Description ...
 */
public class LopTab extends JPanel {

	/**
	 * Create the panel.
	 */
	
	private boolean readClassList(File selectedFile) {
		BufferedReader bf = null;
		try {
			bf = new BufferedReader(new InputStreamReader(new FileInputStream(selectedFile), "utf8"));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		if (bf != null) {
			try {
				String maLop = bf.readLine();
				String row = bf.readLine();
				Lop lop = LopDAO.thongTinLop(maLop);
				if (lop == null) {
					lop = new Lop(maLop);
				}
				row = bf.readLine();
				do {
					String[] info = row.split(",");
					
					lop.getDsSV().add(new SinhVien(info[1], info[2], info[3], info[4], lop, lop.getDsMH()));
					row = bf.readLine();
				} while (row != null);
				LopDAO.themHoacCapNhat(lop);
			} catch (IOException ex) {
				System.out.println(ex.getMessage());
			}
		}
		try {
			bf.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return true;
	}
	
	public LopTab() {
		
		JPanel title = new JPanel();
		
		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(title, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addComponent(title, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE))
		);
		
		JButton btnImportClassList = new JButton("Import danh sách lớp");
		btnImportClassList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				int select = fc.showOpenDialog(Main.getMainPanel());
				if (select == JFileChooser.APPROVE_OPTION) {
					boolean result = readClassList(fc.getSelectedFile());
					if (result == false) {
						JOptionPane.showMessageDialog(new JFrame(), "Có lỗi xảy ra trong quá trình import\n Vui lòng kiểm tra lại định dạng file", "Thêm thất bại!",JOptionPane.ERROR_MESSAGE);
					} else {
						String message = "Import danh sách lớp thành công!\n";
						message += "Thêm thành công " + result + " sinh viên.";
						JOptionPane.showMessageDialog(new JFrame(), message, "Thêm thành công!",JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		btnImportClassList.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnViewClassList = new JButton("Xem danh sách lớp");
		btnViewClassList.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnViewSchedule = new JButton("Xem thời khóa biểu");
		btnViewSchedule.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnImportSchedule = new JButton("Import thời khóa biểu");
		btnImportSchedule.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addGap(85)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnViewClassList, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
						.addComponent(btnImportSchedule, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
						.addComponent(btnViewSchedule, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
						.addComponent(btnImportClassList, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE))
					.addGap(75))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addGap(37)
					.addComponent(btnViewClassList, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(btnImportClassList, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(btnViewSchedule, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(btnImportSchedule, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
					.addGap(37))
		);
		panel.setLayout(gl_panel);
		
		JLabel titleContent = new JLabel("Qu\u1EA3n l\u00FD l\u1EDBp");
		titleContent.setFont(new Font("Tahoma", Font.BOLD, 24));
		title.add(titleContent);
		setLayout(groupLayout);

	}
}
