package org.hbhk.aili.workflow;

import org.hbhk.aili.gen.server.GenerateMain;
import org.hbhk.aili.workflow.share.entity.WorkflowPublish;

public class GenTest {
	
	public static void main(String[] args) {
		GenerateMain.execute("aili","workflow",WorkflowPublish.class, "何波");
	}

}
