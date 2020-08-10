package com.nolva.order.dao;

import com.nolva.order.entity.CompanyAddressEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 公司收发货地址表
 * 
 * @author Nolva
 * @email nolvafighting@gmail.com
 * @date 2020-08-09 18:52:00
 */
@Mapper
public interface CompanyAddressDao extends BaseMapper<CompanyAddressEntity> {
	
}
