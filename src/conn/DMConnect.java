package conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * 达梦数据库连接工具
 * @author zrq
 *
 */
public class DMConnect {
	// JDBC 驱动名及数据库 URL
	public static final String JDBC_DRIVER = "dm.jdbc.driver.DmDriver";
	public static final String DB_URL = "jdbc:dm://localhost:5236";

	// 数据库的用户名与密码，需要根据自己的设置
	public static final String USER = "SYSDBA";
	public static final String PASS = "123456789";

	/**
	 * 获得数据库连接
	 * @param driver
	 * @param url
	 * @param user
	 * @param password
	 * @return
	 */
	public static Connection getConnection(String driver, String url, String user, String password) {
		Connection conn = null;
		try {
			// 注册 JDBC 驱动
			Class.forName(driver);
			// 打开链接
			System.out.println("连接数据库...");
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException se) {
			// 处理 JDBC 错误
			se.printStackTrace();
		} catch (Exception e) {
			// 处理 Class.forName 错误
			e.printStackTrace();
		} 
		return conn;
	}
	
	/**
	 * 获得数据库语句
	 * @param conn
	 * @return
	 */
	public static Statement getStatement(Connection conn) {
		Statement stmt=null;
		// 执行查询
		System.out.println(" 实例化Statement对象...");
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stmt;
	}
	
	/**
	 * 获得查询结果集
	 * @param stmt
	 * @param sql
	 * @return
	 */
	public static ResultSet getResultSet(Statement stmt,String sql) {
		ResultSet rs=null;
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
//	public static void close(Object obj) {
//		try {
//			if(obj instanceof ResultSet) {
//				DMConnect.close(null,null,(ResultSet)obj);
//			}else if(obj instanceof Statement) {
//				DMConnect.close(null,(Statement)obj,null);
//			}else if(obj instanceof Connection) {
//				DMConnect.close((Connection)obj,null,null);
//			}else {
//				System.out.println("语句输入错误！");
//			}
//		}catch(Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	/**
	 * 关闭数据库操作对象
	 * @param conn
	 * @param stmt
	 * @param rs
	 */
	public static void close(Connection conn,Statement stmt,ResultSet rs) {
		try {
			if(rs!=null)
				rs.close();
			if(stmt!=null)
				stmt.close();
			if(conn!=null)
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Connection con=DMConnect.getConnection(JDBC_DRIVER, DB_URL, USER, PASS);
		String sql="select * from dmhr.city";
		Statement stmt=DMConnect.getStatement(con);
		ResultSet rs=DMConnect.getResultSet(stmt, sql);
		try {
			String str="";
			int i=1;
			while(rs.next()) {
				str+=i+": ";
				str+=rs.getString("city_id")+" ";
				str+=rs.getString("city_name")+" ";
				str+=rs.getInt("region_id")+"\n";
				i++;
			}
			System.out.println(str);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close(con,stmt,rs);
	}

}
