package util;

public class ConvertStringBinary {

	/**
	 * 二进制字符串转为utf-8编码字符
	 * @param binary
	 * @return
	 */
	public static String binaryToString(String binary) {
		int num = binary.length() / 16;
    	String[] tempStr = new String[num];
    	int beginIndex = 0;
    	int endIndex = 16;
    	for(int i=0; i<num; i++) {
    		tempStr[i] = binary.substring(beginIndex, endIndex);
    		beginIndex = endIndex;
    		endIndex += 16;
    	}
        char[] tempChar=new char[tempStr.length];
        for(int i=0;i<tempStr.length;i++) {
           tempChar[i]=BinstrToChar(tempStr[i]);
        }
        return String.valueOf(tempChar);
	}
	
	/**
	 * utf-8编码字符转为二进制字符串
	 * @param str
	 * @return
	 */
	public static String stringToBinary(String str) {
		 //把字符串转成字符数组
        char[] strChar=str.toCharArray();
        String result="";
        for(int i=0;i<strChar.length;i++){
        	String result_m = Integer.toBinaryString(strChar[i]);
        	int resultm_length = result_m.length();
            if(resultm_length < 16) {
            	for(int j=0; j< 16-resultm_length; j++) {
            		result_m = "0" + result_m;
            	}
            }
            result += result_m;
        }
        return result;
	}
	
	//将二进制转换成字符
    public static char BinstrToChar(String binStr){
        int[] temp=BinstrToIntArray(binStr);
        int sum=0;
        for(int i=0; i<temp.length;i++){
            sum +=temp[temp.length-1-i]<<i;
        }
        return (char)sum;
   }
    //将二进制字符串转换成int数组
    public static int[] BinstrToIntArray(String binStr) {       
        char[] temp=binStr.toCharArray();
        int[] result=new int[temp.length];   
        for(int i=0;i<temp.length;i++) {
            result[i]=temp[i]-48;  //char变为int
        }
        return result;
    }
}
