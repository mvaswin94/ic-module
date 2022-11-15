package mipl.icmodule.util;

import java.util.List;

public class MiplUtil {
	
	public static String ListToStringClientType(List<String> clientType) {
		String returnString = "";
		int count = 1;
		for (String string : clientType) {
			returnString += "\'"+string+ "\'";
			if(count!= clientType.size()) {
				returnString += ",";
			}
			count++;
		}
		return returnString;
	}
	
	public static String ListToStringEmail(List<String> email) {
		String returnString = "";
		int count = 1;
		for (String string : email) {
			returnString += "\'"+string+ "\'";
			if(count!= email.size()) {
				returnString += ",";
			}
			count++;
		}
		return returnString;
	}
	
	public static String ListToStringRole(List<String> role) {
		String returnString = "";
		int count = 1;
		for (String string : role) {
			returnString += "\'"+string+ "\'";
			if(count!= role.size()) {
				returnString += ",";
			}
			count++;
		}
		return returnString;
	}
	
	public static String ListToStringIC(List<String> ic) {
		String returnString = "";
		int count = 1;
		for (String string : ic) {
			returnString += "\'"+string+ "\'";
			if(count!= ic.size()) {
				returnString += ",";
			}
			count++;
		}
		return returnString;
	}
	
	public static String ListToStringPaymentMode(List<String> clientType) {
		String returnString = "";
		int count = 1;
		for (String string : clientType) {
			returnString += "\'"+string+ "\'";
			if(count!= clientType.size()) {
				returnString += ",";
			}
			count++;
		}
		return returnString;
	}
	
	public static String ListToStringPro(List<String> pro) {
		String returnString = "";
		int count = 1;
		for (String string : pro) {
			returnString += "\'"+string+ "\'";
			if(count!= pro.size()) {
				returnString += ",";
			}
			count++;
		}
		return returnString;
	}
}

