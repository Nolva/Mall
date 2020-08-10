package com.nolva.order.dao;

import com.nolva.order.entity.CartItemEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 购物车表
 * 
 * @author Nolva
 * @email nolvafighting@gmail.com
 * @date 2020-08-09 18:52:00
 */
@Mapper
public interface CartItemDao extends BaseMapper<CartItemEntity> {
	
}
