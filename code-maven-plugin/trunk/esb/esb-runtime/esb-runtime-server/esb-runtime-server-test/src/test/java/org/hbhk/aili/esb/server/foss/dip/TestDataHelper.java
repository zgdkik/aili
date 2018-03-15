package org.hbhk.aili.esb.server.foss.dip;

import java.util.Date;

/**
 * 产生测试数据.
 * 
 * @author qiancheng
 * @date 2012-12-20 下午7:11:29
 */
public class TestDataHelper {
	
	/**
	 * 生成OaReportNolabel对象.
	 * 
	 * @return the oa report nolabel
	 * @throws Exception
	 *             the exception
	 * @author qiancheng
	 * @date 2012-12-20 下午7:11:53
	 */
	public static OaReportNolabel createOaReportNolabel()throws Exception{
		OaReportNolabel biz = new OaReportNolabel();
		biz.setAttributes("attributes");
		biz.setBrand("brand");
		biz.setCategorys("categorys");
		biz.setDeptName("deptName");
		biz.setDeptOrgid("DP08231");
		biz.setEventReport("eventReport");
		biz.setFinalSysCode("DP08231");
		biz.setFindPlace("findPlace");
		biz.setGafTime(new Date());
		biz.setGoodsName("goodName");
		biz.setGoodsPacked("goodsPacked");
		biz.setHandKey("handKey");
		biz.setImageNw("");
		biz.setImageZm("");
		biz.setImageZt("");
		biz.setIsLessGoods("isLessGoods");
		biz.setIsReplayBill("isReplayBill");
		biz.setLessGoodsNote("lessGoodsNote");
		biz.setNoLabelSerail("nolabelSerail");
		biz.setNoLabelWayBill("noLabelWayBill");
		biz.setNoticeObjects("054839");
		biz.setPackageKey("packageKey");
		biz.setReplayBill("replayBill");
		biz.setUserId("054839");
		biz.setVolume(new Double("123"));
		biz.setWeight(new Double("123"));
		return biz;
	}
}
