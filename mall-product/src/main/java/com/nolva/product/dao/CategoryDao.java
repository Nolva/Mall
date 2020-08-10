package com.nolva.product.dao;

import com.nolva.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author Nolva
 * @email nolvafighting@gmail.com
 * @date 2020-08-10 20:07:44
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
