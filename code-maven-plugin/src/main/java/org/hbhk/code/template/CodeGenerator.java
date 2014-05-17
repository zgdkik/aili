package org.hbhk.code.template;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.JarURLConnection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.maven.plugin.MojoExecutionException;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.hbhk.code.domain.GeneratorParams;
import org.hbhk.code.domain.Table;
import org.hbhk.code.util.StringUtils;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class CodeGenerator {
	
	private Configuration config;

	private GeneratorParams params;

	private JarFile jarfile;

	public CodeGenerator(Configuration config, GeneratorParams params) {
		this.config = config;
		this.params = params;
	}

	public CodeGenerator(GeneratorParams params) throws MojoExecutionException {
		this.params = params;
		this.config = FreeMarkerConfig.getInstance().createConfiguration(params.getTemplatePath());
		this.jarfile = this.initJarFile(params.getTemplatePath());
	}

	private JarFile initJarFile(String jarpath) throws MojoExecutionException {
		PathMatchingResourcePatternResolver ss = new PathMatchingResourcePatternResolver();
		JarURLConnection jarCon;
		try {
			jarCon = (JarURLConnection) ss.getResource(jarpath).getURL().openConnection();
			return jarCon.getJarFile();
		} catch (IOException e) {
			throw new MojoExecutionException("cant't get jarfile from " + jarpath);
		}
	}
	
	public void generator(List<Table> tables) throws Exception {
		Enumeration<JarEntry> entries = this.jarfile.entries();
		JarEntry jarentry = null;
		while (entries.hasMoreElements()) {
			jarentry = entries.nextElement();
			this.resolveFile(jarentry, tables);
		}
	}

	private void resolveFile(JarEntry jarentry, List<Table> tables) throws Exception {
		String filepath = jarentry.getName();
		if (!jarentry.getName().startsWith(this.params.getTemplatePath())) {
			return;
		}
		
		String sourcePath = filepath.substring(this.params.getTemplatePath().length());
		if (jarentry.isDirectory()) {
			createDir(sourcePath);
		} else {
			createFile(sourcePath, tables);
		}
	}
	
	void resolveFile(String relativePath, File sourceFile, List<Table> tables) throws Exception {
		if (sourceFile.isHidden()) {
			return;
		}

		if (sourceFile.isDirectory()) {
			relativePath = createDir(relativePath, sourceFile);

			File[] files = sourceFile.listFiles();
			for (File file : files) {
				resolveFile(relativePath, file, tables);
			}
		} else {
			String tempPath = sourceFile.getAbsolutePath().substring(
					this.params.getTemplateDir().getAbsolutePath().length());
			createFile(relativePath, sourceFile.getName(), tables, tempPath);
		}
	}
	
	private void createFile(String sourcePath, List<Table> tables) throws Exception {
		Map<String, Object> map = initRoot(tables);
		if (sourcePath.indexOf(ParamConstants.ITERATOR.expression()) > -1) {
			for (Table table : tables) {
				map.put("table", table);
				String targetPath = ParamConstants.ITERATOR.replace(sourcePath, "");
				targetPath = this.resolveFilename(targetPath, table.getTypeName());
				this.createFile(targetPath, sourcePath, map, tables);
			}
		} else {
			String targetPath = this.resolveFilename(sourcePath);
			this.createFile(targetPath, sourcePath, map, tables);
		}
	}
	
	private void createFile(String relativePath, String filename, List<Table> tables, String tempPath) throws Exception {
		Map<String, Object> map = initRoot(tables);
		if (filename.startsWith(ParamConstants.ITERATOR.expression())) {
			for (Table table : tables) {
				map.put("table", table);
				String actualName = ParamConstants.ITERATOR.replace(filename, "");
				actualName = this.resolveFilename(actualName, table.getTypeName());
				this.createFile(relativePath, tempPath, map, actualName, tables);
			}
		} else {
			String actualName = this.resolveFilename(filename);
			this.createFile(relativePath, tempPath, map, actualName, tables);
		}
	}

	/**
	 * 创建文件
	 * @param targetPath 目标文件的相对路径(相对targetDir)
	 * @param sourcePath 模板文件的相对路径(相对templatePath)
	 * @param map
	 * @param tables
	 * @throws Exception
	 */
	private void createFile(String targetPath, String sourcePath, Map<String, Object> map, List<Table> tables) throws Exception {
		File targetFile = new File(this.params.getTargetDir(), targetPath);
		if (targetFile.exists()) {
			if (targetFile.getName().equals("spring.xml")) {
				creatSpringbean(targetFile, tables);
			} else if (targetFile.getName().equals("struts.xml")) {
				creatStrutsbean(targetFile, tables);
			}
		} else {
			this.generatorCodes(sourcePath, targetFile, map);
		}
	}
	
	/** 创建文件 */
	private void createFile(String relativePath, String tempPath,
			Map<String, Object> map, String actualName, List<Table> tables) throws Exception {
		File targetFile = new File(this.params.getTargetDir(), relativePath + File.separator + actualName);
		if (targetFile.exists()) {
			if (targetFile.getName().equals("spring.xml")) {
				creatSpringbean(targetFile, tables);
			} else if (targetFile.getName().equals("struts.xml")) {
				creatStrutsbean(targetFile, tables);
			}
		} else {
			this.generatorCodes(tempPath, targetFile, map);
		}
	}

	@SuppressWarnings("rawtypes")
	private void creatSpringbean(File file, List<Table> tables)
			throws Exception {

		String projectName = params.getProjectName();
		String moduleName = params.getModuleName();
		StringBuilder packagename = new StringBuilder("com.deppon");
		packagename.append("." + projectName);
		// com.deppon.foss.module.authorization.server
		packagename.append(".module");
		packagename.append("." + moduleName);
		packagename.append(".server.");

		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(file);
		Element root = document.getRootElement();// 获取根节点名称
		List existEle = root.elements("bean");
		for (int i = 0; i < tables.size(); i++) {
			Table tab = tables.get(i);
			String modelName = StringUtils.uncapitalize(tab.getTypeName());
			boolean daoNameexist = true;
			boolean serviceNameexist = true;
			boolean actionNameexist = true;

			String daoName = modelName + "Dao";
			String serviceName = modelName + "Service";
			String actionName = modelName + "Action";

			for (int j = 0; j < existEle.size(); j++) {
				Element ele = (Element) existEle.get(j);
				String name = ele.attribute("id").getValue();
				if (daoName.equals(name)) {
					daoNameexist = false;
				}
				if (serviceName.equals(name)) {
					serviceNameexist = false;
				}
				if (actionName.equals(name)) {
					actionNameexist = false;
				}
			}
			// dao
			if (daoNameexist) {
				Element dao = root.addElement("bean");
				dao.addAttribute("id", daoName);
				dao.addAttribute("class",
						packagename + "dao.impl." + tab.getTypeName() + "Dao");
				Element daoproperty = dao.addElement("property");
				daoproperty.addAttribute("name", "sqlSessionFactory");
				daoproperty.addAttribute("ref", "sqlSessionFactory");
			}
			// service
			if (serviceNameexist) {
				Element service = root.addElement("bean");
				service.addAttribute("id", serviceName);
				service.addAttribute("class", packagename + "service.impl."
						+ tab.getTypeName() + "Service");
				Element serviceproperty = service.addElement("property");
				serviceproperty.addAttribute("name", modelName + "Dao");
				serviceproperty.addAttribute("ref", modelName + "Dao");
			}
			if (actionNameexist) {
				// action
				Element action = root.addElement("bean");
				action.addAttribute("id", actionName);
				action.addAttribute("class",
						packagename + "action." + tab.getTypeName() + "Action");
				Element actionproperty = action.addElement("property");
				actionproperty.addAttribute("name", modelName + "Service");
				actionproperty.addAttribute("ref", modelName + "Service");
			}

		}

		OutputFormat opf = new OutputFormat("\t", true, "UTF-8");
		opf.setTrimText(true);
		XMLWriter writer = new XMLWriter(new FileOutputStream(file), opf);
		writer.write(document);
		writer.close();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void creatStrutsbean(File file, List<Table> tables)
			throws Exception {
		String moduleName = params.getModuleName();
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(file);
		Element root = document.getRootElement();// 获取根节点名称
		List struts = root.selectNodes("package");
		for (int i = 0; i < tables.size(); i++) {
			Element addpackage = null;
			for (int j = 0; j < struts.size(); j++) {
				Element ele = (Element) struts.get(j);
				if (ele.attribute("name").getValue().equals(moduleName)) {
					addpackage = ele;
				}
			}

			Table tab = tables.get(i);
			String modelName = StringUtils.uncapitalize(tab.getTypeName());
			String maxmodelName = tab.getTypeName();
			if (addpackage != null) {

				boolean jspflag = true;
				boolean saveflag = true;
				boolean delflag = true;
				boolean upflag = true;
				boolean quallflag = true;
				boolean qbyeflag = true;

				String jspName = modelName;
				String saveName = "insert" + maxmodelName;
				String delName = "delete" + maxmodelName;
				String upName = "update" + maxmodelName;
				String quallName = "queryAll" + maxmodelName;
				String qbyeName = "queryBy" + maxmodelName;

				List<Element> actions = addpackage.selectNodes("action");

				for (int j = 0; j < actions.size(); j++) {
					Element ele = actions.get(j);
					if (ele.attribute("name").getValue().equals(jspName)) {
						jspflag = false;
					}
					if (ele.attribute("name").getValue().equals(saveName)) {
						saveflag = false;
					}
					if (ele.attribute("name").getValue().equals(delName)) {
						delflag = false;
					}
					if (ele.attribute("name").getValue().equals(upName)) {
						upflag = false;
					}
					if (ele.attribute("name").getValue().equals(quallName)) {
						quallflag = false;
					}
					if (ele.attribute("name").getValue().equals(qbyeName)) {
						qbyeflag = false;
					}

				}
				// 页面
				if (jspflag) {
					Element jsp = addpackage.addElement("action");
					jsp.addAttribute("name", jspName);
					jsp.addAttribute("class", modelName + "Action");
					Element result1 = jsp.addElement("result");
					result1.addAttribute("name", "success");
					result1.setText(tab.getTypeName() + ".jsp");
				}
				// save
				if (saveflag) {
					Element save = addpackage.addElement("action");
					save.addAttribute("name", saveName);
					save.addAttribute("class", modelName + "Action");
					save.addAttribute("method", "insert" + maxmodelName);
					Element saveresult1 = save.addElement("result");
					saveresult1.addAttribute("name", "success");
					saveresult1.addAttribute("type", "json");
					Element saveresult2 = save.addElement("result");
					saveresult2.addAttribute("name", "error");
					saveresult2.addAttribute("type", "json");
				}
				// delete
				if (delflag) {
					Element delete = addpackage.addElement("action");
					delete.addAttribute("name", delName);
					delete.addAttribute("class", modelName + "Action");
					delete.addAttribute("method", "delete" + maxmodelName);
					Element deleteresult1 = delete.addElement("result");
					deleteresult1.addAttribute("name", "success");
					deleteresult1.addAttribute("type", "json");
					Element deleteresult2 = delete.addElement("result");
					deleteresult2.addAttribute("name", "error");
					deleteresult2.addAttribute("type", "json");
				}
				// update
				if (upflag) {
					Element update = addpackage.addElement("action");
					update.addAttribute("name", upName);
					update.addAttribute("class", modelName + "Action");
					update.addAttribute("method", "update" + maxmodelName);
					Element updateresult1 = update.addElement("result");
					updateresult1.addAttribute("name", "success");
					updateresult1.addAttribute("type", "json");
					Element updateresult2 = update.addElement("result");
					updateresult2.addAttribute("name", "error");
					updateresult2.addAttribute("type", "json");
				}

				// queryAll
				if (quallflag) {
					Element queryAll = addpackage.addElement("action");
					queryAll.addAttribute("name", quallName);
					queryAll.addAttribute("class", modelName + "Action");
					queryAll.addAttribute("method", "queryAll" + maxmodelName);
					Element queryAllresult1 = queryAll.addElement("result");
					queryAllresult1.addAttribute("name", "success");
					queryAllresult1.addAttribute("type", "json");
					Element queryAllresult2 = queryAll.addElement("result");
					queryAllresult2.addAttribute("name", "error");
					queryAllresult2.addAttribute("type", "json");
				}
				// queryByEntity
				if (qbyeflag) {
					Element queryByEntity = addpackage.addElement("action");
					queryByEntity.addAttribute("name", qbyeName);
					queryByEntity.addAttribute("class", modelName + "Action");
					queryByEntity.addAttribute("method", "queryBy"
							+ maxmodelName);
					Element queryByEntityresult1 = queryByEntity
							.addElement("result");
					queryByEntityresult1.addAttribute("name", "success");
					queryByEntityresult1.addAttribute("type", "json");
					Element queryByEntityresult2 = queryByEntity
							.addElement("result");
					queryByEntityresult2.addAttribute("name", "error");
					queryByEntityresult2.addAttribute("type", "json");
				}
				OutputFormat opf = new OutputFormat("\t", true, "UTF-8");
				opf.setTrimText(true);
				XMLWriter writer = new XMLWriter(new FileOutputStream(file),
						opf);
				writer.write(document);
				writer.close();
			}
		}
	}

	// tableList
	private Map<String, Object> initRoot(List<Table> tables) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("module", this.params.getModuleName());
		map.put("project", this.params.getProjectName());
		map.put("tableList", tables);
		return map;
	}

	private String createDir(String relativePath) {
		String actualPath = resolveFilename(relativePath);
		
		File targetFile = new File(this.params.getTargetDir(), actualPath);

		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		return actualPath;
	}
	
	private String createDir(String relativePath, File sourceFile) {
		String actualName = resolveFilename(sourceFile.getName());
		if (relativePath == null) {
			relativePath = actualName;
		} else {
			relativePath = relativePath + File.separator + actualName;
		}
		File targetFile = new File(this.params.getTargetDir(), relativePath);

		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		return relativePath;
	}

	/** 替换文件名 */
	private String resolveFilename(String fileName) {
		return resolveFilename(fileName, null);
	}

	/** 替换文件名 */
	private String resolveFilename(String fileName, String typename) {
		fileName = ParamConstants.PROJECT.replace(fileName, this.params.getProjectName());
		fileName = ParamConstants.MODULE.replace(fileName, this.params.getModuleName());
		if (typename != null) {
			// 首字母小写
			fileName = ParamConstants.TYPENAME.replace(fileName, typename);
		}
		return fileName;
	}

	private void generatorCodes(String tempPath, File targetFile, Object templateData) throws Exception {
		Template template = config.getTemplate(tempPath);
		template.process(templateData, new OutputStreamWriter(new FileOutputStream(targetFile)));
	}

}
