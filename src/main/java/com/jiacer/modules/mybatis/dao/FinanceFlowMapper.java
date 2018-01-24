package com.jiacer.modules.mybatis.dao;

import com.jiacer.modules.clientInterface.model.FinanceFlowInfo;
import com.jiacer.modules.common.persistence.CrudDao;
import com.jiacer.modules.common.persistence.annotation.MyBatisDao;

/**
 * Created by gaoyan on 13/07/2017.
 */
@MyBatisDao
public interface FinanceFlowMapper extends CrudDao<FinanceFlowInfo>{

    FinanceFlowInfo findByOutTradeNo(String outTradeNo);
}
