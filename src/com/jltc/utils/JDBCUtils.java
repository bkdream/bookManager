package com.jltc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jltc.constant.Constant;

public class JDBCUtils {

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		// 加载驱动
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/"+Constant.DATABASE_NAME+"?"
							+ "user="+Constant.USERNAME+"&password="+Constant.PASSWORD+"&useUnicode=true&characterEncoding=UTF8";
		// 获得数据的连接
		Connection conn = DriverManager.getConnection(url);
		return conn;
	}

	public static void release(Statement stmt, Connection conn) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stmt = null;
		}
		// 关闭数据库连接，释放资源
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn = null;
		}
	}

	public static void release(ResultSet rs, Statement stmt, Connection conn) {
		// 关闭数据库连接，释放资源
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rs = null;
		}
		release(stmt, conn);
	}
}