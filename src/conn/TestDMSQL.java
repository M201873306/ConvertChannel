package conn;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TestDMSQL {

	public static boolean selectSql() {
		Connection con = DMConnect.getConnection(DMConnect.JDBC_DRIVER, DMConnect.DB_URL, DMConnect.USER,
				DMConnect.PASS);
		String sql = "select * from LOW.EMPLOYEE where \"id\"=3";
		Statement stmt = DMConnect.getStatement(con);
		ResultSet rs = DMConnect.getResultSet(stmt, sql);
		boolean t=false;
		try {
			if(rs.next()) {
				t=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
			t=false;
		}
		DMConnect.close(con, stmt, null);
		return t;
	}

	public static boolean deleteSql() {
		Connection con = DMConnect.getConnection(DMConnect.JDBC_DRIVER, DMConnect.DB_URL, "low",
				DMConnect.PASS);
		String sql = "delete from LOW.EMPLOYEE where \"id\"=3";
		Statement stmt = DMConnect.getStatement(con);
		boolean t=false;
		try {
			stmt.execute(sql);
			t=true;//上一步成功执行才到这一步
		} catch (SQLException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
			t=false;
		}
		DMConnect.close(con, stmt, null);
		return t;
	}
	
	public static boolean insertSql() {
		Connection con = DMConnect.getConnection(DMConnect.JDBC_DRIVER, DMConnect.DB_URL, "low",
				DMConnect.PASS);
		String sql = "insert LOW.EMPLOYEE(\"id\",\"name\",\"date\",\"sex\",\"nativeplace\",\"eduback\") values(3,'wxk','2018-12-9','男','安徽','硕士')";
		Statement stmt = DMConnect.getStatement(con);
		boolean t=false;
		try {
			stmt.execute(sql);
			t=true;//上一步成功执行才到这一步
		} catch (SQLException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
			t=false;
		}
		DMConnect.close(con, stmt, null);
		return t;
	}

	public static void main(String[] args){
		System.out.println("************DM数据库语句测试**********\n");
		System.out.println("1.select\t2.delete\t3.insert");
		int i = new Scanner(System.in).nextInt();
		switch (i) {//达梦数据库严格遵循列名双引号，值字符串单引号
		case 1:
			System.out.println(selectSql());
			break;
		case 2:
			System.out.println(deleteSql());
			break;
		case 3:
			System.out.println(insertSql());
			break;
		}
	}

}
