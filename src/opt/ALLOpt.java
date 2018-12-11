package opt;

import user.Higher;
import user.Lower;
/**
 * 隐通道操作主函数
 * @author zrq
 *
 */
public class ALLOpt {
	
	/**
	 * 隐通道过程执行函数
	 * 
	 * 相关参数：
	 * 1. 数据库高安全级用户high，低安全级用户low
	 * 2.数据库表员工表(employee)、薪资表(payroll)
	 * 3.隐通道实现记录雇员id:666666、999999
	 * 4.id(A):666666用于表示数据状态，id(B):999999用于数据传输
	 * 5.改进：上述采用4条实现引用完整性删除隐通道，改为使用3条记录：表示状态的不变，传数据的变更为只用employee.A
	 * 
	 * 执行过程：
	 * 
		//1.L写所有记录
		InputOpt.inputAll(low.getCon());
		//2.L删除User.A的记录
		DeleteOpt.deleteEmployee(low.getCon(), 666666);
		//3.H操作employee.B进行数据准备
		DeleteOpt.deleteEmployee(high.getCon(), 999999);
		//4.H删除Saraly.A表示传送完数据
		DeleteOpt.deleteSaraly(high.getCon(), 666666);
		//5.L一直在删除A，此时删除成功
		DeleteOpt.deleteEmployee(low.getCon(), 666666);
		//6.L插入User.B读取数据
		DeleteOpt.inputEmployee(low.getCon(), 999999);
		//7.L补全所有记录
		InputOpt.inputAll(low.getCon());
		//8.H删除后一直读Saraly.A的记录，判断是否读数据成功
		//L读数据成功，H重复3的操作，新一轮数据准备
		while(SelectOpt.SelectSaralyA(high.getCon())) {
			System.out.println("L读数据完毕，新一轮已开始！");
		};
	 *  000000011101011111111100011010011011010100000100000000001101100
	 *  000000011101011111111100011010011011010100000100000000001101100
	 *  000000010101011111111100011010011011010100000100000000001101100
	 * 0000000011101011111111100011010011011010100000100000000001101100
	 *0000000001110101111111110001101001101101010000010000000000110110
	 * 
	 */
	public static void excute() {
		Thread low=new Thread(new Lower());
		Thread high=new Thread(new Higher());
		low.start();
		high.start();
	}
	
	public static void main(String[] args) {
		excute();
	}
}
