package QuanLySinhVien.MainClass;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import view.Lop.LopTab;
import view.SinhVien.SinhVienTab;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 * QuanLySinhVien.MainClass
 * @Created by DELL - StudentID: 18120652
 * @Date Jun 10, 2020 - 1:55:19 PM 
 * @Description ...
 */
public class Main extends JFrame {

	private JPanel contentPane;
	
	private static JPanel mainContainer;
	private static JPanel mainPanel = new JPanel();
	
	private JPanel trangChuDb = new JPanel();
	private JPanel sinhVienDb = new JPanel();
	private JPanel lopDb = new JPanel();
	private JPanel monHocDb = new JPanel();
	
	/**
	 * Launch the application.
	 */
	
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
		trangChuDb.setBackground(Color.WHITE);
		sinhVienDb.setBackground(Color.WHITE);
		lopDb.setBackground(Color.WHITE);
		monHocDb.setBackground(Color.WHITE);
	}
	
	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultLookAndFeelDecorated(true);
		
		setTitle("Ch\u01B0\u01A1ng tr\u00ECnh qu\u1EA3n l\u00FD sinh vi\u00EAn");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 811, 518);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel title = new JPanel();
		title.setBackground(Color.ORANGE);
		
		JLabel titleContent = new JLabel("CH\u01AF\u01A0NG TR\u00CCNH QU\u1EA2N L\u00DD SINH VI\u00CAN");
		title.add(titleContent);
		titleContent.setBackground(Color.WHITE);
		titleContent.setForeground(Color.WHITE);
		titleContent.setHorizontalAlignment(SwingConstants.CENTER);
		titleContent.setFont(new Font("Times New Roman", Font.BOLD, 24));
		
		JPanel dashboardContainer = new JPanel();
		dashboardContainer.setBackground(Color.WHITE);
		
		trangChuDb.setBackground(Color.CYAN);
		trangChuDb.setLayout(new BorderLayout(0, 0));
		
		JLabel trangChuLb = new JLabel("Trang ch\u1EE7");
		trangChuLb.setHorizontalAlignment(SwingConstants.CENTER);
		trangChuLb.setFont(new Font("Tahoma", Font.BOLD, 14));
		trangChuDb.add(trangChuLb, BorderLayout.CENTER);
		
		trangChuDb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setDefaultDashboardColor();
				trangChuDb.setBackground(Color.CYAN);
			}
		});
		
		sinhVienDb.setBackground(Color.WHITE);
		sinhVienDb.setLayout(new BorderLayout(0, 0));
		
		JLabel sinhVienLb = new JLabel("Qu\u1EA3n l\u00FD sinh vi\u00EAn");
		sinhVienLb.setHorizontalAlignment(SwingConstants.CENTER);
		sinhVienLb.setFont(new Font("Tahoma", Font.BOLD, 14));
		sinhVienDb.add(sinhVienLb);
		
		sinhVienDb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setDefaultDashboardColor();
				sinhVienDb.setBackground(Color.CYAN);
				setMainPanel(new SinhVienTab());
			}
		});
		
		lopDb.setBackground(Color.WHITE);
		lopDb.setLayout(new BorderLayout(0, 0));
		
		JLabel lopLb = new JLabel("Qu\u1EA3n l\u00FD l\u1EDBp");
		lopLb.setHorizontalAlignment(SwingConstants.CENTER);
		lopLb.setFont(new Font("Tahoma", Font.BOLD, 14));
		lopDb.add(lopLb);
		
		lopDb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setDefaultDashboardColor();
				lopDb.setBackground(Color.CYAN);
				setMainPanel(new LopTab());
			}
		});
		
		monHocDb.setBackground(Color.WHITE);
		monHocDb.setLayout(new BorderLayout(0, 0));
		
		JLabel monHocLb = new JLabel("Qu\u1EA3n l\u00FD m\u00F4n h\u1ECDc");
		monHocLb.setHorizontalAlignment(SwingConstants.CENTER);
		monHocLb.setFont(new Font("Tahoma", Font.BOLD, 14));
		monHocDb.add(monHocLb);
		
		monHocDb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setDefaultDashboardColor();
				monHocDb.setBackground(Color.CYAN);
			}
		});
		
		mainContainer = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(title, GroupLayout.DEFAULT_SIZE, 785, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(dashboardContainer, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(mainContainer, GroupLayout.DEFAULT_SIZE, 619, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(title, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(mainContainer, GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
						.addComponent(dashboardContainer, GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE))
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
		mainContainer.add(mainPanel);
		dashboardContainer.setLayout(gl_dashboardContainer);
		contentPane.setLayout(gl_contentPane);
	}
}
