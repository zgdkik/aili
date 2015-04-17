package com.baozun.zk.server.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.ZkSerializer;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baozun.zk.server.context.RequestContext;
import com.baozun.zk.server.interceptor.AuthInterceptor;
import com.baozun.zk.share.model.Result;
import com.baozun.zk.share.model.User;
import com.baozun.zk.share.model.ZkData;
import com.baozun.zk.share.model.Ztree;
import com.baozun.zk.share.util.CookieUtil;
import com.baozun.zk.share.util.FileLoadUtil;
import com.baozun.zk.share.util.JsonUtil;

@Controller
public class ZkClientController implements InitializingBean {

	public  static Map<String, ZkClient> zkMap =new ConcurrentHashMap<String, ZkClient>();
	@Value("#{zk['zk.host']}")
	private String zkHost;
//	@Value("#{zk['zk.root']}")
//	private String root;
	private ZkClient zkClient;
	

	private int idCount = 0;
	public static final Logger log = LoggerFactory
			.getLogger(AuthInterceptor.class);

	@RequestMapping("/")
	public String toIndex(Model model,HttpServletRequest request) {
		Cookie cookie = CookieUtil.getCookie(request, "zkHost");
		if (cookie != null) {
			String zkHost = cookie.getValue();
			try {
				if (StringUtils.isEmpty(zkHost)) {
					ZkClient zkC = null;
					if(zkMap.containsKey(zkHost)){
						zkC = zkMap.get(zkHost);
					}else{
						zkC = new ZkClient(zkHost, 25000);
						zkC.setZkSerializer(new ZkSerializer() {
							public byte[] serialize(Object data) throws ZkMarshallingError {
								return data.toString().getBytes();
							}

							public Object deserialize(byte[] bytes) throws ZkMarshallingError {
								return new String(bytes);
							}
						});
						zkMap.put(zkHost, zkC);
					}
					RequestContext.setZkClient(zkC);
				}
				model.addAttribute("ztrees", JsonUtil.toJson(getPaths()));
			} catch (Exception e) {
				request.setAttribute("msg", e.getMessage());
			}
		}
		return "index";
	}

	@RequestMapping("/login")
	public String login(HttpServletRequest request) {
		return "login";
	}

	@RequestMapping(value = { "/login" }, params = { "user" })
	@ResponseBody
	public Result login1(String user, String pwd, HttpServletRequest request) {
		if (validateUser(user, pwd)) {
			HttpSession session = request.getSession();
			session.setAttribute(AuthInterceptor.user_session_key, user);
			return new Result();
		}
		return new Result(false);
	}
	
	@RequestMapping(value = { "/logout" })
	@ResponseBody
	public Result logout(String user, String pwd, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute(AuthInterceptor.user_session_key);
		return new Result();
	}

	private boolean validateUser(String user, String pwd) {
		List<User> users = AuthInterceptor.users;
		for (User user2 : users) {
			if (user2.getUser().equals(user) && user2.getPwd().equals(pwd)) {
				return true;
			}
		}
		return false;
	}

	@RequestMapping("/getPaths")
	@ResponseBody
	public List<Ztree> getPaths() {
		List<Ztree> ztrees = new ArrayList<Ztree>();
		if(RequestContext.getZkClient()==null){
			return ztrees;
		}
		String root = RequestContext.getRoot();
		if (StringUtils.isEmpty(root)) {
			root = "/";
		}
	
		List<String> paths = null;
		try {
			paths = RequestContext.getZkClient().getChildren(root);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ztrees;
		}
		Ztree rootTree = new Ztree();
		rootTree.setId(0);
		rootTree.setOpen(true);
		String root1 = root.substring(1, root.length());
		rootTree.setName(root1);
		rootTree.setTitle(root1);
		rootTree.setpId(null);
		ztrees.add(rootTree);
		idCount = 0;
		if(root.equals("/")){
			root = "";
		}
		loadTrees(ztrees, paths, 0, root);
		return ztrees;
	}

	private void loadTrees(List<Ztree> ztrees, List<String> chs, int pid,
			String pt) {
		Collections.sort(chs ,new Comparator<String>() {
			public int compare(String o1, String o2) {
				return o1.compareToIgnoreCase(o2);
			}
		});
		for (int i = 0; chs != null && i < chs.size(); i++) {
			String p = chs.get(i);
			Ztree rootTree = new Ztree();
			int id = ++idCount;
			rootTree.setId(id);
			rootTree.setpId(pid);
			rootTree.setName(p);
			rootTree.setTitle(p);
			ztrees.add(rootTree);
			String newPath = pt + "/" + p;
			List<String> chs1 = RequestContext.getZkClient().getChildren(newPath);
			if (chs1 != null && chs1.size() > 0) {
				loadTrees(ztrees, chs1, id, pt + "/" + p);
			}
		}
	}

	@RequestMapping("/setZkHost")
	@ResponseBody
	public Result setZkHost(String zkHost, String root,
			HttpServletRequest request, HttpServletResponse response) {
		ZkClient newzc = null;
		try {
			if(zkMap.containsKey(zkHost)){
				newzc = zkMap.get(zkHost);
			}else{
				newzc = new ZkClient(zkHost, 25000);
				zkMap.put(zkHost, newzc);
			}
			RequestContext.getSession().setAttribute(
					"zkClient",newzc);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new Result(false,"zk地址配置错误");
		}
		try {
			newzc.getChildren(root);
			RequestContext.getSession().setAttribute("zkClient", newzc);
			CookieUtil.setCookie(request, response, "zkHost", zkHost);
			CookieUtil.setCookie(request, response, "root", root);
			return new Result();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new Result(false,"根节点配置错误");
		}

	}

	@RequestMapping("/getZkData")
	@ResponseBody
	public ZkData getZkData(String path) {
		Object data = null;
		try {
			if (RequestContext.getZkClient().exists(path)) {
				data = RequestContext.getZkClient().readData(path);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return null;
		}
		ZkData zkData = new ZkData();
		if (data instanceof String) {
			zkData.setPath(path);
			zkData.setData((String) data);
			return zkData;
		} else {
			zkData.setPath(path);
			zkData.setData(JsonUtil.toJson(data));
			return zkData;
		}

	}

	@RequestMapping("/updateZkData")
	@ResponseBody
	public Result updateZkData(String path, String data) {
		Object data1 = null;
		try {
			data1 = RequestContext.getZkClient().readData(path);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new Result(false);
		}
		if (data1 instanceof String || data1 == null) {
			RequestContext.getZkClient().writeData(path, data);
			return new Result();
		} else {
			return new Result(false);
		}
	}

	@RequestMapping("/createPath")
	@ResponseBody
	public Result createPath(String path,String data) {
		boolean flag = false;
		try {
			flag = RequestContext.getZkClient().exists(path);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new Result(false);
		}
		if (!flag) {
			try {
				String[] paths = path.trim().split("/");
				String p= "";
				for (int i = 0; i < paths.length; i++) {
					if(StringUtils.isEmpty(paths[i])){
						continue;
					}
					p = p+"/"+paths[i];
					if(!RequestContext.getZkClient().exists(p)){
						 if(i == paths.length-1){
							 RequestContext.getZkClient().createPersistent(p,data);
						 }else{
							 RequestContext.getZkClient().createPersistent(p);
						 }
					}
				}
				return new Result(true, "");
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				return new Result(false, "目录格式不正确或上一级目录不存在");
			}
		} else {
			return new Result(false, "目录已经存在:" + path);
		}
	}

	@RequestMapping("/delPath")
	@ResponseBody
	public Result delPath(String path) {
		boolean flag = false;
		try {
			flag = RequestContext.getZkClient().exists(path);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new Result(false);
		}

		if (flag) {
			try {
				RequestContext.getZkClient().deleteRecursive(path);
				return new Result();
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				return new Result(false, "删除失败:存在子目录");
			}
		} else {
			return new Result(false, "目录不存在:" + path);
		}
	}

	@RequestMapping("/upload")
	@ResponseBody
	public Result upload(HttpServletRequest request,
			HttpServletResponse response, MultipartFile Filedata, String path)
			throws Exception {
		InputStream input = Filedata.getInputStream();
		Properties properties = new Properties();
		properties.load(input);
		Set<Object> keys = properties.keySet();
		for (Object key : keys) {
			String val = (String) properties.get(key);
			String cp = (String) key;
			if(cp.indexOf("/")>-1){
				return new Result(false, "properties文件key不符合规范,key不能以/分隔");
			}
			String newPath = path + "/" + cp;
			try {
				boolean exist = RequestContext.getZkClient().exists(newPath);
				if (exist) {
					RequestContext.getZkClient().writeData(newPath, val);
				} else {
					RequestContext.getZkClient().createPersistent(newPath, val);
				}
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				return new Result(false, e.getMessage());
			}
		}
		return new Result();

	}

	@RequestMapping("/reloadUsers")
	@ResponseBody
	public Result reloadUsers() {
		AuthInterceptor.users = new ArrayList<User>();
		return new Result();
	}
	@RequestMapping("/zkConfigExport")
	public String zkConfigExport(String path,HttpServletResponse response) throws Exception {
		if(StringUtils.isEmpty(path)){
			return null;
		}
		if(!RequestContext.getZkClient().exists(path)){
			return null;
		}
		File input = FileLoadUtil.getInputStreamForClasspath("zk-config-export.properties");
		List<String>  paths = RequestContext.getZkClient().getChildren(path);
		if(paths==null || paths.size()==0){
			return null;
		}
		Collections.sort(paths ,new Comparator<String>() {
			public int compare(String o1, String o2) {
				return o1.compareToIgnoreCase(o2);
			}
		});
		Set<String> newKyes = new HashSet<String>();
		FileUtils.writeStringToFile(input, "", false);
		for (String key : paths) {
			String newPath = path+"/"+key;
			newKyes.add(newPath);
			Object value = RequestContext.getZkClient().readData(newPath);
			String data = key+"="+value+"\r\n";
			FileUtils.writeStringToFile(input, data, true);
		}
        try {
            downloadFile(response, new FileInputStream(input) , path+".properties", 2048);
        } catch (Exception e) {  
        	log.error(e.getMessage(), e);
        } 
		return null;
	}
	public void downloadFile(HttpServletResponse response, InputStream input,
			String fileName, int buffer) throws IOException {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			response.setContentType("text/html;charset=utf-8");
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("utf-8"), "ISO8859-1"));
			//response.setHeader("Content-Length", String.valueOf(fileLength));
			bis = new BufferedInputStream(input);
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[buffer];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException(e);
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
	}

	public void afterPropertiesSet() throws Exception {
		try {
			zkClient = new ZkClient(zkHost,25000);
			zkMap.put(zkHost, zkClient);	
		} catch (Exception e) {
			zkMap.put(zkHost, null);	
		}
	}
	
	
}
