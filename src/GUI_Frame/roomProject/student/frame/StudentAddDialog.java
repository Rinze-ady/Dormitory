package GUI_Frame.roomProject.student.frame;


import bean.ClassBean;
import bean.RoomBean;
import bean.StudentBean;
import com.lovo.netCRM.component.*;
import service.IClassService;
import service.IRoomService;
import service.IStudentService;
import service.impl.ClassServiceImp;
import service.impl.RoomServiceImpl;
import service.impl.StudentServiceImpl;
import util.FileUtil;

import javax.swing.*;

import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

/**
 * 
 * 宿舍管理系统
 * @author 张成峰
 * @version 1.0
 * @see  
 * @description 添加员工对话框
 */
public class StudentAddDialog extends JDialog {

	/**姓名文本框*/
	private LovoTxt nameTxt = new LovoTxt("姓    名",40,90,this);
	/**性别单选钮*/
	private LovoRadioButton sexRadio = new LovoRadioButton("性    别",new String[]{"男","女"},40,140,this){

		@Override
		public void addListener(String itemName) {
			selectRoomBySex(itemName);
		}
	};
	/**班级*/
	private LovoComboBox classComboBox;
	/**所在房间*/
	private LovoComboBox roomComboBox;
	/**联系电话文本框*/
	private LovoTxt phoneTxt = new LovoTxt("联系电话",40,290,this);
	/**学生主面板*/
	private StudentPanel studentPanel;
	/**头像*/
	private LovoFileChooser faceTxt = new LovoFileChooser(this);

	/**房间业务接口实现类组件*/
	private IRoomService roomService = new RoomServiceImpl();
	/**班级业务组件*/
	private IClassService classService = new ClassServiceImp();
	/**学生业务组件*/
	private IStudentService stuService = new StudentServiceImpl();
	
	public StudentAddDialog(JFrame jf,StudentPanel studentPanel){
		super(jf,true);
		this.studentPanel = studentPanel;
		this.setLayout(null);
		
		this.init();
		
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) screensize.getWidth() / 2 - 310;
		int y = (int) screensize.getHeight() / 2 - 240;
		this.setBounds(x, y, 620, 480);
		this.setVisible(true);
	}
	/**
	 * 初始化
	 *
	 */
	private void init(){
		LovoTitleLabel titleLabel = new LovoTitleLabel("学生入住",this);
		titleLabel.setLocation(240, titleLabel.getY());
		faceTxt.setBounds(330, 90, 150, 180); 
		this.initComboBox();
		
		LovoButton addButton = new LovoButton(150,330,"添加","image/add.png",this);
		addButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				boolean isOk = addStudent();
				if(isOk){
					StudentAddDialog.this.dispose();
				}
			}});
		
		LovoButton cancelButton = new LovoButton(360,330,"取消","image/cancel.png",this);
		cancelButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				StudentAddDialog.this.dispose();
			}});
	}
	
	//--------------------------------------------
	/**
	 * 初始化班级和房间下拉框
	 *
	 */
	private void initComboBox(){
		//添加班级List集合

		List<ClassBean> classList = classService.findAllClass();

//		this.classComboBox = new LovoComboBox("班级",new ArrayList(),40,190,this);

		//使用内部类
		this.classComboBox = new LovoComboBox("班级",classList,40,190,this){
			public void onchange(Object obj){
				ClassBean classBean=(ClassBean) obj;
			}
		};


		//添加房间List集合
		List<RoomBean> roomList = roomService.findAllRoom();
		this.roomComboBox = new LovoComboBox("房间",roomList,40,240,this){
			public void onchange(Object obj){
				RoomBean roomBean = (RoomBean) obj;
			}
		};
	}
	/**
	 * 根据性别,查找可住房间
	 * @param sex
	 */
	private void selectRoomBySex(String sex){

		List<RoomBean> roomList = roomService.findBySex(sexRadio.getItem()+"生宿舍");
		//设置房间下拉框
		this.roomComboBox.setList(roomList);
	}
	/**
	 * 添加操作
	 *
	 */
	private boolean addStudent(){
		String str = "";
		//验证数据
//		if(!this.nameTxt.getText().matches("[a-zA-Z\\u4e00-\\u9fa5]{2,20}")){
//			str += "用户名必须为二位以上字母或汉字\n";
//		}
		
		//……
		if(!this.nameTxt.getText().matches("[a-zA-Z\\u4e00-\\u9fa5]{2,20}")){
			str += "用户名必须为二位以上字母或汉字\n";
		}

		ClassBean classBean=(ClassBean) classComboBox.getItem();
		RoomBean roomBean=(RoomBean) roomComboBox.getItem();
		//得到选择文件路径
		String filePath=this.faceTxt.getFilePath();
		//将选择文件目录拷贝到目标目录中，得到重命名文件
		String  fileName= FileUtil.copyFile(filePath,"image/");

		StudentBean studentBean=new StudentBean();
		studentBean.setStuName(nameTxt.getText());
		studentBean.setStuSex(sexRadio.getItem());
		studentBean.setStuClassBean(new ClassBean(classBean.getClassId()));
		studentBean.setStuRoomBean(new RoomBean(roomBean.getRoomId()));
		studentBean.setTelNum(phoneTxt.getText());
		studentBean.setStartDate(LocalDate.now());
		studentBean.setFacePath(fileName);
		stuService.addStudent(studentBean);
		
		if(str.length() != 0){
			JOptionPane.showMessageDialog(null, str);
			return false;
		}
		
		
//		更新数据,显示添加结果
		this.studentPanel.initData();
		return true;
	}
}
