package com.nolva.ware.dao;

import com.nolva.ware.entity.WareInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 仓库信息
 * 
 * @author Nolva
 * @email nolvafighting@gmail.com
 * @date 2020-08-10 15:34:50
 */
@Mapper
public interface WareInfoDao extends BaseMapper<WareInfoEntity> {
	
}
