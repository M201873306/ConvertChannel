package user;

import java.sql.Connection;

import opt.DeleteOpt;
import opt.InputOpt;
import opt.SelectOpt;
import util.ConvertStringBinary;

public class MACHigher extends User implements Runnable{
	private Connection con;
	
	public MACHigher() {
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

	}

	@Override
	public void run() {
//		String ciperStr="u：流6";//密文字符串
		String ciperStr=SelectOpt.SelectUserAndPassword(getCon());
		String binaryCiperStr=ConvertStringBinary.stringToBinary(ciperStr);//密文二进制字符串
		int len=binaryCiperStr.length();
		int tranCount=0;//传输密文计数
		int term=0;
		System.out.println(len+":"+binaryCiperStr);
		int[] cipher=ConvertStringBinary.BinstrToIntArray(binaryCiperStr);//将二进制字符串转为二进制数组
		System.out.println(cipher.length+":"+cipher.toString());
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while(true) {
			while(!InputOpt.inputSalaryA(getCon())) {}//一开始一直插入直到成功

			DeleteOpt.deleteEmployee(getCon(), 999999);
			if(cipher[tranCount++]==1) {
				InputOpt.inputEmployee(getCon(), 999999);
			}
			User ut=null;
			while((ut=SelectOpt.SelectEmployeeA(getCon()))!=null) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(ut.getTag()!=0) {
					break;
				}
			}
			DeleteOpt.deleteSaralyA(getCon());
			User u=null;
			while((u=SelectOpt.SelectEmployeeA(getCon()))!=null) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(u.getTerm()==term) {
					break;
				}
			}
			term++;
			if(tranCount==len) {
				return;
			}
			System.out.println("H执行中...");
		}
	}
	
	public static void main(String[] args) {
		Thread high=new Thread(new MACHigher());
		high.start();
	}
}
