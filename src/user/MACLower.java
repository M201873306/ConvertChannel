package user;

import java.sql.Connection;

import opt.DeleteOpt;
import opt.InputOpt;
import opt.UpdateOpt;
import util.ConvertStringBinary;

public class MACLower extends User implements Runnable{
	private Connection con;
	
	public MACLower() {
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
		if(code==0) {
			DeleteOpt.deleteEmployee(getCon(), 999999);
		}
		return code;
	}
	
	
	@Override
	public void run() {
		//1.L插入userA设置当前轮数=0
//		InputOpt.inputEmployeeAandUpdateTerm(getCon(), 0);
		//2.L删除User.A的记录,循环监视该记录，当第一次成功删除成功后开始数据的读取
		String str="";
		int code=0;
		int term=0;
		while(true) {
			InputOpt.inputEmployeeAandUpdateTerm(getCon(), term);
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int tag = DeleteOpt.deleteEmployee(getCon(), 666666);
			if(tag ==0) {
				UpdateOpt.UpdateEmployeeAandTag(getCon());
				while(true) {
					if(DeleteOpt.deleteEmployee(getCon(), 666666)>=1){
//						str+=this.dataOpt();
						break;
					}
				}
				code=InputOpt.inputEmployee(getCon(), 999999);
				if(code==0) {
					DeleteOpt.deleteEmployee(getCon(), 999999);
				}
				str+=code;
				term++;
				if(term%16==0) {
					System.out.println("**********收到的字符：["+ConvertStringBinary.binaryToString(str)+"]");
				}
				System.out.println("编码："+str);
			}
		
		}
//		int tranOk=0;
//		while(true) {//L第一次读取成功后,开始循环监视判断数据是否读取完毕
//			if(DeleteOpt.deleteEmployee(getCon(), 666666)>=1) {
//				str+=this.dataOpt();//数据读取与记录补全
//				tranOk=0;//L将读取数据是否结束计数置为0
//				System.out.println("编码："+str);
//			}else {
//				tranOk++;
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}//阻塞L执行，防止太快的判断读取完毕
//				if(tranOk==20){
//					System.out.println(str.length()+":"+str);
//					System.out.println(ConvertStringBinary.binaryToString(str));
//					System.out.println("编码："+str+"\n*****************读取完毕！*****************");
//					return ;
//				}
//			}
//			System.out.println(tranOk+":L执行中...");
//		}
	}
	

	public static void main(String[] args) {
		Thread low=new Thread(new MACLower());
		low.start();
	}
}
