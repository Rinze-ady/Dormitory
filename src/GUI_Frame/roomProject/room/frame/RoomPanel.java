package GUI_Frame.roomProject.room.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import bean.RoomBean;
import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoComboBox;
import com.lovo.netCRM.component.LovoTable;
import com.lovo.netCRM.component.LovoTitleLabel;
import com.lovo.netCRM.component.LovoTitlePanel;
import com.lovo.netCRM.component.LovoTxt;
import service.IRoomService;
import service.impl.RoomServiceImpl;


/**
 * 
 * 宿舍管理系统
 * @author 张成峰
 * @version 1.0
 * @see  
 * @description 房间管理面板
 */
public class RoomPanel  extends JPanel{
	/**房间表格组件*/
	private LovoTable roomTable;
	/**窗体组件*/
	private JFrame jf;
	/**房间地址文本框*/
	private LovoTxt addressTxt;
	/**房间状态下拉框*/
	private LovoComboBox<String> statusTxt;
	/**可住房间下拉框*/
	private LovoComboBox<String> enableRoomTxt;
	/**房间类型下拉框*/
	private LovoComboBox<String> roomTypeTxt;

	/**房间业务组件*/
	private IRoomService service = new RoomServiceImpl();

	public RoomPanel(JFrame jf){
		this.jf = jf;
		this.setLayout(null);
		this.init();
	}
	/**
	 * 初始化
	 *
	 */
	private void init() {
		new LovoTitleLabel("房 间 管 理",this);
		this.initTable();
		this.initButton();
		this.initFindPanel();
		this.initData();
	}
	/**
	 * 初始化数据
	 */
	public void initData(){
		this.updateRoomTable();
	}
	/**
	 * 初始化按钮
	 *
	 */
	private void initButton() {
		LovoButton addButton = new LovoButton(50,450,"添加房间","image/add.png",this);
		addButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				new RoomAddDialog(jf,RoomPanel.this);
			}});
		
		LovoButton delButton = new LovoButton(250,450,"删除房间","image/del.png",this);
		delButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				int key = roomTable.getKeyByInt();
				if(key == -1){
					JOptionPane.showMessageDialog(null,"请选择行");
					return;
				}
				
				delRoom(key);
			}});
		
		
		LovoButton showButton = new LovoButton(50,550,"查看房间信息","image/find.png",this);
		showButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				int key = roomTable.getKeyByInt();
				if(key == -1){
					JOptionPane.showMessageDialog(null,"请选择行");
					return;
				}
				
				new RoomShowDialog(jf,key);
			}});
		
		
		LovoButton showSetButton = new LovoButton(250,550,"查看房间设施","image/find.png",this);
		showSetButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				int key = roomTable.getKeyByInt();
				if(key == -1){
					JOptionPane.showMessageDialog(null,"请选择行");
					return;
				}
				
				new RoomSetDialog(jf,key,RoomPanel.this);
			}});
	}
	/**
	 * 初始化查询按钮
	 *
	 */
	private void initFindPanel(){

		LovoTitlePanel jp = new LovoTitlePanel("查询学生信息",400, 400, 320, 250,this);
		this.addressTxt = new LovoTxt("房间地址",30,50,jp);
		this.statusTxt = new LovoComboBox("房间状态",new String[]{"不限","正常","设施损坏"},30,90,jp);
		this.enableRoomTxt = new LovoComboBox("可住房间",new String[]{"不限","可住房间"},30,130,jp);
		this.roomTypeTxt = new LovoComboBox("房间类型",new String[]{"不限","男生宿舍","女生宿舍"},30,170,jp);
		
		LovoButton findButton = new LovoButton("查找",180,210,jp);
		
		findButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				findRoom();
				
			}});
		
	}

	
	//-------------------------------------------------
	/**
	 * 初始化表格
	 *
	 */
	private void initTable() {
		roomTable = new LovoTable(this,
				new String[]{"房间地址","可容纳人数","已住人数","房间状态","房间类型"},
				new String[]{"roomAddress","canNum","useNum","status","type"},//房间实体属性名数组 new String[]{"name","roomNumber"}
				"roomId");//主键属性名 roomId
		roomTable.setSizeAndLocation(20, 90, 700, 300);
		
	}
	/**
	 * 更新表格数据
	 */
	private void updateRoomTable(){
		//更新表格
		roomTable.updateLovoTable(service.findAllRoom());
	}
	

	/**
	 * 删除房间
	 * @param roomId 房间ID
	 */
	private void delRoom(int roomId){


		service.delRoom(roomId);
		//更新表格,显示删除结果
		this.updateRoomTable();
	}

	/**
	 * 查找房间
	 */
	private void findRoom(){
		
		List<RoomBean> list = service.findByRoomItem(addressTxt.getText(),
				statusTxt.getItem(),enableRoomTxt.getItem(), roomTypeTxt.getItem());
		//更新表格,显示查询结果
		roomTable.updateLovoTable(list);
	}
}
