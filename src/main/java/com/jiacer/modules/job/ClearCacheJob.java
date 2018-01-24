package com.jiacer.modules.job;

import com.jiacer.modules.cache.RegisterTryTimesCache;
import com.jiacer.modules.cache.ResetPwdTryTimesCache;
import com.jiacer.modules.common.Log;

/**
 * 清空缓存job
 * @author sk.chen
 *
 */
public class ClearCacheJob {
	public void execute() {
		Log.info("执行缓存清空job");
		RegisterTryTimesCache.clear();
		ResetPwdTryTimesCache.clear();
	}
}
