package com.nolva.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nolva.common.utils.PageUtils;
import com.nolva.product.entity.SkuInfoEntity;

import java.util.Map;

/**
 * sku信息
 *
 * @author Nolva
 * @email nolvafighting@gmail.com
 * @date 2020-08-10 20:07:43
 */
public interface SkuInfoService extends IService<SkuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

