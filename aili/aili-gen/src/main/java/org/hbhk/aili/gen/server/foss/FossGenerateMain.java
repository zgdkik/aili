package org.hbhk.aili.gen.server.foss;

import java.util.ArrayList;
import java.util.List;

import org.hbhk.aili.gen.server.foss.service.FossMakeCodeServiceImpl;
import org.hbhk.aili.gen.server.foss.service.FossMakeModelServiceImpl;
import org.hbhk.aili.gen.server.model.ColumnDesc;
import org.hbhk.aili.gen.server.model.MakeModel;
import org.hbhk.aili.gen.server.service.MakeCodeService;
import org.hbhk.aili.gen.server.service.MakeCodeServiceImpl;
import org.hbhk.aili.gen.server.service.MakeModelService;
import org.hbhk.aili.gen.server.test.CarInfo;

public class FossGenerateMain {

	/**
	 * 生成的实体
	 */
	public final Class<?> modelClass = CarInfo.class;

	public static String projectName = "secretary";

	public static String moduleName = "backend";
	
	public static String entityName = "backend";
	
	public static String tabName="t_test";
	/**
	 * 作者名
	 */
	private final String authName = "何波";


	private static String getAutoMakeCode() {

		return System.getProperty("user.dir") + "/target/template/";
	}

	private void execute(String[] args) throws Exception {

		if (args == null || args.length == 0) {
			args = new String[4];
			args[0] = modelClass.getName();
			args[1] = authName;
		}
		MakeModelService mmService = new FossMakeModelServiceImpl();

		Class<?> clazz = Class.forName(args[0]);

		MakeModel mm = mmService.queryByClass(clazz);
		mm.setAuthName(args[1]);

		//添加属性与列映射 
		List<ColumnDesc> columnList = new ArrayList<ColumnDesc>();
		
		ColumnDesc col = new ColumnDesc("name","c_name");
		columnList.add(col);
		mm.setColumnList(columnList);
		
		MakeCodeService mcs = new FossMakeCodeServiceImpl("foss-template");

		mcs.makeSqlXml(mm, getAutoMakeCode());
		mcs.makeDao(mm, getAutoMakeCode());
		mcs.makeManager(mm, getAutoMakeCode());
		mcs.makeController(mm, getAutoMakeCode());
		mcs.makeJs(mm,  getAutoMakeCode());
		mcs.makeJsp(mm,  getAutoMakeCode());
		System.out.println(args[0] + args[1] + args[2] + args[3]);
	}

	public static void execute(Class<?> cls, String author, String lifecycle,
			boolean hasDeleteLifecycle) throws Exception {
		String[] args = new String[4];
		if (args == null || args.length == 0) {
			args = new String[4];
			args[0] = cls.getName();
			args[1] = author;
			args[2] = lifecycle;
			args[3] = String.valueOf(hasDeleteLifecycle);
		}

		MakeModelService mmService = new FossMakeModelServiceImpl();

		Class<?> clazz = Class.forName(args[0]);

		MakeModel mm = mmService.queryByClass(clazz);

		mm.setAuthName(args[1]);
		if (args[2] == null || "null".equals(args[2])) {
			mm.setLifecycle("");
		} else {
			mm.setLifecycle(args[2]);
		}

		mm.setHasDeleteLifecycle(hasDeleteLifecycle);
		MakeCodeService mcs = new MakeCodeServiceImpl("foss-template");

		mcs.makeSqlXml(mm, getAutoMakeCode());

		mcs.makeDao(mm, getAutoMakeCode());

		mcs.makeManager(mm, getAutoMakeCode());
		mcs.makeController(mm, getAutoMakeCode());

		System.out.println(args[0] + args[1] + args[2] + args[3]);
	}

	public static void main(String[] args) throws Exception {

		FossGenerateMain gm = new FossGenerateMain();

		gm.execute(args);

	}
}
