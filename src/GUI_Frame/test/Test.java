package GUI_Frame.test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import GUI_Frame.test.bean.CityBean;
import GUI_Frame.test.bean.ManBean;
import GUI_Frame.test.bean.SchoolBean;
import com.lovo.netCRM.component.LovoAccordion;
import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoFileChooser;
import com.lovo.netCRM.component.LovoList;
import com.lovo.netCRM.component.LovoTable;


public class Test extends JFrame{
	
	 LovoList<ManBean> lovoList = new LovoList<ManBean>(400, 100, 100, 200,this){

		@Override
		public void onchange(ManBean t) {
			ManBean m = (ManBean)t;
			System.out.println(m.getUserName());
		}
		
};
	public Test(){
		this.setLayout(null);
		
		
		final LovoTable table = new LovoTable(
				this,new String[]{"姓名","生日","自行车"},
				new String[]{"userName","birthday","bike.type"},"id");
		
		//设置表格大小和位置
		table.setSizeAndLocation(50, 50, 300, 100);
		
		List<ManBean> list = new ArrayList<ManBean>();
		list.add(new ManBean(1,"张三","男",Date.valueOf("1990-01-01"),"大运牌"));
		list.add(new ManBean(2,"本田","女",Date.valueOf("1996-07-21"),"解放牌"));
		list.add(new ManBean(3,"张龙","女",Date.valueOf("1992-11-11"),"勇士牌"));
		list.add(new ManBean(4,"王三","男",Date.valueOf("1987-12-09"),"小牛牌"));
		list.add(new ManBean(5,"刘三","男",Date.valueOf("1993-05-06"),"马刺牌"));
		
		//将集合的内容显示在表格
		table.updateLovoTable(list);
		
	
		
		LovoButton showKeyButton = new LovoButton("显示主键",150,200,this);
		showKeyButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				//得到主键值
				int id = table.getKeyByInt();
				JOptionPane.showMessageDialog(null,id);
			}});
		
		LovoButton delButton = new LovoButton("删除",50,200,this);
		delButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				//显示选中行指定列的数据
				JOptionPane.showMessageDialog(null, table.getSelectColumn(0));
				//删除
				table.removeRow();
			}});
		
	
		//列表框组件
//		LovoList<ManBean> lovoList = new LovoList<ManBean>(list){
//			public void onchange(ManBean t){
//				System.out.println(t.getUserName());
//			}
//		};
//		lovoList.setBounds(400, 100, 100, 200);
//		this.add(lovoList);
		
		
		
		lovoList.setList(list);
		
		final LovoFileChooser fc = new LovoFileChooser(this);
		fc.setBounds(400, 300, 200, 150);
		
		LovoButton showButton = new LovoButton("显示路径",600,300,this);
		showButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, fc.getFilePath());
				System.out.println(fc.getFilePath());
			}});
		final List<CityBean> cityList = new ArrayList<CityBean>();
		CityBean c1 = new CityBean(1,"成都市");
		CityBean c2 = new CityBean(2,"绵阳市");
		CityBean c3 = new CityBean(3,"乐山市");
		
		c1.getSchoolList().add(new SchoolBean(1,"成都外国语学校"));
		c1.getSchoolList().add(new SchoolBean(2,"成都十二中"));
		c2.getSchoolList().add(new SchoolBean(3,"绵阳外国语学校"));
		c2.getSchoolList().add(new SchoolBean(4,"绵阳中学"));
		c3.getSchoolList().add(new SchoolBean(5,"乐山外国语学校"));
		c3.getSchoolList().add(new SchoolBean(6,"乐山第一中学"));
		
		cityList.add(c1);
		cityList.add(c2);
		cityList.add(c3);
		
		//创建手风琴组件
		final LovoAccordion ac = new LovoAccordion(this,cityList,"schoolList"){

			@Override
			public void clickEvent(Object obj) {
				SchoolBean bean = (SchoolBean)obj;
				System.out.println(bean.getId()+"  "+bean.getSchoolName());
			}
			
		};
		ac.setBounds(20, 300, 100, 200);
		
		LovoButton lb = new LovoButton("添加",20,550,this);
		lb.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				CityBean c = new CityBean(3,"德阳市");
				
				c.getSchoolList().add(new SchoolBean(1,"德阳外国语学校"));
				c.getSchoolList().add(new SchoolBean(2,"德阳十二中"));
				
				cityList.add(c);
				
				ac.updateAccordion(cityList);
			}});
		
		this.setSize(800,600);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(3);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Test t = new Test();
	}

}
