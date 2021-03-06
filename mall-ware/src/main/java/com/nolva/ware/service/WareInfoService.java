package com.nolva.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nolva.common.utils.PageUtils;
import com.nolva.ware.entity.WareInfoEntity;

import java.util.Map;

/**
 * 仓库信息
 *
 * @author Nolva
 * @email nolvafighting@gmail.com
 * @date 2020-08-10 15:34:50
 */
public interface WareInfoService extends IService<WareInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

