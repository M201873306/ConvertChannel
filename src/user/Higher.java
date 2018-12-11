package user;

import java.sql.Connection;

import opt.DeleteOpt;
import opt.InputOpt;
import opt.SelectOpt;
import util.ConvertStringBinary;
/**
 * 数据库高安全级用户bean
 * @author zrq
 *
 */
public class Higher extends User implements Runnable{
	private Connection con;
	
	public Higher() {
		this.setLevel("high");
		this.setCon();
	}
	
	public Connection getCon() {
		return con;
	}
	public void setCon() {
		this.con = this.getCon0("SYSDBA", "123456789");
	}
	
	/**
	 * H用户传输数据并设置传送单个数据完毕状态
	 * @return
	 */
	public void dataOpt(int code) {
//		if(code==1) {//H用0表示不删除，1表示删除；L在进行employee.B的插入时，插入不成功即数据存在读0，插入成功即数据不存在读1
//			DeleteOpt.deleteEmployee(getCon(), 999999);//H操作employee.B进行数据准备
//		}
//		DeleteOpt.deleteSaraly(getCon(), 666666);//H删除Saraly.A表示传送完数据
		if(code==1) {//H用0表示不删除，1表示删除；L在进行employee.B的插入时，插入不成功即数据存在读0，插入成功即数据不存在读1
			while(DeleteOpt.deleteEmployee(getCon(), 999999) != 1) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("H欲传:1");
				//H操作employee.B进行数据准备
		}
		else {
			while(!SelectOpt.SelectEmployee(getCon())) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("H欲传:0");
		}
		while(DeleteOpt.deleteSaraly(getCon(), 666666) != 1) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

//		DeleteOpt.deleteSaraly(getCon(), 666666);//H删除Saraly.A表示传送完数据
	}

	@Override
	public void run() {
		String ciperStr="u：流6";//密文字符串
		String binaryCiperStr=ConvertStringBinary.stringToBinary(ciperStr);//密文二进制字符串
		int len=binaryCiperStr.length();
		int tranCount=0;//传输密文计数
		System.out.println(len+":"+binaryCiperStr);
		int[] cipher=ConvertStringBinary.BinstrToIntArray(binaryCiperStr);//将二进制字符串转为二进制数组
		System.out.println(cipher.length+":"+cipher.toString());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}//阻塞H执行，主要目的：让H放弃一段时间线程锁，让L有操作锁的权限，防止H因查询记录缓存重复执行同样的操作
		while(true) {
//			if(SelectOpt.SelectSaralyA(getCon())) {//H根据Salary.A记录是否存在判断L是否读取完毕数据
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}//阻塞H执行，主要目的：让H放弃一段时间线程锁，让L有操作锁的权限，防止H因查询记录缓存重复执行同样的操作
//				this.dataOpt(cipher[tranCount++]);//H成功获取L读取完毕，重新进行数据的传输操作
//			}
//			//在这用是否读取完毕判断是否终止循环
//			if(tranCount==len) {
//				return;
//			}
//			System.out.println("H执行中...");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}//阻塞H执行，主要目的：让H放弃一段时间线程锁，让L有操作锁的权限，防止H因查询记录缓存重复执行同样的操作
			
			while(!SelectOpt.SelectSaralyA(getCon())) {//H根据Salary.A记录是否存在判断L是否读取完毕数据
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}//阻塞H执行，主要目的：让H放弃一段时间线程锁，让L有操作锁的权限，防止H因查询记录缓存重复执行同样的操作
			}
			System.out.println("对方已收到。。。");
			this.dataOpt(cipher[tranCount++]);//H成功获取L读取完毕，重新进行数据的传输操作
			//在这用是否读取完毕判断是否终止循环
			if(tranCount==len) {
				return;
			}
			System.out.println("H执行中...");
		}
	}
	
	public static void main(String[] args) {
		Thread high=new Thread(new Higher());
		high.start();
	}
}
