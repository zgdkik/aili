package org.hbhk.code.mojo;

import java.io.File;
import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.hbhk.code.dao.AbstractTableDao;
import org.hbhk.code.dao.OracleTableDao;
import org.hbhk.code.domain.GeneratorParams;
import org.hbhk.code.domain.Table;
import org.hbhk.code.template.CodeGenerator;

@Mojo(name="gen")
public class GeneratorMojo extends AbstractMojo {

	// **************************  jdbc params *****************************
	@Parameter(required=false,defaultValue="oracle.jdbc.driver.OracleDriver")
	private String jdbcDriverClassName ;
	@Parameter(required=false,defaultValue="jdbc:oracle:thin:@192.168.10.119:1521:fossdb")
	private String jdbcUrl;
	@Parameter(required=false,defaultValue="bse")
	private String jdbcUsername;
	@Parameter(required=false,defaultValue="fossdev")
	private String jdbcPassword;
	@Parameter(required=false,defaultValue="")
	private String tableNames;
	
	@Parameter(defaultValue="${project.parent.artifactId}")
	private String projectName;
	@Parameter(defaultValue="${project.artifactId}")
	private String moduleName;
	@Parameter(defaultValue="${basedir}/target", readonly=true)
	private File targetDir;
	
	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		this.getLog().info("\n *params " + this.toString() + " *");
		
		AbstractTableDao dao = new OracleTableDao(
				AbstractTableDao.initDataSource(this.jdbcUrl, this.jdbcUsername, this.jdbcPassword, this.jdbcDriverClassName));
		
		String[] tables = this.splitTableNames(this.tableNames);
		List<Table> list = null;
		if (tables == null) {
			list = dao.queryAllTables();
		} else {
			list = dao.queryTableByTableName(tables);
		}
		
		this.getLog().info(" *get tables : size=" + list.size() + " *");
		try {
			this.getLog().info(" *start generating codes *");
			initCodeGenerator().generator(list);
			this.getLog().info(" *end generating codes *");
		} catch (Exception e) {
			throw new MojoExecutionException(e.getMessage(), e);
		}
	}

	private String[] splitTableNames(String tableNames) {
		if (tableNames == null || tableNames.trim().equals("")) {
			return null;
		}
		
		return tableNames.toUpperCase().split(",");
	}
	
	private CodeGenerator initCodeGenerator() throws MojoExecutionException {
		GeneratorParams params = new GeneratorParams(projectName, moduleName, targetDir, "dpaptemplate/module-web/");
		return new CodeGenerator(params);
	}

	@Override
	public String toString() {
		return "GeneratorMojoParams [jdbcDriverClassName=" + jdbcDriverClassName
				+ ", jdbcUrl=" + jdbcUrl + ", jdbcUsername=" + jdbcUsername
				+ ", jdbcPassword=" + jdbcPassword + ", projectName="
				+ projectName + ", moduleName=" + moduleName + ", targetDir="
				+ targetDir + "]";
	}
	
}
