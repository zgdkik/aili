package org.hbhk.aili.client.core.widget.wizard.service.implement;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.hbhk.aili.client.core.widget.wizard.action.CancelAction;
import org.hbhk.aili.client.core.widget.wizard.action.FinishAction;
import org.hbhk.aili.client.core.widget.wizard.service.IJWizardComponents;
/**
* <b style="font-family:微软雅黑"><small>Description:向导组件的默认实现</small></b>   </br>
 */
public class DefaultJWizardComponents implements IJWizardComponents{

	
	private static final long serialVersionUID = -5838144865275209143L;

	private java.util.ResourceBundle resourceBundle =
    java.util.ResourceBundle.getBundle("com/deppon/foss/framework/client/widget/wizard/service/implement/i18n");
   
	private JButton backButton;
	private JButton nextButton;
	private JButton finishButton;
	private JButton cancelButton;
	
	private FinishAction finishAction;
	private CancelAction cancelAction;
	
	private List<JWizardPanel> panelList;
	private int currentIndex;
	private JPanel wizardPanelsContainer;
	private PropertyChangeSupport propertyChangeListeners;
	
	public DefaultJWizardComponents() {
		try {
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @Title:init
	 * @Description:初始化一个向导组件
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	private void init() {
        this.propertyChangeListeners = new PropertyChangeSupport(this);
		
		backButton = new JButton();
		nextButton = new JButton();
		finishButton = new JButton();
		cancelButton = new JButton();
		
		panelList = new ArrayList<JWizardPanel>();
		currentIndex = 0;
		wizardPanelsContainer = new JPanel();
		
		backButton.setText(resourceBundle.getString("L_BackButton"));
		backButton.setMnemonic(resourceBundle.getString("L_BackButtonMnem").charAt(0)); 
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backButton_actionPerformed(e);
			}
		});
		
		nextButton.setText(resourceBundle.getString("L_NextButton"));
		nextButton.setMnemonic(resourceBundle.getString("L_NextButtonMnem").charAt(0));
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextButton_actionPerformed(e);
			}
		});
		
		cancelButton.setText(resourceBundle.getString("L_CancelButton"));
		cancelButton.setMnemonic(resourceBundle.getString("L_CancelButtonMnem").charAt(0));
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelButton_actionPerformed(e);
			}
		});
		
		finishButton.setText(resourceBundle.getString("L_FinishButton"));
		finishButton.setMnemonic(resourceBundle.getString("L_FinishButtonMnem").charAt(0));
		finishButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishButton_actionPerformed(e);
			}
		});
		
		wizardPanelsContainer.setLayout(new CardLayout());
	}
	
	/**
	 * 
	 * @Title:cancelButton_actionPerformed
	 * @Description:根据行为事件，处理取消按钮动作
	 * @param @param e
	 * @return void
	 * @throws
	 */
	private void cancelButton_actionPerformed(ActionEvent e) {
		getCancelAction().performAction();
	}
	/**
	 * 
	 * @Title:finishButton_actionPerformed
	 * @Description:根据行为事件，处理完成按钮动作
	 * @param @param e
	 * @return void
	 * @throws
	 */
	private void finishButton_actionPerformed(ActionEvent e) {
		getFinishAction().performAction();
	}
	/**
	 * 
	 * @Title:nextButton_actionPerformed
	 * @Description:根据行为事件，处理下一个按钮动作
	 * @param @param e
	 * @return void
	 * @throws
	 */
	private void nextButton_actionPerformed(ActionEvent e) {
		try {
			getCurrentPanel().next();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	/**
	 * 
	 * @Title:backButton_actionPerformed
	 * @Description:根据行为事件，处理前一个按钮动作
	 * @param @param e
	 * @return void
	 * @throws
	 */
	private void backButton_actionPerformed(ActionEvent e) {
		try {
			getCurrentPanel().back();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	/**
	 * 
	 * <p>Title:addWizardPanel
	 * <p>Description:添加向导面板</p>
	 * @param panel
	 * @see org.hbhk.aili.client.core.widget.wizard.service.IJWizardComponents#addWizardPanel(com.deppon.foss.framework.client.widget.wizard.service.implement.JWizardPanel)
	 */
	@Override
	public void addWizardPanel(JWizardPanel panel) {
		getWizardPanelList().add(panel);
		wizardPanelsContainer.add(panel,
		getWizardPanelList().size() - 1 + "");
	}
	/**
	 * 
	 * <p>Title:addWizardPanel
	 * <p>Description:向列表的制定位置添加向导面板</p>
	 * @param index
	 * @param panel
	 * @see org.hbhk.aili.client.core.widget.wizard.service.IJWizardComponents#addWizardPanel(int, com.deppon.foss.framework.client.widget.wizard.service.implement.JWizardPanel)
	 */
	@Override
	public void addWizardPanel(int index, JWizardPanel panel) {
		getWizardPanelList().add(index, panel);
		wizardPanelsContainer.add(panel, index + "", index);
		if (index < getWizardPanelList().size() - 1) {
			for (int i = index + 1; i < getWizardPanelList().size(); i++) {
				wizardPanelsContainer.add(
					(JWizardPanel)getWizardPanelList().get(i),
					i + "");
			}
		}
	}
	
	/**
	 * 
	 * <p>Title:addWizardPanelAfter
	 * <p>Description:</p>
	 * @param panelToBePlacedAfter
	 * @param panel
	 * @see org.hbhk.aili.client.core.widget.wizard.service.IJWizardComponents#addWizardPanelAfter(com.deppon.foss.framework.client.widget.wizard.service.implement.JWizardPanel, com.deppon.foss.framework.client.widget.wizard.service.implement.JWizardPanel)
	 */
	@Override
	public void addWizardPanelAfter(JWizardPanel panelToBePlacedAfter, JWizardPanel panel) {
		addWizardPanel(getWizardPanelList().indexOf(panelToBePlacedAfter) + 1, panel);
	}
	
	/**
	 * 
	 * <p>Title:addWizardPanelBefore
	 * <p>Description:</p>
	 * @param panelToBePlacedBefore
	 * @param panel
	 * @see org.hbhk.aili.client.core.widget.wizard.service.IJWizardComponents#addWizardPanelBefore(com.deppon.foss.framework.client.widget.wizard.service.implement.JWizardPanel, com.deppon.foss.framework.client.widget.wizard.service.implement.JWizardPanel)
	 */
	@Override
	public void addWizardPanelBefore(JWizardPanel panelToBePlacedBefore,
			JWizardPanel panel) {
		addWizardPanel(getWizardPanelList().indexOf(panelToBePlacedBefore) - 1,panel);		
	}
	
	/**
	 * 
	 * <p>Title:addWizardPanelAfterCurrent
	 * <p>Description:</p>
	 * @param panel
	 * @see org.hbhk.aili.client.core.widget.wizard.service.IJWizardComponents#addWizardPanelAfterCurrent(com.deppon.foss.framework.client.widget.wizard.service.implement.JWizardPanel)
	 */
	@Override
	public void addWizardPanelAfterCurrent(JWizardPanel panel) {
		addWizardPanel(getCurrentIndex()+1, panel);
	}
	
	/**
	 * 
	 * <p>Title:removeWizardPanel
	 * <p>Description:根据向导面板删除向导面板</p>
	 * @param panel
	 * @return
	 * @see org.hbhk.aili.client.core.widget.wizard.service.IJWizardComponents#removeWizardPanel(com.deppon.foss.framework.client.widget.wizard.service.implement.JWizardPanel)
	 */
	@Override
	public JWizardPanel removeWizardPanel(JWizardPanel panel) {
		int index = getWizardPanelList().indexOf(panel);
		getWizardPanelList().remove(panel);
		wizardPanelsContainer.remove(panel);
		for (int i = index; i < getWizardPanelList().size(); i++) {
			wizardPanelsContainer.add(
				(JWizardPanel) getWizardPanelList().get(i),
				i + "");
		}
		return panel;
	}
	
	/**
	 * 
	 * <p>Title:removeWizardPanel
	 * <p>Description:删除指定位置的向导面板</p>
	 * @param index
	 * @return
	 * @see org.hbhk.aili.client.core.widget.wizard.service.IJWizardComponents#removeWizardPanel(int)
	 */
	@Override
	public JWizardPanel removeWizardPanel(int index) {
		wizardPanelsContainer.remove(index);
		JWizardPanel panel = (JWizardPanel) getWizardPanelList().remove(index);
		for (int i = index; i < getWizardPanelList().size(); i++) {
			wizardPanelsContainer.add(
				(JWizardPanel) getWizardPanelList().get(i),
				i + "");
		}
		return panel;
	}
	
	/**
	 * 
	 * <p>Title:removeWizardPanelAfter
	 * <p>Description:</p>
	 * @param panel
	 * @return
	 * @see org.hbhk.aili.client.core.widget.wizard.service.IJWizardComponents#removeWizardPanelAfter(com.deppon.foss.framework.client.widget.wizard.service.implement.JWizardPanel)
	 */
	@Override
	public JWizardPanel removeWizardPanelAfter(JWizardPanel panel) {
		return removeWizardPanel(getWizardPanelList().indexOf(panel) + 1);
	}
	
	/**
	 * 
	 * <p>Title:removeWizardPanelBefore
	 * <p>Description:</p>
	 * @param panel
	 * @return
	 * @see org.hbhk.aili.client.core.widget.wizard.service.IJWizardComponents#removeWizardPanelBefore(com.deppon.foss.framework.client.widget.wizard.service.implement.JWizardPanel)
	 */
	@Override
	public JWizardPanel removeWizardPanelBefore(JWizardPanel panel) {
		return removeWizardPanel(getWizardPanelList().indexOf(panel) - 1);
	}
	
	/**
	 * 
	 * <p>Title:getWizardPanel
	 * <p>Description:</p>
	 * @param index
	 * @return
	 * @see org.hbhk.aili.client.core.widget.wizard.service.IJWizardComponents#getWizardPanel(int)
	 */
	@Override
	public JWizardPanel getWizardPanel(int index) {
		return (JWizardPanel) getWizardPanelList().get(index);
	}
	
	/**
	 * 
	 * <p>Title:getIndexOfPanel
	 * <p>Description:</p>
	 * @param panel
	 * @return
	 * @see org.hbhk.aili.client.core.widget.wizard.service.IJWizardComponents#getIndexOfPanel(com.deppon.foss.framework.client.widget.wizard.service.implement.JWizardPanel)
	 */
	@Override
	public int getIndexOfPanel(JWizardPanel panel) {
		return getWizardPanelList().indexOf(panel);
	}
	
	/**
	 * 
	 * <p>Title:updateComponents
	 * <p>Description:更新向导组件</p>
	 * @see org.hbhk.aili.client.core.widget.wizard.service.IJWizardComponents#updateComponents()
	 */
	@Override
	public void updateComponents() {
		try {
			CardLayout cl = (CardLayout) (wizardPanelsContainer.getLayout());
			cl.show(wizardPanelsContainer, currentIndex + "");

			if (currentIndex == 0) {
				backButton.setEnabled(false);
			} else {
				backButton.setEnabled(true);
			}

			if (onLastPanel()) {
				nextButton.setEnabled(false);
				finishButton.setEnabled(true);
			} else {
				finishButton.setEnabled(false);
				nextButton.setEnabled(true);
			}
            // let panel to update itself
            getCurrentPanel().update();

            // inform PropertyChangeListeners
            PropertyChangeEvent event = new PropertyChangeEvent(this, CURRENT_PANEL_PROPERTY
            , null,  getCurrentPanel());
            propertyChangeListeners.firePropertyChange(event);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * <p>Title:getCurrentPanel
	 * <p>Description:得到当前活动面板</p>
	 * @return
	 * @see org.hbhk.aili.client.core.widget.wizard.service.IJWizardComponents#getCurrentPanel()
	 */
	@Override
	public JWizardPanel getCurrentPanel(){
		
		if (getWizardPanelList().size() > 0) {
			return (JWizardPanel) getWizardPanelList().get(currentIndex);
		}
		
		return null;
	}
	
	/**
	 * 
	 * <p>Title:getFinishAction
	 * <p>Description:</p>
	 * @return
	 * @see org.hbhk.aili.client.core.widget.wizard.service.IJWizardComponents#getFinishAction()
	 */
	@Override
	public FinishAction getFinishAction() {
		return finishAction;
	}
	
	/**
	 * 
	 * <p>Title:setFinishAction
	 * <p>Description:</p>
	 * @param aFinishAction
	 * @see org.hbhk.aili.client.core.widget.wizard.service.IJWizardComponents#setFinishAction(org.hbhk.aili.client.core.widget.wizard.action.FinishAction)
	 */
	@Override
	public void setFinishAction(FinishAction aFinishAction) {
		finishAction = aFinishAction;
	}
	
	/**
	 * 
	 * <p>Title:getCancelAction
	 * <p>Description:</p>
	 * @return
	 * @see org.hbhk.aili.client.core.widget.wizard.service.IJWizardComponents#getCancelAction()
	 */
	@Override
	public CancelAction getCancelAction() {
		return cancelAction;
	}
	
	/**
	 * 
	 * <p>Title:setCancelAction
	 * <p>Description:</p>
	 * @param aCancelAction
	 * @see org.hbhk.aili.client.core.widget.wizard.service.IJWizardComponents#setCancelAction(org.hbhk.aili.client.core.widget.wizard.action.CancelAction)
	 */
	@Override
	public void setCancelAction(CancelAction aCancelAction) {
		cancelAction = aCancelAction;
	}
	
	/**
	 * 
	 * <p>Title:getCurrentIndex
	 * <p>Description:</p>
	 * @return
	 * @see org.hbhk.aili.client.core.widget.wizard.service.IJWizardComponents#getCurrentIndex()
	 */
	@Override
	public int getCurrentIndex() {
		return currentIndex;
	}
	
	/**
	 * 
	 * <p>Title:setCurrentIndex
	 * <p>Description:</p>
	 * @param aCurrentIndex
	 * @see org.hbhk.aili.client.core.widget.wizard.service.IJWizardComponents#setCurrentIndex(int)
	 */
	@Override
	public void setCurrentIndex(int aCurrentIndex) {
		currentIndex = aCurrentIndex;
	}
	
	/**
	 * 
	 * <p>Title:getWizardPanelsContainer
	 * <p>Description:</p>
	 * @return
	 * @see org.hbhk.aili.client.core.widget.wizard.service.IJWizardComponents#getWizardPanelsContainer()
	 */
	@Override
	public JPanel getWizardPanelsContainer() {
		return wizardPanelsContainer;
	}
	
	/**
	 * 
	 * <p>Title:setWizardPanelsContainer
	 * <p>Description:</p>
	 * @param aWizardPanelsContainer
	 * @see org.hbhk.aili.client.core.widget.wizard.service.IJWizardComponents#setWizardPanelsContainer(javax.swing.JPanel)
	 */
	@Override
	public void setWizardPanelsContainer(JPanel aWizardPanelsContainer) {
		wizardPanelsContainer = aWizardPanelsContainer;
	}
	
	/**
	 * 
	 * <p>Title:getBackButton
	 * <p>Description:</p>
	 * @return
	 * @see org.hbhk.aili.client.core.widget.wizard.service.IJWizardComponents#getBackButton()
	 */
	@Override
	public JButton getBackButton() {
		return backButton;
	}
	
	/**
	 * 
	 * <p>Title:setBackButton
	 * <p>Description:</p>
	 * @param aBackButton
	 * @see org.hbhk.aili.client.core.widget.wizard.service.IJWizardComponents#setBackButton(javax.swing.JButton)
	 */
	@Override
	public void setBackButton(JButton aBackButton) {
		backButton = aBackButton;
	}
	
	/**
	 * 
	 * <p>Title:getNextButton
	 * <p>Description:</p>
	 * @return
	 * @see org.hbhk.aili.client.core.widget.wizard.service.IJWizardComponents#getNextButton()
	 */
	@Override
	public JButton getNextButton() {
		return nextButton;
	}
	
	/**
	 * 
	 * <p>Title:setNextButton
	 * <p>Description:</p>
	 * @param aNextButton
	 * @see org.hbhk.aili.client.core.widget.wizard.service.IJWizardComponents#setNextButton(javax.swing.JButton)
	 */
	@Override
	public void setNextButton(JButton aNextButton) {
		nextButton = aNextButton;
	}
	
	/**
	 * 
	 * <p>Title:getCancelButton
	 * <p>Description:</p>
	 * @return
	 * @see org.hbhk.aili.client.core.widget.wizard.service.IJWizardComponents#getCancelButton()
	 */
	@Override
	public JButton getCancelButton() {
		return cancelButton;
	}
	
	/**
	 * 
	 * <p>Title:setCancelButton
	 * <p>Description:</p>
	 * @param aCancelButton
	 * @see org.hbhk.aili.client.core.widget.wizard.service.IJWizardComponents#setCancelButton(javax.swing.JButton)
	 */
	@Override
	public void setCancelButton(JButton aCancelButton) {
		cancelButton = aCancelButton;
	}
	
	/**
	 * 
	 * <p>Title:getFinishButton
	 * <p>Description:</p>
	 * @return
	 * @see org.hbhk.aili.client.core.widget.wizard.service.IJWizardComponents#getFinishButton()
	 */
	@Override
	public JButton getFinishButton() {
		return finishButton;
	}
	
	/**
	 * 
	 * <p>Title:setFinishButton
	 * <p>Description:</p>
	 * @param button
	 * @see org.hbhk.aili.client.core.widget.wizard.service.IJWizardComponents#setFinishButton(javax.swing.JButton)
	 */
	@Override
	public void setFinishButton(JButton button) {
		finishButton = button;
	}
	
	/**
	 * 
	 * <p>Title:getWizardPanelList
	 * <p>Description:</p>
	 * @return
	 * @see org.hbhk.aili.client.core.widget.wizard.service.IJWizardComponents#getWizardPanelList()
	 */
	@Override
	public List<JWizardPanel> getWizardPanelList() {
		return this.panelList;
	}
	
	/**
	 * 
	 * <p>Title:setWizardPanelList
	 * <p>Description:</p>
	 * @param panelList
	 * @see org.hbhk.aili.client.core.widget.wizard.service.IJWizardComponents#setWizardPanelList(java.util.List)
	 */
	@Override
	public void setWizardPanelList(List<JWizardPanel> panelList) {
		this.panelList = panelList;
	}
	
	/**
	 * 
	 * <p>Title:onLastPanel
	 * <p>Description:</p>
	 * @return
	 * @see org.hbhk.aili.client.core.widget.wizard.service.IJWizardComponents#onLastPanel()
	 */
	@Override
	public boolean onLastPanel() {
		return (getCurrentIndex() == getWizardPanelList().size() - 1);
	}
	
	/**
	 * 
	 * <p>Title:addPropertyChangeListener
	 * <p>Description:</p>
	 * @param listener
	 * @see org.hbhk.aili.client.core.widget.wizard.service.IJWizardComponents#addPropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeListeners.addPropertyChangeListener(listener);
	}
	
	/**
	 * 
	 * <p>Title:removePropertyChangeListener
	 * <p>Description:</p>
	 * @param listener
	 * @see org.hbhk.aili.client.core.widget.wizard.service.IJWizardComponents#removePropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeListeners.removePropertyChangeListener(listener);
	}
}