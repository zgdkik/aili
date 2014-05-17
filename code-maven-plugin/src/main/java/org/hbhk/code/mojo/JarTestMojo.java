package org.hbhk.code.mojo;

import java.io.IOException;
import java.net.JarURLConnection;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Mojo(name="ten")
public class JarTestMojo extends AbstractMojo {

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		String templatePath = "classpath:dpaptemplate/module-web/";
		PathMatchingResourcePatternResolver ss = new PathMatchingResourcePatternResolver();
		try {
			JarURLConnection jarCon = (JarURLConnection) ss.getResource(templatePath).getURL().openConnection();
			JarFile jar = jarCon.getJarFile();
			Enumeration<JarEntry> entries = jar.entries();
			while (entries.hasMoreElements()) {
				JarEntry jarentry = entries.nextElement();
//				this.getLog().info(jarentry.getName());
				String[] names = jarentry.getName().split("/");
				this.getLog().info(names[names.length - 1]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
