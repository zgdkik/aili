package test;

import com.yimidida.ymdp.core.share.util.BASE64Util;


public class Base64UtilTest {  
	
	public static void main(String[] args) {
		String str = "dadaowl,0001111";
		str = BASE64Util.encode(str);
		System.out.println(str);		
		str = BASE64Util.decode(str);
		System.out.println(str);		
	}
 
}  