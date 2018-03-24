package com.yimidida.ows.home.server.constant;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yimidida.ows.home.server.util.Resources;



public class Constant {

	public static final String CONF_RESOURCE = "resource";
	public static final String MAIL_USER = Resources.instance(CONF_RESOURCE).getAttributeValue( "user" );
	public static final String MAIL_PASSWORD = Resources.instance(CONF_RESOURCE).getAttributeValue( "password" );
	public static final String MAIL_SENDNAME = Resources.instance(CONF_RESOURCE).getAttributeValue( "sendName" );
	public static final String MAIL_SMTP = Resources.instance(CONF_RESOURCE).getAttributeValue( "smtp" );
	public static final String MAIL_TITLE = Resources.instance(CONF_RESOURCE).getAttributeValue( "mailtitle" );
	public static final String MSG_URL = Resources.instance(CONF_RESOURCE).getAttributeValue( "msgUrl" );
	public static final String LOGIN_NAME = Resources.instance(CONF_RESOURCE).getAttributeValue( "loginname" );
	public static final String LOGIN_PWD = Resources.instance(CONF_RESOURCE).getAttributeValue( "loginpwd" );
	public static void main(String[] args) {
		System.out.println(Constant.MAIL_USER);
	}

	public static final class MSG {
		public static final String CODE_NULL = "用户名不能为空";
		public static final String PWD_NULL = "密码不能为空";
		public static final String ADMIN_NULL = "该用户不存在";
		public static final String IMG_TYPE_ERROR = "图片格式不正确（必须为.jpg/.gif/.bmp/.jpeg/.png文件）";
		public static final String IMG_SIZE_ERROR = "图片大小不能超过2M";
		public static final String ICON_SIZE_ERROR = "图片大小不能超过50KB";
		public static final String UNKNOW_ERROR = "未知错误，请联系管理员";
		public static final String FILE_SIZE = "文件大小不可超过";
		public static final String IMG_WH = "图片长宽必须为";
		public static final String FILE_TYPE = "文件格式不符，请确认后上传";
		public static final String SAVE_OK = "{\"success\":\"true\",\"title\":\"成功\",\"content\":\"保存成功\"}";
		public static final String DEL_OK = "{\"success\":\"true\",\"title\":\"成功\",\"content\":\"删除成功\"}";
		public static final String UPDATE_OK = "{\"success\":\"true\",\"title\":\"成功\",\"content\":\"修改成功\"}";
		public static final String SAVE_ERR = "{\"success\":\"false\",\"title\":\"失败\",\"content\":\"保存失败\"}";
		public static final String DEL_ERR = "{\"success\":\"false\",\"title\":\"失败\",\"content\":\"删除失败\"}";
		public static final String UPDATE_ERR = "{\"success\":\"false\",\"title\":\"失败\",\"content\":\"修改失败\"}";
		
		public static final Map<String,Object> ERR_COMMON(String msg){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("title","失败");
			map.put("content",msg);
			map.put("success","error");
			return map;
		}
		
		public static final Map<String, Object> SAVE_OK() {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("title", "成功");
			map.put("content", "保存成功");
			map.put("success","info");
			return map;
		}

		public static final Map<String, Object> UPD_OK() {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("title", "成功");
			map.put("content", "修改成功");
			map.put("success","info");
			return map;
		}

		public static final Map<String, Object> DEL_OK() {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("title", "成功");
			map.put("content", "删除成功");
			map.put("success","info");
			return map;
		}

		public static final Map<String, Object> SAVE_ERROR() {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("title", "失败");
			map.put("content", "保存失败");
			map.put("success","info");
			return map;
		}

		public static final Map<String, Object> UPD_ERROR() {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("title", "失败");
			map.put("content", "修改失败");
			map.put("success","error");
			return map;
		}

		public static final Map<String, Object> DEL_ERROR() {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("title", "失败");
			map.put("content", "删除失败");
			map.put("success","error");
			return map;
		}

		public static String getCKEditorMsg(String CKEditorFuncNum,
				String imgName, String errorMsg) {
			return "<script type=\"text/javascript\">window.parent.CKEDITOR.tools.callFunction("
					+ CKEditorFuncNum
					+ ", '"
					+ imgName
					+ "','"
					+ errorMsg
					+ "');</script>";
		}

	}

	public static final class PATH {
		public static final String CK_IMAGE_PATH = "resources/ckeditor/fileupload/image";
		public static final String CK_IMAGE_PATH1 = File.separator + "resources"
				+ File.separator + "ckeditor" + File.separator + "fileupload"
				+ File.separator + "image";
		// public static final String CK_FLASH_PATH =
		// "them/ckeditor/fileupload/flash";
		public static final String ICON_IMAGE_PATH = "resources/images/treeicon";
		public static final String ICON_IMAGE_PATH1 = File.separator + "resources"
				+ File.separator + "images" + File.separator + "treeicon";

		public static final String ICON_NOTICE_PATH1 = File.separator + "resources"
				+ File.separator + "images" + File.separator + "notice";

		public static final String GETPATH(String savePath) {
			String[] filePaths = savePath.split(",");
			String filePath = "";
			for (String str : filePaths) {
				filePath += str + File.separator;
			}
			filePath = filePath.substring(0, filePath.length() - 1);
			return filePath;
		}

		public static void main(String[] args) {
			System.out.println(GETPATH("them,images,banner"));
		}
	}

	public static final class FILE_TYPE {
		public static List<String> imgTypes() {
			List<String> fileTypes = new ArrayList<String>();
			fileTypes.add("jpg");
			fileTypes.add("jpeg");
			fileTypes.add("bmp");
			fileTypes.add("gif");
			fileTypes.add("png");
			return fileTypes;
		}
	}
}
