package com.nolva.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nolva.common.utils.PageUtils;
import com.nolva.ware.entity.UndoLogEntity;

import java.util.Map;

/**
 * 
 *
 * @author Nolva
 * @email nolvafighting@gmail.com
 * @date 2020-08-10 15:34:50
 */
public interface UndoLogService extends IService<UndoLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

