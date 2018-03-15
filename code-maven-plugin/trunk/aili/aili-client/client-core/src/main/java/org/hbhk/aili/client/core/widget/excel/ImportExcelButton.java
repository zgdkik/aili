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
* Excel文件导入类
 */
public class ImportExcelButton extends JButton {
	private static final long serialVersionUID = 1L;
	
	// 文件选取器
	private final JFileChooser fileChooser;
	/**
	 * 
	 * <p>Title:</p>
	 * <p>Description:实例化，并执行导入功能</p>
	 *
	 * @param table
	 */
	public ImportExcelButton(final JTable table) {
		if (table == null){
			throw new IllegalArgumentException("table == null");
		}
		super.setText("导入Excel");
		fileChooser = new JFileChooser();
		fileChooser.setMultiSelectionEnabled(false);
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setFileFilter(new FileFilter() {
			public String getDescription() {
				return "*.xls";
			}
			public boolean accept(File f) {
				return f.getName().endsWith(".xls");
			}
		});
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileChooser.setVisible(true);
				fileChooser.showOpenDialog(ImportExcelButton.this); // 打开
				File file = fileChooser.getSelectedFile();
				if (file != null) {
					file = new File(file.getAbsolutePath());
					if (file.exists()) {
						try {
							ExcelUtils.importExcel(table, file);
							JOptionPane.showMessageDialog(ImportExcelButton.this, "已成功导入表格数据从Excel文件: " + file.getAbsolutePath(), "成功", JOptionPane.INFORMATION_MESSAGE);
						} catch (Exception t) {
							JOptionPane.showMessageDialog(ImportExcelButton.this, "导入Excel失败，原因: " + t.getMessage() + "，文件：" + file.getAbsolutePath(), "失败", JOptionPane.WARNING_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(ImportExcelButton.this, "导入Excel失败，文件不存在：" + file.getAbsolutePath(), "失败", JOptionPane.WARNING_MESSAGE);
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

}
