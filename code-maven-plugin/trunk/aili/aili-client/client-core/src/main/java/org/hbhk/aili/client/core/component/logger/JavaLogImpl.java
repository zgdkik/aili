package org.hbhk.aili.client.core.component.logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.logging.Log;

/**
 * 
 *  客户端日志组件的功能：
 *  	1.客户端的日志输出；
 *      2.用于开发调试，以及出错诊断的日志，输出到本地。
 *  设计思路：
 *  	1.采用commons-logging-api封装，保持与客户端开发一致；
 *      2.使用JDK自带的日志功能，减少下发程序包的大小。
 */
public class JavaLogImpl implements Log {
	/**
	 * cosoleLog输出所有的log
	 * fileLog只是记录warn级别以上的信息
	 */
	private Logger loger = null;
	private String name;
	private Properties prop=null;
	
	private final String OUTPUT_STYLE	="com.deppon.foss.client.log.JavaLogImpl.outputStyle";
	private final String OUTPUT_LEVEL	="com.deppon.foss.client.log.JavaLogImpl.level";
	private final String FILE_PATH		="com.deppon.foss.client.log.JavaLogImpl.filePath";
	
	private final String CONFIG_FILE		="client-log.properties";
	private final String defaltFilePaht	="client-log.xml";
	
	// 默认的日志输出级别
	private static Level defLevel = Level.FINE;
	
	/**
	 * 
	 * <p>Title: JavaLogImpl</p>
	 * <p>Description: 构造函数</p>
	 * @param name
	 */
	public JavaLogImpl(String name){
		this.name = name;
		getLogger();
		loadProperties();
		initLogger();
	}
	
	/**
	 * 加载配置资源
	 */
	private void loadProperties(){
		try {
			InputStream ins = Thread.currentThread().getContextClassLoader().getResourceAsStream(CONFIG_FILE);
			
			if (ins!=null) {
				prop = new Properties();
				prop.load(ins);
				ins.close();
			}
		} catch (FileNotFoundException e) {
			log(Level.INFO, "当前的logger是默认的logger", null);
		} catch (IOException e) {
			log(Level.INFO, "当前的logger是默认的logger", null);
		}
	}
	
	/**
	 * 根据相应配置资源初始化相应的logger
	 */
	private void initLogger() {
		if (prop!=null) {
			String output = prop.getProperty(OUTPUT_STYLE);
			if ("cosole".equalsIgnoreCase(output)) {
				//初始化控制台logger
				Level level = findLevel(prop.getProperty(OUTPUT_LEVEL));
				ConsoleHandler consoleHandler = new ConsoleHandler();
				//控制器输出级别
				consoleHandler.setLevel(level);
				Logger logger = getLogger();
				//logger输出级别
				logger.setLevel(level);
				logger.addHandler(consoleHandler);
				logger.setUseParentHandlers(false);
			} else if("file".equalsIgnoreCase(output)) {
				//初始化文件logger
				Level level = findLevel(prop.getProperty(OUTPUT_LEVEL));
				Logger logger = getLogger();
				//logger输出级别
				logger.setLevel(level);
				logger.setUseParentHandlers(false);
				FileHandler fileHandler = 
					findFileHandler(prop.getProperty(FILE_PATH));
				if (fileHandler!=null) {
					fileHandler.setLevel(level);
					
					//控制器输出级别
					logger.addHandler(fileHandler);
				} else {
					ConsoleHandler consoleHandler = new ConsoleHandler();
					consoleHandler.setLevel(level);
					logger.addHandler(consoleHandler);
				}
			} else {
				initDefaultLogger();
			}
		} else {
			initDefaultLogger();
		}
	}

	/**
	 * 
	 * <p>Title: findLevel</p>
	 * <p>Description: 匹配日志级别</p>
	 * @param level
	 * @return
	 */
	private Level findLevel(String level){
		if ("trace".equalsIgnoreCase(level)) {
			return Level.FINEST;
		} else if ("debug".equalsIgnoreCase(level)) {
			return Level.FINE;
		} else if ("info".equalsIgnoreCase(level)) {
			return Level.INFO;
		} else if ("warn".equalsIgnoreCase(level)) {
			return Level.WARNING;
		} else if ("error".equalsIgnoreCase(level)) {
			return Level.SEVERE;
		} else if ("fatal".equalsIgnoreCase(level)) {
			return Level.SEVERE;
		} else {
			return defLevel;
		}
	}
	
	/**
	 * 
	 * <p>Title: findFileHandler</p>
	 * <p>Description: 获得查找文件处理对象</p>
	 * @param filePath
	 * @return
	 */
	private FileHandler findFileHandler(String filePath) {
		if (filePath==null || "".equals(filePath)) {
			return null;
		}
		FileHandler fileHandler = null;
		try {
			fileHandler = new FileHandler(filePath, true);
		} catch (SecurityException e) {
			log(Level.SEVERE, "初始化文件输出错误...", e);
		} catch (IOException e) {
			log(Level.SEVERE, "日志文件输出初始化失败...", e);
		}
		
		return fileHandler;
	}

	/**
	 * 此处加载的Logger是默认的Logger，所有信息都将答应在
	 */
	private void initDefaultLogger(){
		///初始化默认logger
		Logger logger = getLogger();
		logger.setLevel(defLevel);
		ConsoleHandler consoleHandler = new ConsoleHandler();
		consoleHandler.setLevel(defLevel);
		logger.addHandler(consoleHandler);
		try {
			FileHandler fileHandler = new FileHandler(defaltFilePaht, true);
			fileHandler.setLevel(defLevel);
			logger.addHandler(fileHandler);
		} catch (SecurityException e) {
			log(Level.SEVERE, "初始化文件输出错误...", e);
		} catch (IOException e) {
			log(Level.SEVERE, "日志文件输出初始化失败...", e);
		}
		logger.setUseParentHandlers(false);
	}
	
	/**
	 * 
	 * <p>Title: isDebugEnabled</p>
	 * <p>Description: 是否调试信息</p>
	 * @return
	 */
	@Override
	public boolean isDebugEnabled() {
		return getLogger().isLoggable(Level.FINE);
	}

	/**
	 * 
	 * <p>Title: isErrorEnabled</p>
	 * <p>Description: 是否错误信息</p>
	 * @return
	 */
	@Override
	public boolean isErrorEnabled() {
		return getLogger().isLoggable(Level.SEVERE);
	}

	/**
	 * 
	 * <p>Title: isFatalEnabled</p>
	 * <p>Description: 是否致命错误</p>
	 * @return
	 */
	@Override
	public boolean isFatalEnabled() {
		return getLogger().isLoggable(Level.SEVERE);
	}

	/**
	 * 
	 * <p>Title: isInfoEnabled</p>
	 * <p>Description: 是否一般信息</p>
	 * @return
	 */
	@Override
	public boolean isInfoEnabled() {
		return getLogger().isLoggable(Level.INFO);
	}

	/**
	 * 
	 * <p>Title: isTraceEnabled</p>
	 * <p>Description: 是否跟踪信息</p>
	 * @return
	 */
	@Override
	public boolean isTraceEnabled() {
		return getLogger().isLoggable(Level.FINEST);
	}

	/**
	 * 
	 * <p>Title: isWarnEnabled</p>
	 * <p>Description: 是否警告信息</p>
	 * @return
	 */
	@Override
	public boolean isWarnEnabled() {
		return getLogger().isLoggable(Level.WARNING);
	}

	/**
	 * 
	 * <p>Title: trace</p>
	 * <p>Description: 记录跟踪日志</p>
	 * @param message 日志信息
	 */
	@Override
	public void trace(Object message) {
		log(Level.FINEST, String.valueOf(message),null);
	}

	/**
	 * 
	 * <p>Title: trace</p>
	 * <p>Description: 记录跟踪日志</p>
	 * @param message 日志信息
	 * @param t 异常
	 */
	@Override
	public void trace(Object message, Throwable t) {
		log(Level.FINEST, String.valueOf(message), t);
	}

	/**
	 * 
	 * <p>Title: debug</p>
	 * <p>Description: 记录调试日志</p>
	 * @param message 日志信息
	 */
	@Override
	public void debug(Object message) {
		log(Level.FINE, String.valueOf(message), null);
	}

	/**
	 * 
	 * <p>Title: debug</p>
	 * <p>Description: 记录调试日志</p>
	 * @param message 日志信息
	 * @param t 异常
	 */
	@Override
	public void debug(Object message, Throwable t) {
		log(Level.FINE, String.valueOf(message), t);
	}

	/**
	 * 
	 * <p>Title: info</p>
	 * <p>Description: 记录一般日志</p>
	 * @param message 日志信息
	 */
	@Override
	public void info(Object message) {
		log(Level.INFO, String.valueOf(message), null);
	}

	/**
	 * 
	 * <p>Title: info</p>
	 * <p>Description: 记录一般日志</p>
	 * @param message 日志信息
	 * @param t 异常
	 */
	@Override
	public void info(Object message, Throwable t) {
		log(Level.INFO, String.valueOf(message), t);
	}

	/**
	 * 
	 * <p>Title: warn</p>
	 * <p>Description: 记录警告日志</p>
	 * @param message 日志信息
	 */
	@Override
	public void warn(Object message) {
		log(Level.WARNING, String.valueOf(message), null);
	}

	/**
	 * 
	 * <p>Title: warn</p>
	 * <p>Description: 记录警告日志</p>
	 * @param message 日志信息
	 * @param t 异常
	 */
	@Override
	public void warn(Object message, Throwable t) {
		log(Level.WARNING, String.valueOf(message), t);
	}

	/**
	 * 
	 * <p>Title: error</p>
	 * <p>Description: 记录错误日志</p>
	 * @param message 日志信息
	 */
	@Override
	public void error(Object message) {
		log(Level.SEVERE, String.valueOf(message), null);
	}

	/**
	 * 
	 * <p>Title: error</p>
	 * <p>Description:记录错误日志 </p>
	 * @param message 日志信息
	 * @param t 异常
	 */
	@Override
	public void error(Object message, Throwable t) {
		log(Level.SEVERE, String.valueOf(message), t);
	}

	/**
	 * 
	 * <p>Title: fatal</p>
	 * <p>Description: 记录致命错误日志</p>
	 * @param message 日志信息
	 */
	@Override
	public void fatal(Object message) {
		log(Level.SEVERE, String.valueOf(message), null);
	}

	/**
	 * 
	 * <p>Title: fatal</p>
	 * <p>Description: 记录致使错误日志</p>
	 * @param message 日志信息
	 * @param t
	 */
	@Override
	public void fatal(Object message, Throwable t) {
		log(Level.SEVERE, String.valueOf(message), t);
	}
	
	/**
	 * 
	 * <p>Title: log</p>
	 * <p>Description: 记录日志</p>
	 * @param level
	 * @param msg
	 * @param ex
	 */
	private void log(Level level, String msg, Throwable ex) {
		Logger logger = getLogger();
		
		if (logger.isLoggable(level)) {
			Throwable dummyException = new Throwable();
			StackTraceElement locations[] = dummyException.getStackTrace();
			String cname = "unknown";
			String method = "unknown";
			
			if (locations != null && locations.length > 2) {
				StackTraceElement caller = locations[2];
				cname = caller.getClassName();
				method = caller.getMethodName();
			}
			
			if (ex == null) {
				logger.logp(level, cname, method, msg);
			} else {
				logger.logp(level, cname, method, msg, ex);
			}
		}
	}
	
	/**
	 * 
	 * <p>Title: getLogger</p>
	 * <p>Description: 获取Logger对象</p>
	 * @return
	 */
	public Logger getLogger() {
		if (loger==null) {
			loger = Logger.getLogger(name);
		}
		return loger;
	}
}