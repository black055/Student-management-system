package view.Lop;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;

import QuanLySinhVien.MainClass.Main;
import dao.BangDiemDAO;
import dao.LopDAO;
import dao.MonHocDAO;
import dao.SinhVienDAO;
import dao.TaiKhoanDAO;
import pojo.BangDiem;
import pojo.Lop;
import pojo.MonHoc;
import pojo.SinhVien;
import pojo.TaiKhoan;

import javax.swing.JButton;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

/**
 * view.Lop
 * @Created by DELL - StudentID: 18120652
 * @Date Jun 12, 2020 - 7:15:54 PM 
 * @Description ...
 */
public class PanelLop extends JPanel {

	/**
	 * Create the panel.
	 */
	
	private boolean validateClassListFile(File selectedFile) {
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
				row = bf.readLine();
				if (!row.contentEquals("STT,MSSV,Họ tên,Giới tính,CMND"))
					return false;
				row = bf.readLine();
				do {
					if (row.split(",").length != 5)
						return false;
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
	
	private int readClassList(File selectedFile) {
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
				String maLop = bf.readLine();
				String row = bf.readLine();
				Lop lop = LopDAO.thongTinLop(maLop);
				if (lop == null) {
					lop = new Lop(maLop);
					LopDAO.themLop(lop);
				}
				row = bf.readLine();
				do {
					String[] info = row.split(",");
					Set<MonHoc> dsMH = new HashSet<MonHoc>(lop.getDsMH());
					boolean success = SinhVienDAO.themSinhVien(new SinhVien(info[1], info[2], info[3], info[4], lop, dsMH));
					
					if (success == true) {
						TaiKhoanDAO.themTaiKhoan(new TaiKhoan(info[1], info[1], false));
						
						for (MonHoc mh : dsMH) {
							BangDiem bd = new BangDiem(info[1], mh.getMaMH());
							BangDiemDAO.suaBangDiem(bd);
						}
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
	
	private boolean validateScheduleFile(File selectedFile) {
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
				row = bf.readLine();
				if (!row.contentEquals("STT,Mã môn,Tên môn,Phòng học"))
					return false;
				row = bf.readLine();
				do {
					if (row.split(",").length != 4)
						return false;
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
	
	private int readScheduleFile(File selectedFile) {
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
				String maLop = bf.readLine();
				String row = bf.readLine();
				Lop lop = LopDAO.thongTinLop(maLop);
				if (lop == null) {
					lop = new Lop(maLop);
					LopDAO.themLop(lop);
				}
				row = bf.readLine();
				do {
					String[] info = row.split(",");
					Set<SinhVien> dsSV = new HashSet<SinhVien>(lop.getDsSV());
					boolean success = MonHocDAO.themMonHoc(new MonHoc(info[1], info[2], info[3], lop, dsSV));
					
					if (success == true) {
						for (SinhVien sv : dsSV) {
							BangDiem bd = new BangDiem(sv.getMaSV(), info[1]);
							BangDiemDAO.suaBangDiem(bd);
						}
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
	
	public PanelLop() {
		setBackground(new Color(245, 245, 245));
		
		JPanel title = new JPanel();
		title.setBackground(new Color(245, 245, 245));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 245, 245));
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
					if (validateClassListFile(fc.getSelectedFile())) {
						int result = readClassList(fc.getSelectedFile());
						if (result == -1) {
							JOptionPane.showMessageDialog(new JFrame(), "Có lỗi xảy ra trong quá trình import\n Vui lòng kiểm tra lại định dạng file", "Thêm thất bại!",JOptionPane.ERROR_MESSAGE);
						} else {
							String message = "Import danh sách lớp thành công!\n";
							message += "Thêm " + result + " sinh viên vào danh sách\n";
							JOptionPane.showMessageDialog(new JFrame(), message, "Thêm thành công!",JOptionPane.INFORMATION_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(new JFrame(), "File không đúng định dạng", "Thêm thất bại!",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnImportClassList.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnViewClassList = new JButton("Xem danh sách lớp");
		btnViewClassList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.setMainPanel(new PanelDSLop());
			}
		});
		btnViewClassList.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnViewSchedule = new JButton("Xem thời khóa biểu");
		btnViewSchedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.setMainPanel(new PanelTKB());
			}
		});
		btnViewSchedule.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnImportSchedule = new JButton("Import thời khóa biểu");
		btnImportSchedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				int select = fc.showOpenDialog(Main.getMainPanel());
				if (select == JFileChooser.APPROVE_OPTION) {
					if (validateScheduleFile(fc.getSelectedFile())) {
						int result = readScheduleFile(fc.getSelectedFile());
						if (result == -1) {
							JOptionPane.showMessageDialog(new JFrame(), "Có lỗi xảy ra trong quá trình import\n Vui lòng kiểm tra lại định dạng file", "Thêm thất bại!",JOptionPane.ERROR_MESSAGE);
						} else {
							String message = "Import thời khóa biểu thành công!\n";
							message += "Thêm " + result + " môn học vào danh sách\n";
							JOptionPane.showMessageDialog(new JFrame(), message, "Thêm thành công!",JOptionPane.INFORMATION_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(new JFrame(), "File không đúng định dạng", "Thêm thất bại!",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
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
