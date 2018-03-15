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
 * Description:CSV文件导出类
 */
public class ExportCsvButton extends JButton {

	private static final long serialVersionUID = 1L;

	private final JFileChooser fileChooser;

	/**
	 * 
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:实例化，并执行导出功能
	 * </p>
	 * 
	 * @param table
	 */
	public ExportCsvButton(final JTable table) {
		if (table == null){
			throw new IllegalArgumentException("table does not allow null");
		}
			
		super.setText("导出CSV");
		fileChooser = new JFileChooser();
		fileChooser.setMultiSelectionEnabled(false);
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setFileFilter(new ExportCsvFileFilter());
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileChooser.setVisible(true);
				fileChooser.showSaveDialog(ExportCsvButton.this); // 保存
				File file = fileChooser.getSelectedFile();
				if (file != null) {
					file = new File(file.getAbsolutePath());
					if (file.exists()) {
						int confirm = JOptionPane.showConfirmDialog(ExportCsvButton.this, "CSV文件已存在，是否覆盖？", "覆盖", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (confirm != JOptionPane.YES_OPTION) {
							return;
						}
					}
					try {
						ExcelUtils.exportCSV(table, file);
						JOptionPane.showMessageDialog(ExportCsvButton.this, "已成功导出表格数据到CSV文件: " + file.getAbsolutePath(), "成功", JOptionPane.INFORMATION_MESSAGE);
					} catch (Exception t) {
						JOptionPane.showMessageDialog(ExportCsvButton.this, "导出CSV失败，原因: " + t.getMessage() + "，文件：" + file.getAbsolutePath(), "失败", JOptionPane.WARNING_MESSAGE);
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

	private static class ExportCsvFileFilter extends FileFilter {
		public String getDescription() {
			return "*.csv";
		}

		public boolean accept(File f) {
			return f.getName().endsWith(".csv");
		}

	}

}
