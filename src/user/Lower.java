package user;

import java.sql.Connection;

import opt.DeleteOpt;
import opt.InputOpt;
import util.ConvertStringBinary;
/**
 * 数据库低安全级用户
 * @author zrq
 *
 */
public class Lower extends User implements Runnable{
	private Connection con;
	
	public Lower() {
		this.setLevel("low");
		this.setCon();
	}

	public Connection getCon() {
		return con;
	}

	public void setCon() {
		this.con = this.getCon0("low", "123456789");
	}

	/**
	 * L用户读取数据并设置读取完成状态
	 * @return
	 */
	public int dataOpt() {
		int code=InputOpt.inputEmployee(getCon(), 999999);//L进行数据读取
		InputOpt.inputAll(getCon());//L补全所有记录
		return code;
	}
	
	
	@Override
	public void run() {
		//1.L写所有记录
		InputOpt.inputAll(getCon());
		//2.L删除User.A的记录,循环监视该记录，当第一次成功删除成功后开始数据的读取
		String str="";
		while(true) {
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}//阻塞L执行，防止太快的执行
			System.out.println("等待高级用户传送数据...");
			if(DeleteOpt.deleteEmployee(getCon(), 666666)>=1){
				str+=this.dataOpt();//第一次的读取操作
				System.out.println("编码："+str);
				break;
			}
		}
		int tranOk=0;
		while(true) {//L第一次读取成功后,开始循环监视判断数据是否读取完毕
			if(DeleteOpt.deleteEmployee(getCon(), 666666)>=1) {
				str+=this.dataOpt();//数据读取与记录补全
				tranOk=0;//L将读取数据是否结束计数置为0
				System.out.println("编码："+str);
			}else {
				tranOk++;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//阻塞L执行，防止太快的判断读取完毕
				if(tranOk==20){
					System.out.println(str.length()+":"+str);
					System.out.println(ConvertStringBinary.binaryToString(str));
					System.out.println("编码："+str+"\n*****************读取完毕！*****************");
					return ;
				}
			}
			System.out.println(tranOk+":L执行中...");
		}
	}
	

	public static void main(String[] args) {
		Thread low=new Thread(new Lower());
		low.start();
	}
}
