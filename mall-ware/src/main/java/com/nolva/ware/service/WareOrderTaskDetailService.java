package com.nolva.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nolva.common.utils.PageUtils;
import com.nolva.ware.entity.WareOrderTaskDetailEntity;

import java.util.Map;

/**
 * 库存工作单
 *
 * @author Nolva
 * @email nolvafighting@gmail.com
 * @date 2020-08-10 15:34:50
 */
public interface WareOrderTaskDetailService extends IService<WareOrderTaskDetailEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

