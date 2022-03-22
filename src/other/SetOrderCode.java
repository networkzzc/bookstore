package other;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class SetOrderCode {
	
	private static final int[] r = new int[]{7,9,6,2,8,1,3,0,5,4};
	private static final int maxLength = 14;
	
	private static long getRandom(long n) {
		long min = 1,max = 9;
		for(int i=1;i<n;i++){
			min *=10;
			max *=10;
		}
		long rangeLong = (((long)(new Random().nextDouble()*(max-min))))+min;
		return rangeLong;
	}
	
	private static String getDateTime(){
		DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return sdf.format(new Date());
	}

	private static String toCode(int id){
		String idStr = id+"";
		StringBuilder idsbs = new StringBuilder();
		for(int i=idStr.length()-1;i>=0;i--){
			idsbs.append(r[idStr.charAt(i)-'0']);
		}
		return idsbs.append(getRandom(maxLength - idStr.length())).toString();
	}
	
	public static String getCode(int userId){
		return "1"+getDateTime() + toCode(userId);
	}
	
//
//	public static void main(String[] args){
//		System.out.println(getCode(2));
//	}
}
