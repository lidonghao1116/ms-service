package com.jiacer.modules.clientInterface.validation;

import com.jiacer.modules.common.Log;
import com.jiacer.modules.common.utils.StringUtils;

public class UserControllerValidation {

	public static void main(String[] args) {
		System.out.println(checkPwd("aa23456"));
	}
	
	public static boolean checkMobile(String mobile) {
		if (StringUtils.isBlank(mobile) || !mobile.startsWith("1") || mobile.length() != 11){
			return false;
		}
		return true;
	}
	
	public static boolean checkPwd(String password) {
		if (StringUtils.isBlank(password) ) {
			return false;
		} else {
			int pwdSize = password.length();
			if (pwdSize < 6 || pwdSize > 12) {
				return false;
			} else {
				char capsCharStart = 'A';
				char capsCharEnd = 'Z';
				char lowercaseCharStart = 'a';
				char lowercaseCharEnd = 'z';
				char numStart = '0';
				char numEnd = '9';
				String otherChars = "`~!@#$%^";
				int ch = 0, num = 0, other = 0, total = 0;
				char[] pwdChars = password.toCharArray();
				for (char c : pwdChars) {
					if ((c >= capsCharStart && c <= capsCharEnd) || (c >= lowercaseCharStart && c <= lowercaseCharEnd)) {
						if (ch == 0) {
							ch = 1;
						}
					} else if (c >= numStart && c <= numEnd) {
						if (num == 0) {
							num = 2;
						}
					} else if (otherChars.contains(String.valueOf(c))){
						if (other == 0) {
							other = 4;
						}
					}
					
					total = ch + num + other;
					if (total == 3 || total == 5 || total == 6 || total == 7) {
						return true;
					}
				}
				return false;
			}
		}
	}
	
	public static boolean validateLogin(String mobile, String password) {
		Log.info(new StringBuilder("Login params : " ).append(mobile).append("|"));
		if (!checkMobile(mobile)) {
			return false;
		}
		if (StringUtils.isBlank(password) || password.length() < 6 || password.length() > 12) {
			return false;
		}
		
		return true;
	}
}
