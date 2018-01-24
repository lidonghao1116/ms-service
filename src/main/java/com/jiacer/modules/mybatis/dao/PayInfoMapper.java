package com.jiacer.modules.mybatis.dao;

import com.jiacer.modules.clientInterface.model.OnlineOrderInfo;
import com.jiacer.modules.clientInterface.model.PayInfo;
import com.jiacer.modules.common.persistence.CrudDao;
import com.jiacer.modules.common.persistence.annotation.MyBatisDao;

/**
 * Created by gaoyan on 05/07/2017.
 */
@MyBatisDao
public interface PayInfoMapper extends CrudDao<PayInfo> {

}

