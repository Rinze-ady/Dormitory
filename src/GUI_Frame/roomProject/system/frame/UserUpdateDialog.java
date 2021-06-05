package GUI_Frame.roomProject.system.frame;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import bean.UserBean;
import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoComboBox;
import com.lovo.netCRM.component.LovoLabel;
import com.lovo.netCRM.component.LovoTitleLabel;
import service.IUserService;
import service.impl.UserServiceImpl;

/**
 * 
 * 宿舍管理系统
 * @author 张成峰
 * @version 1.0
 * @see  
 * @description 修改用户对话框
 */
public class UserUpdateDialog extends JDialog{

	/**用户名标签*/
	private LovoLabel nameLabel = new LovoLabel("用户名",40,90,this);
	/**真实姓名文本框*/
	private LovoLabel realNameLabel = new LovoLabel("真实姓名",40,140,this);
	/**等级*/
	private LovoComboBox<String> gradeComboBox = new LovoComboBox<String>("用户等级",
			new String[] {"班主任","房间管理员","系统管理员","超级管理员"}, 40, 190, this);
	/**用户主面板*/
	private UserPanel userPanel;
	/**要修改的用户id*/
	private int userId;

	/**用户组件*/
	private IUserService service = new UserServiceImpl();

	
	public UserUpdateDialog(JFrame jf,int userId,UserPanel userPanel){
		super(jf,true);
		this.userId = userId;
		this.userPanel = userPanel;
		this.setLayout(null);
		
		this.init();
		this.initUser(userId);
		
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) screensize.getWidth() / 2 - 200;
		int y = (int) screensize.getHeight() / 2 - 200;
		this.setBounds(x, y, 400, 400);
		this.setVisible(true);
	}
	/**
	 * 初始化
	 *
	 */
	private void init(){
		LovoTitleLabel titleLabel = new LovoTitleLabel("用户等级修改",this);
		titleLabel.setLocation(150, titleLabel.getY());
		
		LovoButton addButton = new LovoButton(30,240,"修改","image/update.png",this);
		addButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				updateUser(userId);
				UserUpdateDialog.this.dispose();
			}});
		
		LovoButton cancelButton = new LovoButton(200,240,"取消","image/cancel.png",this);
		cancelButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				UserUpdateDialog.this.dispose();
			}});
	}
	
	//--------------------------------------------
	/**
	 * 初始化用户信息
	 * @param userId 用户ID
	 */
	private void initUser(int userId) {
		UserBean userBean=new UserBean();
		userBean=service.findUserById(userId);
		nameLabel.setText(userBean.getUserName());
		realNameLabel.setText(userBean.getRealName());
		gradeComboBox.setItem(userBean.getUserGrade());
	}

	/**
	 * 修改用户等级
	 * @param userId 用户ID
	 */
	private void updateUser(int userId){
		//数据验证
		service.updateUser(userId,gradeComboBox.getItem());
		
//		更新数据,显示添加结果
		this.userPanel.initData();
	}
}
