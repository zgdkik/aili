1. 所有引用EXTJS的地方，写成${extjs4}的方式引入，
如<script type="text/javascript" src="${extjs4}/bootstrap.js"></script>
在web.xml文件中配置extjs4资源位置，添加如下代码：
<web-app>
...
	<context-param>
		<param-name>staticServerAddress</param-name>
		<param-value>/extjs4</param-value>
	</context-param>
...
</web-app>

2. 测试用户 huanghua:huanghua

3. camel watch 需要在war包中修改需要JMX地址.文件地址(camelwatch.war\WEB-INF\classes\camelwatch.properties);
4. 