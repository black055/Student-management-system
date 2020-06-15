package QuanLySinhVien.MainClass;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import pojo.TaiKhoan;
import view.Lop.PanelLop;
import view.MonHoc.PanelMonHoc;
import view.SinhVien.PanelSinhVien;
import view.TrangChu.PanelDangNhap;
import view.TrangChu.PanelTrangChu;
import view.dashboard.DangNhapDashboard;
import view.dashboard.GiaoVuDashboard;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.BevelBorder;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

/**
 * QuanLySinhVien.MainClass
 * @Created by DELL - StudentID: 18120652
 * @Date Jun 10, 2020 - 1:55:19 PM 
 * @Description ...
 */
public class Main extends JFrame {

	private static TaiKhoan taiKhoan = null;
	
	private JPanel contentPane;
	
	private static JPanel mainContainer;
	private static JPanel mainPanel = new JPanel();
	private static JPanel dashboard = new JPanel();
	private static JPanel dashboardContainer;
	
	//private JPanel trangChuDb = new JPanel();
	//private JPanel sinhVienDb = new JPanel();
	//private JPanel lopDb = new JPanel();
	//private JPanel monHocDb = new JPanel();
	
	/**
	 * Launch the application.
	 */
	
	public static TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}
	
	public static void setTaiKhoan(TaiKhoan tk) {
		taiKhoan = tk;
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	public static JPanel getMainPanel() {
		return mainPanel;
	}
	
	public static void setMainPanel(JPanel newMainPanel) {
		mainContainer.remove(mainPanel);
		mainPanel = newMainPanel;
		mainContainer.add(mainPanel, BorderLayout.CENTER);
		mainContainer.revalidate();
		mainContainer.repaint();
	}
	
	/*private void setDefaultDashboardColor() {
		trangChuDb.setBackground(Color.DARK_GRAY);
		sinhVienDb.setBackground(Color.DARK_GRAY);
		lopDb.setBackground(Color.DARK_GRAY);
		monHocDb.setBackground(Color.DARK_GRAY);
	}*/
	
	public static void setDashboard(JPanel newDb) {
		dashboardContainer.remove(dashboard);
		dashboard = newDb;
		dashboardContainer.add(dashboard, BorderLayout.CENTER);
		dashboardContainer.revalidate();
		dashboardContainer.repaint();
	}
	
	/**
	 * Create the frame.
	 */
	public Main() {
		setBackground(Color.CYAN);
		setDefaultLookAndFeelDecorated(true);
		
		setTitle("Ch\u01B0\u01A1ng tr\u00ECnh qu\u1EA3n l\u00FD sinh vi\u00EAn");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 811, 518);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		
		mainContainer = new JPanel();
		dashboardContainer = new JPanel();
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(dashboardContainer, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(mainContainer, GroupLayout.PREFERRED_SIZE, 619, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(mainContainer, GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
						.addComponent(dashboardContainer, GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE))
					.addGap(0))
		);
		
		dashboardContainer.setLayout(new BorderLayout(0, 0));
		dashboard = new DangNhapDashboard();
		dashboardContainer.add(dashboard, BorderLayout.CENTER);
		
		mainContainer.setLayout(new BorderLayout(0, 0));
		mainPanel = new PanelDangNhap();
		mainPanel.setBackground(new Color(245, 245, 245));
		mainContainer.add(mainPanel, BorderLayout.CENTER);
		
		JPanel title = new JPanel();
		title.setBorder(new LineBorder(new Color(0, 0, 0)));
		mainContainer.add(title, BorderLayout.NORTH);
		title.setBackground(new Color(245, 245, 245));
		
		JLabel titleContent = new JLabel("CH\u01AF\u01A0NG TR\u00CCNH QU\u1EA2N L\u00DD SINH VI\u00CAN");
		title.add(titleContent);
		titleContent.setBackground(Color.WHITE);
		titleContent.setForeground(Color.DARK_GRAY);
		titleContent.setHorizontalAlignment(SwingConstants.CENTER);
		titleContent.setFont(new Font("Times New Roman", Font.BOLD, 24));
		contentPane.setLayout(gl_contentPane);
	}
}
