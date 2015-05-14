package org.hbhk.test.foss.sql;

import java.io.IOException;
import java.util.List;

import org.hbhk.aili.core.share.util.FileAsStringUtil;

public class GenBuMaSql {

	
	public static void main(String[] args) throws IOException {
		
		List<String> cList= FileAsStringUtil.readLines("foss/sql/c.sql", "sql"); 
		
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE PKP.T_SRV_ACTUAL_FREIGHT A SET A.STATUS = 'EFFECTIVE', A.MODIFY_TIME = SYSDATE WHERE WAYBILL_NO IN (");
		sql.append("\r");
		for (int i = 0; i < cList.size(); i++) {
			String wayBill = cList.get(i);
			if(i+1==cList.size()){
				sql.append("'"+wayBill+"'");
			}else{
				sql.append("'"+wayBill+"',");
			}
			sql.append("\r");
		}
		sql.append(")");
		
		System.out.println(sql);
	}
}
