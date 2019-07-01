package PanelMember;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import LoginView.JoinView;
import secondView.TableTwinView;

import view.TableView;
import view.ShopInsertView;

public class paneMember extends JFrame{
	ShopInsertView shop;
	TableView table;
	JoinView join;
	
	public paneMember() {
		shop=new ShopInsertView();//객체생성
		table=new TableView();
		join=new JoinView();
		
		//탭추가
		JTabbedPane pane=new JTabbedPane();
		pane.add("상품정보", table);
		pane.add("상품관리", shop);
		pane.add("회원가입", join);
		pane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int num = pane.getSelectedIndex();
				if(num == 3) {
					setSize(500,500);
				}else {
					setSize(800,600);
				}
			}
		});

		pane.setSelectedIndex(0);
		add("Center",pane);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800,600);
		setVisible(true);
	}
}
