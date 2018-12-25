package opt;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import user.User;
/**
 * 隐通道查询操作
 * @author zrq
 *
 */
public class SelectOpt {
	
	/**
	 * 查询Salary.A的记录是否存在
	 * @param con
	 * @return
	 */
	public static boolean SelectSaralyA(Connection con) {
		boolean tag=false;
		String sql="select count(*) from LOW.EMPLOYEE where \"id\"=666666";
		sql = "select count(*) from LOW.PAYROLL where \"id\"=666666";
		try {
			tag=con.createStatement().executeQuery(sql).next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tag;
	}
	public static boolean SelectEmployee(Connection con) {
		boolean tag=false;
		String sql="select count(*) from LOW.EMPLOYEE where \"id\"=999999";
		//sql = "select count(*) from LOW.PAYROLL where \"id\"=666666";
		try {
			tag=con.createStatement().executeQuery(sql).next();
		} catch (SQLException e) {
			return false;
		}
		return tag;
	}
	
	/**
	 * 查询userA是否存在,且userA的字段数据
	 * @param con
	 * @return
	 */
	public static User SelectEmployeeA(Connection con) {
		User user=null;
		String sql="select * from LOW.EMPLOYEE where \"id\"=666666";
		try {
			ResultSet rs=con.createStatement().executeQuery(sql);
			if(rs.next()) {
				user=new User();
				user.setTerm(rs.getInt("term"));
				user.setTag(rs.getInt("tag"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
	public static String SelectUserAndPassword(Connection con) {
		String str="";
		String sql="select * from \"SYSDBA\".\"USER\" where \"username\"='root'";
		try {
			ResultSet rs=con.createStatement().executeQuery(sql);
			if(rs.next()) {
				str+=rs.getString("username")+" ";
				str+=rs.getString("password");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
}
