/**
 *  initial comments.
 */
/*******************************************************************************
 * Copyright 2013 PKP
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *    http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * PROJECT NAME	: pkp-common
 * 
 * FILE PATH        	: src/main/java/com/deppon/foss/module/pickup/common/client/ui/commonUI/LinkedButtonColumn.java
 * 
 * FILE NAME        	: LinkedButtonColumn.java
 * 
 * AUTHOR			: FOSS接送货系统开发组
 * 
 * HOME PAGE		: http://www.deppon.com
 * 
 * COPYRIGHT		: Copyright (c) 2012  Deppon All Rights Reserved.
 ******************************************************************************/
/*
 * Copyright by Deppon and the original author or authors.
 * 
 * This document only allow internal use ,Any of your behaviors using the file
 * not internal will pay legal responsibility.
 *
 * You may learn more information about Deppon from
 *
 *      http://www.deppon.com
 *
 */
package org.hbhk.aili.client.core.widget.itservice.common;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;


/**
 * 
 * 带下划线Button样式列
 */
public class LinkedButtonColumn extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, ActionListener {
	
	/**
	 * 序列化版本号
	 */
	private static final long serialVersionUID = 531762441230966753L;

	@SuppressWarnings("unused")
	private JTable table;

	/**
	 * 不可编辑状态下按钮
	 */
	private JLabel renderButton;

	/**
	 * 编辑状态下按钮
	 */
	private JButton editButton;

	/**
	 * 按钮文本
	 */
	private String text;

	/**
	 * 按钮事件监听
	 */
	private ActionListener listener;

	LinkedButtonColumn(JTable table, int column) {
		super();
		//初始化按钮
		this.table = table;
		renderButton = new JLabel();
		editButton = new JButton();
		//设置监听
		editButton.addActionListener(this);
		//添加样式
		renderButton.setHorizontalAlignment(JLabel.CENTER);
		editButton.setFocusPainted(false);
		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(column).setCellRenderer(this);
		columnModel.getColumn(column).setCellEditor(this);
	}
	
	LinkedButtonColumn(JTable table, int column, int width) {
		this(table, column);
		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(column).setMaxWidth(width);
		columnModel.getColumn(column).setMinWidth(width);
	}

	/**
	 * 
	 * 非编辑状态下渲染组件
	 */
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		if (hasFocus) {
			renderButton.setForeground(table.getForeground());
			renderButton.setBackground(UIManager.getColor("Button.background"));
		} else if (isSelected) {
			renderButton.setForeground(table.getSelectionForeground());
			renderButton.setBackground(table.getSelectionBackground());
		} else {
			renderButton.setForeground(table.getForeground());
			renderButton.setBackground(UIManager.getColor("Button.background"));
		}
		renderButton.setEnabled(table.isEnabled());
		String txt = (value == null) ? "" : value.toString();
		renderButton.setText(getHtmlText(txt));
		return renderButton;
	}

	private String getHtmlText(String txt) {
		return "<html><font color=blue><u>&nbsp;"+txt+"&nbsp;</u></font></html>";
	}


	/**
	 * 
	 * 编辑状态下渲染组件
	 */
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		text = (value == null) ? "" : value.toString();
		editButton.setText(getHtmlText(text));
		return editButton;
	}

	/**
	 * 
	 * 编辑状态下文本值
	 */
	public Object getCellEditorValue() {
		return text;
	}


	/**
	 * 
	 * 添加按钮监听
	 */
	public void addActionListener(ActionListener listener) {
		this.listener = listener;
	}

	/**
	 * 
	 * 回调监听事件
	 */
	public void actionPerformed(ActionEvent e) {
		fireEditingStopped();
		if (listener != null) {
			listener.actionPerformed(e);
		}

	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		JTable table = new JTable(new Object[][]{{"1","1","1"},{"2","2","2"}},new Object[]{"col1","col2","col3"});
		TableFactory.createLinkedButtonColumn(table, 2);
		frame.add(table);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(200, 100);
		frame.setVisible(true);
	}
}