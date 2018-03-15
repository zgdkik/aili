package org.hbhk.aili.client.core.widget.combbox;

import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;

import com.sun.java.swing.plaf.windows.WindowsComboBoxUI;

/**
 * 1、支持键盘上下循环切换选中条目
 * 
 * 2、下拉框增加确认功能，需要按下Enter键ComboBox值才发生变化
 * 
 * @author 102246-foss-shaohongliang
 * @date 2013-3-23 上午11:04:45
 */
public class RollingCycleComboBoxUI extends WindowsComboBoxUI{

	/**
	 * 
	 * 不覆盖该方法不会生效
	 * @author 102246-foss-shaohongliang
	 * @date 2013-3-23 上午11:25:27
	 */
    public static ComponentUI createUI(JComponent c) {
        return new RollingCycleComboBoxUI();
    }  

	/**
	 * 
	 * 覆盖selectNextPossibleValue方法，下拉框变化时不修改ComboBox绑定变量
	 * 
	 * @author 102246-foss-shaohongliang
	 * @date 2013-3-23 上午11:06:45
	 * @see javax.swing.plaf.basic.BasicComboBoxUI#selectNextPossibleValue()
	 */
	@Override
	protected void selectNextPossibleValue() {
		if(!comboBox.isPopupVisible()){
			comboBox.showPopup();
		}else{
			int si = listBox.getSelectedIndex();
			int sz = comboBox.getModel().getSize();
			
			if ( si < sz - 1 ) {
	            listBox.setSelectedIndex( si + 1 );
	            listBox.ensureIndexIsVisible( si + 1 );
	        }else if(si == sz - 1 ) {
	            listBox.setSelectedIndex( 0 );
	            listBox.ensureIndexIsVisible( 0 );
	        }
		}
	}
	
	/**
	 * 
	 * 覆盖selectNextPossibleValue方法，下拉框变化时不修改ComboBox绑定变量
	 * 
	 * @author 102246-foss-shaohongliang
	 * @date 2013-3-23 上午11:06:45
	 * @see javax.swing.plaf.basic.BasicComboBoxUI#selectNextPossibleValue()
	 */
	@Override
	protected void selectPreviousPossibleValue() {
		if(!comboBox.isPopupVisible()){
			comboBox.showPopup();
		}else{
			int si = listBox.getSelectedIndex();
			int sz = comboBox.getModel().getSize();
	        if ( si > 0 ) {
	            listBox.setSelectedIndex( si - 1 );
	            listBox.ensureIndexIsVisible( si - 1 );
	        }else if(si == 0 ) {
	            listBox.setSelectedIndex( sz - 1 );
	            listBox.ensureIndexIsVisible( sz - 1 );
	        }
		}
		
	}
	
}
