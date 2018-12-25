package opt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import conn.DMConnect;
/**
 * 隐通道删除操作
 * @author zrq
 *
 */
public class DeleteOpt {

	/**
	 * 删除user记录
	 * @param con
	 * @param id
	 * @return
	 */
	public static int deleteEmployee(Connection con,int id) {
		int tag=0;
		String sql="delete from LOW.EMPLOYEE where \"id\"=?";
		//sql = "delete from LOW.PAYROLL where \"id\"=?";
		PreparedStatement stmt=null;
//		System.out.println("deleteEmployee"+id);
		try {
			stmt=con.prepareStatement(sql);
			stmt.setInt(1, id);
			tag=stmt.executeUpdate()>0?1:0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return 0;
//			System.out.println("删除用户失败（外键（引用）存在！违反引用完整性！）");
		}finally {
			DMConnect.close(null,stmt,null);
		}
		return tag;
	}

	/**
	 * 删除saraly记录
	 * @param con
	 * @param id
	 * @return
	 */
	public static int deleteSaraly(Connection con,int id) {
		int tag=0;
		String sql="delete from LOW.PAYROLL where \"id\"=?";
		PreparedStatement stmt=null;
		try {
			stmt=con.prepareStatement(sql);
			stmt.setInt(1, id);
			tag=stmt.executeUpdate()>0?1:0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DMConnect.close(null,stmt,null);
		}
		return tag;
	}
	
	/**
	 * 删除SaralyA
	 * @param con
	 * @return
	 */
	public static boolean deleteSaralyA(Connection con) {
		int tag=deleteSaraly(con,666666);
		return tag==1?true:false;
	}
}
