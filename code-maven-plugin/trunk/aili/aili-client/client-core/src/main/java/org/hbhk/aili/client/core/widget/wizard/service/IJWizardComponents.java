package org.hbhk.aili.client.core.widget.wizard.service;

import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.hbhk.aili.client.core.widget.wizard.action.CancelAction;
import org.hbhk.aili.client.core.widget.wizard.action.FinishAction;
import org.hbhk.aili.client.core.widget.wizard.service.implement.JWizardPanel;
/**
* <b style="font-family:微软雅黑"><small>Description:向导组件接口</small></b>   </br>
 */
public interface IJWizardComponents extends Serializable{
	/**
	 * 
	 * <p>Title: addWizardPanel</p>
	 * <p>Description: 添加向导面板</p>
	 * @param panel 向导面板
	 */
	public void addWizardPanel(JWizardPanel panel);
	
	/**
	 * 
	 * @Title:addWizardPanel
	 * @Description: 添加向导面板
	 * @param @param index 面板索引
	 * @param @param panel 向导面板
	 * @return void
	 * @throws
	 */
	public void addWizardPanel(int index, JWizardPanel panel);
	
	/**
	 * 
	 * @Title:addWizardPanelAfter
	 * @Description: 添加后向导面板
	 * @param @param panelToBePlacedAfter 后向导面板
	 * @param @param panel 向导面板
	 * @return void
	 * @throws
	 */
	public void addWizardPanelAfter(
		JWizardPanel panelToBePlacedAfter,
		JWizardPanel panel);

	/**
	 * 
	 * <p>Title: addWizardPanelBefore</p>
	 * <p>Description: 添加前向导面板</p>
	 * @param panelToBePlacedBefore 前向导面板
	 * @param panel 向导面板
	 */
	public void addWizardPanelBefore(
		JWizardPanel panelToBePlacedBefore,
		JWizardPanel panel);
	
	/**
	 * 
	 * <p>Title: addWizardPanelAfterCurrent</p>
	 * <p>Description: 添加当前后向导面板</p>
	 * @param panel 向导面板
	 */
	public void addWizardPanelAfterCurrent(JWizardPanel panel);
	
	/**
	 * 
	 * <p>Title: removeWizardPanel</p>
	 * <p>Description: 移除指定向导面板</p>
	 * @param panel 向导面板
	 * @return
	 */
	public JWizardPanel removeWizardPanel(JWizardPanel panel);

	/**
	 * 
	 * <p>Title: removeWizardPanel</p>
	 * <p>Description: 移除指定索引的向导面板</p>
	 * @param index 索引
	 * @return
	 */
	public JWizardPanel removeWizardPanel(int index);

	/**
	 * 
	 * <p>Title: removeWizardPanelAfter</p>
	 * <p>Description: 移除后向导面板</p>
	 * @param panel 向导面板
	 * @return
	 */
	public JWizardPanel removeWizardPanelAfter(JWizardPanel panel);

	/**
	 * 
	 * <p>Title: removeWizardPanelBefore</p>
	 * <p>Description: 移除前向导面板</p>
	 * @param panel 向导面板
	 * @return
	 */
	public JWizardPanel removeWizardPanelBefore(JWizardPanel panel);

	/**
	 * 
	 * <p>Title: getWizardPanel</p>
	 * <p>Description: 根据索引获取向导面板</p>
	 * @param index 索引
	 * @return
	 */
	public JWizardPanel getWizardPanel(int index);

	/**
	 * 
	 * <p>Title: getIndexOfPanel</p>
	 * <p>Description: 获取指定向导面板的索引</p>
	 * @param panel 向导面板
	 * @return
	 */
	public int getIndexOfPanel(JWizardPanel panel);	

	/**
	 * 
	 * <p>Title: updateComponents</p>
	 * <p>Description: 更新组件</p>
	 */
	public void updateComponents();

	/**
	 * 
	 * <p>Title: getCurrentPanel</p>
	 * <p>Description: 获取当前向导面板</p>
	 * @return
	 * @throws Exception
	 */
	public JWizardPanel getCurrentPanel();

	/**
	 * 
	 * <p>Title: getFinishAction</p>
	 * <p>Description: 获取结束动作</p>
	 * @return
	 */
	public FinishAction getFinishAction();

	/**
	 * 
	 * <p>Title: setFinishAction</p>
	 * <p>Description: 设置结束动作</p>
	 * @param aFinishAction 结束动作
	 */
	public void setFinishAction(FinishAction aFinishAction);

	/**
	 * 
	 * <p>Title: getCancelAction</p>
	 * <p>Description: 获取取消动作</p>
	 * @return
	 */
	public CancelAction getCancelAction();
	
	/**
	 * 
	 * <p>Title: setCancelAction</p>
	 * <p>Description: 设置取消动作</p>
	 * @param aCancelAction 取消动作
	 */
	public void setCancelAction(CancelAction aCancelAction);
	
	/**
	 * 
	 * <p>Title: getCurrentIndex</p>
	 * <p>Description: 获取当前索引</p>
	 * @return
	 */
	public int getCurrentIndex();

	/**
	 * 
	 * <p>Title: setCurrentIndex</p>
	 * <p>Description: 设置当前索引</p>
	 * @param aCurrentIndex
	 */
	public void setCurrentIndex(int aCurrentIndex);

	/**
	 * 
	 * <p>Title: getWizardPanelsContainer</p>
	 * <p>Description: 获取向导面板容器</p>
	 * @return
	 */
	public JPanel getWizardPanelsContainer();

	/**
	 * 
	 * <p>Title: setWizardPanelsContainer</p>
	 * <p>Description: 设置向导面板容器</p>
	 * @param aWizardPanelsContainer 向导面板容器
	 */
	public void setWizardPanelsContainer(JPanel aWizardPanelsContainer);

	/**
	 * 
	 * <p>Title: getBackButton</p>
	 * <p>Description: 获取返回按钮</p>
	 * @return
	 */
	public JButton getBackButton();

	/**
	 * 
	 * <p>Title: setBackButton</p>
	 * <p>Description: 设置返回按钮</p>
	 * @param aBackButton 返回按钮
	 */
	public void setBackButton(JButton aBackButton);

	/**
	 * 
	 * <p>Title: getNextButton</p>
	 * <p>Description: 获取下一步按钮</p>
	 * @return
	 */
	public JButton getNextButton();

	/**
	 * 
	 * <p>Title: setNextButton</p>
	 * <p>Description: 设置下一步按钮</p>
	 * @param aNextButton 下一步按钮
	 */
	public void setNextButton(JButton aNextButton);

	/**
	 * 
	 * <p>Title: getCancelButton</p>
	 * <p>Description: 获取取消按钮</p>
	 * @return
	 */
	public JButton getCancelButton();

	/**
	 * 
	 * <p>Title: setCancelButton</p>
	 * <p>Description: 设置取消按钮</p>
	 * @param aCancelButton 取消按钮
	 */
	public void setCancelButton(JButton aCancelButton);

	/**
	 * 
	 * <p>Title: getFinishButton</p>
	 * <p>Description: 获取完成按钮</p>
	 * @return
	 */
	public JButton getFinishButton();
	
	/**
	 * 
	 * <p>Title: setFinishButton</p>
	 * <p>Description: 设置完成按钮</p>
	 * @param button 完成按钮
	 */
	public void setFinishButton(JButton button);
	
	/**
	 * 
	 * <p>Title: getWizardPanelList</p>
	 * <p>Description: 获取向导面板列表</p>
	 * @return
	 */
	public List<JWizardPanel> getWizardPanelList();

	/**
	 * 
	 * <p>Title: setWizardPanelList</p>
	 * <p>Description: 设置向导面板列表</p>
	 * @param panelList 向导面板列表
	 */
	public void setWizardPanelList(List<JWizardPanel> panelList);
	
	/**
	 * 
	 * <p>Title: onLastPanel</p>
	 * <p>Description: 返回是否是最后一个面板</p>
	 * @return
	 */
	public boolean onLastPanel();
    
	/**
	 * 常量（当前面板）
	 */
	public final static String CURRENT_PANEL_PROPERTY = "currentPanel";
        
    /**
     * 
     * <p>Title: addPropertyChangeListener</p>
     * <p>Description: 添加属性变更监听器</p>
     * @param listener 属性变更监听器
     */
	public void addPropertyChangeListener(PropertyChangeListener listener);
    
	/**
	 * 
	 * <p>Title: removePropertyChangeListener</p>
	 * <p>Description: 移除指定属性变更监听器</p>
	 * @param listener 属性变更监听器
	 */
    public void removePropertyChangeListener(PropertyChangeListener listener);
}
