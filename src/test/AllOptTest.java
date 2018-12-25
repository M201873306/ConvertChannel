package test;


import org.junit.Test;

import opt.DeleteOpt;
import opt.InputOpt;
import opt.SelectOpt;
import opt.UpdateOpt;
import user.Higher;
import user.MACLower;
import util.ConvertStringBinary;

class AllOptTest {

	@Test
	void testSelectOpt_SelectSaralyA() {
		System.out.println("testSelectOpt_SelectSaralyA:");
		System.out.println(SelectOpt.SelectSaralyA(new Higher().getCon()));
	}

	@Test
	void testInputOpt_inputAll() {
		System.out.println("testInputOpt_inputAll:");
		System.out.println(InputOpt.inputAll(new Higher().getCon()));
	}

	@Test
	void testInputOpt_inputEmployee() {
		System.out.println("testInputOpt_inputEmployee:");
		System.out.println(InputOpt.inputEmployee(new Higher().getCon(),999999));
	}

	@Test
	void testDeleteOpt_deleteEmployee() {
		System.out.println("testDeleteOpt_deleteEmployee:");
		System.out.println(DeleteOpt.deleteEmployee(new Higher().getCon(),666666));
	}

	@Test
	void testDeleteOpt_deleteSaraly() {
		System.out.println("testDeleteOpt_deleteSaraly:");
		System.out.println(DeleteOpt.deleteSaraly(new Higher().getCon(),666666));
	}

	@Test
	void testConvertStringBinary_stringToBinary() {
		System.out.println("testConvertStringBinary_stringToBinary:");
		System.out.println(ConvertStringBinary.stringToBinary("u：流6"));
	}
	
	@Test
	void testConvertStringBinary_binaryToString() {
		System.out.println("testConvertStringBinary_binaryToString:");
		System.out.println(ConvertStringBinary.binaryToString("0000000001110101111111110001101001101101010000010000000000110110"));
	}
	
	@Test
	void testUpdateOpt_UpdateEmployeeAandTag() {
		System.out.println("testUpdateOpt_UpdateEmployeeAandTag:");
		System.out.println(UpdateOpt.UpdateEmployeeAandTag(new MACLower().getCon()));
	}
}
