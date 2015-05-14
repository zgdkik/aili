package org.hbhk.test.foss.sql;

import java.io.IOException;
import java.util.List;

import org.hbhk.aili.core.share.util.FileAsStringUtil;

public class CompareWayBill {

	
	public static void main(String[] args) throws IOException {
		
		List<String> cList= FileAsStringUtil.readLines("foss/sql/c.sql", "sql"); 
		List<String> dList= FileAsStringUtil.readLines("foss/sql/db.sql", "sql"); 
		
		for (String str : cList) {
			if(!dList.contains(str)){
				System.out.println(str);
			}
			
		}
	}
}
