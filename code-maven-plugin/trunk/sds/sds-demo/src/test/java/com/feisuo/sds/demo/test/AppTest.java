package com.feisuo.sds.demo.test;

import org.hbhk.aili.mybatis.server.support.AutoCreateTable;

import com.feisuo.sds.demo.share.entity.DemoEntity;

public class AppTest {

	
	public static void main(String[] args) {
		String sql =	AutoCreateTable.createTable(DemoEntity.class);
		System.out.println(sql);
	}
}
