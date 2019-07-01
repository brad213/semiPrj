package LoginView;

import PanelMember.paneMember;
import secondView.TableTwinView;

public class MainProcess{
    LoginView loginView;
    TestFrm testFrm;
    paneMember vs;
    TableTwinView mt;
   
    public static void main(String[] args) {
       
        // 메인클래스 실행
        MainProcess main = new MainProcess();
        main.loginView = new LoginView(); // 로그인창 보이기
        main.loginView.setMain(main); // 로그인창에게 메인 클래스보내기
    }
    
    // 테스트프레임창
    public void showFrameTest(){
        loginView.dispose(); // 로그인창닫기
        //this.testFrm = new TestFrm(); // 테스트프레임 오픈
        this.vs = new paneMember();		//
        try {
			this.mt = new TableTwinView();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
    }
 
}