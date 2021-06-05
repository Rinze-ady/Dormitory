package GUI_Frame.roomProject.student.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoTable;
import com.lovo.netCRM.component.LovoTitleLabel;
import com.lovo.netCRM.component.LovoTitlePanel;
import com.lovo.netCRM.component.LovoTxt;
import service.IStudentService;
import service.impl.StudentServiceImpl;

/**
 * 
 * 宿舍管理系统
 * @author 张成峰
 * @version 1.0
 * @see  
 * @description 学生管理面板
 */
public class StudentPanel extends JPanel{
	/**学生表格组件*/
	private LovoTable studentTable;
	/**窗体组件*/
	private JFrame jf;
	/**学生姓名文本框*/
	private LovoTxt nameTxt;
	/**班级文本框*/
	private LovoTxt classTxt;
	/**所在房间文本框*/
	private LovoTxt roomTxt;

	/**学生业务组件*/
	private IStudentService service = new StudentServiceImpl();

	public StudentPanel(JFrame jf){
		this.jf = jf;
		this.setLayout(null);
		this.init();
	}
	/**
	 * 初始化
	 *
	 */
	private void init() {
		new LovoTitleLabel("学 生 管 理",this);
		this.initTable();
		this.initButton();
		this.initFindPanel();
		this.initData();
	}
	/**
	 * 初始化数据
	 */
	public void initData(){
		this.updateStudentTable();
	}
	/**
	 * 初始化按钮
	 *
	 */
	private void initButton() {
		LovoButton addButton = new LovoButton(50,450,"学生入住","image/add.png",this);
		addButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				new StudentAddDialog(jf,StudentPanel.this);
			}});
		
		LovoButton delButton = new LovoButton(250,450,"学生退房","image/del.png",this);
		delButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				int key = studentTable.getKeyByInt();
//				if(key == -1){
//					JOptionPane.showMessageDialog(null,"请选择行");
//					return;
//				}
				
				delStudent(key);
			}});
		
		
		LovoButton updateButton = new LovoButton(50,550,"学生换房","image/update.png",this);
		updateButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				int key = studentTable.getKeyByInt();
//				if(key == -1){
//					JOptionPane.showMessageDialog(null,"请选择行");
//					return;
//				}
				
				new StudentUpdateDialog(jf,key,StudentPanel.this);
			}});
		
		
		LovoButton showButton = new LovoButton(250,550,"学生详细信息","image/find.png",this);
		showButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				int key = studentTable.getKeyByInt();
//				if(key == -1){
//					JOptionPane.showMessageDialog(null,"请选择行");
//					return;
//				}
				
				new StudentShowDialog(jf,key);
			}});
	}
	/**
	 * 初始化查询按钮
	 *
	 */
	private void initFindPanel(){

		LovoTitlePanel jp = new LovoTitlePanel("查询学生信息",400, 400, 320, 250,this);
		this.nameTxt = new LovoTxt("学生姓名",30,50,jp);
		this.classTxt = new LovoTxt("班级",30,100,jp);
		this.roomTxt = new LovoTxt("所在房间",30,150,jp);
		
		LovoButton findButton = new LovoButton("查找",180,200,jp);
		findButton.setSize(60, 20);
		
		findButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				findStudent();
				
			}});
		
	}

	
	//-------------------------------------------------
	/**
	 * 初始化表格
	 *
	 */
	private void initTable() {
		studentTable = new LovoTable(this,
				new String[]{"学生姓名","性别","班级","所在房间","联系电话"},
				new String[]{"stuName","stuSex","stuClassBean.className","stuRoomBean.roomAddress","telNum"},//员工实体属性名数组 new String[]{"name","sex"}
				"stuId");//主键属性名 studentId
		studentTable.setSizeAndLocation(20, 90, 700, 300);
		
	}
	/**
	 * 更新表格数据
	 */
	private void updateStudentTable(){
		//更新表格
		studentTable.updateLovoTable(service.findByItem(null,null,null));
	}
	

	/**
	 * 学生退房
	 * @param studentId 学生ID
	 */
	private void delStudent(int studentId){
		service.delStudent(studentId);
		
		//更新表格,显示删除结果
		this.updateStudentTable();
	}
	/**
	 * 查找学生
	 */
	private void findStudent(){

		
		//更新表格,显示查询结果
		studentTable.updateLovoTable(service.findByItem(nameTxt.getText(),classTxt.getText(),roomTxt.getText()));
	}
}
