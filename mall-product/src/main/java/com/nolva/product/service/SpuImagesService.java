package com.nolva.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nolva.common.utils.PageUtils;
import com.nolva.product.entity.SpuImagesEntity;

import java.util.Map;

/**
 * spu图片
 *
 * @author Nolva
 * @email nolvafighting@gmail.com
 * @date 2020-08-10 20:07:44
 */
public interface SpuImagesService extends IService<SpuImagesEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

