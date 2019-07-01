package view;


import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import PanelMember.paneMember;


public class TableView extends JPanel {
	public TableView() {
		String[] headings=new String[] {"주문번호","주문일자","받는자","상품명","금액","결제상태"};
		
		Object[][] data=new Object[][] {
			{"1","2015-01-01","임채웅","나이키(에어조단)","100000","카드"},
			{"2","2017-05-05","아이유","소니(헤드폰)","300000","현금"},
			{"3","2018-12-12","청아","프라다(원피스)","500000","계좌이체"}
		};
	
		JPanel panel=new JPanel();
		JPanel panel1=new JPanel();
		JPanel panel2=new JPanel();
		
		panel.setLayout(new BorderLayout());
		
		
		JLabel label=new JLabel("상품정보 데이터베이스");

		panel1.add(label);
		
		JTable table=new JTable(data,headings);
		table.setPreferredScrollableViewportSize(new Dimension(700, 600));
		table.setFillsViewportHeight(true);
		
		panel2.add(new JScrollPane(table));

		panel.add(panel1, BorderLayout.NORTH);
		panel.add(panel2, BorderLayout.CENTER);
		add(panel);
	}
}

