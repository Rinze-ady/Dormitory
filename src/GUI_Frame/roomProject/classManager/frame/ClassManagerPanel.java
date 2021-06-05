package GUI_Frame.roomProject.classManager.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoTable;
import com.lovo.netCRM.component.LovoTitleLabel;
import service.IClassService;
import service.impl.ClassServiceImp;

/**
 * 
 * 宿舍管理系统
 * @author 张成峰
 * @version 1.0
 * @see  
 * @description 班级管理面板
 */
public class ClassManagerPanel  extends JPanel{
	/**班级表格组件*/
	private LovoTable classTable;

	/**班级对象持久层接口组件*/
	private IClassService service = new ClassServiceImp();

	/**窗体对象*/
	private JFrame jf;
	public ClassManagerPanel(JFrame jf){
		this.jf = jf;
		this.setLayout(null);
		this.init();
	}
	/**
	 * 初始化
	 *
	 */
	private void init() {
		new LovoTitleLabel("班 级 管 理",this);
		this.initTable();
		this.initButton();
		this.initData();
	}
	/**
	 * 初始化数据
	 */
	public void initData(){
		this.updateClassTable();
	}
	/**
	 * 初始化按钮
	 *
	 */
	private void initButton() {
		LovoButton addButton = new LovoButton(200,500,"添加班级","image/add.png",this);
		addButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				new ClassAddDialog(jf,ClassManagerPanel.this);
			}});
		
		
		LovoButton delButton = new LovoButton(400,500,"删除班级","image/del.png",this);
		delButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				int key = classTable.getKeyByInt();
//				if(key == -1){
//					JOptionPane.showMessageDialog(null,"请选择行");
//					return;
//				}
				delClass(key);
				
			}});
	}
	
	//--------------------------
	
	/**
	 * 初始化表格
	 */
	private void initTable() {
		classTable = new LovoTable(this,
				new String[]{"班级名称","开班时间","带班老师"},
				new String[]{"className","startDate","teacher"},//班级实体属性名数组 new String[]{"name","createTime"}
				"classId");//主键属性名 classId
		classTable.setSizeAndLocation(20, 90, 700, 300);
		
	}
	/**
	 * 更新表格数据
	 */
	private void updateClassTable(){
		//更新表格,插入班级List集合
		classTable.updateLovoTable(service.findAllClass());
	}
	/**
	 * 删除班级
	 * @param classId 班级ID
	 */
	private void delClass(int classId){
		//删除班级
		service.delClass(classId);
		//更新表格
		this.updateClassTable();
	}
}
