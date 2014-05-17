package org.hbhk.code.template;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.maven.plugin.MojoExecutionException;
import org.hbhk.code.dao.AbstractTableDao;
import org.hbhk.code.dao.OracleTableDao;
import org.hbhk.code.domain.GeneratorParams;
import org.hbhk.code.domain.Table;
import org.hbhk.code.template.CodeGenerator;
import org.hbhk.code.template.FreeMarkerConfig;
import org.junit.Before;
import org.junit.Test;

public class CodeGeneratorTest {
	
	static String TEMPLATE_DIR = CodeGenerator.class.getClassLoader().getResource("template/module-web").getFile();
	static String TARGET_DIR = "D:/test";
	
	CodeGenerator codegen;
	List<Table> list = new ArrayList<Table>();
	private String ids;
	@Before
	public void setUp() throws MojoExecutionException {
		GeneratorParams params = new GeneratorParams("foss", "authorization", new File(TARGET_DIR), new File(TEMPLATE_DIR));
		codegen = new CodeGenerator(
				FreeMarkerConfig.getInstance().getConfiguration(new File(TEMPLATE_DIR)), 
				params);
		this.initTables();
	}
	
	public void initTables() {
		AbstractTableDao dao = new OracleTableDao(
				AbstractTableDao.initDataSource(
						"jdbc:oracle:thin:@127.0.0.1:1521:HBHK", "hbhk", "as135246", "oracle.jdbc.driver.OracleDriver"));
		//this.list = dao.queryAllTables();
		this.list = dao.queryTableByTableName(new String[]{"T_CONFLICT"});
	}
	
	@Test
	public void testGenerator() throws Exception {
		this.codegen.generator(list);
	}
	
	public static void main(String[] args) {
		System.out.println("src".replace("${}", "ray"));
		System.out.println(new File(TEMPLATE_DIR).exists());
		System.out.println(new File(TEMPLATE_DIR).getAbsolutePath());
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	
	
}
