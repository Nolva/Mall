package com.nolva.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nolva.common.utils.PageUtils;
import com.nolva.product.entity.AttrEntity;

import java.util.Map;

/**
 * 商品属性
 *
 * @author Nolva
 * @email nolvafighting@gmail.com
 * @date 2020-08-10 20:07:44
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

