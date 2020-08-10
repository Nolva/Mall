package com.nolva.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nolva.common.utils.PageUtils;
import com.nolva.order.entity.CompanyAddressEntity;

import java.util.Map;

/**
 * 公司收发货地址表
 *
 * @author Nolva
 * @email nolvafighting@gmail.com
 * @date 2020-08-09 18:52:00
 */
public interface CompanyAddressService extends IService<CompanyAddressEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

