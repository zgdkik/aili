/**
 * 
 */
package org.hbhk.aili.i18n.server.support.file.handler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


/**
 * 字符类型的文件处理类
 */
public abstract class AbstractCharFileHandler implements FileHandler{
	
	public static final String ENTER_LINE = "\r\n";
	protected BufferedReader fileReader;
	private File file;
	
	@Override
	public void doHandle(File file) throws Exception{
		init(file);
		execute();
		finish();
	}
	private void init(File file) throws Exception{
		this.setFile(file);
		doInit();
		openFile();
	}
	private void finish() throws Exception{
		doFinish();
		closeFile();
	}
	protected abstract void doInit() throws Exception;
	protected abstract void doFinish() throws Exception;
	
	private void openFile() throws FileNotFoundException{
		fileReader = new BufferedReader(new FileReader(file));
	}
	private void closeFile() throws IOException{
		fileReader.close();
	}
	protected void execute()throws Exception{
		executeByLine();
	}
	
	protected void executeByLine() throws Exception{
		String line = null;
		while ((line = fileReader.readLine()) != null){
			executeLine(line);
		}
	}
	protected abstract void executeLine(String line) throws Exception;
	
	public File getFile(){
		return file;
	}
	
	public void setFile(File file){
		this.file = file;
	}
	
}
