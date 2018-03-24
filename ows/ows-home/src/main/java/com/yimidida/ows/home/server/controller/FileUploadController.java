package com.yimidida.ows.home.server.controller;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.yimidida.ows.base.share.util.SystemParameterUtil;
import com.yimidida.ows.home.server.constant.Constant;
import com.yimidida.ows.home.server.util.Guid;




@Controller
@RequestMapping("/upload")
public class FileUploadController {

	
	 /**
     * CKEditor 图片上传
     * Create of Elvis
     * @param HttpServletRequest request,HttpServletResponse response
     * @return JavaScript
	 * @throws IOException 
     */
	
    @RequestMapping(value = "/CKImgUpload",method=RequestMethod.POST)
    public void CKImgUpload(HttpServletRequest request,HttpServletResponse response) throws IOException {
    	response.setContentType("text/html; charset=UTF-8");  
    	String CKEditorFuncNum = request.getParameter("CKEditorFuncNum");
    	MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;  
        MultipartFile file  =  multipartRequest.getFile("upload");//获取文件流
        String fileName = file.getOriginalFilename();//获取文件名
        String suffix = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());//获取文件后缀名
        String resultName = Guid.getGuid()+"."+suffix;//UID新文件名
        String strScript ="";
        if(Constant.FILE_TYPE.imgTypes().contains(suffix)){//验证文件格式
        	if(file.getSize()/1024<2048){
        		String tagatPath =SystemParameterUtil.getValue("upload.path.prefix");
        		//request.getSession().getServletContext().getRealPath("")+Constant.PATH.CK_IMAGE_PATH1; 
            	File targetFile = new File(tagatPath, resultName);
                if(!targetFile.exists()){ //文件夹是否存在
                    targetFile.mkdirs();  
                }  
                try { 
                	//存
                    file.transferTo(targetFile);
                    //读
                	String basePath =request.getContextPath()+"/common/viewImage";// request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ request.getContextPath() + "/";
                	//strScript=Constant.MSG.getCKEditorMsg(CKEditorFuncNum,basePath+Constant.PATH.CK_IMAGE_PATH+"/"+resultName,"");
                	strScript=Constant.MSG.getCKEditorMsg(CKEditorFuncNum,basePath+"/"+resultName+"/","");
                    System.out.println(strScript);
                } catch (Exception e) {  
                	strScript=Constant.MSG.getCKEditorMsg(CKEditorFuncNum,"",Constant.MSG.UNKNOW_ERROR);
                }  	
        	}else{
        		strScript=Constant.MSG.getCKEditorMsg(CKEditorFuncNum,"",Constant.MSG.IMG_SIZE_ERROR);
        	}
        }else{
        	strScript=Constant.MSG.getCKEditorMsg(CKEditorFuncNum,"",Constant.MSG.IMG_TYPE_ERROR);
        }
        
        PrintWriter out;
        out = response.getWriter();
        out.print(strScript);
        out.flush();
    }
    
    
    //后台-图片上传-新增&修改
	@RequestMapping("/fileUpload")
	public void fileUpload(HttpServletRequest request,HttpServletResponse response,String fileObjectId,int MaxSize,String suffixStr,int width,int height) throws IOException{
		MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;  
        MultipartFile file  =  multipartRequest.getFile(fileObjectId);//获取文件流
        String fileName = file.getOriginalFilename();//获取文件名
        String suffix = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());//获取文件后缀名
        String[] suffixs = suffixStr.split(",");
        boolean isSuffix = false;
        //判断文件是否合法
        for(String str:suffixs){
        	if(str==suffix||str.equals(suffix)){
        		isSuffix=true;
        	}
        }
        String result = "";
		String msg = "";
		String resultName = Guid.getGuid()+"."+suffix;//UID新文件名
        if(isSuffix){
        	if(file.getSize()/1024<=MaxSize){
        		String path = SystemParameterUtil.getValue("upload.path.prefix");
            	File targetFile = new File(path, resultName);  
                if(!targetFile.exists()){ //文件夹是否存在
                    targetFile.mkdirs();  
                }  
                try {
                	//存文件
                    file.transferTo(targetFile);
                    if(width!=0&&height!=0){
                    	//FileInputStream fis = new FileInputStream(targetFile);
                        BufferedImage bufferedImg = ImageIO.read(targetFile);
                        if(bufferedImg.getWidth()!=width||bufferedImg.getHeight()!=height){
                        	
                        	targetFile.delete();
                        	result = "false";
                        	msg = Constant.MSG.IMG_WH+width+"*"+height;
                        }else{
                        	result="true";
                        }
                        bufferedImg.flush();
                    	//fis.close();
                    }else{
                    	result = "true";
                    }
                } catch (Exception e) {  
                	msg = Constant.MSG.UNKNOW_ERROR;
                }  	
        	}else{
        		msg = Constant.MSG.FILE_SIZE+MaxSize+"KB";
        		result = "false";
        	}
        }else{
        	msg = Constant.MSG.FILE_TYPE;
        	result = "false";
        }
        String basePath =request.getContextPath()+"/common/viewImage/"+resultName;
        response.setContentType("text/html; charset=UTF-8");  
        PrintWriter out;
        out = response.getWriter();
        //返回页面信息
        String res = "{ success:'"+result+"', msg:'"+msg+"',imgurl:'" + basePath+"/'}";
        out.print(res);
        out.flush();
	}
	
	 	//后台-核心产品图片上传-新增&修改
		@RequestMapping("/fileUploadImg")
		public void fileUploadImg(HttpServletRequest request,HttpServletResponse response,String fileObjectId,int MaxSize,String suffixStr,int width,int height,int flag) throws IOException{
			MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;  
	        MultipartFile file  =  multipartRequest.getFile(fileObjectId);//获取文件流
	        String fileName = file.getOriginalFilename();//获取文件名
	        String suffix = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());//获取文件后缀名
	        String[] suffixs = suffixStr.split(",");
	        HttpSession session=request.getSession();
	        if(flag==1){   //flag为1是第一张图片    将它存入session中
	        	session.setAttribute("imgName", fileName);
	        }else if(flag==2){ //flag为2的时候,取临时session中的数据
	        	String imgName = (String) session.getAttribute("imgName");
	        	fileName = imgName.substring(0, imgName.length()-4)+2+"."+suffix;
	        }
	        boolean isSuffix = false;
	        //判断文件是否合法
	        for(String str:suffixs){
	        	if(str==suffix||str.equals(suffix)){
	        		isSuffix=true;
	        	}
	        }
	        String result = "";
			String msg = "";
//			String resultName = Guid.getGuid()+"."+suffix;//UID新文件名
			String resultName = fileName;//UID新文件名
	        if(isSuffix){
	        	if(file.getSize()/1024<=MaxSize){
	        		String path = SystemParameterUtil.getValue("upload.path.prefix");
	            	File targetFile = new File(path, resultName);  
	                if(!targetFile.exists()){ //文件夹是否存在
	                    targetFile.mkdirs();  
	                }  
	                try {
	                	//存文件
	                    file.transferTo(targetFile);
	                    if(width!=0&&height!=0){
	                    	//FileInputStream fis = new FileInputStream(targetFile);
	                        BufferedImage bufferedImg = ImageIO.read(targetFile);
	                        if(bufferedImg.getWidth()!=width||bufferedImg.getHeight()!=height){
	                        	
	                        	targetFile.delete();
	                        	result = "false";
	                        	msg = Constant.MSG.IMG_WH+width+"*"+height;
	                        }else{
	                        	result="true";
	                        }
	                        bufferedImg.flush();
	                    	//fis.close();
	                    }else{
	                    	result = "true";
	                    }
	                } catch (Exception e) {  
	                	msg = Constant.MSG.UNKNOW_ERROR;
	                }  	
	        	}else{
	        		msg = Constant.MSG.FILE_SIZE+MaxSize+"KB";
	        		result = "false";
	        	}
	        }else{
	        	msg = Constant.MSG.FILE_TYPE;
	        	result = "false";
	        }
	        String basePath =request.getContextPath()+"/common/viewImage/"+resultName;
	        response.setContentType("text/html; charset=UTF-8");  
	        PrintWriter out;
	        out = response.getWriter();
	        //返回页面信息
	        String res = "{ success:'"+result+"', msg:'"+msg+"',imgurl:'" + basePath+"/'}";
	        out.print(res);
	        out.flush();
		}
}
