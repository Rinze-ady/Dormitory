package GUI_Frame.test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import GUI_Frame.test.bean.ManBean;
import GUI_Frame.test.bean.SexBean;
import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoComboBox;

/**
 * 
 * 四川网脉CRM系统
 * @author 张成峰
 * @version 1.0
 * @see  
 * @description 下拉框
 * 开发日期:2019-04-28
 */
public class ComboBoxFrame extends JFrame{
	private LovoComboBox<ManBean> combox;
	
	private LovoComboBox<SexBean> sexBox;
	
	private LovoComboBox<ManBean> manBox;
	
	
	public ComboBoxFrame(){
		this.setLayout(null);
		
		List<ManBean> list = new ArrayList<ManBean>();
		list.add(new ManBean(1,"张三","男",Date.valueOf("1988-02-02"),"大运牌"));
		list.add(new ManBean(2,"李四","女",Date.valueOf("1990-12-12"),"小运牌"));
		list.add(new ManBean(3,"王五","男",Date.valueOf("1993-09-09"),"中运牌"));
		list.add(new ManBean(4,"王六加二","女",Date.valueOf("1987-11-02"),"霉运牌"));
		
		//下拉框中显示的文字为List集合中元素的toString()返回值
		combox = new LovoComboBox<ManBean>("员工",list,50,50,this);
		
		LovoButton showButton = new LovoButton("显示选择项",50,80,this);
		showButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				//得到选中项的对象
				ManBean bean = combox.getItem();
				JOptionPane.showMessageDialog(null,
						"你选择的是:"+bean.getId()+"   "+bean.getUserName());
			}
		});
		
		LovoButton setButton = new LovoButton("设置选择项",150,80,this);
		setButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				String value = JOptionPane.showInputDialog(null,"请输入选项ID");
				combox.setItem(Integer.parseInt(value), "id");
			}
		});
		//--------------
		
		final List<ManBean> manlist = new ArrayList<ManBean>();
		manlist.add(new ManBean(1,"张三","男",Date.valueOf("1988-02-02"),"大运牌"));
		manlist.add(new ManBean(2,"李四","男",Date.valueOf("1990-12-12"),"小运牌"));
		
		final List<ManBean> womanlist = new ArrayList<ManBean>();
		womanlist.add(new ManBean(3,"王五","女",Date.valueOf("1988-11-10"),"中运牌"));
		womanlist.add(new ManBean(4,"赵六","女",Date.valueOf("1990-09-12"),"小运牌"));
		
		//-----------------------
		List<SexBean> sexList = new ArrayList<SexBean>();
		sexList.add(null);
		sexList.add(new SexBean("男"));
		sexList.add(new SexBean("女"));
		sexBox = new LovoComboBox("性别",sexList,50,200,this){

			@Override
			public void onchange(Object t) {
				SexBean bean = (SexBean)t;
				
				if(bean.getSex().equals("男")){
					manBox.setList(manlist);
				}
				else{
					manBox.setList(womanlist);
				}
			}
			
		};
		
		//-----------
		
		manBox = new LovoComboBox("员工",new ArrayList(),300,200,this);
		
		
		this.setSize(600,500);
		this.setVisible(true);
		this.setDefaultCloseOperation(3);
		this.setLocationRelativeTo(null);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ComboBoxFrame();
	}

}
