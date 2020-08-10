package com.nolva.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nolva.common.utils.PageUtils;
import com.nolva.order.entity.OrderOperateHistoryEntity;

import java.util.Map;

/**
 * 订单操作历史记录
 *
 * @author Nolva
 * @email nolvafighting@gmail.com
 * @date 2020-08-09 18:52:00
 */
public interface OrderOperateHistoryService extends IService<OrderOperateHistoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

