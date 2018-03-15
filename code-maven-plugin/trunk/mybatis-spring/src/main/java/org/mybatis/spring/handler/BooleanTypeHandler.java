package org.mybatis.spring.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

public class BooleanTypeHandler implements TypeHandler<Boolean> {

	@Override
	public Boolean getResult(ResultSet arg0, String arg1) throws SQLException {
		String str = arg0.getString(arg1);
		Boolean rt = Boolean.FALSE;
		if (str.equalsIgnoreCase("Y")) {
			rt = Boolean.TRUE;
		}
		return rt;
	}

	@Override
	public Boolean getResult(ResultSet arg0, int arg1) throws SQLException {
		String str = arg0.getString(arg1);
		Boolean rt = Boolean.FALSE;
		if (str.equalsIgnoreCase("Y")) {
			rt = Boolean.TRUE;
		}
		return rt;
	}

	@Override
	public Boolean getResult(CallableStatement arg0, int arg1)
			throws SQLException {
		String str = arg0.getString(arg1);
		Boolean rt = Boolean.FALSE;
		if (str.equalsIgnoreCase("Y")) {
			rt = Boolean.TRUE;
		}
		return rt;
	}

	@Override
	public void setParameter(PreparedStatement arg0, int arg1, Boolean arg2,
			JdbcType arg3) throws SQLException {
		Boolean b = (Boolean) arg2;
		String value = (Boolean) b == true ? "Y" : "N";
		arg0.setString(arg1, value);

	}

}
