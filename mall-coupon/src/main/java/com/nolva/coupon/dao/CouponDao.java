package com.nolva.coupon.dao;

import com.nolva.coupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author Nolva
 * @email nolvafighting@gmail.com
 * @date 2020-08-09 18:29:31
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}
