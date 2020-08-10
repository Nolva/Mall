package com.nolva.order.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nolva.common.utils.PageUtils;
import com.nolva.common.utils.Query;

import com.nolva.order.dao.CartItemDao;
import com.nolva.order.entity.CartItemEntity;
import com.nolva.order.service.CartItemService;


@Service("cartItemService")
public class CartItemServiceImpl extends ServiceImpl<CartItemDao, CartItemEntity> implements CartItemService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CartItemEntity> page = this.page(
                new Query<CartItemEntity>().getPage(params),
                new QueryWrapper<CartItemEntity>()
        );

        return new PageUtils(page);
    }

}