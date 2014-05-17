1.在目标模块的pom.xml里面添加如下插件：
<plugin>
	<groupId>com.deppon.dpap</groupId>
	<artifactId>codeg-maven-plugin</artifactId>
	<version>2.0.0-SNAPSHOT</version>
	<configuration>
		<jdbcDriverClassName>oracle.jdbc.driver.OracleDriver</jdbcDriverClassName>
		<jdbcUrl>jdbc:oracle:thin:@127.0.0.1:1521:ORCL</jdbcUrl>
		<jdbcUsername>dpap_learn</jdbcUsername>
		<jdbcPassword>dpap_learn</jdbcPassword>
		<tableNames>T_CONFLICT1,T_CONFLICT1,T_CONFLICT1</tableNames>
		<projectName>ray</projectName> <!-- 项目名 -->
		<moduleName>codeg</moduleName><!-- 模块名 -->
	</configuration>
	<dependencies>
		<dependency>
			<groupId>oracle</groupId>
			<artifactId>oracle-jdbc</artifactId>
			<version>10.1.0.2.0</version>
		</dependency>
	</dependencies>
</plugin>
2.tableNames 配置用户下的表名 ,如果没有就是配置用户下的所有表
3.然后执行: mvn codeg:gen