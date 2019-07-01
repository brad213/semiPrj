package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.attribute.FileStoreAttributeView;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import model.ShopModel;
import model.vo.ShopGetSet;
//검색까지 구현
public class ShopInsertView extends Panel implements ActionListener{
	JTextField tfshopcode,tfshopBrand,tfshopGoods,tfshopPrice;
	JComboBox comshopSexes;
	JTextArea taShopContent;
	
	JCheckBox cbMultiInsert;
	JTextField tfInsertCount;
	
	JButton bShopInsert,bShopModify,bShopDelete;
	
	JComboBox comShopSearch;
	JTextField tfShopSearch;
	JTable tableShop;//jtable view역할
	
	//추가
	ShopModel model;
	ShopTableModel tbModeShop;//jTable모델관련
	
	//이미지작업추가
	JPanel picPanel;
	JLabel picLabel;
	
	JButton bChooseFile;
	JTextField jfPath;
	
	File f=null;
	String fName="";
	
	public ShopInsertView() {
		addLayout();//화면설계
		//초기화 작업
		initStyle();
		eventProc();
		//db연결
		connectDB();//호출
	}
	void initStyle() {
		tfshopcode.setEditable(false);//비활성화
		tfInsertCount.setEditable(false);
	}
	public void connectDB() {
		try {
			model=new ShopModel();
			System.out.println("비디오연결성공");
		} catch (Exception e) {
			System.out.println("비디오연결실패");
		}
	}
	public void eventProc() {
		//수신기 부착
		cbMultiInsert.addActionListener(this);//다중입고
		bShopDelete.addActionListener(this);
		bShopInsert.addActionListener(this);
		bShopModify.addActionListener(this);
		tfShopSearch.addActionListener(this);//검색
		//jTable에 리스너 부착
		tableShop.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row=tableShop.getSelectedRow();
				int col=0;
				//클릭레코드 비디오번호 얻기
				String data=(String) tableShop.getValueAt(row, col);
				int no=Integer.parseInt(data);
				
				try {
					ShopGetSet vo=model.selectbyPk(no);
					selectbyPk(vo);
				} catch (Exception e1) {
				
					e1.printStackTrace();
				}
			}
		});
		
	}

	private void addLayout() {
		// 탭의 디자인 구성
		tfshopcode=new JTextField();
		tfshopBrand=new JTextField();
		tfshopGoods=new JTextField();
		tfshopPrice=new JTextField();
		
		String[] cbJanreStr= {"남성","여성","아동","공용"};
		comshopSexes=new JComboBox(cbJanreStr);
		taShopContent=new JTextArea();
		
		cbMultiInsert=new JCheckBox("다중입고");
		tfInsertCount=new JTextField("1",5);
		
		//버튼
		bShopInsert=new JButton("입고");
		bShopModify=new JButton("수정");
		bShopDelete=new JButton("삭제");
		
		String[] cbVideoSearch= {"브랜드","상품명"};
		comShopSearch=new JComboBox(cbVideoSearch);
		tfShopSearch=new JTextField(15);
		//나중추가 엑셀 테이블처럼
		tbModeShop=new ShopTableModel();
		tableShop=new JTable(tbModeShop);
		tableShop.setModel(tbModeShop);
		
		picLabel=new JLabel();
		picPanel=new JPanel();
		
		
		//화면구성
		//west 판넬 구성
		JPanel p_west=new JPanel();
		p_west.setLayout(new BorderLayout());
		
		//왼쪽 가운데
		JPanel p_west_center=new JPanel();
		p_west_center.setLayout(new BorderLayout());
		
		//왼쪽 가운데 위쪽
		JPanel p_west_center_north=new JPanel();
		p_west_center_north.setLayout(new GridLayout(5, 2));
		p_west_center_north.add(new JLabel("상품등록"));
		p_west_center_north.add(tfshopcode);
		
		p_west_center_north.add(new JLabel("성별"));
		p_west_center_north.add(comshopSexes);
		
		p_west_center_north.add(new JLabel("브랜드"));
		p_west_center_north.add(tfshopBrand);
		
		p_west_center_north.add(new JLabel("상품명"));
		p_west_center_north.add(tfshopGoods);
		
		p_west_center_north.add(new JLabel("가격"));
		p_west_center_north.add(tfshopPrice);
		
		
		//왼쪽 가운데 가운데 비디오 설명
		JPanel p_west_center_center=new JPanel();
		p_west_center_center.setLayout(new GridLayout(0, 2));
		
		p_west_center_center.add(new JLabel("상세내용"));
		p_west_center_center.add(new JLabel("제품사진"));
		
		p_west_center_center.add(taShopContent);
		p_west_center_center.add(picLabel);
		//이미지 찾기 버튼 경로 추가
		bChooseFile=new JButton("selFile");
		jfPath=new JTextField();
		p_west_center_center.add(jfPath);
		p_west_center_center.add(bChooseFile);
		//버튼에 리스너 부착
		bChooseFile.addActionListener(this);
		
		
		//왼쪽 가운데 판넬에 두개의 판넬추가
		p_west_center.add(p_west_center_north,BorderLayout.NORTH);
		p_west_center.add(p_west_center_center,BorderLayout.CENTER);
		
		p_west_center.setBorder(new TitledBorder("상품정보입력"));//경계선 만들기
		
		//왼쪽 아래
		JPanel p_west_south=new JPanel();
		p_west_south.setLayout(new GridLayout(2, 1));
		
		//왼쪽 아래에 사용될 판넬
		JPanel p_west_south_1=new JPanel();
		p_west_south_1.setLayout(new FlowLayout());
		p_west_south_1.add(cbMultiInsert);
		p_west_south_1.add(tfInsertCount);
		p_west_south_1.add(new JLabel("개"));
		p_west_south_1.setBorder(new TitledBorder("다중입력시선택"));
		
		JPanel p_west_south_2=new JPanel();
		p_west_south_2.setLayout(new GridLayout(1, 3));
		p_west_south_2.add(bShopInsert);
		p_west_south_2.add(bShopModify);
		p_west_south_2.add(bShopDelete);
		
		p_west_south.add(p_west_south_1);
		p_west_south.add(p_west_south_2);
		
		p_west.add(p_west_center,BorderLayout.CENTER);
		p_west.add(p_west_south,BorderLayout.SOUTH);
		
		
		//east 판넬 구성
		JPanel p_east=new JPanel();
		p_east.setLayout(new BorderLayout());
		
		//오른쪽의 위쪽
		JPanel p_east_north=new JPanel();
		p_east_north.add(comShopSearch);
		p_east_north.add(tfShopSearch);
		//경계선 만들기
		p_east_north.setBorder(new TitledBorder("상품검색"));
		p_east.add(p_east_north,BorderLayout.NORTH);
		p_east.add(new JScrollPane(tableShop),BorderLayout.CENTER);//
		
		setLayout(new GridLayout(1, 2));
		add(p_west);
		add(p_east);
	}
	
	class ShopTableModel extends AbstractTableModel{
		//Table를 구성하기위한 데이터 세팅
		ArrayList data=new ArrayList();
		String [] columnNames= {"상품코드","브랜드","성별","상품명","가격"};
		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		@Override
		public int getRowCount() {
			return data.size();
		}

		@Override
		public Object getValueAt(int row, int col) {
			ArrayList temp=(ArrayList) data.get(row);
			return temp.get(col);
		}
		public String getColumnName(int col) {
			return columnNames[col];
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object evt=e.getSource();
		if (evt==bShopInsert) {//입고버튼이 눌리면
			fName=System.currentTimeMillis()+f.getName();//파일 이름을 가져온다.
			insertVideo(fName);
			System.out.println("f : "+f);
			System.out.println("fname : "+fName);
			fileSave(f,".//upload2",fName);
			
		}else if (evt==tfShopSearch) {
			searchVideo();
		}else if(evt==bShopModify) {
			modify();
		}else if (evt==bShopDelete) {
			delete();
		}else if(evt==bChooseFile) {
			System.out.println("파일선택");
			JFileChooser jc=new JFileChooser();
			jc.showOpenDialog(this);
			f=jc.getSelectedFile();
			jfPath.setText(f.getPath());
		}
	}
	void fileSave(File file,String path,String name) {
		//파일 업로드
		try {
			File f=new File(path);
			if (!f.exists()) {
				f.mkdir();//폴더만들기
			}
			String filePath=path+"\\"+name;
			FileInputStream fis=new FileInputStream(file);
			FileOutputStream fos=new FileOutputStream(filePath);
			
			int i=0;
			byte[] buffer=new byte[1024];
			while ((i=fis.read(buffer,0,1024))!=-1) {
				fos.write(buffer,0,i);
			}
			fis.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void delete() {
					String shco=tfshopcode.getText();
					
					try {
						model.deleteShop(shco);
						JOptionPane.showMessageDialog(null,"삭제완료");
						tfshopcode.setText(null);
						tfshopPrice.setText(null);
						tfshopGoods.setText(null);
						tfshopBrand.setText(null);
						taShopContent.setText(null);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null,"삭제실패");
						e1.printStackTrace();
					}//수정작업메소드 호출
	}
	private void modify() {
		ShopGetSet vo=new ShopGetSet();
		vo.setCode(Integer.parseInt(tfshopcode.getText()));
		vo.setBrand(tfshopBrand.getText());
		vo.setPrice(tfshopPrice.getText());
		vo.setGoods(tfshopGoods.getText());
		vo.setExp(taShopContent.getText());
		vo.setSexes((String)comshopSexes.getSelectedItem());
		
		try {
			model.modifyShop(vo);
			JOptionPane.showMessageDialog(null,"수정완료");
			tfshopcode.setText(null);
			tfshopPrice.setText(null);
			tfshopGoods.setText(null);
			tfshopBrand.setText(null);
			taShopContent.setText(null);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null,"수정실패");
			e1.printStackTrace();
		}//수정작업메소드 호출
	}
	private void searchVideo() {
		int idx=comShopSearch.getSelectedIndex();
		String str=tfShopSearch.getText();
		
		try {
			ArrayList data=model.searchShop(idx,str);
			tbModeShop.data=data;//ShopTableModel

			tbModeShop.fireTableDataChanged();
			
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
	}
	ImageIcon icon;
	void selectbyPk(ShopGetSet vo) {
		//클릭 레코드를 textfield에 뿌리기
		tfshopcode.setText(String.valueOf(vo.getCode()));
		tfshopBrand.setText(vo.getBrand());
		tfshopGoods.setText(vo.getGoods());
		tfshopPrice.setText(vo.getPrice());
		taShopContent.setText(vo.getExp());
		comshopSexes.setSelectedItem(vo.getSexes());
		
		//이미지 뿌리기 처리
		System.out.println(vo.getImgfile());
		icon=new ImageIcon(".\\upload2\\"+vo.getImgfile());
		ImageIcon newIcon;
		Image image=icon.getImage();
		image.getScaledInstance(picLabel.getWidth(), picLabel.getHeight(), 0);
		int imgW=picLabel.getWidth();
		int imgH=picLabel.getHeight();
		Image img=icon.getImage();
		Image newimg=img.getScaledInstance(imgW, imgH, java.awt.Image.SCALE_SMOOTH);
		newIcon=new ImageIcon(newimg);
		picLabel.setIcon(newIcon);
	}
	
	private void insertVideo(String fName) {
		ShopGetSet vo=new ShopGetSet();
		vo.setSexes((String) comshopSexes.getSelectedItem());
		vo.setPrice(tfshopPrice.getText());
		vo.setGoods(tfshopGoods.getText());
		vo.setExp(taShopContent.getText());
		vo.setBrand(tfshopBrand.getText());
		
		//이미지 vo에 셋팅
		vo.setImgfile(fName);
		int count=Integer.parseInt(tfInsertCount.getText());
		
		try {
			model.insertShop(vo,count);
			JOptionPane.showMessageDialog(null, "입고완료");
			//text창 내용지우기
			tfshopcode.setText(null);
			tfshopPrice.setText(null);
			tfshopGoods.setText(null);
			tfshopBrand.setText(null);
			taShopContent.setText(null);
			//////
			ArrayList data=model.selectAll();
			tbModeShop.data=data;//ShopTableModel
			tbModeShop.fireTableDataChanged();
			
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "입고실패");
			e1.printStackTrace();
		}
	}
}
