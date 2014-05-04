package org.hbhk.module.framework.server.controller;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.hbhk.module.framework.shared.domain.EmpEntity;
import org.hbhk.module.framework.shared.domain.Person;
import org.hbhk.module.framework.shared.util.ModuleConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
@Controller
@RequestMapping(ModuleConstants.MODULE_FRAMEWORK)
public class PersonController {

	@RequestMapping(value = "/view")
	public Person etPerson() throws Exception {
		Person person = new Person();
		person.setId(1);
		String encStr = URLEncoder.encode("aaaaaa撒打死","UTF-8");
		String ss = new BASE64Encoder().encode(encStr.getBytes());
		person.setName(ss);
		List<EmpEntity>  ls = new ArrayList<EmpEntity>();
		EmpEntity  emp = new EmpEntity();
		emp.setAge(111);
		emp.setLoginname("sdfsdf");
		ls.add(emp);
		//person.setLs(ls);
		
		List<Person>  pl = new ArrayList<Person>();
		
		pl.add(person);
		
		return person;
	}
}
