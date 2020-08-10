package com.nolva.product.dao;

import com.nolva.product.entity.AttrEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品属性
 * 
 * @author Nolva
 * @email nolvafighting@gmail.com
 * @date 2020-08-10 20:07:44
 */
@Mapper
public interface AttrDao extends BaseMapper<AttrEntity> {
	
}
