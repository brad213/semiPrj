package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.vo.ShopGetSet;

public class ShopModel {
	Connection con;
	public ShopModel() throws Exception {
		con=DBcon.getConnection();
	}
	public void insertShop(ShopGetSet vo, int count) throws Exception {
		con.setAutoCommit(false);//�ڵ�Ŀ���� ����
		
		//DB�� insert ��Ű��
		String sql1="INSERT INTO shop(CODE,BRAND,SEXES,GOODS,PRICE,DETAIL,imgfile) " + 
				"VALUES(seq_shop_ICODE.nextval,?,?,?,?,?,?)";
		
		String sql2="INSERT INTO shop2(shcoDE,ICODE) "
				+ "VALUES(SEQ_shop2_ICODE.nextval,SEQ_shop_ICODE.CURRVAL)";
		String sql3="select seq_shop_ICODE.currval from dual";
		
		PreparedStatement ps1=con.prepareStatement(sql1);
		ps1.setString(1, vo.getBrand());
		ps1.setString(2, vo.getSexes());
		ps1.setString(3, vo.getGoods());
		ps1.setString(4, vo.getPrice());
		ps1.setString(5, vo.getExp());
		ps1.setString(6, vo.getImgfile());
		
		
		PreparedStatement ps2=con.prepareStatement(sql2);
		
		
		PreparedStatement ps3=con.prepareStatement(sql3);
		
		
		int r1=ps1.executeUpdate();//sql1����
		int r2=ps2.executeUpdate();//sql2����
		ResultSet r3=ps3.executeQuery();//sql2����
		
		while (r3.next()) {
			System.out.println("������ī��Ʈ : "+r3.getInt("currval"));
		}
		
		if (r1!=1 || r2!=1) {//�ΰ��� sql������ �ϳ��� �����ϸ�
			con.rollback();
			System.out.println("�ѹ�");
		}
		con.commit();
		ps1.close();
		ps2.close();
		con.setAutoCommit(true);//�ڵ�Ŀ�� ��ȯ
	}
	public ArrayList searchShop(int idx, String str) throws Exception{
		//�˻����
		String[] key= {"BRAND","GOODS"};
		String sql="SELECT CODE, BRAND, SEXES, GOODS, PRICE " + 
				"FROM shop " + 
				"WHERE "+key[idx]+" LIKE '%"+str+"%'";
		PreparedStatement ps=con.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		ArrayList data=new ArrayList();
		while (rs.next()) {
			ArrayList temp=new ArrayList();
			temp.add(rs.getString("CODE"));
			temp.add(rs.getString("BRAND"));
			temp.add(rs.getString("SEXES"));
			temp.add(rs.getString("GOODS"));
			temp.add(rs.getString("PRICE"));
			data.add(temp);//arraylist�� arraylist�� �߰�
		}
		rs.close();
		ps.close();
		return data;
	}
	public ShopGetSet selectbyPk(int no) throws Exception {
		//jtable���� Ŭ���� ���ڵ��� ������ Shop Ÿ������ �����ؼ� return
		ShopGetSet vo=new ShopGetSet();
		String sql="SELECT * FROM shop WHERE CODE="+no;
		PreparedStatement ps=con.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while (rs.next()) {
			vo.setCode(Integer.parseInt(rs.getString("code")));		//����Ŭ ���ڿ� ���ڴ� int������ �ٲ���
			vo.setPrice(rs.getString("price"));
			vo.setGoods(rs.getString("goods"));
			vo.setSexes(rs.getString("sexes"));
			vo.setBrand(rs.getString("brand"));
			vo.setExp(rs.getString("detail"));
			//�̹��� ����
			vo.setImgfile(rs.getString("imgfile"));
			
		}
		rs.close();
		ps.close();
		
		return vo;
	}
	public void modifyShop(ShopGetSet vo) throws Exception {
		// ������ �����۾�
		String sql="UPDATE shop SET BRAND=?, " + 
				"SEXES=?, " + 
				"GOODS=?, " + 
				"PRICE=?, " + 
				"DETAIL=? " + 
				"WHERE CODE=?";
		
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1,vo.getBrand() );
		ps.setString(2,vo.getSexes());
		ps.setString(3,vo.getGoods() );
		ps.setString(4,vo.getPrice() );
		ps.setString(5,vo.getExp() );
		ps.setInt(6,vo.getCode() );
		
		ps.executeUpdate();//����
		ps.close();
		
	}
	public void deleteShop(String shco) throws Exception {
		con.setAutoCommit(false);
		String sql1="delete from shop where CODE=?";
		String sql2="delete from shop2 where CODE=?";
		
		PreparedStatement ps1=con.prepareStatement(sql1);
		ps1.setInt(1,Integer.parseInt(shco));
		PreparedStatement ps2=con.prepareStatement(sql2);
		ps2.setInt(1,Integer.parseInt(shco));
		
		//sql����
		int r2=ps2.executeUpdate();
		int r1=ps1.executeUpdate();
		
		if (r1!=1 || r2!=1) {
			con.rollback();
		}
		con.commit();
		
		ps1.close();
		ps2.close();
		con.setAutoCommit(true);
	}
	public ArrayList selectAll() throws Exception {
		String sql="SELECT CODE, BRAND, SEXES, GOODS, PRICE " + 
				"FROM shop";
				
		PreparedStatement ps=con.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		ArrayList data=new ArrayList();
		while (rs.next()) {
			ArrayList temp=new ArrayList();
			temp.add(rs.getString("CODE"));
			temp.add(rs.getString("BRAND"));
			temp.add(rs.getString("SEXES"));
			temp.add(rs.getString("GOODS"));
			temp.add(rs.getString("PRICE"));
			data.add(temp);//arraylist�� arraylist�� �߰�
		}
		rs.close();
		ps.close();
		return data;
	}
}
