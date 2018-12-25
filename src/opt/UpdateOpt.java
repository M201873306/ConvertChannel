package opt;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import user.User;

public class UpdateOpt {
	
	/**
	 * 修改userA的是否知道h准备传输数据的标志
	 * @param con
	 * @return
	 */
	public static boolean UpdateEmployeeAandTag(Connection con) {
		User user=null;
		boolean isok=false;
		String sql="update LOW.EMPLOYEE set \"tag\"=1 where \"id\"=666666";
		try {
			isok=con.createStatement().executeUpdate(sql)>0?true:false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isok;
	}
}
