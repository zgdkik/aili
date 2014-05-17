package org.hbhk.code.domain;

import java.io.File;


public class GeneratorParams {
	
	private String projectName;
	
	private String moduleName;

	private File targetDir;
	
	private File templateDir;
	
	/** 模板文件的根目录 */
	private String templatePath;

	public GeneratorParams(String projectName, String moduleName, File targetDir, File templateDir) {
		this.projectName = projectName;
		this.moduleName = moduleName;
		this.targetDir = targetDir;
		this.templateDir = templateDir;
	}

	public GeneratorParams(String projectName, String moduleName, File targetDir, String templatePath) {
		this.projectName = projectName;
		this.moduleName = moduleName;
		this.targetDir = targetDir;
		this.templatePath = templatePath;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	/** 目标目录的根目录 */
	public File getTargetDir() {
		return targetDir;
	}

	public void setTargetDir(File targetDir) {
		this.targetDir = targetDir;
	}

	public File getTemplateDir() {
		return templateDir;
	}

	public void setTemplateDir(File templateDir) {
		this.templateDir = templateDir;
	}

	public String getTemplatePath() {
		return templatePath;
	}

	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}
	
}
