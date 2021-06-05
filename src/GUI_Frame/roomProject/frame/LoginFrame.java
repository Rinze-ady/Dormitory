package GUI_Frame.roomProject.frame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import bean.UserBean;
import com.lovo.netCRM.component.LovoImageLabel;
import com.lovo.netCRM.component.LovoPassWordTxt;
import com.lovo.netCRM.component.LovoTxt;
import service.IUserService;
import service.impl.UserServiceImpl;

/**
 * 
 * 宿舍管理系统
 * @author 张成峰
 * @version 1.0
 * @see  
 * @description 登陆窗体
 */
public class LoginFrame extends JFrame{
	/**用户名文本框*/
	private LovoTxt userTxt = new LovoTxt("",260,240,this);
	/**密码文本框*/
	private LovoPassWordTxt pwdTxt = new LovoPassWordTxt("",260,300,this);

	/**用户服务组件*/
	protected IUserService service = new UserServiceImpl();
	
	public LoginFrame(){
		this.setLayout(null);
		LovoImageLabel userImg = new LovoImageLabel(280,220,50,50,this,"image/user.png");
		userImg.setBorder(null);
		
		LovoImageLabel pwdImg = new LovoImageLabel(280,280,50,50,this,"image/pwd.png");
		pwdImg.setBorder(null);
		
		LovoImageLabel enterImg = new LovoImageLabel(420,340,50,50,this);
		enterImg.setBorder(null);
		enterImg.setImage("image/login.png");

		enterImg.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				login();
			}
		});
		
		LovoImageLabel crmImg = new LovoImageLabel(0,0,840,570,this);
		crmImg.setImage("image/back.png");
		
		this.setSize(840,570);
		this.setVisible(true);
		this.setDefaultCloseOperation(3);
		this.setLocationRelativeTo(null);
	}
	
	//--------------------
	/**
	 * 登陆方法
	 */
	private void login(){
		//成功后,进入主界面
		//String userName = this.userTxt.getText();
		UserBean user = service.logIn(this.userTxt.getText(), this.pwdTxt.getText());
		if(this.userTxt.getText().equals(user.getUserName())){

			this.dispose();
			new MainFrame(user);
		}else{
			JOptionPane.showMessageDialog(null,"登录失败，请重新输入");
		}


	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new LoginFrame();
	}

}
