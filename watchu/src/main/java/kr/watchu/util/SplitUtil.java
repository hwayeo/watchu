package kr.watchu.util;

public class SplitUtil {
	public static String[] splitByComma(String str) {
		String[] list; 
		if(str.substring(0, 1).equals(",")) {
			list = str.substring(1).split(",");
		}else if(str.substring(0, 1).equals(",") && str.substring(str.length()-1,str.length()).equals(",")) {
			list = str.substring(1,str.length()-1).split(",");
		}else {
			list = str.split(",");
		}
		String newlist[] = new String[list.length];
		for(int i=0;i<list.length;i++) {
			if(list[i].substring(0,1).equals(" ")) {
				newlist[i] = list[i].substring(1);
			}else {
				newlist[i] = list[i];
			}
		}
		for (int i = 0; i < newlist.length; i++) {
			System.out.println("index:"+i+" -"+ newlist[i]);
		}
		return newlist;
	}
}
