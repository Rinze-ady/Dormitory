package GUI_Frame.test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoDate;
import com.lovo.netCRM.component.LovoFileChooser;
/**
 * 
 * 四川网脉CRM系统
 * @author 张成峰
 * @version 1.0
 * @see  
 * @description 文件选择器和日历框
 * 开发日期:2019-04-28
 */
public class HeadFrame extends JFrame{
	//产生文件选择器组件,第一个参数为组件加入的容器,
	//第二个参数为用户选择文件后,文件存放的目录名
	private LovoFileChooser fc = new LovoFileChooser(this);
	//创建日历组件对象
	private LovoDate date = new LovoDate("生日",50,200,this);
	
	public HeadFrame(){
		this.setLayout(null);
		//设置组件的大小和位置
		fc.setBounds(50, 50, 100, 150);
		
		LovoButton show = new LovoButton("显示",180,50,this);
		
		show.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				//得到拷贝到工程下的文件名
				String path = fc.getFilePath();
				JOptionPane.showMessageDialog(null,path);
			}});
		
		
		LovoButton showDate = new LovoButton("显示日期",180,100,this);
		
		showDate.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				//date.getText()用户选择的日期
				JOptionPane.showMessageDialog(null,"选择的日期是:"+date.getText());
			}});
		
	
		
		this.setSize(600,500);
		this.setVisible(true);
		this.setDefaultCloseOperation(3);
		this.setLocationRelativeTo(null);
	}
	
	public static void main(String[] args) {
		HeadFrame h = new HeadFrame();
	}
}
