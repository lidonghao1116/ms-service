package com.jiacer.modules.common.utils;

import com.jiacer.modules.common.config.Global;


public class EntryptUtils {

	/**
	 * 生成安全的密码，生成随机的16位salt并经过1024次 sha-1 hash
	 */
	public static String entryptPassword(String plainPassword) {
		byte[] salt = Digests.generateSalt(Global.SALT_SIZE);
		byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, Global.HASH_INTERATIONS);
		return Encodes.encodeHex(salt)+Encodes.encodeHex(hashPassword);
	}
	
	/**
	 * 验证密码
	 * @param plainPassword 明文密码
	 * @param password 密文密码
	 * @return 验证成功返回true
	 */
	public static boolean validatePassword(String plainPassword, String password) {
		byte[] salt = Encodes.decodeHex(password.substring(0,16));
		byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, Global.HASH_INTERATIONS);
		return password.equals(Encodes.encodeHex(salt)+Encodes.encodeHex(hashPassword));
	}
	
	/**
	 * 用户密码
	 * @param plainPassword
	 * @return
	 */
	public static String entryptUserPassword(String plainPassword,String randomSalt) {
		StringBuffer sb=new StringBuffer();
		sb.append(plainPassword.toUpperCase()).append(String.format(Global.ENTRY_STRING, randomSalt));
		return MD5.getStrMD5(sb.toString());
	}

	public static void main(String[] args) {
		System.out.println(entryptUserPassword("107403","13761995916"));
	}
}
