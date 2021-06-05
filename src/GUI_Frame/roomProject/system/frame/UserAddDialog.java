package GUI_Frame.roomProject.system.frame;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import bean.UserBean;
import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoComboBox;
import com.lovo.netCRM.component.LovoTitleLabel;
import com.lovo.netCRM.component.LovoTxt;
import service.IUserService;
import service.impl.UserServiceImpl;

/**
 * 
 * 宿舍管理系统
 * @author 张成峰
 * @version 1.0
 * @see  
 * @description 添加用户对话框
 */
public class UserAddDialog extends JDialog{

	/**用户名文本框*/
	private LovoTxt nameTxt = new LovoTxt("用户名",40,90,this);
	/**真实姓名文本框*/
	private LovoTxt realNameTxt = new LovoTxt("真实姓名",40,140,this);
	/**等级*/
	private LovoComboBox<String> gradeComboBox = new LovoComboBox<String>("用户等级",
			new String[] {"班主任","房间管理员","系统管理员","超级管理员"}, 40, 190, this);
	/**用户主面板*/
	private UserPanel userPanel;

	/**用户组件*/
	private IUserService service = new UserServiceImpl();
	
	public UserAddDialog(JFrame jf,UserPanel userPanel){
		super(jf,true);
		this.userPanel = userPanel;
		this.setLayout(null);
		
		this.init();
		
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) screensize.getWidth() / 2 - 180;
		int y = (int) screensize.getHeight() / 2 - 200;
		this.setBounds(x, y, 360, 400);
		this.setVisible(true);
	}
	/**
	 * 初始化
	 *
	 */
	private void init(){
		LovoTitleLabel titleLabel = new LovoTitleLabel("添加用户",this);
		titleLabel.setLocation(150, titleLabel.getY());
		
		LovoButton addButton = new LovoButton(30,240,"添加","image/add.png",this);
		addButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				boolean isOk = addUser();
				if(isOk){
					UserAddDialog.this.dispose();
				}
			}});
		
		LovoButton cancelButton = new LovoButton(200,240,"取消","image/cancel.png",this);
		cancelButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				UserAddDialog.this.dispose();
			}});
	}
	
	//--------------------------------------------

	/**
	 * 添加用户
	 * @return 验证是否成功
	 */
	private boolean addUser(){
		String str = "";
		//验证数据
//		if(!this.nameTxt.getText().matches("[a-zA-Z\\u4e00-\\u9fa5]{2,20}")){
//			str += "用户名必须为二位以上字母或汉字\n";
//		}
		
		//……

		UserBean userBean=new UserBean();
		userBean.setUserName(nameTxt.getText());
		userBean.setRealName(realNameTxt.getText());
		userBean.setUserGrade(gradeComboBox.getItem());
		service.addUser(userBean);

		if(userBean.getUserPwd()==null){
			JOptionPane.showMessageDialog(null,"添加失败，请重新输入" );
			return false;
		}
		
		
//		更新数据,显示添加结果
		this.userPanel.initData();
		return true;
	}
}
