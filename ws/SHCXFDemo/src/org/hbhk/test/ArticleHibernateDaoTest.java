package org.hbhk.test;

import java.util.Date;

import org.hbhk.dao.impl.ArticleHibernateDao;
import org.hbhk.domain.Article;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ArticleHibernateDaoTest {

	ApplicationContext ctx;
	ArticleHibernateDao adh;

	@Before
	public void before() {
		ctx = new ClassPathXmlApplicationContext("ssh.xml");
		adh = (ArticleHibernateDao) ctx.getBean("articleHibernateDao");
	}

	@Test
	public void testSave() {

		Article art = new Article();
		art.setId(5l);
		art.setTitle("±ÍÃ‚1");
		art.setAuthor("Œ÷∂˚Œ÷≈∂v");
		art.setContent("1");
		art.setPubDate(new Date());

		adh.save(art);

	}

	public void testget() {
		Article art = (Article) adh.get(1l);

		System.out.println(art.getAuthor());
	}

}
