package org.hbhk.aili.orm.server;

import java.io.IOException;
import java.util.Set;

import org.hbhk.aili.orm.server.context.OrmContext;

public class OrmContextTest {
	public static void main(String[] args) throws IOException {
		OrmContext context1 = new OrmContext();
		context1.inti();
		Set<String> keys = OrmContext.context.keySet();
		for (String string : keys) {
			System.out.println(OrmContext.context.get(string));

		}
	}
}
