package org.hbhk.aili.mybatis.server.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.hbhk.aili.mybatis.share.util.RSAUtil;

/**
 * 
 * @Description: java中的boolean和jdbc中的char之间转换;true-Y;false-N
 * @author 何波
 * @date 2014年6月6日 上午9:07:15
 * 
 */
public class EncryptTypeHandler implements TypeHandler<String> {

	@Override
	public String getResult(ResultSet arg0, String arg1) throws SQLException {
		String str = arg0.getString(arg1);
		if(StringUtils.isNotEmpty(str)){
			str = RSAUtil.decrypt(str);
		}
		return str;
	}

	@Override
	public String getResult(ResultSet arg0, int arg1) throws SQLException {
		String str = arg0.getString(arg1);
		if(StringUtils.isNotEmpty(str)){
			str = RSAUtil.decrypt(str);
		}
		return str;
	}

	@Override
	public String getResult(CallableStatement arg0, int arg1)
			throws SQLException {
		String str = arg0.getString(arg1);
		if(StringUtils.isNotEmpty(str)){
			str = RSAUtil.decrypt(str);
		}
		return str;
	}

	@Override
	public void setParameter(PreparedStatement arg0, int arg1, String arg2,
			JdbcType arg3) throws SQLException {
		if(StringUtils.isNotEmpty(arg2)){
			arg2 = RSAUtil.encrypt(arg2);
		}
		arg0.setObject(arg1, arg2);
	}

}
