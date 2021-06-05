package GUI_Frame.roomProject.system.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import bean.UserBean;
import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoTable;
import com.lovo.netCRM.component.LovoTitleLabel;
import service.IUserService;
import service.impl.UserServiceImpl;

/**
 * 
 * 宿舍管理系统
 * @author 张成峰
 * @version 1.0
 * @see  
 * @description 用户管理面板
 */
public class UserPanel extends JPanel{
	/**用户表格组件*/
	private LovoTable userTable;
	/**窗体组件*/
	private JFrame jf;
	/**登陆用户*/
	private UserBean loginUser;

	/**用户组件*/
	private IUserService service = new UserServiceImpl();

	public UserPanel(UserBean loginUser,JFrame jf){
		this.jf = jf; 
		this.loginUser = loginUser;
		this.setLayout(null);
		this.init();
	}
	/**
	 * 初始化
	 *
	 */
	private void init() {
		new LovoTitleLabel("用 户 管 理",this);
		this.initTable();
		this.initButton();
		this.initData();
	}
	/**
	 * 初始化数据
	 */
	public void initData(){
		this.updateUserTable();
	}
	/**
	 * 初始化按钮
	 *
	 */
	private void initButton() {
		LovoButton addButton = new LovoButton(50,500,"添加用户","image/add.png",this);
		addButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				new UserAddDialog(jf,UserPanel.this);
			}});
		
		LovoButton delButton = new LovoButton(250,500,"删除用户","image/del.png",this);
		delButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				int key = userTable.getKeyByInt();
				if(key == -1){
					JOptionPane.showMessageDialog(null,"请选择行");
					return;
				}
				
				delUser(key,loginUser);
			}});
		
		
		LovoButton updateButton = new LovoButton(450,500,"用户等级修改","image/update.png",this);
		updateButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				int key = userTable.getKeyByInt();
				if(key == -1){
					JOptionPane.showMessageDialog(null,"请选择行");
					return;
				}
				
				new UserUpdateDialog(jf,key,UserPanel.this);
			}});
		
		
	}
	
	//-------------------------------------------------
	/**
	 * 初始化表格
	 *
	 */
	private void initTable() {
		userTable = new LovoTable(this,
				new String[]{"用户名","密码","真实姓名","等级"},
				new String[]{"userName","userPwd","realName","userGrade"},//用户实体属性名数组 new String[]{"userName","password"}
				"userId");//主键属性名 userId
		userTable.setSizeAndLocation(20, 90, 700, 300);
		
	}
	/**
	 * 更新表格数据
	 */
	private void updateUserTable(){
		//更新表格
		userTable.updateLovoTable(service.findAllUser());
	}
	

	/**
	 * 删除用户
	 * @param userId 用户ID
	 * @param loginUser 登陆用户
	 */
	private void delUser(int userId, UserBean loginUser){
		if (loginUser.getUserId() != userId) {
			service.delUser(userId);
			//更新表格,显示删除结果
			this.updateUserTable();
		}
	}

}
