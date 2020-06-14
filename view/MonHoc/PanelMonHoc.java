package view.MonHoc;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import QuanLySinhVien.MainClass.Main;
import dao.BangDiemDAO;
import dao.LopDAO;
import dao.MonHocDAO;
import dao.SinhVienDAO;
import pojo.BangDiem;
import pojo.Lop;
import pojo.MonHoc;
import pojo.SinhVien;

/**
 * view.MonHoc
 * @Created by DELL - StudentID: 18120652
 * @Date Jun 14, 2020 - 11:07:32 PM 
 * @Description ...
 */
public class PanelMonHoc extends JPanel {

	/**
	 * Create the panel.
	 */
	private boolean validateResultFile(File selectedFile) {
		BufferedReader bf = null;
		try {
			bf = new BufferedReader(new InputStreamReader(new FileInputStream(selectedFile), "utf8"));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		if (bf != null) {
			try {
				String row = bf.readLine();
				MonHoc temp = MonHocDAO.thongTinMonHoc(row.split("-")[1]);
				if (temp == null)
					return false;
				else {
					if (!temp.getLop().getMaLop().equals(row.split("-")[0]))
						return false;
				}
				row = bf.readLine();
				if (!row.contentEquals("STT,MSSV,Họ tên,Điểm GK,Điểm CK,Điểm khác,Điểm tổng"))
					return false;
				row = bf.readLine();
				do {
					String[] info = row.split(",");
					if (info.length != 7)
						return false;
					try {
						Float.parseFloat(info[3]);
						Float.parseFloat(info[4]);
						Float.parseFloat(info[5]);
						Float.parseFloat(info[6]);
					} catch (NumberFormatException ex) {
						return false;
					}
					row = bf.readLine();
				} while (row != null);
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
	
	private int readResultFile(File selectedFile) {
		BufferedReader bf = null;
		try {
			bf = new BufferedReader(new InputStreamReader(new FileInputStream(selectedFile), "utf8"));
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		int count = 0;
		if (bf != null) {
			try {
				String maMH = bf.readLine();
				maMH = maMH.split("-")[1];
				String row = bf.readLine();
				
				row = bf.readLine();
				do {
					String[] info = row.split(",");
					
					String maSV = info[1];
					float diemGK = Float.parseFloat(info[3]),
							diemCK = Float.parseFloat(info[4]),
							diemKhac = Float.parseFloat(info[5]),
							diemTong = Float.parseFloat(info[6]);
					
					boolean success = BangDiemDAO.suaBangDiem(new BangDiem(maSV, maMH, diemGK, diemCK, diemKhac, diemTong));
					
					if (success == true) {
						count++;
					}
					
					row = bf.readLine();
				} while (row != null);
			} catch (IOException ex) {
				System.out.println(ex.getMessage());
			}
		}
		try {
			bf.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return count;
	}
	
	public PanelMonHoc() {
		
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
		
		JButton btnViewResult = new JButton("Xem bảng điểm môn học");
		btnViewResult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.setMainPanel(new PanelBangDiem());
			}
		});
		btnViewResult.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnViewClassList = new JButton("Xem danh sách lớp của môn học");
		btnViewClassList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.setMainPanel(new PanelDSMonHoc());
			}
		});
		btnViewClassList.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnImport = new JButton("Import bảng điểm");
		btnImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				int select = fc.showOpenDialog(Main.getMainPanel());
				if (select == JFileChooser.APPROVE_OPTION) {
					if (validateResultFile(fc.getSelectedFile())) {
						int result = readResultFile(fc.getSelectedFile());
						if (result == -1) {
							JOptionPane.showMessageDialog(new JFrame(), "Có lỗi xảy ra trong quá trình import\n Vui lòng kiểm tra lại định dạng file", "Thêm thất bại!",JOptionPane.ERROR_MESSAGE);
						} else {
							String message = "Import bảng điểm thành công!\n";
							message += "Thêm điểm của " + result + " sinh viên vào hệ thống\n";
							JOptionPane.showMessageDialog(new JFrame(), message, "Thêm thành công!",JOptionPane.INFORMATION_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(new JFrame(), "File không đúng định dạng hoặc không tồn tại môn học", "Thêm thất bại!",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnImport.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(85)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnImport, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
						.addComponent(btnViewResult, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
						.addComponent(btnViewClassList, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE))
					.addGap(75))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(37)
					.addComponent(btnViewClassList, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
					.addGap(43)
					.addComponent(btnViewResult, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
					.addGap(30)
					.addComponent(btnImport, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
					.addGap(49))
		);
		panel.setLayout(gl_panel);
		
		JLabel titleContent = new JLabel("Quản lý môn học");
		titleContent.setFont(new Font("Tahoma", Font.BOLD, 24));
		title.add(titleContent);
		setLayout(groupLayout);

	}
}
