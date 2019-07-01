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
//�˻����� ����
public class ShopInsertView extends Panel implements ActionListener{
	JTextField tfshopcode,tfshopBrand,tfshopGoods,tfshopPrice;
	JComboBox comshopSexes;
	JTextArea taShopContent;
	
	JCheckBox cbMultiInsert;
	JTextField tfInsertCount;
	
	JButton bShopInsert,bShopModify,bShopDelete;
	
	JComboBox comShopSearch;
	JTextField tfShopSearch;
	JTable tableShop;//jtable view����
	
	//�߰�
	ShopModel model;
	ShopTableModel tbModeShop;//jTable�𵨰���
	
	//�̹����۾��߰�
	JPanel picPanel;
	JLabel picLabel;
	
	JButton bChooseFile;
	JTextField jfPath;
	
	File f=null;
	String fName="";
	
	public ShopInsertView() {
		addLayout();//ȭ�鼳��
		//�ʱ�ȭ �۾�
		initStyle();
		eventProc();
		//db����
		connectDB();//ȣ��
	}
	void initStyle() {
		tfshopcode.setEditable(false);//��Ȱ��ȭ
		tfInsertCount.setEditable(false);
	}
	public void connectDB() {
		try {
			model=new ShopModel();
			System.out.println("�������Ἲ��");
		} catch (Exception e) {
			System.out.println("�����������");
		}
	}
	public void eventProc() {
		//���ű� ����
		cbMultiInsert.addActionListener(this);//�����԰�
		bShopDelete.addActionListener(this);
		bShopInsert.addActionListener(this);
		bShopModify.addActionListener(this);
		tfShopSearch.addActionListener(this);//�˻�
		//jTable�� ������ ����
		tableShop.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row=tableShop.getSelectedRow();
				int col=0;
				//Ŭ�����ڵ� ������ȣ ���
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
		// ���� ������ ����
		tfshopcode=new JTextField();
		tfshopBrand=new JTextField();
		tfshopGoods=new JTextField();
		tfshopPrice=new JTextField();
		
		String[] cbJanreStr= {"����","����","�Ƶ�","����"};
		comshopSexes=new JComboBox(cbJanreStr);
		taShopContent=new JTextArea();
		
		cbMultiInsert=new JCheckBox("�����԰�");
		tfInsertCount=new JTextField("1",5);
		
		//��ư
		bShopInsert=new JButton("�԰�");
		bShopModify=new JButton("����");
		bShopDelete=new JButton("����");
		
		String[] cbVideoSearch= {"�귣��","��ǰ��"};
		comShopSearch=new JComboBox(cbVideoSearch);
		tfShopSearch=new JTextField(15);
		//�����߰� ���� ���̺�ó��
		tbModeShop=new ShopTableModel();
		tableShop=new JTable(tbModeShop);
		tableShop.setModel(tbModeShop);
		
		picLabel=new JLabel();
		picPanel=new JPanel();
		
		
		//ȭ�鱸��
		//west �ǳ� ����
		JPanel p_west=new JPanel();
		p_west.setLayout(new BorderLayout());
		
		//���� ���
		JPanel p_west_center=new JPanel();
		p_west_center.setLayout(new BorderLayout());
		
		//���� ��� ����
		JPanel p_west_center_north=new JPanel();
		p_west_center_north.setLayout(new GridLayout(5, 2));
		p_west_center_north.add(new JLabel("��ǰ���"));
		p_west_center_north.add(tfshopcode);
		
		p_west_center_north.add(new JLabel("����"));
		p_west_center_north.add(comshopSexes);
		
		p_west_center_north.add(new JLabel("�귣��"));
		p_west_center_north.add(tfshopBrand);
		
		p_west_center_north.add(new JLabel("��ǰ��"));
		p_west_center_north.add(tfshopGoods);
		
		p_west_center_north.add(new JLabel("����"));
		p_west_center_north.add(tfshopPrice);
		
		
		//���� ��� ��� ���� ����
		JPanel p_west_center_center=new JPanel();
		p_west_center_center.setLayout(new GridLayout(0, 2));
		
		p_west_center_center.add(new JLabel("�󼼳���"));
		p_west_center_center.add(new JLabel("��ǰ����"));
		
		p_west_center_center.add(taShopContent);
		p_west_center_center.add(picLabel);
		//�̹��� ã�� ��ư ��� �߰�
		bChooseFile=new JButton("selFile");
		jfPath=new JTextField();
		p_west_center_center.add(jfPath);
		p_west_center_center.add(bChooseFile);
		//��ư�� ������ ����
		bChooseFile.addActionListener(this);
		
		
		//���� ��� �ǳڿ� �ΰ��� �ǳ��߰�
		p_west_center.add(p_west_center_north,BorderLayout.NORTH);
		p_west_center.add(p_west_center_center,BorderLayout.CENTER);
		
		p_west_center.setBorder(new TitledBorder("��ǰ�����Է�"));//��輱 �����
		
		//���� �Ʒ�
		JPanel p_west_south=new JPanel();
		p_west_south.setLayout(new GridLayout(2, 1));
		
		//���� �Ʒ��� ���� �ǳ�
		JPanel p_west_south_1=new JPanel();
		p_west_south_1.setLayout(new FlowLayout());
		p_west_south_1.add(cbMultiInsert);
		p_west_south_1.add(tfInsertCount);
		p_west_south_1.add(new JLabel("��"));
		p_west_south_1.setBorder(new TitledBorder("�����Է½ü���"));
		
		JPanel p_west_south_2=new JPanel();
		p_west_south_2.setLayout(new GridLayout(1, 3));
		p_west_south_2.add(bShopInsert);
		p_west_south_2.add(bShopModify);
		p_west_south_2.add(bShopDelete);
		
		p_west_south.add(p_west_south_1);
		p_west_south.add(p_west_south_2);
		
		p_west.add(p_west_center,BorderLayout.CENTER);
		p_west.add(p_west_south,BorderLayout.SOUTH);
		
		
		//east �ǳ� ����
		JPanel p_east=new JPanel();
		p_east.setLayout(new BorderLayout());
		
		//�������� ����
		JPanel p_east_north=new JPanel();
		p_east_north.add(comShopSearch);
		p_east_north.add(tfShopSearch);
		//��輱 �����
		p_east_north.setBorder(new TitledBorder("��ǰ�˻�"));
		p_east.add(p_east_north,BorderLayout.NORTH);
		p_east.add(new JScrollPane(tableShop),BorderLayout.CENTER);//
		
		setLayout(new GridLayout(1, 2));
		add(p_west);
		add(p_east);
	}
	
	class ShopTableModel extends AbstractTableModel{
		//Table�� �����ϱ����� ������ ����
		ArrayList data=new ArrayList();
		String [] columnNames= {"��ǰ�ڵ�","�귣��","����","��ǰ��","����"};
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
		if (evt==bShopInsert) {//�԰��ư�� ������
			fName=System.currentTimeMillis()+f.getName();//���� �̸��� �����´�.
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
			System.out.println("���ϼ���");
			JFileChooser jc=new JFileChooser();
			jc.showOpenDialog(this);
			f=jc.getSelectedFile();
			jfPath.setText(f.getPath());
		}
	}
	void fileSave(File file,String path,String name) {
		//���� ���ε�
		try {
			File f=new File(path);
			if (!f.exists()) {
				f.mkdir();//���������
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
						JOptionPane.showMessageDialog(null,"�����Ϸ�");
						tfshopcode.setText(null);
						tfshopPrice.setText(null);
						tfshopGoods.setText(null);
						tfshopBrand.setText(null);
						taShopContent.setText(null);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null,"��������");
						e1.printStackTrace();
					}//�����۾��޼ҵ� ȣ��
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
			JOptionPane.showMessageDialog(null,"�����Ϸ�");
			tfshopcode.setText(null);
			tfshopPrice.setText(null);
			tfshopGoods.setText(null);
			tfshopBrand.setText(null);
			taShopContent.setText(null);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null,"��������");
			e1.printStackTrace();
		}//�����۾��޼ҵ� ȣ��
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
		//Ŭ�� ���ڵ带 textfield�� �Ѹ���
		tfshopcode.setText(String.valueOf(vo.getCode()));
		tfshopBrand.setText(vo.getBrand());
		tfshopGoods.setText(vo.getGoods());
		tfshopPrice.setText(vo.getPrice());
		taShopContent.setText(vo.getExp());
		comshopSexes.setSelectedItem(vo.getSexes());
		
		//�̹��� �Ѹ��� ó��
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
		
		//�̹��� vo�� ����
		vo.setImgfile(fName);
		int count=Integer.parseInt(tfInsertCount.getText());
		
		try {
			model.insertShop(vo,count);
			JOptionPane.showMessageDialog(null, "�԰�Ϸ�");
			//textâ ���������
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
			JOptionPane.showMessageDialog(null, "�԰����");
			e1.printStackTrace();
		}
	}
}
