package org.hbhk.aili.mybatis.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
  
public class DBHelper {  
    public static final String url = "jdbc:p6spy:mysql://10.10.0.211:8066/wms?useUnicode=true";  
    public static final String name = "com.mysql.jdbc.Driver";  
    public static final String user = "root";  
    public static final String password = "MySQL@123";  
  
    public  static Connection conn = null;  
    public static PreparedStatement pst = null;  
  
    public static void exeute(String sql) {  
        try {  
            Class.forName(name);//指定连接类型  
            conn = DriverManager.getConnection(url, user, password);//获取连接  
            pst = conn.prepareStatement(sql);//准备执行语句  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    public static void main(String[] args) {
    	exeute("select *from wms_inventory");
	}
    public void close() {  
        try {  
            this.conn.close();  
            this.pst.close();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }  
}  