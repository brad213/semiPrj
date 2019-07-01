package LoginView;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;


public class JoinView extends JPanel {

	public JoinView() {
		setSize(500, 300);
	
		//레이아웃 지정
		setLayout(new BorderLayout());
		
		//판넬생성
		JPanel panel1=new JPanel();
		JPanel panel2=new JPanel();
		JPanel panel3=new JPanel();
		
		//판넬1
		panel1.setLayout(new GridLayout(1,0) );
		JLabel label1=new JLabel("아이디");

		//판넬2
		panel2.setLayout(new GridLayout(1, 0) );
		JTextField field1=new JTextField(30);
		JButton btn1=new JButton("중복확인");
		
		//판넬3
		panel3.setLayout(new GridLayout(6, 0) );
		JLabel label2=new JLabel("비밀번호");
		JTextField field2=new JTextField();
		JLabel label3=new JLabel("비밀번호재확인");
		JTextField field3=new JTextField();
		JLabel label4=new JLabel("직급");
		JTextField field4=new JTextField();
		JLabel label5=new JLabel("휴대전화번호");
		JTextField field5=new JTextField();
		
	
		JButton b6=new JButton("");
		//버튼을 판넬에 부착
		
		panel1.add(label1);
	
		panel2.add(field1);
		panel2.add(btn1);
		panel3.add(label2);
		panel3.add(field2);
		panel3.add(label3);
		panel3.add(field3);
		panel3.add(label4);
		panel3.add(field4);
		panel3.add(label5);
		panel3.add(field5);
	
		//panel1,2,3 위치 부착
		add(panel1,BorderLayout.NORTH);
		add(panel2,BorderLayout.CENTER);
		add(panel3,BorderLayout.SOUTH);


		
		setVisible(true);
	}
//	public static void main(String[] args) {
//		Join b=new Join();
//	}
}
