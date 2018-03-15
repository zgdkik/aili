package org.hbhk.aili.client.core.widget.excel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
* excel文件导入导出工具类
 */
public final class ExcelUtils {
	
	private ExcelUtils(){
		
	}
	
	/**
	 * 
	 * @Title:exportCSV
	 * @Description:把JTable的内容导出到文件中
	 * @param @param table
	 * @param @param file
	 * @param @throws IOException
	 * @return void
	 * @throws
	 */
	public static void exportCSV(JTable table, File file) throws IOException {
		if (table == null){
			throw new IllegalArgumentException("table == null");
		}
		if (file == null){
			throw new IllegalArgumentException("file == null");
		}
			
		exportCSV((DefaultTableModel) table.getModel(), new FileWriter(file));
	}
	/**
	 * 
	 * @Title:exportCSV
	 * @Description:把DefaultTableModel中的内容导出到Writer中
	 * @param @param model
	 * @param @param writer
	 * @param @throws IOException
	 * @return void
	 * @throws
	 */
	public static void exportCSV(DefaultTableModel model, Writer writer) throws IOException {
		
		try {
			
			// 总行数，不包括列头
			int rows = model.getRowCount();
			// 列数
			int columns = model.getColumnCount();
			// 导出标题
			for (int column = 0; column < columns; column ++) {
				if (column > 0){
					writer.write(",");
				}
				writer.write(filterComma(model.getColumnName(column)));
			}
			// 导出所有行
			for (int row = 0; row < rows; row ++) {
				writer.write("\n");
				for (int column = 0; column < columns; column ++) {
					if (column > 0){
						writer.write(",");
					}
					writer.write(filterComma(model.getValueAt(row, column)));
				}
			}
		} finally {
			writer.close();
		}
	}
	/**
	 * 
	 * @Title:importCSV
	 * @Description:解析文件内容，把文件内容导入到JTable中
	 * @param @param table
	 * @param @param file
	 * @param @throws IOException
	 * @return void
	 * @throws
	 */
	public static void importCSV(JTable table, File file) throws IOException {
		if (table == null){
			throw new IllegalArgumentException("table is not allowed null");
		}
			
		if (file == null){
			throw new IllegalArgumentException("file is not allowed null");
		}
		importCSV((DefaultTableModel) table.getModel(), new FileReader(file));
	}
	/**
	 * 
	 * @Title:importCSV
	 * @Description:读取Reader中的内容，把内容导入到DefaultTableModel中
	 * @param @param model
	 * @param @param reader
	 * @param @throws IOException
	 * @return void
	 * @throws
	 */
	public static void importCSV(DefaultTableModel model, Reader reader) throws IOException {
		if (model == null){
			throw new IllegalArgumentException("model == null");
		}
		if (reader == null){
			throw new IllegalArgumentException("reader == null");
		}
		try {
			
			BufferedReader br = new BufferedReader(reader);
			String line = br.readLine();
			if (line == null) {
				return;
			}
			String[] headers = line.split(",");
			int columns = model.getColumnCount();
			int len = Math.min(columns, headers.length);
			for (int column = 0; column < len; column ++) {
				if (! model.getColumnName(column).equals(headers[column])) {
					throw new IllegalStateException("Csv.columnHeaders != Table.columnHeaders");
				}
			}
			int row = 0;
			while ((line = br.readLine()) != null) {
				String[] cells = line.split(",");
				if (cells.length != columns) {
					String[] dest = new String[columns];
					System.arraycopy(cells, 0, dest, 0, Math.min(columns, cells.length));
					cells = dest;
				}
				model.addRow(cells);
				row ++;
			}
		} finally {
			reader.close();
		}
	}
	/**
	 * 
	 * @Title:filterComma
	 * @Description:过滤掉CSV文件中的逗号
	 * @param @param value
	 * @param @return
	 * @return String
	 * @throws
	 */
	private static String filterComma(Object value) {
		if (value == null) {
			return "";
		}
		String str = value.toString();
		if (str.contains(",")) {
			return "\"" + value + "\"";
		}
		return str;
	}
	/**
	 * 
	 * @Title:exportExcel
	 * @Description:把JTable中的内容以excel格式导出到文件中
	 * @param @param table
	 * @param @param file
	 * @param @throws IOException
	 * @return void
	 * @throws
	 */
	public static void exportExcel(JTable table, File file) throws IOException {
		if (table == null){
			throw new IllegalArgumentException("table does not allow null");
		}
			
		if (file == null){
			throw new IllegalArgumentException("file does not allow null");
		}
		exportExcel((DefaultTableModel) table.getModel(), new FileOutputStream(file));
	}
	/**
	 * 
	 * @Title:exportExcel
	 * @Description:把DefaultTableModel中的内容以excel格式导出到输出流中
	 * @param @param model
	 * @param @param output
	 * @param @throws IOException
	 * @return void
	 * @throws
	 */
	public static void exportExcel(DefaultTableModel model, OutputStream output) throws IOException {
		
		if (model == null){
			throw new IllegalArgumentException("model does not allow  null");
		}
		if (output == null){
			throw new IllegalArgumentException("outputstream does not allow null");
		}
			
		try {
			
			ExcelExporter exporter = new ExcelExporter(output);
			// 总行数，不包括列头
			int rows = model.getRowCount();
			// 列数
			int columns = model.getColumnCount();
			// 导出标题
			exporter.createRow(0);
			for (int column = 0; column < columns; column ++) {
				exporter.setCell(column, model.getColumnName(column));
			}
			// 导出所有行
			for (int row = 1; row <= rows; row ++) {
				exporter.createRow(row);
				for (int column = 0; column < columns; column ++) {
					Object value = model.getValueAt(row - 1, column);
					if (value == null) {
						exporter.setCell(column, "");
					} else if (value instanceof Double || value instanceof Float) {
						exporter.setCell(column, ((Number) value).doubleValue());
					} else if (value instanceof Number) {
						exporter.setCell(column, ((Number) value).intValue());
					} else if (value instanceof Calendar) {
						exporter.setCell(column, (Calendar) value);
					} else if (value instanceof Date) {
						Calendar calendar =  Calendar.getInstance();
						calendar.setTime((Date) value);
						exporter.setCell(column, calendar);
					} else {
						exporter.setCell(column, value.toString());
					}
				}
			}
			exporter.export();
		} finally {
			output.close();
		}
	}
	/**
	 * 
	 * @Title:importExcel
	 * @Description:解析excel文件中的内容，导入到JTable对象中
	 * @param @param table
	 * @param @param file
	 * @param @throws IOException
	 * @return void
	 * @throws
	 */
	public static void importExcel(JTable table, File file) throws IOException {
		if (table == null){
			throw new IllegalArgumentException("table does not allow  null");
		}
			
		if (file == null){
			throw new IllegalArgumentException("file does not allow null");
		}
		importExcel((DefaultTableModel) table.getModel(), new FileInputStream(file));
	}
	/**
	 * 
	 * @Title:importExcel
	 * @Description:读取输入流中的数据，导入到DefaultTableModel对象中
	 * @param @param model
	 * @param @param input
	 * @param @throws IOException
	 * @return void
	 * @throws
	 */
	public static void importExcel(DefaultTableModel model, InputStream input) throws IOException {
		if (model == null){
			throw new IllegalArgumentException("model does not allow  null");
		}
			
		if (input == null){
			throw new IllegalArgumentException("file does not allow null");
		}
			
		try {
			ExcelImporter importer = new ExcelImporter(input);
			// 总行数，包括列头
			int rows = importer.getRowCount();
			if (rows == 0){
				return;
			}
			int columns = model.getColumnCount();
			// 检查第一行列头是否一致
			String[] headers = importer.readExcelLine(0);
			if (headers == null){
				return;
			}
			int len = Math.min(columns, headers.length);
			for (int column = 0; column < len; column ++) {
				if (! model.getColumnName(column).equals(headers[column])) {
					throw new IllegalStateException("Excel.columnHeaders != Table.columnHeaders");
				}
			}
			// 读取所有行
			for (int row = 1; row < rows; row ++) {
				String[] cells = importer.readExcelLine(row);
				if (cells.length != columns) {
					String[] dest = new String[columns];
					System.arraycopy(cells, 0, dest, 0, Math.min(columns, cells.length));
					cells = dest;
				}
				model.addRow(cells);
			}
		} finally {
			input.close();
		}
	}

}
