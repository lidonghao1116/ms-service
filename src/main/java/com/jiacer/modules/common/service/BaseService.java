package com.jiacer.modules.common.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.jiacer.modules.common.utils.StringUtils;


/**
 * Service基类
 * @author hezp
 */
@Transactional(readOnly = true)
public abstract class BaseService {
	
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	protected int maxPage(Integer totalCount, Integer pageS, Integer pNo) {
		int maxPage = totalCount % pageS == 0 ? totalCount / pageS : totalCount
				/ pageS + 1;// 最大页数
		if (maxPage < pNo)
			return maxPage;
		else
			return -1;
	}

	/**校验文件类型
	 * @param suffix
	 * @return
	 */
	protected boolean verifyFileType(String suffix){
		if(StringUtils.isEmpty(suffix)){
			return false;
		}
		if(suffix.endsWith(".xls") || suffix.endsWith(".xlsx")){
			return true;
		}
		return false;
	}

}
