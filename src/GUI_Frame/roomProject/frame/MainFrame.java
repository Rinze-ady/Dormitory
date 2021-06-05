package GUI_Frame.roomProject.frame;

import GUI_Frame.roomProject.classManager.frame.ClassManagerPanel;
import GUI_Frame.roomProject.room.frame.RoomPanel;
import GUI_Frame.roomProject.student.frame.StudentPanel;
import GUI_Frame.roomProject.system.frame.UserPanel;
import GUI_Frame.roomProject.updatePwd.frame.UpdatePwdPanel;
import bean.UserBean;
import com.lovo.netCRM.component.LovoTree;
import com.lovo.netCRM.component.LovoTreeNode;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;



/**
 * 
 * 宿舍管理系统
 * @author 张成峰
 * @version 1.0
 * @see  
 * @description 布局主界面
 */
public class MainFrame extends JFrame{
	/**树形菜单*/
	private LovoTree tree;
	/**右边的容器组件*/
	private JPanel mainPanel = new JPanel();
	/**卡片布局*/
	private CardLayout card = new CardLayout();

	/**学生主面板*/
	private StudentPanel studentPanel = new StudentPanel(this);
	/**房间主面板*/
	private RoomPanel roomPanel = new RoomPanel(this);
	/**班级管理主面板*/
	private ClassManagerPanel classManagerPanel = new ClassManagerPanel(this);
	/**用户管理主面板*/
	private UserPanel userPanel ;
	/**修改密码面板*/
	private UpdatePwdPanel updatePwdPanel;

	/**登陆用户*/
	private UserBean loginUser;
			
	public MainFrame(UserBean loginUser){
		super("宿舍管理系统");
		this.loginUser = loginUser;
		
		this.initTree();
		this.initPanel();
		
		
		this.setSize(1000,700);
		this.setVisible(true);
		this.setDefaultCloseOperation(3);
		this.setLocationRelativeTo(null);
	}
	/**
	 * 初始化上面的容器
	 *
	 */
	private void initPanel() {
		//设置容器的布局管理器为卡片布局
		mainPanel.setLayout(this.card);
		this.add(mainPanel);
		
		updatePwdPanel = new UpdatePwdPanel(loginUser, this);
		userPanel = new UserPanel(loginUser,this);
		
		//给卡布局的容器添加面板，每加上一个面板，由第二个参数给面板取名
		mainPanel.add(new InitPanel(),"init");
		mainPanel.add(studentPanel,"student");
		mainPanel.add(roomPanel,"room");
		mainPanel.add(classManagerPanel,"class");
		mainPanel.add(updatePwdPanel,"updatePwd");
		mainPanel.add(userPanel,"user");
	}
	
	
	/**
	 * 设置树形菜单
	 *
	 */
	private void initTree() {
		//根节点
		LovoTreeNode rootNode = new LovoTreeNode("宿舍管理系统");
		
		//枝节点
		LovoTreeNode sorceNode = new LovoTreeNode("资料管理");
		LovoTreeNode roomManagerNode = new LovoTreeNode("房间管理");
		
		//叶节点
		LovoTreeNode studentNode = new LovoTreeNode("学生管理"){
			
			public void addListener(){
				//切换到"student"标签指定的面板
				card.show(mainPanel, "student");
			}
		};
		
		
		LovoTreeNode classNode = new LovoTreeNode("班级管理"){
			
			public void addListener(){
				card.show(mainPanel, "class");
			}
		};
		
		LovoTreeNode roomNode = new LovoTreeNode("房间管理"){
			
			public void addListener(){
				roomPanel.initData();
				card.show(mainPanel, "room");
			}
		};
		
		LovoTreeNode systemNode = new LovoTreeNode("系统管理");
		
		//叶节点
		LovoTreeNode userNode = new LovoTreeNode("用户管理"){
			
			public void addListener(){
				//切换到"user"标签指定的面板
				card.show(mainPanel, "user");
			}
		};
		
		LovoTreeNode infoNode = new LovoTreeNode("个人信息管理");
		
		//叶节点
		LovoTreeNode updatePwdNode = new LovoTreeNode("修改密码"){
			
			public void addListener(){
				//切换到"updatePwd"标签指定的面板
				card.show(mainPanel, "updatePwd");
			}
		};
		
	
		
		//将叶节点加入枝节点
		sorceNode.add(studentNode);
		sorceNode.add(classNode);
		
		roomManagerNode.add(roomNode);
		
		systemNode.add(userNode);
		
		infoNode.add(updatePwdNode);
		
		
		initNode(rootNode,sorceNode,roomManagerNode,systemNode,infoNode);
		
		
		//创建树形菜单
		this.tree = new LovoTree(rootNode);
		//设置树形菜单的字体
		this.tree.setFont(new Font("华文行楷",Font.BOLD,25));
		
		//将树形菜单加入窗体
		this.add(this.tree,BorderLayout.WEST);
	}
	
	//--------------------
	/**
	 * 将枝节点加入根节点
	 */
	private void initNode(LovoTreeNode rootNode,LovoTreeNode sorceNode,
			LovoTreeNode roomManagerNode,LovoTreeNode systemNode,LovoTreeNode infoNode){

		if(loginUser.getUserGrade().equals("班主任") || loginUser.getUserGrade().equals("超级管理员")){
			//加入资料管理节点
			rootNode.add(sorceNode);
		}
		if(loginUser.getUserGrade().equals("房间管理员") || loginUser.getUserGrade().equals("超级管理员")){
			//加入房间管理节点
			rootNode.add(roomManagerNode);
		}
		if(loginUser.getUserGrade().equals("系统管理员") || loginUser.getUserGrade().equals("超级管理员")){
			//加入系统管理节点
			rootNode.add(systemNode);
		}

		//加入个人信息管理节点
		rootNode.add(infoNode);
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		UserBean user = new UserBean("超级管理员");
		MainFrame frame = new MainFrame(user);
	}

}
