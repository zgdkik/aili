package org.hbhk.aili.client.core.widget.wizard.service.implement;

import javax.swing.JPanel;

import org.hbhk.aili.client.core.widget.wizard.service.IJWizardComponents;

/**
 * <p>Description: 向导面板类</p>
 */
public class JWizardPanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6407947321980753814L;
	// 向导组件
	private IJWizardComponents wizardComponents;
	
	// 标题
    private String panelTitle;
    
    /**
     * 
     * <p>Title: JWizardPanel</p>
     * <p>Description: 构造方法</p>
     * @param wizardComponents 向导组件
     */
    public JWizardPanel(IJWizardComponents wizardComponents) {
        this(wizardComponents, null);
    }
    
    /**
     * 
     * <p>Title: JWizardPanel</p>
     * <p>Description: 构造方法</p>
     * @param wizardComponents 向导组件
     * @param title 标题
     */
    public JWizardPanel(IJWizardComponents wizardComponents, String title) {
        this.wizardComponents = wizardComponents;
        this.panelTitle = title;
    }
    
    /**
     * 
     * <p>Title: update</p>
     * <p>Description: 更新</p>
     */
    public void update() {
    }
    
    /**
     * 
     * <p>Title: next</p>
     * <p>Description: 下一个</p>
     */
    public void next() {
        goNext();
    }
    
    /**
     * 
     * <p>Title: back</p>
     * <p>Description: 返回</p>
     */
    public void back() {
        goBack();
    }
    
    /**
     * 
     * <p>Title: getWizardComponents</p>
     * <p>Description: 获取向导组件</p>
     * @return
     */
    public IJWizardComponents getWizardComponents(){
        return wizardComponents;
    }
    
    /**
     * 
     * <p>Title: setWizardComponents</p>
     * <p>Description: 设置向导组件</p>
     * @param awizardComponents 向导组件
     */
    public void setWizardComponents(IJWizardComponents awizardComponents){
        wizardComponents = awizardComponents;
    }
    
    /**
     * 
     * <p>Title: getPanelTitle</p>
     * <p>Description: 获取面板标题</p>
     * @return
     */
    public String getPanelTitle() {
        return panelTitle;
    }
    
    /**
     * 
     * <p>Title: setPanelTitle</p>
     * <p>Description: 设置面板标题</p>
     * @param title
     */
    public void setPanelTitle(String title) {
        panelTitle = title;
    }
    
    /**
     * 
     * <p>Title: goNext</p>
     * <p>Description: 返回是否可继续往下走</p>
     * @return
     */
    protected boolean goNext() {
        if (wizardComponents.getWizardPanelList().size() > wizardComponents.getCurrentIndex()+1 ) {
            wizardComponents.setCurrentIndex(wizardComponents.getCurrentIndex()+1);
            wizardComponents.updateComponents();
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * 
     * <p>Title: goBack</p>
     * <p>Description: 返回是否可继续后退</p>
     * @return
     */
    protected boolean goBack() {
        if (wizardComponents.getCurrentIndex()-1 >= 0) {
            wizardComponents.setCurrentIndex(wizardComponents.getCurrentIndex()-1);
            wizardComponents.updateComponents();
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * 
     * <p>Title: switchPanel</p>
     * <p>Description: 激活面板</p>
     * @param panelIndex 面板下标
     */
    protected void switchPanel(int panelIndex) {
        getWizardComponents().setCurrentIndex(panelIndex);
        getWizardComponents().updateComponents();
    }
    
    /**
     * 
     * <p>Title: setBackButtonEnabled</p>
     * <p>Description: 设置后退按钮是否可用</p>
     * @param set
     */
    protected void setBackButtonEnabled(boolean set) {
        wizardComponents.getBackButton().setEnabled(set);
    }
    
    /**
     * 
     * <p>Title: setNextButtonEnabled</p>
     * <p>Description: 设置下一个按钮是否可用</p>
     * @param set
     */
    protected void setNextButtonEnabled(boolean set) {
        wizardComponents.getNextButton().setEnabled(set);
    }
    
    /**
     * 
     * <p>Title: setFinishButtonEnabled</p>
     * <p>Description: 设置结束按钮是否可用</p>
     * @param set
     */
    protected void setFinishButtonEnabled(boolean set) {
        wizardComponents.getFinishButton().setEnabled(set);
    }
}