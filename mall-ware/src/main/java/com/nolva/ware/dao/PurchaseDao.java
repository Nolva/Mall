package com.nolva.ware.dao;

import com.nolva.ware.entity.PurchaseEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 采购信息
 * 
 * @author Nolva
 * @email nolvafighting@gmail.com
 * @date 2020-08-10 15:34:50
 */
@Mapper
public interface PurchaseDao extends BaseMapper<PurchaseEntity> {
	
}
