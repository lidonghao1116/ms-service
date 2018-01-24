package com.jiacer.modules.mybatis.dao;

import com.jiacer.modules.clientInterface.model.CertInfo;
import com.jiacer.modules.common.persistence.CrudDao;
import com.jiacer.modules.common.persistence.annotation.MyBatisDao;

import java.util.List;

/**
 * Created by gaoyan on 14/07/2017.
 */
@MyBatisDao
public interface UserCertMapper extends CrudDao<CertInfo> {

    List<CertInfo> getByUserId(Integer userId);

}
