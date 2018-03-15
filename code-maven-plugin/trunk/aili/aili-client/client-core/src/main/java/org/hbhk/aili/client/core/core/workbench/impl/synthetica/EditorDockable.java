package org.hbhk.aili.client.core.core.workbench.impl.synthetica;//

import javax.swing.JComponent;
import javax.swing.JScrollPane;

import org.hbhk.aili.client.core.core.workbench.IEditor;

/**
 * 
 * <p>Description: 可停靠编辑器类</p>
 *
 */
public class EditorDockable extends JScrollPane {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7018364805489443328L;
	
	// 编辑器
	private IEditor editor;
	
	/**
	 * 
	 * <p>Title: EditorDockable</p>
	 * <p>Description: 构造函数</p>
	 * @param editor 编辑器
	 * @param comp 组件
	 */
	public EditorDockable(IEditor editor, JComponent comp) {
		super(comp);
		this.editor = editor;
		getVerticalScrollBar().setUnitIncrement(16);
	}
	
	/**
	 * 
	 * <p>Title: getEditor</p>
	 * <p>Description: 获取编辑器</p>
	 * @return
	 */
	public IEditor getEditor() {
		return editor;
	}
}