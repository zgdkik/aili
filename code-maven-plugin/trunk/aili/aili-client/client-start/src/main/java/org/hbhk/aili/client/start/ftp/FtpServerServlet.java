package org.hbhk.aili.client.start.ftp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FtpServerServlet extends HttpServlet {
	
	private static final Log log = LogFactory.getLog(FtpServerServlet.class);
    private static final long serialVersionUID = 5539642787624981705L;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	
        PrintWriter wr = resp.getWriter();
        
        wr.print("<html>");
        wr.print("<head>");
        wr.print("<title>FtpServer status servlet</title>");
        wr.print("</head>");
        wr.print("<body>");
    	
    	try{    		
    		
	        GuiFTPServer server = GuiFTPServer.getInstance();

	        wr.print("<form method='post'>");
	
	        if(server.isStopped()) {
	            wr.print("<p>FtpServer is stopped.</p>");
	        } else {
	            if(server.isSuspended()) {
	                wr.print("<p>FtpServer is suspended.</p>");
	            } else {
	                wr.print("<p>FtpServer is running.</p>");
	            }
	        }
	        
	        wr.print("</form>");

    	}catch (Exception e) {
    		log.error("FtpServerServlet access error ",e);
    		wr.print("<span>"+e.toString()+"</span>");
		}
        wr.print("</body>");
        wr.print("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
            
    }
    
    @Override
    public void destroy() {
    	super.destroy();
    	try{
	    	GuiFTPServer server = GuiFTPServer.getInstance();
	    	server.stop();
    	}catch (Exception e) {
    		log.error("FtpServerServlet destroy error ",e);
    	}
    }
    
    public String realpath = null;
    @Override
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	try {
    		realpath = "D:/ftp/client-app";//config.getServletContext().getRealPath("");
			startFTPServer();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("FtpServerServlet init error ",e);
		}
    }
    
    private void startFTPServer() throws Exception {
    	initFossGuiFtpRoot();
    	
    	GuiFTPServer server = GuiFTPServer.getInstance();
    	server.start();
    }
    
    static final int BUFFER = 2048;
    private void initFossGuiFtpRoot() throws Exception {
		// init foss gui ftp root dir and gui apphome file.
		// get zip file of apphonme 
		// unzip apphome.zip into /fossguiftp dir
		if(realpath==null){
			throw new Exception("initFossGuiFtpRoot error no web app real path found");
		}
		
		String filePath = File.separator + "hbhkguiftp" + File.separator + "appHome" + File.separator;
		File dir = new File(filePath);
		if(dir.exists()){
			dir.delete();
			dir.mkdirs();
		}
		
		String apphomezippath = realpath ;// parseToAppHomeZipFilePath(realpath);
		File appZipDir = new File(apphomezippath);
		File[] files = appZipDir.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				if(name.startsWith("appHome")){
					return true;
				}
				else {
					return false;
				}
			}
		});
		
		if(files!=null && files.length>0 ){
	        ZipFile zipFile = new ZipFile(files[0]);
	        
	        Enumeration emu = zipFile.entries();
	        int i=0;
	        while(emu.hasMoreElements()){
	            ZipEntry entry = (ZipEntry)emu.nextElement();
	            if (entry.isDirectory())
	            {
	                new File(filePath + entry.getName()).mkdirs();
	                continue;
	            }
	            BufferedInputStream bis = new BufferedInputStream(zipFile.getInputStream(entry));
	            File file = new File(filePath + entry.getName());
	            File parent = file.getParentFile();
	            if(parent != null && (!parent.exists())){
	                parent.mkdirs();
	            }
	            FileOutputStream fos = new FileOutputStream(file);
	            BufferedOutputStream bos = new BufferedOutputStream(fos,BUFFER);           
	            
	            int count;
	            byte data[] = new byte[BUFFER];
	            while ((count = bis.read(data, 0, BUFFER)) != -1)
	            {
	                bos.write(data, 0, count);
	            }
	            bos.flush();
	            bos.close();
	            bis.close();
	        }
	        zipFile.close();
		}
    }
    
    private String parseToAppHomeZipFilePath(String realpath) throws Exception {
    	if(realpath.toLowerCase().indexOf("jboss")!=-1){
    		
    		String path = realpath.substring(0, realpath.indexOf("tmp"));
    		return path + "deploy" + File.separator;
    		
    	}
    	else if(realpath.toLowerCase().indexOf("webapp")!=-1){
    		String path = realpath.substring(0, realpath.lastIndexOf(File.separator));
    		return path + File.separator;
    	}
    	else {
    		throw new Exception("parseToAppHomeZipFileName");
    	}
    }
}