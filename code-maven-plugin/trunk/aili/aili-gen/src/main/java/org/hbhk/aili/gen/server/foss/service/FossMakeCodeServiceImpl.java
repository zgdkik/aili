package org.hbhk.aili.gen.server.foss.service;

import org.hbhk.aili.gen.server.Constants;
import org.hbhk.aili.gen.server.GenerateMain;
import org.hbhk.aili.gen.server.model.MakeModel;
import org.hbhk.aili.gen.server.service.MakeCodeService;
import org.hbhk.aili.gen.server.utils.BaseFreemarkUtils;

public class FossMakeCodeServiceImpl implements MakeCodeService {
	String prefix="template";
	
	

	public FossMakeCodeServiceImpl(String prefix) {
		super();
		this.prefix = prefix;
	}

	private String queryTemplatePath(String fileName) {
		String path = GenerateMain.class.getClassLoader()
				.getResource(prefix+"/" + fileName).getPath();
		return path;
	}

	@Override
	public void makeDao(MakeModel makeModel, String generateOutDir) {
		String name =  makeModel.getEntityName();
		name = name.replaceAll("Entity", "").trim();
		makeModel.setFname(name);
		BaseFreemarkUtils.generate(queryTemplatePath(Constants.DAO_TEMPLATE),
				makeModel, generateOutDir + Constants.DAO_FILE_START + "I"
						+ name+ "Dao.java");
		BaseFreemarkUtils.generate(
				queryTemplatePath("dao_template_impl.fl"),
				makeModel,
				generateOutDir + Constants.DAO_FILE_START+"/impl/"
						+name + "Dao.java");
	}

	@Override
	public void makeManager(MakeModel makeModel, String generateOutDir) {
		String name =  makeModel.getEntityName();
		name = name.replaceAll("Entity", "").trim();
		makeModel.setFname(name);
		BaseFreemarkUtils.generate(
				queryTemplatePath(Constants.MANAGER_TEMPLATE),
				makeModel,
				generateOutDir + Constants.MANAGER_FILE_START + "I"
						+ name + "Service.java");
		BaseFreemarkUtils.generate(
				queryTemplatePath(Constants.MANAGER_IMPL_TEMPLATE),
				makeModel,
				generateOutDir + Constants.MANAGER_IMPL_FILE_START
						+name + "Service.java");
	}
	
	public void makeController(MakeModel makeModel, String generateOutDir) {
		String name =  makeModel.getEntityName();
		name = name.replaceAll("Entity", "").trim();
		makeModel.setFname(name);
		BaseFreemarkUtils.generate(
				queryTemplatePath("controller_template.fl"),
				makeModel,
				generateOutDir +"action/"
						+ name + "Action.java");
	}

	@Override
	public void makeSqlXml(MakeModel makeModel, String generateOutDir) {
		if(makeModel.getColumnList()==null || makeModel.getColumnList().size()==0){
			return;
		}
		String name =  makeModel.getEntityName();
		name = name.replaceAll("Entity", "").trim();
		BaseFreemarkUtils.generate(queryTemplatePath("xml_template.xml"),
				makeModel, generateOutDir 
						+ "ibatis/" 
						+ name.toLowerCase() + ".xml");
	}

	@Override
	public void makeJs(MakeModel makeModel, String generateOutDir) {
		String name =  makeModel.getEntityName();
		name = name.replaceAll("Entity", "").trim();
		makeModel.setFname(name);
		BaseFreemarkUtils.generate(queryTemplatePath("script.js"),
				makeModel, generateOutDir + "scripts/" +name.toLowerCase() + ".js");
	}

	@Override
	public void makeJsp(MakeModel makeModel, String generateOutDir) {
		String name =  makeModel.getEntityName();
		name = name.replaceAll("Entity", "").trim();
		makeModel.setFname(name);
		BaseFreemarkUtils.generate(queryTemplatePath("page.jsp"),
				makeModel, generateOutDir + "views/" +name.toLowerCase() + ".jsp");
	}

}
