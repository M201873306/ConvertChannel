package opt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 * 隐通道输入操作
 * @author zrq
 *
 */

public class InputOpt {
	
	private static int failedTag=0;
	
	/**
	 * 插入所有数据
	 * @param con
	 * @return
	 */
	public static int inputAll(Connection con) {
		int[] id= {666666,999999};
		String[] writeSql= {
				"insert LOW.EMPLOYEE(\"id\",\"name\") values(?,'user')",
				"insert LOW.PAYROLL(\"id\",\"year\",\"month\",\"salary\") values(?,2018,12,8000)"
				};
		String[] selectSql={
				"select * from LOW.EMPLOYEE where \"id\"=?",
				"select * from LOW.PAYROLL where \"id\"=?"
				};
		PreparedStatement stmt=null;
		try {
			for(int i=0;i<2;i++) {
				for(int j=0;j<2;j++) {
					stmt=con.prepareStatement(selectSql[j]);
					stmt.setInt(1, id[i]);
//					System.out.println(stmt.toString());
					if(!stmt.executeQuery().next()) {//若是查询记录为空执行插入
						PreparedStatement wstmt=con.prepareStatement(writeSql[j]);
						wstmt.setInt(1, id[i]);
						if(i==1&&j==1) {
							wstmt.close();
							continue;//判断为saraly.B的记录不写入
						}
						wstmt.execute();//执行不成功继续执行
						wstmt.close();
					};
					stmt.close();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("插入所有记录失败，重试尝试插入！");
			failedTag++;
			inputAll(con);//没插入成功重复插入
			if(failedTag==5) {
				System.out.println("五次插入失败，请手动检查！");
				return -1;
			}
		}
		return 1;
	}
	
	/**
	 * 插入雇员数据
	 * @param con
	 * @param id
	 * @return
	 */
	public static int inputEmployee(Connection con,int id) {
		int code=0;
		String sql="insert LOW.EMPLOYEE(\"id\",\"name\",\"date\",\"sex\",\"nativeplace\",\"eduback\") values(?,'zrq','2018-12-9','男','福建','硕士')";
		PreparedStatement stmt=null;
		try {
			stmt=con.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.execute();
			code=1;
		} catch (SQLException e) {
			//插入失败，编码为0
			code=0;
			return code;
		}
		return code;
	}
}
