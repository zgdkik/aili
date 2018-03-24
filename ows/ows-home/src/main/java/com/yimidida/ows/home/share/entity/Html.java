package com.yimidida.ows.home.share.entity;

import java.util.Date;

import org.mybatis.spring.annotation.Column;
import org.mybatis.spring.annotation.Id;
import org.mybatis.spring.annotation.Table;

@Table("tb_html")
public class Html {
   @Id
   @Column("html_Id")
	private String htmlId;
   @Column("html_Content")
	private String htmlContent;
   @Column("html_Backup")
	private String htmlBackup;
   @Column("menu_Id")
	private String menuId;
   @Column("change_Date")
	private Date changeDate;
   @Column("change_User")
	private String changeUser;
   @Column("comp_code")
    private String compCode;
   
   
	
	public String getCompCode() {
		return compCode;
	}
	public void setCompCode(String compCode) {
		this.compCode = compCode;
	}
	public String getHtmlId() {
		return htmlId;
	}
	public void setHtmlId(String htmlId) {
		this.htmlId = htmlId;
	}
	public String getHtmlContent() {
		return htmlContent;
	}
	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}
	public String getHtmlBackup() {
		return htmlBackup;
	}
	public void setHtmlBackup(String htmlBackup) {
		this.htmlBackup = htmlBackup;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getChangeUser() {
		return changeUser;
	}
	public void setChangeUser(String changeUser) {
		this.changeUser = changeUser;
	}
	public Date getChangeDate() {
		return changeDate;
	}
	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}
	

}
