package org.hbhk.aili.client.core.widget.excel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;
/**
 *Excel文件导出类
 */
public class ExportExcelButton extends JButton {
	
	private static final long serialVersionUID = 1L;
	
	// 文件选取器
	private final JFileChooser fileChooser;
	
	/**
	 * 
	 * <p>Title:ExportExcelButton</p>
	 * <p>Description:实例化并执行导出功能</p>
	 *
	 * @param table
	 */
	public ExportExcelButton(final JTable table) {
		if (table == null){
			throw new IllegalArgumentException("target component table can not be null");
		}
		super.setText("导出Excel");
		fileChooser = new JFileChooser();
		fileChooser.setMultiSelectionEnabled(false);
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setFileFilter(new ExcelFileFilter());
		this.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent event) {
				fileChooser.setVisible(true);
				fileChooser.showSaveDialog(ExportExcelButton.this); // 保存
				File file = fileChooser.getSelectedFile();
				if (file != null) {
					file = new File(file.getAbsolutePath());
					if (file.exists()) {
						final int confirm = JOptionPane.showConfirmDialog(ExportExcelButton.this, "Excel文件已存在，是否覆盖？", "覆盖", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (confirm != JOptionPane.YES_OPTION) {
							return;
						}
					}
					try {
						ExcelUtils.exportExcel(table, file);
						JOptionPane.showMessageDialog(ExportExcelButton.this, "已成功导出表格数据到Excel文件: " + file.getAbsolutePath(), "成功", JOptionPane.INFORMATION_MESSAGE);
					} catch (Exception t) {
						JOptionPane.showMessageDialog(ExportExcelButton.this, "导出Excel失败，原因: " + t.getMessage() + "，文件：" + file.getAbsolutePath(), "失败", JOptionPane.WARNING_MESSAGE);
					}
				}
				fileChooser.setVisible(false);
			}
		});
	}
	/**
	 * 
	 * @Title:getFileChooser
	 * @Description:获取文件选取器
	 * @param @return
	 * @return JFileChooser
	 * @throws
	 */
	public JFileChooser getFileChooser() {
		return fileChooser;
	}
	
	private class ExcelFileFilter extends FileFilter{

		public String getDescription() {
			return "*.xls";
		}
		public boolean accept(final File file) {
			return file.getName().endsWith(".xls");
		}
		
	}

}
