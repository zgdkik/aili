package org.hbhk.home.core.server.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.hbhk.aili.core.server.web.BaseController;
import org.hbhk.aili.core.share.util.BeanToMapUtil;
import org.hbhk.aili.mybatis.server.annotation.Table;
import org.hbhk.aili.mybatis.server.support.AutoCreateTable;
import org.hbhk.home.core.server.dao.ICustomerDao;
import org.hbhk.home.core.server.dao.IUserDao;
import org.hbhk.home.core.share.model.CustomerModel;
import org.hbhk.home.core.share.model.UserModel;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController extends BaseController implements InitializingBean {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private IUserDao userDao;
	@Autowired
	private ICustomerDao customerDao;

	@RequestMapping(value = {"/list" }, method = RequestMethod.GET)
	public String index(Model model) {
		List<CustomerModel> list = customerDao.get(null);
		if(list != null){
			if(list.size()>20){
				list = list.subList(0, 19);
			}
		}
		model.addAttribute("list", list);
		model.addAttribute("pageNum", "1");
		return "list";

	}
	
	@RequestMapping(value = {"/cus/edit" })
	public String edit(CustomerModel cus,String type,Model model) {
		String name = cus.getName();
		Long id = cus.getId();
		if(id!=null){
			cus = customerDao.getById(id+"");
			model.addAttribute("cus",cus);
		}
		if(StringUtils.isNotEmpty(name)){
			try {
				if(id== null){
					cus.setId(System.currentTimeMillis());
					customerDao.insertNoId(cus);
				}else{
					customerDao.update(cus);
				}
				model.addAttribute("msg", "提交成功");
			} catch (Exception e) {
				model.addAttribute("msg", "提交失败");
			}
		}
		model.addAttribute("msg", "");
		return "edit";
	}
	
	@RequestMapping(value = {"/cus/del" })
	public String del(String id) {
		if(id!=null){
			customerDao.deleteById(id);
		}
		return "redirect:/list";
	}
	
	@RequestMapping(value = {"/cus/list" })
	public String list(CustomerModel cus,String pageNum,Model model) {
		Map<String, Object> params = BeanToMapUtil.convert(cus);
		int num = 0;
		if(StringUtils.isNotEmpty(pageNum)){
			try {
				num = Integer.parseInt(pageNum);
			} catch (Exception e) {
				num = 0;
			}
		}
		List<CustomerModel> list = customerDao.get(params);
		if(list != null){
			if(list.size()>20){
				if(num >1){
					int s =19*num;
					int e = 19*num+19;
					if(list.size()<e){
						e = list.size()-1;
					}
					list = list.subList(s, e);
				}else{
					list = list.subList(0, 19);	
				}
			}
		}
		
		model.addAttribute("list", list);
		model.addAttribute("pageNum", pageNum);
		return "list";
	}

	public void afterPropertiesSet() throws Exception {
		Connection conn = dataSource.getConnection();
		System.out.println("Connection:" + conn);
		List<Class<?>> tableClass = new ArrayList<Class<?>>();
		tableClass.add(UserModel.class);
		tableClass.add(CustomerModel.class);
		for (Class<?> cls : tableClass) {
//			PreparedStatement pst1 = conn.prepareStatement("drop table "+cls.getAnnotation(Table.class).value());
//			pst1.execute();
			String str = AutoCreateTable.createTable(cls);
			try {
				PreparedStatement pst = conn.prepareStatement(str);
				pst.execute();
			} catch (Exception e) {
				System.out.println("表已经存在:"+cls.getAnnotation(Table.class).value());
			}
			
		}

	}

}
