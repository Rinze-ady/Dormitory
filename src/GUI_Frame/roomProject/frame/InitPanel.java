package GUI_Frame.roomProject.frame;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * 
 * 宿舍管理系统
 * @author 张成峰
 * @version 1.0
 * @see  
 * @description 初始化面板
 */
public class InitPanel extends JPanel{
	public InitPanel(){
		this.setLayout(new BorderLayout());
		Image img = new ImageIcon("image/main.jpg").getImage();
		img = img.getScaledInstance(700, 650, 1);
		this.add(new JLabel(new ImageIcon(img)));
	}
}
