package com.jiacer.modules.mybatis.dao;

import com.jiacer.modules.clientInterface.model.OnlineOrderInfo;
import com.jiacer.modules.common.persistence.CrudDao;
import com.jiacer.modules.common.persistence.annotation.MyBatisDao;

import java.util.Date;
import java.util.Map;

/**
 * Created by gaoyan on 05/07/2017.
 */
@MyBatisDao
public interface OnlineOrderMapper extends CrudDao<OnlineOrderInfo> {

    OnlineOrderInfo getByCourseId(OnlineOrderInfo orderInfo);
}

