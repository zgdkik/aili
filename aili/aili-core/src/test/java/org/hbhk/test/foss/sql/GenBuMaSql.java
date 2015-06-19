package org.hbhk.test.foss.sql;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hbhk.aili.core.share.util.FileAsStringUtil;

public class GenBuMaSql {

	
	public static void main(String[] args) throws IOException {
		
		List<String> cList= FileAsStringUtil.readLines("foss/sql/c.sql", "sql"); 
		
		System.out.println("处理单号个数:"+cList.size());
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE PKP.T_SRV_ACTUAL_FREIGHT A SET A.STATUS = 'EFFECTIVE', A.MODIFY_TIME = SYSDATE WHERE WAYBILL_NO IN (");
		sql.append("\r");
		for (int i = 0; i < cList.size(); i++) {
			String wayBill = cList.get(i);
			if(StringUtils.isEmpty(wayBill)){
				continue;
			}
			if(i+1==cList.size()){
				sql.append("'"+wayBill+"'");
			}else{
				sql.append("'"+wayBill+"',");
			}
			sql.append("\r");
		}
		sql.append(");");
		File f = new File("D:/无法补码.sql");
		OutputStream os = new FileOutputStream(f);
		os.write(sql.toString().getBytes());
		System.out.println(sql);
		os.close();
	}
}
