package org.hbhk.jpa.test;

import org.junit.Test;
import org.leochen.samples.dao.UserRepository;
import org.leochen.samples.domain.User;
import org.springframework.beans.factory.annotation.Autowired;

public class CrudRepositoryTest extends BaseTestCase {

	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void test1() throws Exception {
		User u =  userRepository.findUserByUsername("hbhk");
		System.out.println(u);
	}
}
