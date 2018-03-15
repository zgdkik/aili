package org.hbhk.aili.client.core.widget.wizard.ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import org.hbhk.aili.client.core.widget.wizard.action.CancelAction;
import org.hbhk.aili.client.core.widget.wizard.action.FinishAction;
import org.hbhk.aili.client.core.widget.wizard.service.IJWizardComponents;
import org.hbhk.aili.client.core.widget.wizard.service.implement.DefaultJWizardComponents;
import org.hbhk.aili.client.core.widget.wizard.service.implement.JWizardPanel;
/**
* <b style="font-family:微软雅黑"><small>Description:向导组件</small></b>   </br>
 */
@SuppressWarnings({"unused"})
public class JWizardFrame extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 8067800662585822412L;

	// 向导组件
	private IJWizardComponents wizardComponents;
    
	// 按钮面板
    private JPanel buttonPanel;
    
    // 面板标题标签
    private JLabel panelTitleLabel;
    
    /**
     * 
     * <p>Title: JWizardFrame</p>
     * <p>Description: 构造函数</p>
     */
    public JWizardFrame() {
        init();
    }
    
    /**
     * 
     * <p>Title: init</p>
     * <p>Description: 初始化向导窗口</p>
     */
    private void init() {
        wizardComponents = new DefaultJWizardComponents();
        wizardComponents.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent event) {
                setPanelTitle(((JWizardPanel)event.getNewValue()).getPanelTitle());
            }
        });
        
        this.getContentPane().setLayout(new GridBagLayout());
        this.getContentPane().add(createTitlePanel()
        , new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0
        , GridBagConstraints.CENTER, GridBagConstraints.BOTH
        , new Insets(5, 5, 5, 5), 0, 0));
        
        this.getContentPane().add(new JSeparator()
        , new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0
        , GridBagConstraints.WEST, GridBagConstraints.BOTH,
        new Insets(1, 1, 1, 1), 0, 0));
        
        this.getContentPane().add(wizardComponents.getWizardPanelsContainer()
        , new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0
        , GridBagConstraints.CENTER, GridBagConstraints.BOTH
        , new Insets(0, 0, 0, 0), 0, 0));
        
        this.getContentPane().add(new JSeparator()
        , new GridBagConstraints(0, 3, 1, 1, 1.0, 0.0
        , GridBagConstraints.WEST, GridBagConstraints.BOTH
        , new Insets(1, 1, 1, 1), 0, 0));
        
        this.getContentPane().add(createButtonPanel(),
        new GridBagConstraints(0, 4, 1, 1, 1.0, 0.0
        ,GridBagConstraints.EAST, GridBagConstraints.NONE,
        new Insets(5, 5, 5, 5), 0, 0));
        
        handleWindowClosing();
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
     * @param aWizardComponents 向导组件
     */
    public void setWizardComponents(IJWizardComponents aWizardComponents){
        wizardComponents = aWizardComponents;
    }
    
    /*
     * (non-Javadoc)
     * <p>Title: show</p>
     * <p>Description: 显示向导</p>
     * @see java.awt.Window#show()
     */
    @Override
    public void show() {
        wizardComponents.updateComponents();
        super.setVisible(true);
    }
    
    /**
     * 
     * <p>Title: setPanelTitle</p>
     * <p>Description: 设置面板标题</p>
     * @param title
     */
    protected void setPanelTitle(String title) {
        panelTitleLabel.setText(title);
    }
    
    /**
     * 
     * <p>Title: createTitlePanel</p>
     * <p>Description: 创建标题面板</p>
     * @return
     */
    protected JPanel createTitlePanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelTitleLabel = new JLabel();
        panelTitleLabel.setHorizontalAlignment(SwingConstants.LEADING);
        panel.add(panelTitleLabel);
        return panel;
    }
    
    /**
     * 
     * <p>Title: createButtonPanel</p>
     * <p>Description: 创建按钮面板</p>
     * @return
     */
    protected JPanel createButtonPanel() {
        JPanel panel = new JPanel(new GridLayout());
        panel.add(wizardComponents.getBackButton());
        panel.add(wizardComponents.getNextButton());
        panel.add(wizardComponents.getFinishButton());
        panel.add(wizardComponents.getCancelButton());
        return panel;
    }
    
    /**
     * 
     * <p>Title: createFinishAction</p>
     * <p>Description: 创建结束处理动作</p>
     * @return
     */
    protected FinishAction createFinishAction() {
        return new FinishAction(wizardComponents) {
            public void performAction() {
                //System.out.println("FinishAction performed");
                dispose();
            }
        };
    }
    
    /**
     * 
     * <p>Title: createCancelAction</p>
     * <p>Description: 创建取消处理动作</p>
     * @return
     */
    protected CancelAction createCancelAction() {
        return new CancelAction(wizardComponents) {
            public void performAction() {
                System.out.println("CancelAction performed");
                dispose();
            }
        };
    }
    
    /**
     * 
     * <p>Title: handleWindowClosing</p>
     * <p>Description: 窗口关闭处理函数</p>
     */
    protected void handleWindowClosing() {
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {		
            public void windowClosing(WindowEvent e) {
                wizardComponents.getCancelAction().performAction();
            }
        });
    }
    
    /**
     * 设置新位置
     * @param component
     */
    public static void centerComponentOnScreen(Component component) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension d = toolkit.getScreenSize();

        Point p = new Point();
        p.x += ((d.width - component.getWidth()) / 2);
        p.y += ((d.height - component.getHeight()) / 2);

        if (p.x < 0) {
          p.x = 0;
        }

        if (p.y < 0) {
          p.y = 0;
        }

        component.setLocation(p);
      }
}