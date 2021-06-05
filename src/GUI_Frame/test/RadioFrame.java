package GUI_Frame.test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoCheckBox;
import com.lovo.netCRM.component.LovoRadioButton;
/**
 * 
 * 测试单选钮,复选框
 * @author 张成峰
 * @version 1.0
 * @see  
 * @description 
 * 开发日期:2019-04-28
 */
public class RadioFrame extends JFrame{
	private LovoRadioButton lr = new LovoRadioButton("性别",new String[]{"男","女"},50,50,this);
	private LovoCheckBox lc = new LovoCheckBox("权限",new String[]{"查询","添加","删除","修改"},50,100,this);
	
	public RadioFrame(){
		this.setLayout(null);
		
		
		LovoButton lb = new LovoButton("显示",50,80,this);
		
		lb.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,lr.getItem());
			}
			
		});
		
		LovoButton ls = new LovoButton("设置",150,80,this);
		
		ls.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				String value = JOptionPane.showInputDialog(null,"输入设置的值");
				lr.setItem(value);
			}
			
		});
		//--------------
		
		LovoButton lcb = new LovoButton("显示选项",50,180,this);
		
		lcb.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				String str = "";
//				for(String s : lc.getItem()){
//					str += s+",";
//				}
				
				str = Arrays.toString(lc.getItem());
				
				JOptionPane.showMessageDialog(null,str);
			}
			
		});
		
		
		LovoButton lsb = new LovoButton("设置选项",150,180,this);
		
		lsb.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				
				String str = JOptionPane.showInputDialog(null,"请输入选项,以','分开");
			
				String[] strs = str.split(",");
				
				lc.setItem(strs);
			}
			
		});
		
		this.setSize(600,500);
		this.setVisible(true);
		this.setDefaultCloseOperation(3);
		this.setLocationRelativeTo(null);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new RadioFrame();
	}

}
