/**
 * 
 */
package org.hbhk.aili.i18n.server.support.file.handler;

import java.io.FileWriter;

/**
 * @author xianze.zhang
 * @creattime 2013-6-21
 */
public abstract class AbstractExtractStringToFile extends AbstractCharFileHandler{


	protected FileWriter			toFileWrite;

	protected String				toFile;
	
	/*
	 * (non-Javadoc)
	 * @see com.baozun.nebula.i18n.AbstractCharFileHandler#doInit()
	 */
	@Override
	protected void doInit() throws Exception{
		// TODO Auto-generated method stub
		toFileWrite = new FileWriter(toFile, true);
	}

	/*
	 * (non-Javadoc)
	 * @see com.baozun.nebula.i18n.AbstractCharFileHandler#doFinish()
	 */
	@Override
	protected void doFinish() throws Exception{
		// TODO Auto-generated method stub
		toFileWrite.close();
	}


	
	public String getToFile(){
		return toFile;
	}

	
	public void setToFile(String toFile){
		this.toFile = toFile;
	}

}
