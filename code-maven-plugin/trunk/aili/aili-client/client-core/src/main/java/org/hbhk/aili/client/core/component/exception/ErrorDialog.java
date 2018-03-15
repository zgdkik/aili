package org.hbhk.aili.client.core.component.exception;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import org.hbhk.aili.client.core.commons.i18n.I18nManager;
import org.hbhk.aili.client.core.commons.i18n.I18nTemplate;
import org.hbhk.aili.client.core.commons.i18n.II18n;
import org.hbhk.aili.client.core.commons.util.WindowUtil;
import org.hbhk.aili.client.core.core.application.IApplication;
import org.hbhk.aili.client.core.widget.itservice.common.ITServiceConstants;
import org.hbhk.aili.client.core.widget.itservice.common.ITServiceInfoDialog;
import org.hbhk.aili.client.core.widget.msgbox.MsgBox;
/**
* 	和用户交互的异常处理界面，显示详细异常信息并且和
*   根据异常的严重程度，给用户选择的机会。
 */
@SuppressWarnings("serial")
public class ErrorDialog extends JDialog {
	private JPanel jContentPane;
	private JPanel jPanel;
	private JLabel messageLabel;
	private JPanel buttonsPanel;
	private JScrollPane spErrorInfo;
	private JTextArea taErrorDetails;
	private JTabbedPane jTabbedPane;
	private JPanel jPanelInfo;
	private JScrollPane spErrorDetails;
	private JTextArea taErrorInfo;
	private JButton btClose;
	private JCheckBox cbRestartApp;
	private JButton btReportError;
	private boolean showReportError;
	private boolean forceExit;
	
	@SuppressWarnings("unused")
	private boolean showRestart;
	private List<Listener> listeners;
	
	@SuppressWarnings("unused")
	private final IApplication application;
	
	// 国际化对象
	private static final II18n i18n = I18nManager.getI18n(ErrorDialog.class);
	
	/**
	 * 一键上报
	 */
	private JButton btnITService;
	
	/**
	 * IT服务台一键上报Service
	 */
	//private IUploadITService uploadITService=FactoryBean.getIUploadITService();
	
	/**
	 * 
	 * <p>Title: ErrorDialog</p>
	 * <p>Description: 构造方法</p>
	 * @param owner 所属框架
	 * @param application 应用程序
	 * @param title 标题
	 * @param message 信息
	 * @param t 导常
	 * @wbp.parser.constructor
	 */
	public ErrorDialog(final Frame owner, final IApplication application, final String title, final String message,
			final Throwable t) {
		this(owner, application, title, message, t, true);
	}
	
	/**
	 * 
	 * <p>Title: ErrorDialog</p>
	 * <p>Description: 构造方法</p>
	 * @param owner 所属框架
	 * @param application 应用程序
	 * @param title 标题
	 * @param message 信息
	 * @param t 导常
	 * @param showReportError 是否报告错误
	 */
	public ErrorDialog(final Frame owner, final IApplication application, final String title, final String message,
			final Throwable t, boolean showReportError) {
		this(owner, application, title, message, t, showReportError, false);
	}
	
	/**
	 * 
	 * <p>Title: ErrorDialog</p>
	 * <p>Description: 构造方法</p>
	 * @param owner 所属框架
	 * @param application 应用程序
	 * @param title 标题
	 * @param message 信息
	 * @param t 导常
	 * @param showReportError 是否报告错误
	 * @param forceExit 是否强制退出
	 */
	public ErrorDialog(final Frame owner, final IApplication application, final String title, final String message,
			final Throwable t, boolean showReportError, boolean forceExit) {
		this(owner, application, title, message, t, showReportError, forceExit, false);
	}
	
	/**
	 * 
	 * <p>Title: ErrorDialog</p>
	 * <p>Description: 构造方法</p>
	 * @param owner 所属框架
	 * @param application 应用程序
	 * @param title 标题
	 * @param message 信息
	 * @param t 导常
	 * @param showReportError 是否报告错误
	 * @param forceExit 是否强制退出
	 * @param showRestart 是否重新启动
	 */
	public ErrorDialog(final Frame owner, final IApplication application, final String title, final String message,
			final Throwable t, final boolean showReportError, final boolean forceExit,
				final boolean showRestart) {
		super((owner != null) ? owner : JOptionPane.getRootFrame());
		this.application = application;
		this.showReportError = showReportError;
		this.forceExit = forceExit;
		if (!forceExit) {
			this.showRestart = false;
		} else {
			this.showRestart = showRestart;
		}
		
		initialize();
		setTitle(title);
		setLocationRelativeTo(getOwner());
		
		messageLabel.setText(message);
		
		taErrorInfo.setText(getErrorInfo(t));
		taErrorInfo.setCaretPosition(0);
		
		if (showReportError) {
			taErrorDetails.setText(getErrorDetails(t));
			taErrorDetails.setCaretPosition(0);
		}
		
		listeners = new ArrayList<Listener>();
	}
	
	/**
	 * 
	 * <p>Title: getErrorInfo</p>
	 * <p>Description: 获取错误信息</p>
	 * @param t
	 * @return
	 */
	private String getErrorInfo(Throwable t) {
		if (t == null) {
			return "";
		}
		
		if (t instanceof IException) {
			II18n resource = I18nManager.getI18n(ErrorDialog.class);
			IException e = (IException)t;
			II18n eI18n = I18nManager.getI18n(e.getClass());
			String errorCode = e.getErrorCode();
			String nativeMessage = e.getNativeMessage();
			nativeMessage = mergeI18n(eI18n, nativeMessage);
			
			StringBuilder sb = new StringBuilder();
	        String nl = System.getProperty("line.separator"); //$NON-NLS-1$
            // Print exception info.
            sb.append(resource.get("TitleErrorInfo")).append(nl);
            sb.append(
    		"-----------------------------------------------")
    		.append(nl);
            sb.append(resource.get("TitleErrorCode")).append(errorCode).append(nl);
            sb.append(resource.get("TitleErrorDescription")).append(getErrorDescription(eI18n, errorCode)).append(nl);
            sb.append(resource.get("TitleAdditionalErrorInfo")).append(nativeMessage == null ? "" : nativeMessage).append(nl);

            return sb.toString();
		} else {
			return I18nManager.getI18n(ErrorDialog.class).get("UnknownSystemError");
		}
	}

	/**
	 * 
	 * <p>Title: mergeI18n</p>
	 * <p>Description: 合并国际化信息</p>
	 * @param i18n
	 * @param nativeMessage
	 * @return
	 */
	private String mergeI18n(II18n i18n, String nativeMessage) {
		return I18nTemplate.merge(i18n, nativeMessage);
	}

	/**
	 * 
	 * <p>Title: getErrorDescription</p>
	 * <p>Description: 获取错误描述</p>
	 * @param i18n
	 * @param errorCode
	 * @return
	 */
	private String getErrorDescription(II18n i18n, String errorCode) {
		String errorDescription = i18n.get("Error-" + errorCode);		
		return errorDescription == null ? "" : errorDescription;
	}

	/**
	 * 
	 * <p>Title: addListener</p>
	 * <p>Description: 添加监听器</p>
	 * @param listener
	 */
	public void addListener(Listener listener) {
		listeners.add(listener);
	}
	
	/**
	 * 
	 * <p>Title: removeListener</p>
	 * <p>Description: 移除监听器</p>
	 * @param listener
	 */
	public void removeListener(Listener listener) {
		listeners.remove(listener);
	}
	
	/**
	 * 
	 * <p>Title: getErrorDetails</p>
	 * <p>Description: 获取错误详细信息</p>
	 * @param t
	 * @return
	 */
    public String getErrorDetails(final Throwable t) {
        StringBuilder sb = new StringBuilder();
        String nl = System.getProperty("line.separator"); //$NON-NLS-1$
        sb.append(new Date()).append(nl);
        if (t != null) {
            // Print exception details.
            sb.append(nl).append(
                    "-----------------------------------------------")
                    .append(nl);
            sb.append("Exception Details: ").append(nl).append(nl);
            sb.append("Class: ").append(t.getClass().getName()).append(nl);
            sb.append("Message: ").append(t.getMessage()).append(nl);
            printError(t, "Stack trace:", sb);
        }
        
        // Print system properties.
        sb.append(nl).append("-----------------------------------------------")
            .append(nl);
        sb.append("System properties:").append(nl).append(nl); //$NON-NLS-1$
        for (Map.Entry<Object, Object> entry : new TreeMap<Object, Object>(System.getProperties()).entrySet()) {
            sb.append(entry.getKey()).append("=")
                .append(entry.getValue()).append(nl);
        }
        // Print runtime info.
        sb.append(nl).append("-----------------------------------------------")
            .append(nl);
        sb.append("Runtime info:").append(nl).append(nl);
        Runtime rt = Runtime.getRuntime();
        sb.append("Memory TOTAL / FREE / MAX: ")
            .append(rt.totalMemory()).append(" / ")
            .append(rt.freeMemory()).append(" / ")
            .append(rt.maxMemory()).append(nl);
        sb.append("Available processors: ")
            .append(rt.availableProcessors()).append(nl);
        sb.append("System class loader: ").append(""
                + ClassLoader.getSystemClassLoader()).append(nl);
        sb.append("Thread context class loader: ").append(""
                + Thread.currentThread().getContextClassLoader()).append(nl);
        sb.append("Security manager: ").append(""
                + System.getSecurityManager()).append(nl);
        
        return sb.toString();
    }
    
    /**
     * 
     * <p>Title: printError</p>
     * <p>Description: 打印错误信息</p>
     * @param t
     * @param header
     * @param sb
     */
    public void printError(final Throwable t, final String header,
            final StringBuilder sb) {
        if (t == null) {
            return;
        }
        String nl = System.getProperty("line.separator");
        sb.append(nl).append(header).append(nl).append(nl);
        StackTraceElement[] stackTrace = t.getStackTrace();
        for (int i = 0; i < stackTrace.length; i++) {
            sb.append(stackTrace[i].toString()).append(nl);
        }
        Throwable next = t.getCause();
        printError(next, "Caused by " + next, sb);
        if (t instanceof SQLException) {
            // Handle SQLException specifically.
            next = ((SQLException) t).getNextException();
            printError(next, "Next exception: " + next, sb);
        } else if (t instanceof InvocationTargetException) {
            // Handle InvocationTargetException specifically.
            next = ((InvocationTargetException) t).getTargetException();
            printError(next, "Target exception: " + next, sb);
        }
    }
    
    /**
     * 
     * <p>Title: initialize</p>
     * <p>Description: 初始化</p>
     */
    private void initialize() {
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        this.setModal(true);
        this.setTitle("An error has occurred");
        this.setSize(460, 280);
        this.setContentPane(getJContentPane());
        getRootPane().setWindowDecorationStyle(JRootPane.ERROR_DIALOG);
        getRootPane().setDefaultButton(btClose);
        getRootPane().getInputMap(
                JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                "doCloseDefault");
        getRootPane().getActionMap().put("doCloseDefault",
                new AbstractAction() {
            private static final long serialVersionUID = -9167454634726502084L;

            public void actionPerformed(final ActionEvent evt) {
                dispose();
            }
        });
        getRootPane().setDefaultButton(getCloseButton());
    }
    
    /**
     * 
     * <p>Title: getJContentPane</p>
     * <p>Description: 获取内容面板</p>
     * @return
     */
    private JPanel getJContentPane() {
        if (jContentPane == null) {
            BorderLayout borderLayout2 = new BorderLayout();
            jContentPane = new javax.swing.JPanel();
            jContentPane.setLayout(borderLayout2);
            borderLayout2.setVgap(2);
            jContentPane.add(getButtonsPanel(), java.awt.BorderLayout.SOUTH);
            jContentPane.add(getJTabbedPane(), java.awt.BorderLayout.CENTER);
        }
        return jContentPane;
    }
    
    /**
     * 
     * <p>Title: getJPanel</p>
     * <p>Description: 获取面板</p>
     * @return
     */
    private JPanel getJPanel() {
        if (jPanel == null) {
            messageLabel = new JLabel();
            jPanel = new JPanel();
            jPanel.setLayout(new BoxLayout(getJPanel(), BoxLayout.Y_AXIS));
            messageLabel.setText("JLabel");
            jPanel.add(messageLabel, null);
        }
        
        return jPanel;
    }

    
    /**
     * 
     * <p>Title: getButtonsPanel</p>
     * <p>Description: 获取按钮面板</p>
     * @return
     */
    private JPanel getButtonsPanel() {
        if (buttonsPanel == null) {
            FlowLayout flowLayout = new FlowLayout();
            flowLayout.setAlignment(java.awt.FlowLayout.RIGHT);
            buttonsPanel = new JPanel();
            buttonsPanel.setLayout(flowLayout);
            buttonsPanel.add(getRestartCheckBox(), null);
            //取消系统异常一键上报
            //buttonsPanel.add(getItServiceButton(),null);                       
            buttonsPanel.add(getReportErrorButton(), null);            
            buttonsPanel.add(getCloseButton(), null);
        }
        return buttonsPanel;
    }

    /**
     * 
     * <p>Title: getReportErrorButton</p>
     * <p>Description: 获取报告错误的按钮</p>
     * @return
     */
    private JButton getReportErrorButton() {
    	 if (btReportError == null) {
    		 btReportError = new JButton();
    		 btReportError.setText(i18n.get("ReportError"));
    		 btReportError.setSelected(true);
    		 btReportError.addActionListener(new java.awt.event.ActionListener() {
                 public void actionPerformed(java.awt.event.ActionEvent e) {
                	 for (Listener listener : listeners) {
                		 listener.reportError(taErrorInfo.getText(), taErrorDetails.getText());
                	 }
                 }
             });
         }
    	 
    	 if (!showReportError) {
    		 btReportError.setVisible(false);
    	 }
    	 
         return btReportError;
	}

    /**
     * 
     * <p>Title: getCloseButton</p>
     * <p>Description: 获取关闭按钮</p>
     * @return
     */
	private JButton getCloseButton() {
        if (btClose == null) {
            btClose = new JButton();
            if (forceExit) {
            	btClose.setText(i18n.get("ExitApp"));
            } else {
            	btClose.setText(i18n.get("Close"));
            }
            
            btClose.setSelected(true);
            btClose.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                	if (forceExit) {
                		if (cbRestartApp.isSelected()) {
                			for (Listener listener : listeners) {
                				listener.restartApplication();
                			}
                		} else {
                			for (Listener listener : listeners) {
                				listener.exitApplication();
                			}
                		}
                	} else {
                		dispose();
                	}
                }
            });
        }
        
        return btClose;
    }

	/**
	 * 
	 * <p>Title: getJScrollPane</p>
	 * <p>Description: 获取滚动面板</p>
	 * @return
	 */
    private JScrollPane getJScrollPane() {
        if (spErrorInfo == null) {
            spErrorInfo = new JScrollPane();
            spErrorInfo.setViewportView(getErrorInfoComponent());
        }
        return spErrorInfo;
    }

    /**
     * 
     * <p>Title: getErrorDetailsComponent</p>
     * <p>Description: 获取详细信息组件</p>
     * @return
     */
    private JTextArea getErrorDetailsComponent() {
        if (taErrorDetails == null) {
            taErrorDetails = new JTextArea();
            
            taErrorDetails.setBackground(java.awt.SystemColor.control);
            taErrorDetails.setEditable(false);
            taErrorDetails.setOpaque(false);
            taErrorDetails.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(final MouseEvent evt) {
                    if (!evt.isPopupTrigger()) {
                        return;
                    }
                    copyText();
                }
                
                @Override
                public void mouseReleased(final MouseEvent evt) {
                    if (!evt.isPopupTrigger()) {
                        return;
                    }
                    copyText();
                }
                
                private void copyText() {
                    if (taErrorDetails.getSelectedText() != null) {
                        taErrorDetails.copy();
                        return;
                    }
                    taErrorDetails.setSelectionStart(0);
                    taErrorDetails.setSelectionEnd(taErrorDetails.getText().length());
                    taErrorDetails.copy();
                    taErrorDetails.setSelectionEnd(0);
                }
            });
        }
        
        return taErrorDetails;
    }

    /**
     * 
     * <p>Title: getJTabbedPane</p>
     * <p>Description: 获取选项卡面板</p>
     * @return
     */
    private JTabbedPane getJTabbedPane() {
        if (jTabbedPane == null) {
            jTabbedPane = new JTabbedPane();
            jTabbedPane.addTab("Info", null, getPanelInfo(), null);
            jTabbedPane.setTitleAt(0, i18n.get("ErrorInfo"));
            
            if (showReportError) {
            	jTabbedPane.addTab("Details", null, getScrollPaneDetails(), null);
            	jTabbedPane.setTitleAt(1, i18n.get("ErrorDetails"));
            }
        }
        
        return jTabbedPane;
    }

    /**
     * 
     * <p>Title: getPanelInfo</p>
     * <p>Description: 获取信息面板</p>
     * @return
     */
    private JPanel getPanelInfo() {
        if (jPanelInfo == null) {
            jPanelInfo = new JPanel();
            jPanelInfo.setLayout(new BorderLayout());
            jPanelInfo.add(getJPanel(), java.awt.BorderLayout.NORTH);
           	jPanelInfo.add(getJScrollPane(), java.awt.BorderLayout.CENTER);
        }
        
        return jPanelInfo;
    }

    /**
     * 
     * <p>Title: getScrollPaneDetails</p>
     * <p>Description: 获取详细信息滚动面板</p>
     * @return
     */
    private JScrollPane getScrollPaneDetails() {
        if (spErrorDetails == null) {
            spErrorDetails = new JScrollPane();
            spErrorDetails.setViewportView(getErrorDetailsComponent());
        }
        
        return spErrorDetails;
    }

    /**
     * 
     * <p>Title: getErrorInfoComponent</p>
     * <p>Description: 获取错误信息组件</p>
     * @return
     */
    private JTextArea getErrorInfoComponent() {
        if (taErrorInfo == null) {
            taErrorInfo = new JTextArea();
            
            taErrorInfo.setBackground(java.awt.SystemColor.control);
            taErrorInfo.setEditable(false);
            taErrorInfo.setOpaque(false);
            taErrorInfo.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(final MouseEvent evt) {
                    if (!evt.isPopupTrigger()) {
                        return;
                    }
                    copyText();
                }
                
                @Override
                public void mouseReleased(final MouseEvent evt) {
                    if (!evt.isPopupTrigger()) {
                        return;
                    }
                    copyText();
                }
                
                private void copyText() {
                    if (taErrorInfo.getSelectedText() != null) {
                    	taErrorInfo.copy();
                        return;
                    }
                    taErrorInfo.setSelectionStart(0);
                    taErrorInfo.setSelectionEnd(taErrorDetails.getText().length());
                    taErrorInfo.copy();
                    taErrorInfo.setSelectionEnd(0);
                }
            });
        }
        
        return taErrorInfo;
    }

    /**
     * 
     * <p>Title: getRestartCheckBox</p>
     * <p>Description: 获取重启复选框</p>
     * @return
     */
    private JCheckBox getRestartCheckBox() {
        if (cbRestartApp == null) {
            cbRestartApp = new JCheckBox();
            cbRestartApp.setText(i18n.get("RestartApp"));
        }
        
        /*if (!showRestart) {
        	cbRestartApp.setVisible(false);
        }
        
        if (showRestart && forceExit) {
        	cbRestartApp.setSelected(true);
        }*/
        
        return cbRestartApp;
    }
    
    /**
     * 
     * <p>Title: Listener</p>
     * <p>Description: 监听接口定义</p>
     * <p>Company: DEPPON</p>
     * @author Polo Yuan
     * @date 2011-6-14
     *
     */
	public interface Listener {
		void reportError(String errorInfo, String errorDetails);
		void restartApplication();
		void exitApplication();
	}
	
	/**
	 * 一键上报
	 * @author WangQianJin
	 * @return
	 */
	private JButton getItServiceButton() {		
		if (btnITService == null) {
			btnITService = new JButton(i18n.get("pickup.changing.itservice.report"));
			btnITService.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                	boolean isStart= true;// uploadITService.isStartItServiceUpload();
                	//判断是否启用一键上报
        			if(isStart){
        				// 创建弹出窗口
    					ITServiceInfoDialog itDialog = new ITServiceInfoDialog(taErrorDetails.getText(),ITServiceConstants.PKP);				
    					// 剧中显示弹出窗口
    					WindowUtil.centerAndShow(itDialog);
    					// 关闭本窗口
    					dispose();
        			}else{
						MsgBox.showInfo(i18n.get("pickup.itservice.upload.notStart"));
					}
                	
                }
            });
		}
		return btnITService;
	}
	
}
