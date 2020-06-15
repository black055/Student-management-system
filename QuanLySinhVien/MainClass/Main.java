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
	private static JPanel mainPanel = new PanelDangNhap();
	
	private JPanel trangChuDb = new JPanel();
	private JPanel sinhVienDb = new JPanel();
	private JPanel lopDb = new JPanel();
	private JPanel monHocDb = new JPanel();
	
	/**
	 * Launch the application.
	 */
	
	public static TaiKhoan getTaiKhoan() {
		return taiKhoan;
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
	
	private void setDefaultDashboardColor() {
		trangChuDb.setBackground(Color.DARK_GRAY);
		sinhVienDb.setBackground(Color.DARK_GRAY);
		lopDb.setBackground(Color.DARK_GRAY);
		monHocDb.setBackground(Color.DARK_GRAY);
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
		
		JPanel dashboardContainer = new JPanel();
		dashboardContainer.setBackground(Color.DARK_GRAY);
		
		trangChuDb.setBackground(Color.GRAY);
		trangChuDb.setLayout(new BorderLayout(0, 0));
		
		JLabel trangChuLb = new JLabel("Trang ch\u1EE7");
		trangChuLb.setForeground(Color.WHITE);
		trangChuLb.setIcon(new ImageIcon("src\\icon\\home-icon.png"));
		trangChuLb.setHorizontalAlignment(SwingConstants.LEFT);
		trangChuLb.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		trangChuDb.add(trangChuLb, BorderLayout.CENTER);
		
		trangChuDb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setDefaultDashboardColor();
				trangChuDb.setBackground(Color.GRAY);
				Main.setMainPanel(new PanelDangNhap());
			}
		});
		
		sinhVienDb.setBackground(Color.DARK_GRAY);
		sinhVienDb.setLayout(new BorderLayout(0, 0));
		
		JLabel sinhVienLb = new JLabel("Qu\u1EA3n l\u00FD sinh vi\u00EAn");
		sinhVienLb.setForeground(Color.WHITE);
		sinhVienLb.setIcon(new ImageIcon("src\\icon\\student-icon.png"));
		sinhVienLb.setHorizontalAlignment(SwingConstants.LEFT);
		sinhVienLb.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		sinhVienDb.add(sinhVienLb);
		
		sinhVienDb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setDefaultDashboardColor();
				sinhVienDb.setBackground(Color.GRAY);
				setMainPanel(new PanelSinhVien());
			}
		});
		
		lopDb.setBackground(Color.DARK_GRAY);
		lopDb.setLayout(new BorderLayout(0, 0));
		
		JLabel lopLb = new JLabel("Qu\u1EA3n l\u00FD l\u1EDBp");
		lopLb.setForeground(Color.WHITE);
		lopLb.setIcon(new ImageIcon("src\\icon\\class-icon.png"));
		lopLb.setHorizontalAlignment(SwingConstants.LEFT);
		lopLb.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lopDb.add(lopLb);
		
		lopDb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setDefaultDashboardColor();
				lopDb.setBackground(Color.GRAY);
				setMainPanel(new PanelLop());
			}
		});
		
		monHocDb.setBackground(Color.DARK_GRAY);
		monHocDb.setLayout(new BorderLayout(0, 0));
		
		JLabel monHocLb = new JLabel("Qu\u1EA3n l\u00FD m\u00F4n h\u1ECDc");
		monHocLb.setForeground(Color.WHITE);
		monHocLb.setIcon(new ImageIcon("src\\icon\\course-icon.png"));
		monHocLb.setHorizontalAlignment(SwingConstants.LEFT);
		monHocLb.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		monHocDb.add(monHocLb);
		
		monHocDb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setDefaultDashboardColor();
				monHocDb.setBackground(Color.GRAY);
				setMainPanel(new PanelMonHoc());
			}
		});
		
		mainContainer = new JPanel();
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
		GroupLayout gl_dashboardContainer = new GroupLayout(dashboardContainer);
		gl_dashboardContainer.setHorizontalGroup(
			gl_dashboardContainer.createParallelGroup(Alignment.LEADING)
				.addComponent(trangChuDb, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
				.addComponent(sinhVienDb, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
				.addComponent(lopDb, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
				.addComponent(monHocDb, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
		);
		gl_dashboardContainer.setVerticalGroup(
			gl_dashboardContainer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_dashboardContainer.createSequentialGroup()
					.addContainerGap()
					.addComponent(trangChuDb, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(sinhVienDb, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lopDb, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(monHocDb, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(86, Short.MAX_VALUE))
		);
		mainContainer.setLayout(new BorderLayout(0, 0));
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
		dashboardContainer.setLayout(gl_dashboardContainer);
		contentPane.setLayout(gl_contentPane);
	}
}
