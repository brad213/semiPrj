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
		String[] headings=new String[] {"�ֹ���ȣ","�ֹ�����","�޴���","��ǰ��","�ݾ�","��������"};
		
		Object[][] data=new Object[][] {
			{"1","2015-01-01","��ä��","����Ű(��������)","100000","ī��"},
			{"2","2017-05-05","������","�Ҵ�(�����)","300000","����"},
			{"3","2018-12-12","û��","�����(���ǽ�)","500000","������ü"}
		};
	
		JPanel panel=new JPanel();
		JPanel panel1=new JPanel();
		JPanel panel2=new JPanel();
		
		panel.setLayout(new BorderLayout());
		
		
		JLabel label=new JLabel("��ǰ���� �����ͺ��̽�");

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

