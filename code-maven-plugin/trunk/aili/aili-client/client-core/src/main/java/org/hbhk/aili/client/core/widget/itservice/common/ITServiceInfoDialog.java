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
 * PROJECT NAME	: pkp-creating
 * 
 * FILE PATH        	: src/main/java/com/deppon/foss/module/pickup/creating/client/ui/customer/QueryOtherChargePanel.java
 * 
 * FILE NAME        	: QueryOtherChargePanel.java
 * 
 * AUTHOR			: FOSS接送货系统开发组
 * 
 * HOME PAGE		: http://www.deppon.com
 * 
 * COPYRIGHT		: Copyright (c) 2012  Deppon All Rights Reserved.
 ******************************************************************************/
/*
 * PROJECT NAME: pkp-creating
 * PACKAGE NAME: com.deppon.foss.module.pickup.creating.client.ui.customer
 * FILE    NAME: QueryOtherChargePanel.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package org.hbhk.aili.client.core.widget.itservice.common;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.client.core.commons.i18n.I18nManager;
import org.hbhk.aili.client.core.commons.i18n.II18n;
import org.hbhk.aili.client.core.core.context.ApplicationContext;
import org.hbhk.aili.client.core.core.context.SessionContext;
import org.hbhk.aili.client.core.widget.msgbox.MsgBox;
import org.hbhk.aili.client.core.widget.service.IUploadITService;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

/**
 * IT服务台一键上报界面
 */
public class ITServiceInfoDialog extends JDialog {

	/**
	 * 序列货标识
	 */
	private static final long serialVersionUID = -1422237527226994454L;
	
	/**
	 * 国际化对象
	 */
	private II18n i18n = I18nManager.getI18n(ITServiceInfoDialog.class);
	
	private static final Log LOG = LogFactory.getLog(ITServiceInfoDialog.class);
	
	/**
	 * 一键上报
	 */
	private JButton btnUpload;
	
	/**
	 * 关闭
	 */
	private JButton btnClose;
	
	/**
	 * 上传panel
	 */
	private UploadITServicePanel uploadITServicePanel;
	
	/**
	 * IT服务台一键上报Service
	 */
	private IUploadITService uploadITService;

	/**
	 * 提示信息
	 */
	private String showMessage;
	/**
	 * 问题类型
	 */
	private String questionType;
	
	/**
	 * 构造方法
	 * @message 提示消息
	 * @qtype 问题类型
	 */
	public ITServiceInfoDialog(String message,String qtype) {
		super(ApplicationContext.getApplication().getWorkbench().getFrame());		
		this.showMessage=message;
		this.questionType=qtype;
		//uploadITService=FactoryBean.getIUploadITService();
		init();
	}

	/**
	 * 初始化界面
	 */
	private void init() {
		getContentPane().setLayout(
				new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("10dlu"),
				ColumnSpec.decode("45dlu"),
				ColumnSpec.decode("10dlu"),
				ColumnSpec.decode("45dlu"),
				ColumnSpec.decode("130dlu"),
				ColumnSpec.decode("10dlu"),},
			new RowSpec[] {
				RowSpec.decode("10dlu"),
				RowSpec.decode("30dlu"),
				RowSpec.decode("30dlu"),
				RowSpec.decode("30dlu"),
				RowSpec.decode("30dlu"),}));
		
			uploadITServicePanel = new UploadITServicePanel();
			getContentPane().add(uploadITServicePanel, "2, 2, 4, 3, fill, fill");
			
			btnUpload = new JButton(i18n.get("pickup.changing.itservice.report"));
			getContentPane().add(btnUpload, "2, 5");
			
			btnClose = new JButton(i18n.get("Close"));
			getContentPane().add(btnClose, "4, 5");
			
			pack();
			
			setModal(true);
			
			/**
			 * 一键上报监听事件
			 */
			btnUpload.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						List<ITServiceVo> itList=new ArrayList<ITServiceVo>();
						//封装一键上报VO
						ITServiceVo itVo=new ITServiceVo();
						//上报人工号
						String empCode = (String)SessionContext.get("FRAMEWORK_KEY_EMPCODE");
						//所属系统
						String systemCode = ITServiceConstants.FOSS;
						itVo.setEmpCode(empCode);
						itVo.setQuestionType(questionType);
						itVo.setShowMessage(showMessage);
						itVo.setSystemCode(systemCode);
						//获取上传图片信息					
						List<UploadPictureDto> uploadVoucherList = uploadITServicePanel.getUploadVoucherList();
						//判断是否上传图片
						if(isUploadPicture(uploadVoucherList)){
							//设置上传信息列表
							itVo.setUploadVoucherList(uploadVoucherList);
							//将上报信息添加到上报列表
							itList.add(itVo);
							//一键上报至IT服务台
							ITServiceResultDto resultDto=uploadITService.uploadItServiceForGui(itList);
							if(resultDto.getIsSuccess()!=null && resultDto.getIsSuccess().booleanValue()){
								MsgBox.showInfo(i18n.get("pickup.itservice.upload.success"));
								// 关闭本窗口
								dispose();
							}else{
								MsgBox.showInfo(resultDto.getFailReason());
							}
						}else{
							//请上传图片！
							MsgBox.showInfo(i18n.get("pickup.itservice.upload.notnull"));						
						}												
					} catch (Exception e1) {
						LOG.error(i18n.get("pickup.itservice.upload.fail")+e1.getMessage());
						MsgBox.showInfo(i18n.get("pickup.itservice.upload.fail")+e1.getMessage());
					}					
				}
			});
			
			/**
			 *关闭监听事件
			 */
			btnClose.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});

	}
	
	/**
	 * 是否上传图片
	 */
	private boolean isUploadPicture(List<UploadPictureDto> uploadVoucherList){
		boolean flag=false;
		if(uploadVoucherList!=null && uploadVoucherList.size()>0){
			for(int i=0;i<uploadVoucherList.size();i++){
				UploadPictureDto pic=uploadVoucherList.get(i);
				//如果有图片转换后的字符流，则有上传图片
				if(pic!=null && pic.getProofBytes()!=null && !"".equals(pic.getProofBytes())){
					flag=true;
					break;
				}
			}
		}
		return flag;		
	}

}