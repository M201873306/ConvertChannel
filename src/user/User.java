package user;

import java.sql.Connection;

import conn.DMConnect;
/**
 * 数据库用户基类
 * @author zrq
 *
 */
public class User {
	private Integer id;
	private String sex;
	private String level;
	private Integer age;
	private String username;
	private String password;
	public User() {}
	public User(String username,String password) {
		this.username=username;
		this.password=password;
	}
	
	/**
	 * 获得用户的数据库连接
	 * @return
	 */
	public Connection getCon0(String username,String password) {
		return DMConnect.getConnection(DMConnect.JDBC_DRIVER, DMConnect.DB_URL, username, password);
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
