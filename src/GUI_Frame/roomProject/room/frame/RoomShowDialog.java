package GUI_Frame.roomProject.room.frame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;

import bean.RoomBean;
import bean.StudentBean;
import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoLabel;
import com.lovo.netCRM.component.LovoTable;
import com.lovo.netCRM.component.LovoTitleLabel;
import service.IClassService;
import service.IRoomService;
import service.IStudentService;
import service.impl.ClassServiceImp;
import service.impl.RoomServiceImpl;
import service.impl.StudentServiceImpl;

/**
 * 
 * 宿舍管理系统
 * @author 张成峰
 * @version 1.0
 * @see  
 * @description 显示房间信息
 */
public class RoomShowDialog extends JDialog{

	/**房间地址标签*/
	private LovoLabel addressLabel = new LovoLabel("房间地址",40,120,this);
	/**可容纳人数标签*/
	private LovoLabel roomNumberLabel = new LovoLabel("可容纳人数",320,120,this);
	/**房间状态标签*/
	private LovoLabel roomStatusLabel = new LovoLabel("房间状态",40,180,this);
	/**已住人数标签*/
	private LovoLabel inNumberLabel = new LovoLabel("已住人数",320,180,this);
	/**房间类型标签*/
	private LovoLabel roomTypeLabel = new LovoLabel("房间类型",40,240,this);
	/**所住学生表格组件*/
	private LovoTable studentTable;

	/**房间业务接口实现类组件*/
	private IRoomService roomService = new RoomServiceImpl();
	/**班级业务组件*/
	private IClassService classService = new ClassServiceImp();
	/**学生业务组件*/
	private IStudentService stuService = new StudentServiceImpl();
	
	/**房间id*/
	private int roomId;
	public RoomShowDialog(JFrame jf,int roomId){
		super(jf,true);
		this.roomId = roomId;
		
		this.setLayout(null);
		
		this.init();
		
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) screensize.getWidth() / 2 - 310;
		int y = (int) screensize.getHeight() / 2 - 315;
		this.setBounds(x, y, 620, 630);
		this.setVisible(true);
	}
	/**
	 * 初始化
	 *
	 */
	private void init() {
		LovoTitleLabel titleLabel = new LovoTitleLabel("房间信息",this);
		titleLabel.setLocation(240, titleLabel.getY());
		this.initTable();
		this.initData(roomId);
		
		LovoButton okButton = new LovoButton(260,480,"确定","image/ok.png",this);
		okButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				RoomShowDialog.this.dispose();
			}});
	}
	
	
	
	//----------------------
	/**
	 * 初始化表格
	 */
	private void initTable() {
		studentTable = new LovoTable(this,
				new String[]{"学生姓名","所在班级","联系电话"},
				new String[]{"stuName","stuClassBean.className","telNum"},//学生实体属性名数组 new String[]{"name","className"}
				"stuId");//主键属性名 studentId
		studentTable.setSizeAndLocation(40, 300, 540, 150);
	}
	/**
	 * 初始化数据
	 * @param roomId 房间ID
	 */
	private void initData(int roomId) {
		RoomBean room = new RoomBean();
		room = roomService.findRoomById(roomId);

		addressLabel.setText(room.getRoomAddress());
		roomNumberLabel.setText(String.valueOf(room.getCanNum()));
		roomStatusLabel.setText(room.getStatus());
		inNumberLabel.setText(String.valueOf(roomService.findStudentNumByRoomId(roomId)));
		roomTypeLabel.setText(room.getType());

		List<StudentBean> list = new ArrayList<>();
		list = stuService.findStuByRoomId(roomId);

		room.setList(list);

		studentTable.updateLovoTable(room.getList());

	}

}
