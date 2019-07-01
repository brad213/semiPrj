package secondView;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
//bshop ���̺�
//b_Code NUMBER primary key,
//b_Date DATE,
//b_Receive VARCHAR2(30),
//b_Goods VARCHAR2(30),
//b_Price NUMBER,
//b_Payment VARCHAR2(20) );
class MyFrame extends JFrame {
	// ����â�� ���� ������Ʈ
	JTextField b_Code, b_Date, b_Receive, b_Goods, b_Price, b_Payment;
	JButton privBtn, nextBtn, insertBtn, delBtn, searchBtn, clearBtn;
	ResultSet rs;
	Statement stmt;
	
	public MyFrame() throws SQLException {
		setTitle("��ǰ���� ���� ");
		Connection con = makeCon();// ����
		// stmt=con.createStatement();
		stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE,
				ResultSet.CONCUR_UPDATABLE);
		String sql = "select * from bshop";
		rs = stmt.executeQuery(sql);

		setLayout(new GridLayout(0, 2));

		add(new JLabel("b_Code", JLabel.CENTER));
		add(b_Code= new JTextField());
		add(new JLabel("b_Date", JLabel.CENTER));
		add(b_Date = new JTextField());
		add(new JLabel("b_Receive", JLabel.CENTER));
		add(b_Receive = new JTextField());
		add(new JLabel("b_Goods", JLabel.CENTER));
		add(b_Goods = new JTextField());
		add(new JLabel("b_Price", JLabel.CENTER));
		add(b_Price = new JTextField());
		add(new JLabel("b_Payment", JLabel.CENTER));
		add(b_Payment = new JTextField());

		privBtn = new JButton("privBtn");
		privBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					rs.previous();
					b_Code.setText("" + rs.getInt("b_Code"));
					b_Date.setText("" + rs.getString("b_Date"));
					b_Receive.setText("" + rs.getString("b_Receive"));
					b_Goods.setText("" + rs.getString("b_Goods"));
					b_Price.setText("" + rs.getString("b_Price"));
					b_Payment.setText("" + rs.getString("b_Payment"));
				} catch (SQLException e1) {
					System.out.println("first data");
				}

			}
		});

		nextBtn = new JButton("nextBtn");
		nextBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					rs.next();
					b_Code.setText("" + rs.getInt("b_Code"));
					b_Date.setText("" + rs.getString("b_Date"));
					b_Receive.setText("" + rs.getString("b_Receive"));
					b_Goods.setText("" + rs.getString("b_Goods"));
					b_Price.setText("" + rs.getString("b_Price"));
					b_Payment.setText("" + rs.getString("b_Payment"));

				} catch (SQLException e1) {
					System.out.println("last data");
				} // ���� ���ڵ�

			}
		});
		insertBtn = new JButton("insertBtn");
		insertBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String str = "insert into bshop values(?,?,?,?,?,?)";
					PreparedStatement pstmt = con.prepareStatement(str);
					pstmt.setInt(1, Integer.parseInt(b_Code.getText()));
					pstmt.setString(2, b_Date.getText());
					pstmt.setString(3, b_Receive.getText());
					pstmt.setString(4, b_Goods.getText());
					pstmt.setInt(5, Integer.parseInt(b_Price.getText()));
					pstmt.setString(6, b_Payment.getText());
					// ����
					pstmt.executeUpdate();
					System.out.println("insert done!!!");
					pstmt.close();
					rs = stmt.executeQuery(sql);// db���� rs�� �����Ͱ�������
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}

			}
		});
		delBtn = new JButton("delBtn");
		delBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String str = "DELETE FROM bshop WHERE b_Code=?";
					PreparedStatement pstmt = con.prepareStatement(str);
					pstmt.setInt(1, Integer.parseInt(b_Code.getText()));

					// ����
					int r = pstmt.executeUpdate();
					if (r == 1) {
						System.out.println("delete done!!!");
					}
					pstmt.close();
					rs = stmt.executeQuery(sql);// db���� rs�� �����Ͱ�������
				} catch (SQLException e1) {
				
					e1.printStackTrace();
				}

			}
		});
		clearBtn = new JButton("clearBtn");
		clearBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				b_Code.setText("");
				b_Date.setText("");
				b_Receive.setText("");
				b_Goods.setText("");
				b_Price.setText("");
				b_Payment.setText("");
				
			}
		});
		searchBtn = new JButton("searchBtn");
		searchBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					Statement stmt=con.createStatement();
					String mna=b_Code.getText();
					String sql="SELECT * FROM bshop WHERE b_Code="+mna;
					ResultSet rs=stmt.executeQuery(sql);

					while (rs.next()) {
						b_Code.setText("" + rs.getInt("b_Code"));
						b_Date.setText("" + rs.getString("b_Date"));
						b_Receive.setText("" + rs.getString("b_Receive"));
						b_Goods.setText("" + rs.getString("b_Goods"));
						b_Price.setText("" + rs.getString("b_Price"));
						b_Payment.setText("" + rs.getString("b_Payment"));

					}
					System.out.println(b_Code.getText()+"search done!!!");
					stmt.close();

				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}

			}
		});
		add(nextBtn);
		add(privBtn);
		add(insertBtn);
		add(clearBtn);
		add(delBtn);
		add(searchBtn);
		setSize(350, 200);
		setVisible(true);
	}
	private static Connection makeCon() {
		// db����
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String user = "scott";
		String pass = "123456";
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, user, pass);
			System.out.println("���Ἲ��");
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		return con;
	}
}

public class TableTwinView {
	public TableTwinView() throws Exception {
	
		MyFrame fm = new MyFrame();
	}
}