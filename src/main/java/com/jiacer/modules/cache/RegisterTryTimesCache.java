package com.jiacer.modules.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * 手机号注册尝试次数缓存
 * 每天上限20次，每天凌晨零点job更新
 * @author sk.chen
 *
 */
public class RegisterTryTimesCache {
	private static Map<String, Integer> info = new HashMap<String, Integer>();
	
	/**
	 * 次数上限
	 */
	private static int MAX_COUNT = 20;
	
	public static void addOneCount(String mobile) {
		Integer count = info.get(mobile);
		if (count == null) {
			count = 1;
		} else {
			count += 1;
		}
		info.put(mobile, count);
	}
	
	/**
	 * 判断是否超过次数限制
	 * @param mobile
	 * @return true ：超过限制
	 */
	public static boolean isOutLimit(String mobile){
		Integer count = info.get(mobile);
		return count == null ? false : count >= MAX_COUNT;
	}
	
	/**
	 * 清理缓存，每天凌晨0点执行定时任务
	 */
	public static void clear() {
		info.clear();
	}
	
	public static void clearMobile(String mobile) {
		info.remove(mobile);
	}
}
