package LoginView;

import PanelMember.paneMember;
import secondView.TableTwinView;

public class MainProcess{
    LoginView loginView;
    TestFrm testFrm;
    paneMember vs;
    TableTwinView mt;
   
    public static void main(String[] args) {
       
        // ����Ŭ���� ����
        MainProcess main = new MainProcess();
        main.loginView = new LoginView(); // �α���â ���̱�
        main.loginView.setMain(main); // �α���â���� ���� Ŭ����������
    }
    
    // �׽�Ʈ������â
    public void showFrameTest(){
        loginView.dispose(); // �α���â�ݱ�
        //this.testFrm = new TestFrm(); // �׽�Ʈ������ ����
        this.vs = new paneMember();		//
        try {
			this.mt = new TableTwinView();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
    }
 
}