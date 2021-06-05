package GUI_Frame.roomProject.student.frame;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import bean.StudentBean;
import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoImageLabel;
import com.lovo.netCRM.component.LovoLabel;
import com.lovo.netCRM.component.LovoTitleLabel;
import service.IStudentService;
import service.impl.StudentServiceImpl;

/**
 * 
 * 宿舍管理系统
 * @author 张成峰
 * @version 1.0
 * @see  
 * @description 学生信息显示对话框
 */
public class StudentShowDialog extends JDialog{


	/**姓名标签*/
	private LovoLabel nameLabel = new LovoLabel("姓    名",40,90,this);
	/**性别标签*/
	private LovoLabel sexLabel = new LovoLabel("性    别",40,130,this);
	/**班级标签*/
	private LovoLabel classLabel = new LovoLabel("班级",40,170,this);
	/**联系电话标签*/
	private LovoLabel phoneLabel = new LovoLabel("联系电话",40,210,this);
	/**所住房间标签*/
	private LovoLabel roomLabel = new LovoLabel("所住房间",320,250,this);
	/**入住时间标签*/
	private LovoLabel joinDateLabel = new LovoLabel("入住时间",40,250,this);
	/**头像标签*/
	private LovoImageLabel faceLabel = new LovoImageLabel(330, 120, 100, 100,this);
	
	/**学生Id*/
	private int studentId;

	/**学生业务组件*/
	private IStudentService service = new StudentServiceImpl();
	
	public StudentShowDialog(JFrame jf,int studentId){
		super(jf,true);
		this.studentId = studentId;
	
		this.setLayout(null);
		
		this.init();
		

		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) screensize.getWidth() / 2 - 330;
		int y = (int) screensize.getHeight() / 2 - 225;
		this.setBounds(x,y,660, 450);
		this.setVisible(true);
	}
	/**
	 * 初始化
	 *
	 */
	private void init() {
		LovoTitleLabel titleLabel = new LovoTitleLabel("学生信息一览",this);
		titleLabel.setLocation(240, titleLabel.getY());
		this.initStudentData(studentId);
		LovoButton cancelButton = new LovoButton(240,300,"确定","image/ok.png",this);
		cancelButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				StudentShowDialog.this.dispose();
			}});
	}
	
	//-------------------------
	/**
	 * 初始化数据
	 * @param studentId 学生ID
	 */
	private void initStudentData(int studentId){
		StudentBean stu = new StudentBean();
		stu = service.findStuByStuId(studentId);

		nameLabel.setText(stu.getStuName());
		sexLabel.setText(stu.getStuSex());
		classLabel.setText(stu.getStuClassBean().getClassName());
		phoneLabel.setText(stu.getTelNum());
		roomLabel.setText(stu.getStuRoomBean().getRoomAddress());
		joinDateLabel.setText(String.valueOf(stu.getStartDate()));
		faceLabel.setImage("image/"+stu.getFacePath());
	}
}
